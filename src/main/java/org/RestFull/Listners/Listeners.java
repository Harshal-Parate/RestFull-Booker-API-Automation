package org.RestFull.Listners;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public final class Listeners implements ITestListener {

    /*
    Listeners are used to hear the results for the test

    in this class the listeners are setup in such a manner that
    when the test case gets fail or skipped
    it'll give the name fo case and logs for it

    Log4j is used to logg the stack traces when test gets failed of skipped

    Listeners are setup in xml file below suit

    */

    Logger logger = LogManager.getLogger(Listeners.class);

    public void onTestFailure (ITestResult result) {
        logger.error("Failure of test"+ result.getName(), result.getThrowable());
        Allure.step(result.getName());
    }

    public void onTestSkipped (ITestResult result) {
        logger.error("Skip of test"+ result.getName(), result.getThrowable());
        Allure.step(result.getName());
    }
}