package com.codecool.jiratests.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateMTPIssue extends JiraSoftware {
    private String summary;
    private WebDriverWait wait;

    public CreateMTPIssue(WebDriver driver, String summary, WebDriverWait wait) {
        super(driver);
        this.summary = summary;
        this.wait = wait;
    }

    @Override
    public void run() {

        WebElement createButton = driver.findElement(By.id("create_link"));
        createButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-issue-submit")));
        WebElement summaryField = driver.findElement(By.id("summary"));
        summaryField.sendKeys(summary);

        WebElement createIssueButton = driver.findElement(By.id("create-issue-submit"));
        createIssueButton.click();
    }
}
