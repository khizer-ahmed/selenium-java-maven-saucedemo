package com.example.wallethub.config;
import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:config.properties"})
public interface Configuration extends Config {

    @Key("browser")
    String browser();
  
    @Key("headless")
    Boolean headless();

    @Key("timeout")
    long timeout();
   
    @Key("base.url")
    String baseUrl();

    @Key("profile.username")
    String username();

    @Key("profile.name")
    String name();

    @Key("profile.email")
    String email();

    @Key("profile.password")
    String password();

    @Key("base.test.data.path")
    String baseTestDataPath();

    @Key("base.report.path")
    String baseReportPath();
 
    @Key("base.screenshot.path")
    String baseScreenshotPath();
}