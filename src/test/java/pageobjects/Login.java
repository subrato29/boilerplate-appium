package pageobjects;

import com.mobile.utilities.Util;

public class Login {
		public static String email = Util.locator("//android.widget.EditText[1]");
		public static String password = Util.locator("//android.widget.EditText[2]");
		public static String btnSignIn = Util.locator("//android.widget.Button[1]");
		public static String txtAuthenticate = Util.locator("//android.widget.TextView[@text='Authenticate']");
		public static String btnMaybeLater = Util.locator("//android.widget.Button[@text='MAYBE LATER']");
		
}
