package com.example.wallethub.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.wallethub.config.ConfigurationManager.configuration;

public final class ExtentReportManager {
    private ExtentReportManager() {}

    public static ExtentReports createReport() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        String fileName =
                String.format(
                        "%sTestReport_%s.html", configuration().baseReportPath(), currentDate);

        ExtentReports extentReport = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        extentReport.attachReporter(spark);

        extentReport.setSystemInfo("Platform", System.getProperty("os.name"));
        extentReport.setSystemInfo("Version", System.getProperty("os.version"));
        extentReport.setSystemInfo("Browser", configuration().browser());
        extentReport.setSystemInfo("Base URL", configuration().baseUrl());
        extentReport.setSystemInfo("Test Data Username", configuration().username());
        extentReport.setSystemInfo("Test Data Email ID", configuration().email());
        extentReport.setSystemInfo("Test Data Password", configuration().password());
        extentReport.setSystemInfo("Test Data Full Name", configuration().name());

        return extentReport;
    }
}
