module persistence_module {

    requires transitive businessentities_api_module; 
    exports persistence;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires nl.fontys.sebivenlo.genericdao;
    requires nl.fontys.sebivenlo.genericranges;

}