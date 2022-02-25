package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	private SoftAssert sa = new SoftAssert();

	@Test(priority = 5)
	public void addMealToCartTest() throws InterruptedException {
		
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		mealPage.addMealToCart("3");

		sa.assertTrue(notificationSistemPage.getMessageText().contains("The Following Errors Occurred:"),
				"[ERROR] The expected message did not appear.");

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Please Select Location"),
				"[ERROR] The expected message did not appear.");

		notificationSistemPage.waitMessageToDisappear();
		locationPopupPage.PopUpSelectLocation();
		locationPopupPage.setLocation("City Center - Albany");
		Thread.sleep(2000);
		mealPage.addMealToCart("3");

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Meal Added To Cart"),
				"[ERROR] The expected message did not appear.");

		sa.assertAll();
	}

	@Test(priority = 10)
	public void addMealToFavoriteTest() {
		
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopupPage.closePopup();
		mealPage.addMealToFavorite();

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Please login first!"),
				"[ERROR] The expected message did not appear.");

		loginPage.getLoginBtn().click();
		loginPage.loginMember(this.customer, this.password);
		this.driver.navigate().to(this.baseUrl + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addMealToFavorite();

		sa.assertTrue(notificationSistemPage.getMessageText().contains("Product has been added to your favorites."),
				"[ERROR] The expected message did not appear.");
		
		sa.assertAll();
	}

	@Test(priority = 15)
	public void clearCartTest() throws IOException {
		
		driver.navigate().to(this.baseUrl + "/meals");
		locationPopupPage.setLocation("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {

			String url = sheet.getRow(i).getCell(0).getStringCellValue();
			double quantityD = sheet.getRow(i).getCell(1).getNumericCellValue();
			String quantity = String.valueOf(quantityD);

			this.driver.navigate().to(url);
			mealPage.addMealToCart(quantity);

			sa.assertTrue(notificationSistemPage.getMessageText().contains("Meal Added To Cart"),
					"[ERROR] The expected message did not appear.");

			cartSummaryPage.ClearAllFromCart();

			sa.assertTrue(notificationSistemPage.getMessageText().contains("All meals removed from Cart successfully"),
					"[ERROR] The expected message did not appear.");	
		}
		
		sa.assertAll();
		fis.close();
		wb.close();
	}
}