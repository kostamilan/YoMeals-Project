package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	private String url = "/member/profile";

	public WebElement getName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getEmail() {
		return this.driver.findElement(By.name("user_email"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhoneNo() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement countrySelect = this.driver.findElement(By.id("user_country_id"));
		Select selectCountry = new Select(countrySelect);
		return selectCountry;
	}

	public Select getState() {
		WebElement stateSelect = this.driver.findElement(By.id("user_state_id"));
		Select selectState = new Select(stateSelect);
		return selectState;
	}

	public Select getCity() {
		WebElement citySelect = this.driver.findElement(By.id("user_city"));
		Select selectCity = new Select(citySelect);
		return selectCity;
	}

	public WebElement getCurrentPassword() {
		return this.driver.findElement(By.name("current_password"));
	}

	public WebElement getNewPassword() {
		return this.driver.findElement(By.name("new_password"));
	}

	public WebElement getConfirmPassword() {
		return this.driver.findElement(By.name("conf_new_password"));
	}

	public WebElement saveBtn() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));
	}

	public WebElement uploadBtn() {
		return this.driver.findElement(By.xpath("//a[@title='Uplaod']"));
	}

	public WebElement deleteBtn() {
		return this.driver.findElement(By.className("remove"));
	}

	public void uploadImage(String pictureUrl) {
		js.executeScript("arguments[0].click()", this.uploadBtn());
		this.driver.findElement(By.xpath("//input[@type='file']")).sendKeys(pictureUrl);
	}

	public void deleteImage() {
		js.executeScript("arguments[0].click()", this.deleteBtn());
	}

	public void changeAllInfo(String name, String lastName, String address, String phoneNo, String zipCode,
			String country, String state, String city) throws InterruptedException {
		this.getName().clear();
		this.getName().sendKeys(name);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		this.getPhoneNo().clear();
		this.getPhoneNo().sendKeys(phoneNo);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(2000);
		this.getState().selectByVisibleText(state);
		Thread.sleep(2000);
		this.getCity().selectByVisibleText(city);
		Thread.sleep(3000);
		this.saveBtn().click();
	}
}
