package genericmapper;

import static genericmapper.Constants.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;
import nl.fontys.sebivenlo.sebiannotations.ID;

/**
 * Generates mappers for named Types. The types are fully qualified types to be
 * read from the class path.
 *
 * @author Pieter Van den Hombergh {@code Pieter.van.den.Hombergh@gmail.com}
 */
public class MapperGenerator {

    public static void main( String[] args ) throws ClassNotFoundException,
            FileNotFoundException,
            IOException,
            InterruptedException {
        System.err.println( "generic mapper generator" );
        for ( String arg : args ) {
            System.err.flush();
            System.out.println( "// generating for " + arg );
            Class<?> clz = Class.forName( arg );
            String fileName = generatedJavaFileName( clz );
            File dir = new File( fileName );
            dir.getParentFile().mkdirs();
            System.out.println( "fileName = " + fileName );
            String javaSource = new MapperGenerator( clz ).javaSource();
            System.out.println( "classText = " + javaSource );
            if ( !javaSource.isBlank() ) {
                try ( PrintStream out = new PrintStream( fileName ) ) {
                    out.print( javaSource );
                    out.flush();
                }
            }
        }
    }

    final Class<?> entityType;

    public MapperGenerator( Class<?> entitype ) {
        this.entityType = entitype;
    }

    /**
     * Generate the java code using the template MAPPER_TEMPLATE.
     *
     * @return the template text or an empty string when the class has no usable
     * id field.
     */
    final String javaSource() {
        //TODO generate the codes
        String typeName = entityType.getSimpleName();
        String paramName = typeName.substring( 0, 1 ).toLowerCase();
        System.out.println("ClassText " + paramName);
        String classText = String.format( MAPPER_TEMPLATE,
                GENERATED_PACKAGE, // <1>
                entityType.getName(),
                typeName, // <2>
                getKeyField().getType().getSimpleName(),
                paramName, // <3>
                getters( ), // <4>
                getterName(getKeyField())
        );
        return classText;
    }

    /**
     * Turn the fields of a class into getter call strings.
     *
     * @param type to reflect
     * @return The getters as one indented string.
     */
    final String getters( ) {
        String typeName = entityType.getSimpleName();
        String paramName = typeName.substring( 0, 1 ).toLowerCase();
        String paramNameDot = paramName + ".";
        Field[] declaredFields = getAllFieldsInClassHierarchy();
        String indent = "              ";
        return Stream.of( declaredFields ) //.map( this::getterName).
                .map( this::getterName )
                .map( s -> indent + paramNameDot + s )
                .collect( joining( ",\n" ) );
    }

    /**
     * Generate the array of fields in top down declaration order. Top down
     * means the super stuff first.
     *
     * @return the array of all declared fields in the class hierarchy.
     */
    final Field[] getAllFieldsInClassHierarchy() {
        List<Field[]> fieldArrays = new ArrayList<>();
        Class<?> currentClz = entityType;
            
            while (currentClz != null){
                for (Class<?> c : currentClz.getInterfaces()){
                    fieldArrays.add(c.getDeclaredFields());
                }
                fieldArrays.add(currentClz.getDeclaredFields());
                currentClz = currentClz.getSuperclass();
            }
            Collections.reverse(fieldArrays);
        //TODO walk the class hierarchy from type up
        return fieldArrays.stream().
                flatMap(Stream::of)
                .toArray(Field[]::new);
    }

    /**
     * Produce a getter call like getName().
     *
     * @param f for field
     * @return getName() for field name.
     */
    String getterName( Field f ) {
        //TODO create a getter name including prefix and closing ()
        String getter = f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
        return "get" + getter + "()";
    }

    /**
     * Try to find the Annotation @ID and if that fails the field called "id".
     *
     *
     * @return the field.
     * @throws NoSuchFieldError after two attempts
     */
    Field getKeyField() {
        return Stream.of( entityType.getDeclaredFields() )
                .peek( System.out::println )
                .filter( f -> f.getAnnotation( ID.class ) != null )
                .findFirst()
                .or( this::getFieldNamedId )
                .orElseThrow( () -> new NoSuchFieldError(
                "for entity '" + entityType.getName()
                + "' neither Annotated nor named id" ) );

    }

    Optional<Field> getFieldNamedId() {
        return Stream.of( entityType.getDeclaredFields() ).filter(
                f -> "id".equals( f.getName() ) )
                .findFirst();
    }
}
