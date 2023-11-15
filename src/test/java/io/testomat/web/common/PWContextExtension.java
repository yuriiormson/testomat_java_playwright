package io.testomat.web.common;

import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class PWContextExtension {

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    public void beforeMethod(Method method,ITestResult result) {
        PlaywrightWrapper.initTestContext(getTestName(method));

        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log( "TEST RUN", true);
        Reporter.log(ReportUtils.getClassNameTestName(method, result), true);
    }

    @AfterMethod
    public void afterMethod(Method method,ITestResult result) {
        PlaywrightWrapper.closeContext(getTestName(method));

        Reporter.log(ReportUtils.getTestStatistics(method, result), true);
    }

    public String getTestName(Method method) {
        return this.getClass().getName() + " " + method.getName();
    }
}

