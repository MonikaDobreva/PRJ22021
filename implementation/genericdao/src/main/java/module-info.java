module genericdao_module {

    exports genericdao.dao;
    exports genericdao.pgdao;
    requires nl.fontys.sebivenlo.sebiannotations;
    requires genericmapper_module;
    requires java.logging;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.naming;
    opens genericdao.dao;
    opens genericdao.pgdao;
    
}
