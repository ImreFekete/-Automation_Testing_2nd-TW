package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraSoftwareUnsuccessfulLoginTest {
    private static WebDriver driver = null;
    private static WebDriverWait wait = null;
    private static final Dotenv dotenv = Dotenv.load();

    @BeforeEach
    public void setup() {
        int timeOut = 10;
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        driver.manage().window().maximize();
    }

    @Test
    public void loginWithWrongPassword() {
        String username = dotenv.get("JIRA_USERNAME");
        String wrongPassword = "wrongpasswordfortest";
        String errorId = "usernameerror";
        JiraSoftware login = new LogIn(driver, username, wrongPassword);

        login.run();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(errorId)));
        WebElement errorMessage = driver.findElement(By.id(errorId));

        Assertions.assertTrue(errorMessage.isDisplayed());

    }

    @Test
    public void captchaAfterThirdTry() {
        String username = dotenv.get("JIRA_USERNAME");
        String wrongPassword = "wrongpasswordfortest";
        String captchaId = "captcha";
        int tries = 3;
        JiraSoftware login = new LogIn(driver, username, wrongPassword);

        for (int i = 0; i < tries; i++) {
            login.run();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(captchaId)));
        WebElement captcha = driver.findElement(By.id(captchaId));

        Assertions.assertTrue(captcha.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
