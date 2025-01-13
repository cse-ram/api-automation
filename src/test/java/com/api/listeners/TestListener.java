package com.api.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger  = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context){
        logger.info("Test suite started.....");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite execution completed!!!");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("===============Started...{}", result.getMethod().getMethodName());
        logger.info("===============Description: {}", result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Passed!!! {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Failed!!!! {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.error("Skipped!!!! {}", result.getMethod().getMethodName());
    }
}
