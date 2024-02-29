package com.codecool.jiratests.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CancelIssueCreate extends JiraSoftware{
    private WebDriverWait wait;
    public CancelIssueCreate(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.wait = wait;
    }

    @Override
    public void run() {
        WebElement createButton = driver.findElement(By.id("create_link"));
        createButton.click();

        String issueSubmitButton = "create-issue-submit";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(issueSubmitButton)));
        WebElement createIssueButton = driver.findElement(By.id(issueSubmitButton));
        createIssueButton.click();
    }

}