package config;

import org.aeonbits.owner.Config;

@Config.Sources({"file:src/test/resources/properties/test.properties"})
public interface Configuration extends Config {
    @Key("baseURL")
    String baseURL();
}
