package com.codecool.jiratests.model;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

public class JiraSoftwareBrowseProjectTest {
    private final static String PROFILE_MENU_ID = "header-details-user-fullname";
    private static final Dotenv dotenv = Dotenv.load();
    private static String username;
    private static String password;
    private WebDriver driver;
    private WebDriverWait wait;

    private static Stream<String> provideProjectNamesForBrowseProject() {
        return Stream.of(
                "Main Testing Project",
                "TOUCAN project",
                "COALA project"
        );
    }

    @BeforeAll
    public static void initializeData() {
        username = dotenv.get("JIRA_USERNAME");
        password = dotenv.get("JIRA_PASSWORD");
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

    @ParameterizedTest
    @MethodSource("provideProjectNamesForBrowseProject")
    public void browseProject(String projectToSearch) {
        String xpathOfProjectField = "//a[@title='" + projectToSearch + "']";
        JiraSoftware browseProject = new BrowseProject(driver, wait, projectToSearch);

        browseProject.run();
        WebElement projectField = driver.findElement(By.xpath(xpathOfProjectField));

        Assertions.assertTrue(projectField.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
