package SDET_10_Selenium_assignmTest;

import SDET_10_SeleniumBasics.SharedDriver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class M10_assignm_Selenium {

    private static final String FB_main = "https://www.facebook.com/login/?next=https%3A%2F%2Fwww.facebook.com%2F";
    private static final String SignUpPage = "https://www.facebook.com/r.php?next=https%3A%2F%2Fwww.facebook.com%2F&locale=en_GB&display=page";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(FB_main);
    }
    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }
    @AfterEach
    public void testTearDown() {
        driver.get(FB_main);
    }
    @Test
    public void fbMainScreenTest() {
        String actualURL = driver.getCurrentUrl();
        assertTrue(actualURL.contains("https://www.facebook.com/"), "URLs do not match.");
    }
    @Test
    public void createNewAccXpathTest() {
        WebElement createNewAccButton = driver.findElement(By.xpath("//a[@role='button']"));
        assertNotNull(createNewAccButton);
    }
    @Test
    public void createNewAccButtonClickTest() throws ElementClickInterceptedException {
        try {
            WebElement createNewAccButton = driver.findElement(By.xpath("//a[@role='button']"));
            assertNotNull(createNewAccButton);
            createNewAccButton.click();
            Thread.sleep(3000);

        } catch (ElementClickInterceptedException | InterruptedException e) {
            System.out.println(e);
        }
    }
    @BeforeEach
    public void setSignUpPageSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(SignUpPage);
    }
    @Test
    public void fbSignUpPageTest() {
        String actualURL = driver.getCurrentUrl();
        assertEquals(SignUpPage, actualURL, "URLs do not match.");
    }
    @Test
    public void signUpElementsXpathTest() {
        WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(firstNameElement);
        WebElement surnameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(surnameElement);
        WebElement mobOrEmailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(mobOrEmailElement);
        WebElement newPswElement = driver.findElement(By.xpath("//input[@id='password_step_input']"));
        assertNotNull(newPswElement);
        WebElement dayOfBirthElement = driver.findElement(By.xpath("//select[@title='Day']"));
        assertNotNull(dayOfBirthElement);
        WebElement monthOfBirthElement = driver.findElement(By.xpath("//select[@title='Month']"));
        assertNotNull(monthOfBirthElement);
        WebElement yearOfBirthElement = driver.findElement(By.xpath("//select[@title='Year']"));
        assertNotNull(yearOfBirthElement);
        WebElement genderFemaleElement = driver.findElement(By.xpath("//label[text()='Female']"));
        assertNotNull(genderFemaleElement);
        WebElement genderMaleElement = driver.findElement(By.xpath("//label[text()='Male']"));
        assertNotNull(genderMaleElement);
        WebElement genderCustomElement = driver.findElement(By.xpath("//label[text()='Custom']"));
        assertNotNull(genderCustomElement);
        WebElement pronounSelectElement = driver.findElement(By.xpath("//select[@aria-label='Select your pronoun']"));
        assertNotNull(pronounSelectElement);
        WebElement pronounOptionElement = driver.findElement(By.xpath("//option[text()='He: \"Wish him a happy birthday!\"']"));
        assertNotNull(pronounOptionElement);
        WebElement optionalGenderElement = driver.findElement(By.xpath("//input[@id='custom_gender']"));
        assertNotNull(optionalGenderElement);
        WebElement signUpButtonElement = driver.findElement(By.xpath("//button[@name='websubmit']"));
        assertNotNull(signUpButtonElement);
    }
    static Stream<Arguments> optionsProvider() {
        return Stream.of(
                Arguments.of("Michael", "Jackson", "mjackson@gmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Jackson", "2265078071", "validPasswordhere2024"),
                Arguments.of("Mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmichael", "Jackson", "mjackson@gmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Jacksonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "mjackson@gmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Jackson", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmjackson@gmail.com", "validPasswordhere2024"),
                Arguments.of("Mi156*&^chael", "Jackson", "mjackson@gmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Ja09%$#ckson", "mjackson@gmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Jackson", "mjackson(gmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Jackson", "mjacksongmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Jackson", "mjackson(gmail.com", "validPasswordhere2024"),
                Arguments.of("Michael", "Jackson", "mjackson@gmail.com", "InnnnnnnnnnnnnnnnnnnnnnnnnnnnnnvalidPasswordhere2024"),
                Arguments.of("Michael", "Jackson", "mjackson@gmail.com", "")
        );
    }
    @ParameterizedTest
    @MethodSource("optionsProvider")
    public void signUpPageTest(String firstName, String lastName, String mobOrEmail, String newPassword) throws ElementClickInterceptedException, InterruptedException {
        try {
            WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
            firstNameElement.sendKeys(firstName);
            WebElement surnameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
            surnameElement.sendKeys(lastName);
            WebElement mobOrEmailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
            mobOrEmailElement.sendKeys(mobOrEmail);
            WebElement newPswElement = driver.findElement(By.xpath("//input[@id='password_step_input']"));
            newPswElement.sendKeys(newPassword);
            WebElement signUpButtonElement = driver.findElement(By.xpath("//button[@name='websubmit']"));
            signUpButtonElement.click();

            Thread.sleep(3000);

            String newScreenPage = driver.getCurrentUrl();
            assertSame(SignUpPage, newScreenPage);
        } catch (ElementClickInterceptedException | InterruptedException e) {
            System.out.println(e);
        }
    }
    @ParameterizedTest
    @CsvSource({"Michael, Jackson, mjackson@gmail.com, validPasswordhere2024, XXXXX"})
    public void customGenderTest(String firstName, String lastName, String mobOrEmail, String newPassword, String optionalG) throws ElementClickInterceptedException, InterruptedException {
        try {
            WebElement firstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
            firstNameElement.sendKeys(firstName);
            WebElement surnameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
            surnameElement.sendKeys(lastName);
            WebElement mobOrEmailElement = driver.findElement(By.xpath("//input[@name='reg_email__']"));
            mobOrEmailElement.sendKeys(mobOrEmail);
            WebElement newPswElement = driver.findElement(By.xpath("//input[@id='password_step_input']"));
            newPswElement.sendKeys(newPassword);
            WebElement genderCustomElement = driver.findElement(By.xpath("//label[text()='Custom']"));
            genderCustomElement.click();
            WebElement pronounSelectElement = driver.findElement(By.xpath("//select[@aria-label='Select your pronoun']"));
            pronounSelectElement.click();
            WebElement pronounOptionElement = driver.findElement(By.xpath("//option[text()='He: \"Wish him a happy birthday!\"']"));
            pronounOptionElement.click();
            WebElement optionalGenderElement = driver.findElement(By.xpath("//input[@id='custom_gender']"));
            optionalGenderElement.sendKeys(optionalG);
            WebElement signUpButtonElement = driver.findElement(By.xpath("//button[@name='websubmit']"));
            signUpButtonElement.click();

            Thread.sleep(3000);

            String newScreenPage = driver.getCurrentUrl();
            assertSame(SignUpPage, newScreenPage);
        } catch (ElementClickInterceptedException | InterruptedException e) {
            System.out.println(e);
        }
    }
}


