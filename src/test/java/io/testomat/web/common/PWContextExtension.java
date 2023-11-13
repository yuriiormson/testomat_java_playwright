package io.testomat.web.common;

import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class PWContextExtension {

//    @BeforeMethod
//    public void beforeEach(ITestContext context) {
//        PlaywrightWrapper.initTestContext(getTestName(context));
//    }
//
//    @AfterMethod
//    public void afterEach(ITestContext context) {
//        PlaywrightWrapper.closeContext(getTestName(context));
//    }
//
//    private String getTestName(ITestContext context) {
//        return context.getClass().getName() + " " + context.getName();
//    }

//    @Override
//    public void onTestStart(ITestResult result) {
//        PlaywrightWrapper.initTestContext(getTestName(result));
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        PlaywrightWrapper.closeContext(getTestName(result));
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        PlaywrightWrapper.closeContext(getTestName(result));
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        PlaywrightWrapper.closeContext(getTestName(result));
//    }
//
//    // Implement other ITestListener methods as needed
//
//    private String getTestName(ITestResult result) {
//        return result.getTestClass().getName() + " " + result.getMethod().getMethodName();
//    }



//    private String testName;

    @BeforeMethod
    public void beforeMethod(Method method) {
        PlaywrightWrapper.initTestContext(getTestName(method));
    }

    @AfterMethod
    public void afterMethod(Method method) {
        PlaywrightWrapper.closeContext(getTestName(method));
    }

    public String getTestName(Method method) {
        return this.getClass().getName() + " " + method.getName();
    }

//    public void setTestName(String testName) {
//        this.testName = testName;
//    }

}

