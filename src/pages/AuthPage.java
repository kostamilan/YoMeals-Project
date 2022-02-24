package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getMember() {
		return this.driver.findElement(By.className("filled"));
	}

	public WebElement getMyAccount() {
		return this.driver.findElement(By.linkText("My Account"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.linkText("Logout"));
	}

	public void logoutMember() {
		this.getMember().click();
		this.getLogout().click();
	}
}