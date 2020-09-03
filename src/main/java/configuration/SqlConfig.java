package configuration;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:sql.properties")
public interface SqlConfig extends Config {

    @Key("sql.servername")
    String server();
    @Key("sql.dbname")
    String db();
    @Key("sql.username")
    String user();
    @Key("sql.password")
    String password();
}
