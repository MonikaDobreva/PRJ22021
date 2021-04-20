module persistence_module {

    requires transitive businessentities_api_module; 
    exports persistence;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires genericdao_module;
    
}