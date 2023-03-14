package org.kos.db;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;

import javax.sql.DataSource;

public class FlywayMigrations {

    public Flyway flywayFrom(DataSource ds) {
        ClassicConfiguration configuration = new ClassicConfiguration();
        configuration.setDataSource(ds);
        configuration.setBaselineOnMigrate(true);
        configuration.setCleanDisabled(false);
        return new Flyway(configuration);
    }
}
