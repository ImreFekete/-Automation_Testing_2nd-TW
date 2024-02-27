package com.codecool.jiratests.model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogIn extends JiraSoftware {
    private static final String url = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    private final String username;
    private final String password;
    public LogIn(WebDriver driver, String username, String password) {
        super(driver);
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        driver.get(url);
        WebElement usernameField = driver.findElement(By.id("login-form-username"));
        WebElement passwordField = driver.findElement(By.id("login-form-password"));
        WebElement loginButton = driver.findElement(By.id("login"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
