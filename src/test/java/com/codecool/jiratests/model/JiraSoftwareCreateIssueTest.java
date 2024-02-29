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

public class JiraSoftwareCreateIssueTest {

    private static WebDriver driver = null;
    private static WebDriverWait wait = null;
    private static Dotenv dotenv = Dotenv.load();
    private static final String PROFILE_MENU_ID = "header-details-user-fullname";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        int timeout = 10;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        driver.manage().window().maximize();

        String username = dotenv.get("JIRA_USERNAME");
        String password = dotenv.get("JIRA_PASSWORD");

        JiraSoftware login = new LogIn(driver, username, password);
        login.run();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PROFILE_MENU_ID)));
    }

    @Test
    public void createMTPIssue() {
        String summary = "Hello World!";
        String issueIDXpath = "//*[@id=\"aui-flag-container\"]/div/div/a";

        JiraSoftware MTPIssue = new CreateMTPIssue(driver, summary);
        MTPIssue.run();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(issueIDXpath)));
        WebElement issueName = driver.findElement(By.xpath(issueIDXpath));
        String expected = issueName.getAccessibleName();
        issueName.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("key-val")));
        WebElement testID = driver.findElement(By.id("key-val"));
        String actual = testID.getAccessibleName() + " - " + summary;

        Assertions.assertEquals(actual, expected);

        JiraSoftware deleteIssue = new DeleteIssue(driver);
        deleteIssue.run();
    }

    @Test
    public void cancelIssueEditing() {
        CreateIssue createIssue = new CreateIssue(driver, wait);
        createIssue.run();

        String errorMessageXpath = "//*[@id=\"dialog-form\"]/div/div[2]/div[1]/div";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(errorMessageXpath)));
        WebElement errorMessage = driver.findElement(By.xpath(errorMessageXpath));

        Assertions.assertTrue(errorMessage.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
