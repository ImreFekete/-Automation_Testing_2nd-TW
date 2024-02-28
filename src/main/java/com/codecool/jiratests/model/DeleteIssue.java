package com.codecool.jiratests.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteIssue extends JiraSoftware {

    public DeleteIssue(WebDriver driver) {
        super(driver);
    }

    @Override
    public void run() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement moreDropdown = driver.findElement(By.xpath("//*[@id=\"opsbar-operations_more\"]/span"));
        moreDropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"delete-issue\"]/a/span")));
        WebElement deleteButton = driver.findElement(By.xpath("//*[@id=\"delete-issue\"]/a/span"));
        deleteButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete-issue-submit")));
        WebElement finalDeleteButton = driver.findElement(By.id("delete-issue-submit"));
        finalDeleteButton.click();
    }
}
