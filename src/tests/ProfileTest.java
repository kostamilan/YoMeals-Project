package tests;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest extends BasicTest {

	private SoftAssert sa = new SoftAssert();

	@Test (priority = 5)
	public void editProfileTest() throws InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.loginMember(this.customer, this.password);

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Login Successfull"), 
				"[ERROR] The expected message did not appear.");

		this.driver.navigate().to(this.baseUrl + "/member/profile");
		profilePage.changeAllInfo("Jelena", "Bajat", "Bulevar", "06405021", "18000", "India", "Goa", "Betora");

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Setup Successful"),
				"[ERROR] The expected message did not appear.");

		authPage.logoutMember();

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Logout Successfull!"),
				"[ERROR] The expected message did not appear.");
		
		sa.assertAll();
	}

	@Test (priority = 10)
	public void changeProfileImageTest() throws IOException {
		
		this.driver.navigate().to(this.baseUrl + "/guest-user/login-form");
		locationPopupPage.closePopup();
		loginPage.loginMember(this.customer, this.password);

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Login Successfull"),
				"[ERROR] The expected message did not appear.");

		this.driver.navigate().to(this.baseUrl + "/member/profile");
		String imgPath = new File("img/V.jpg").getCanonicalPath();
		profilePage.uploadImage(imgPath);

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Profile Image Uploaded Successfully"),
				"[ERROR] The expected message did not appear.");

		notificationSistemPage.waitMessageToDisappear();
		profilePage.deleteImage();

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Profile Image Deleted Successfully"),
				"[ERROR] The expected message did not appear.");

		notificationSistemPage.waitMessageToDisappear();
		authPage.logoutMember();

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Logout Successfull!"),
				"[ERROR] The expected message did not appear.");
		
		sa.assertAll();
	}
}