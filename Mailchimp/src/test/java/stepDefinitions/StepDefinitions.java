package stepDefinitions;

import net.bytebuddy.utility.RandomString;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

	private WebDriver driver;

	@Given("I have entered an {string} address")
	public void i_have_entered_an_address(String email) {
		System.setProperty("webdriver.chrome.driver", "C:\\Eclipse\\mydrivers\\chromedriver.exe");
		driver = new ChromeDriver(); // Start chrome

		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/"); // Navigate to

		elementLocated(By.cssSelector("input#email"));

		WebElement input = driver.findElement(By.cssSelector("input#email"));
		input.sendKeys(email);

	}

	@Given("I have entered a {string}")
	public void i_have_entered_a(String usernameSort) {

		String username = "";
		switch (usernameSort) {
		case "newUser":
			username = RandomString.make(50);
			break;
		case "LongName":
			username = RandomString.make(101);
			break;
		case "InUse":
			username = "TheDaveEC";
			break;
		}
		WebElement input = driver.findElement(By.cssSelector("input#new_username"));

		input.sendKeys(username);

	}

	@Given("I have entered a password")
	public void i_have_entered_a_password() throws InterruptedException {

		WebElement input = driver.findElement(By.cssSelector("input#new_password"));
		input.sendKeys("TheD1337!");

		elementLocated(By.cssSelector("button#onetrust-accept-btn-handler"));

	}

	@Given("I have accepted the cookies")
	public void i_have_accepted_the_cookies() {

		WebElement cookiesButton = driver.findElement(By.cssSelector("#onetrust-accept-btn-handler"));
		cookiesButton.click();

	}

	@When("I click on the Sign Up button")
	public void i_click_on_the_sign_up_button() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.elementToBeClickable(By.id("create-account")));

		WebElement signUpButton = driver.findElement(By.cssSelector("button#create-account"));

		signUpButton.click();

	}

	@Then("This {string} is shown on screen")
	public void this_is_shown_on_screen(String outcome) {

		WebElement outcomeResult;

		if (outcome.equals("Success!")) {
			elementLocated(By.cssSelector("h1[class^='!margin-bottom--lv3']"));
			outcomeResult = driver.findElement(By.cssSelector("h1[class^='!margin-bottom--lv3']"));

			assertEquals("Check your email", outcomeResult.getText());

		} else if (outcome.equals("The username is too long.")) {
			elementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));

			outcomeResult = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));

			assertEquals("Enter a value less than 100 characters long", outcomeResult.getText());

		} else if (outcome.equals("Username is already in use.")) {
			elementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));
			outcomeResult = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[2]/div/span"));
			assertEquals("Another user with this username already exists. Maybe it's your evil twin. Spooky.",
					outcomeResult.getText());

		} else if (outcome.equals("Email is missing.")) {
			elementLocated(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[1]/div/span"));
			outcomeResult = driver.findElement(By.xpath("//*[@id=\"signup-form\"]/fieldset/div[1]/div/span"));
			assertEquals("Please enter a value", outcomeResult.getText());
		}

		driver.quit();
	}

	private void elementLocated(By by) {

		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(by));

		driver.findElement(by);

	}

}
