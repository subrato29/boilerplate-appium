package testscripts;
import java.io.IOException;

import org.testng.annotations.Test;

import com.mobile.base.DriverScript;
import com.app.keywords.*;


public class TestScripts extends DriverScript {
    @Test
    public void loginTest() throws InterruptedException, IOException {
        String testCaseID = "TC001";
        if (isTestCaseRunnable(testCaseID)) {
            Keywords.login();
        }

    }
}