package configuration;

import org.aeonbits.owner.Config;

public interface SqlConfig extends Config {

    @Key("sql.servername")
    String server();
    @Key("sql.databasename")
    String db();
    @DefaultValue("sa")
    @Key("sql.username")
    String user();
    @Key("sql.password")
    String password();
}
