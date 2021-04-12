package genericdao.pgdao;

import genericmapper.Mapper;
import static java.lang.String.format;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import nl.fontys.sebivenlo.sebiannotations.ID;
import nl.fontys.sebivenlo.sebiannotations.TableName;

/**
 * Class to compute and cache query strings.
 *
 * The queries are to be computed from the meta data on the java entity type in
 * the mapper. After the first invocation of on of the non private method, the
 * same should be returned on each call.
 *
 *
 * @author Pieter van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class QueryFactory {

    final Mapper<?, ?> mapper;

    ConcurrentMap<String, String> queryTextCache = new ConcurrentHashMap<>();

    public QueryFactory( Mapper<?, ?> mapper ) {
        this.mapper = mapper;
    }

    /**
     * Returned a value from the cache.
     *
     * @return
     */
    String allColumns() {
        return this.queryTextCache
                .computeIfAbsent( "allColumns", x -> computeAllColumns() );
    }

    /**
     * Compute the value of all columns joins with a comma.
     *
     * @return text
     */
    private String computeAllColumns() {
        //TODO
        var columnNames = mapper.entityFields()
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
        return String.join(",", columnNames);
    }

    /**
     * The name of the key column.
     *
     * @return the name of the key column
     */
    String idName() {
        return this.queryTextCache
                .computeIfAbsent( "idName", x -> mapper.getKeyFieldName() );
    }

    String getQueryText() {
        return queryTextCache
                .computeIfAbsent( "selectsingle", ( x )
                        -> computeGetQueryText() );
    }

    /**
     * Compute the select query.
     *
     * @return the text
     */
    private String computeGetQueryText() {
        //TODO
        String columnNames = allColumns();
        String placeholders = makePlaceHolders( columnNames.split(",").length);
        String sqlt = format(
            "select (%1$s) from %2$s where (%3$s)=(?)",
            columnNames,
            tableName(),
            mapper.getKeyFieldName() );
        return sqlt;
    }

    /**
     * Get the table name fom the @TableName annotation.
     *
     * @return the table name or a synthesised name using simple plurar.
     */
    String tableName() {
        Class<?> entityType = mapper.getEntityType();
        //TODO
        var annotation = entityType.getAnnotation(TableName.class);        
        return annotation != null ? annotation.value() : entityType.getSimpleName().toLowerCase() + "s";
    }

    /**
     * Get the delete query form the cache.
     *
     * @return the text
     */
    String deleteQueryText() {
        return queryTextCache
                .computeIfAbsent( "delete", x -> computeDeleteQueryText() );
    }

    private String computeDeleteQueryText() {
        //TODO
        return format(
        "delete from %1$s where (%2$s)=(?)",
                tableName(),
                mapper.getKeyFieldName());
    }

    String updateQueryText() {
        return queryTextCache
                .computeIfAbsent( "update", x -> computeUpdateQueryText()
                );
    }

    private String computeUpdateQueryText() {
        //TODO
        String columnNames = computeAllColumns();
        String placeholders = makePlaceHolders( columnNames.split(",").length);
        String sqlt = format(
            "update %1$s set (%2$s)=(%3$s) where (%4$s)=(?)"
            + " returning  %2$s",
            tableName(),
            columnNames,
            placeholders,
            mapper.getKeyFieldName() );
        return sqlt;
    }

    final String saveQueryText() {
        return queryTextCache
                .computeIfAbsent( "save", x -> computeSaveQueryText() );
    }

    final String makePlaceHolders( final int count ) {
        String[] qm = new String[ count ];
        Arrays.fill( qm, "?" );
        return String.join( ",", qm );
    }

    private String computeSaveQueryText() {
        //TODO
        String columnNames = allColumns();
        String placeholders = makePlaceHolders( columnNames.split(",").length);
        return format(
                "insert into %1$s (%2$s) values (%3$s) returning (%3$s)",
                tableName(),
                columnNames,
                placeholders  
        );
    }

    String allQuery() {
        return queryTextCache
                .computeIfAbsent( "all", x -> computeAllQueryText() );
    }

    private String computeAllQueryText() {
        //TODO
        String columnNames = allColumns();
        return format(
                "select (%1$s) from %2$s",
                columnNames,
                tableName()
        );
    }

    String lastIdQuery() {
        return queryTextCache
                .computeIfAbsent( "lastIdQuery", x -> computeLastIdQuery() );
    }

    private String computeLastIdQuery() {
        //TODO
        return format(
                "select max(%1$s) as lastid from %2$s",
                mapper.getKeyFieldName(),
                tableName()
        );
    }

    String dropallQuery() {
        return queryTextCache
                .computeIfAbsent( "dropAllQuery", x -> computeDropallQuery() );

    }

    String computeDropallQuery() {
        return "truncate " + tableName() + " restart identity cascade";
    }
}
