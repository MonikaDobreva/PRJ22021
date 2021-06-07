module businesslogic_module {   
    requires transitive businessentities_api_module;
    requires transitive persistence_module;
    requires nl.fontys.sebivenlo.sebiannotations;
    requires ranges;
    requires nl.fontys.sebivenlo.genericdao;
    exports businesslogic;
}
