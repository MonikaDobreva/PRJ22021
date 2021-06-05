module businesslogic_module {   
    requires transitive businessentities_api_module;
    requires transitive persistence_module;
    requires nl.fontys.sebivenlo.sebiannotations;
    requires nl.fontys.sebivenlo.genericranges;
    exports businesslogic;
}
