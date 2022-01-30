package com.app.keywords;
import com.mobile.base.DriverScript;
import com.mobile.utilities.*;

public class Keywords extends DriverScript {

    static int errorCount = 0;
    static String TESTDATASHEET = Constants.TEST_DATA;

    /**
     * @keyword: login
     * @description:  Login
     * @date: 05/20/2020
     * @author: Surato Sarkar
     */
    public static void login() {
        if (continueRun) {
            System.out.println("**************************************************login**************************************************");
        }
    }

}