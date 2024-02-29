package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraSoftwareBrowseIssueByAssigneeNameTest {
    private final static String PROFILE_MENU_ID = "header-details-user-fullname";
    private static final Dotenv dotenv = Dotenv.load();
    private static String username;
    private static String password;
    private static String fullname;
    private WebDriver driver;
    private WebDriverWait wait;
    @BeforeAll
    public static void initializeData() {
        username = dotenv.get("JIRA_USERNAME");
        password = dotenv.get("JIRA_PASSWORD");
        fullname = dotenv.get("JIRA_FULL_NAME");
    }

    @BeforeEach
    public void setUp() {
        int timeOut = 10;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        JiraSoftware login = new LogIn(driver, username, password);
        login.run();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PROFILE_MENU_ID)));
    }

    @Test
    public void browseIssueByAssigneeName() {
        String assigneeFieldXpath = "//span[@rel='" + username + "']";
        JiraSoftware search = new SearchIssueByUser(driver, fullname);

        search.run();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(assigneeFieldXpath)));
        WebElement firstIssueAssigneeField = driver.findElement(By.xpath(assigneeFieldXpath));
        String assigneeName = firstIssueAssigneeField.getAttribute("rel");

        Assertions.assertEquals(assigneeName, username);
    }
}
