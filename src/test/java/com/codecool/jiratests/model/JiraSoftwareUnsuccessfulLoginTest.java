package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraSoftwareUnsuccessfulLoginTest {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String WRONG_PASSWORD = "test";
    private static final String ERROR_FIELD_ID = "usernameerror";
    private static final String CAPTCHA_DIV_ID = "captcha";
    private static String username;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void initializeData() {
        username = dotenv.get("JIRA_USERNAME");
    }

    @BeforeEach
    public void setup() {
        int timeOut = 10;
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        driver.manage().window().maximize();
    }

    @Test
    public void loginWithWrongPassword() {
        JiraSoftware login = new LogIn(driver, username, WRONG_PASSWORD);

        login.run();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ERROR_FIELD_ID)));
        WebElement errorMessage = driver.findElement(By.id(ERROR_FIELD_ID));

        Assertions.assertTrue(errorMessage.isDisplayed());
    }

    @Test
    public void captchaAfterThirdTry() {
        int tries = 3;
        JiraSoftware login = new LogIn(driver, username, WRONG_PASSWORD);

        for (int i = 0; i < tries; i++) {
            login.run();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CAPTCHA_DIV_ID)));
        WebElement captcha = driver.findElement(By.id(CAPTCHA_DIV_ID));

        Assertions.assertTrue(captcha.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
