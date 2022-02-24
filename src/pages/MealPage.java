package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getSearch() {
		return this.driver.findElement(By.name("keywords"));
	}

	public WebElement getQuantity() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public void addMealToCart(String quantity){
		this.getQuantity().sendKeys(Keys.CONTROL + "a");
		this.getQuantity().sendKeys(quantity);
		this.driver.findElement(By.linkText("Add To Cart")).click();
	}

	public void addMealToFavorite() {
		this.driver.findElement(By.className("favourite")).click();
	}
}