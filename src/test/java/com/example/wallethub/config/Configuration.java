package com.example.wallethub.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "classpath:config.properties"})
public interface Configuration extends Config {
    /**
     * Associates the property 'browser' with the browser() method.
     *
     * @return a string containing the browser name
     */
    @Key("browser")
    String browser();

    /**
     * Associates the property 'headless' with the headless() method.
     *
     * @return a boolean containing the choice whether the browser will run in headless mode
     */
    @Key("headless")
    Boolean headless();

    /**
     * Associates the property 'timeout' with the timeout() method.
     *
     * @return an integer containing the timeout value
     */
    @Key("timeout")
    long timeout();

    /**
     * Associates the property 'base.url' with the baseUrl() method.
     *
     * @return a string containing the base url of the application under test
     */
    @Key("base.url")
    String baseUrl();

    /**
     * Associates the property 'base.test.data.path' with the baseTestDataPath() method.
     *
     * @return a string containing the base path to store all the test data
     */
    @Key("base.test.data.path")
    String baseTestDataPath();

    /**
     * Associates the property 'base.report.path' with the baseReportPath() method.
     *
     * @return a string containing the base path to store all the test reports
     */
    @Key("base.report.path")
    String baseReportPath();

    /**
     * Associates the property 'base.screenshot.path' with the baseScreenshotPath() method.
     *
     * @return a string containing the base path to store all the failed test screenshots
     */
    @Key("base.screenshot.path")
    String baseScreenshotPath();
}