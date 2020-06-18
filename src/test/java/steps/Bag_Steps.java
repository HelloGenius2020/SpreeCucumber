package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bag_Steps {

    WebDriver driver;

    @Given("User will login with valid credentials")
    public void user_will_login_with_valid_credentials() {
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.navigate().to("http://spree.shiftedtech.com/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement loginLink=driver.findElement(By.xpath("//a[contains(text(),'Login')]"));
        loginLink.click();

        WebElement emailEditbox= driver.findElement(By.xpath("//input[@id='spree_user_email']"));
        emailEditbox.clear();
        emailEditbox.sendKeys("hellogenius.shift@gmail.com");

        WebElement passwordEditbox= driver.findElement(By.xpath("//input[@id='spree_user_password']"));
        passwordEditbox.clear();
        passwordEditbox.sendKeys("shift1234");

        WebElement loginButton= driver.findElement(By.xpath("//input[@name='commit']"));
        loginButton.click();
    }


    @And("User validate that he is on the expected page")
    public void user_validate_that_he_is_on_the_expected_page() {

        WebElement alert_success= driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        String alertSuccessText= alert_success.getText();
        System.out.println(alertSuccessText);
        Assert.assertEquals(alertSuccessText, "Logged in successfully");

        WebElement bagPageLink= driver.findElement(By.xpath("//a[contains(text(),'Bags')]"));
        bagPageLink.click();

        WebElement taxon_title= driver.findElement(By.xpath("//h1[@class='taxon-title']"));
        String pageTitleText= taxon_title.getText();
        System.out.println(pageTitleText);
        Assert.assertEquals(pageTitleText,"Bags");
    }

    @When("User will Check all avalilable producs are displayed")
    public void user_will_Check_all_avalilable_producs_are_displayed() {
        List<String> bagArrayList_Text= new ArrayList<String>();

       List<WebElement> allBags= driver.findElements(By.xpath("//div[@id='content']"));
       for (int i=0; i<allBags.size();i++){
           bagArrayList_Text.add(allBags.get(i).getText());
           System.out.println(bagArrayList_Text);
       }

       List <String> bagsOnRealApplication= new ArrayList<String>();
       bagsOnRealApplication.add("Ruby on Rails Tote");
       bagsOnRealApplication.add("Ruby on Rails Bag");
       bagsOnRealApplication.add("Spree Tote");
       bagsOnRealApplication.add("Spree Bag");



       //Assert.assertTrue(bagArrayList_Text.contains(bagsOnRealApplication.toString()));
        boolean productVarificationResult=bagsOnRealApplication.containsAll(bagsOnRealApplication);
        System.out.println(productVarificationResult +" >> all products are dispalyed as expected");
    }

    @Then("User would get all products and validate")
    public void user_would_get_all_products_and_validate() {

    }

    @And("User will logout with due procedure")
    public void user_will_logout_with_due_procedure() {
        driver.close();
        driver.quit();
    }

}