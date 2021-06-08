module businesslogic_module {   
    requires transitive businessentities_api_module;
    requires transitive persistence_module;
    requires nl.fontys.sebivenlo.sebiannotations;
    requires ranges;
    requires nl.fontys.sebivenlo.genericdao;
    requires java.logging;
    exports businesslogic;
    exports businesslogic.validators;
    exports businesslogic.bulkvalidator;
    opens businesslogic.bulkvalidator;
    opens businesslogic.validators;
    opens businesslogic;
}
