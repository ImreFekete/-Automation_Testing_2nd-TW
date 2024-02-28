package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JiraSoftwareTest {

    private static WebDriver driver = null;

    private static Stream<String> provideProjectNamesForBrowseProject() {
        return Stream.of(
                "Main Testing Project",
                "TOUCAN project",
                "COALA project"
        );
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("JIRA_USERNAME");
        String password = dotenv.get("JIRA_PASSWORD");
        JiraSoftware login = new LogIn(driver, username, password);
        login.run();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
    }

    @Test
    public void successfulLogIn() {
        Dotenv dotenv = Dotenv.load();
        String username = dotenv.get("JIRA_USERNAME");
        WebElement profileMenu = driver.findElement(By.id("header-details-user-fullname"));
        assertEquals(username, profileMenu.getAttribute("data-username"));
    }

    @Test
    public void successfulLogOut() {
        LogOut logOut = new LogOut(driver);
        logOut.run();
        WebElement logOutPanel = driver.findElement(By.id("main"));
        Assertions.assertTrue(logOutPanel.isDisplayed());
    }


    @Test
    public void createMTPIssue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
  
    @ParameterizedTest
    @MethodSource("provideProjectNamesForBrowseProject")
    public void browseProject(String projectToSearch) {
        JiraSoftware browseProject = new BrowseProject(driver, projectToSearch);

        browseProject.run();
        WebElement projectField = driver.findElement(By.xpath("//a[@title='" + projectToSearch + "']"));

        Assertions.assertTrue(projectField.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}