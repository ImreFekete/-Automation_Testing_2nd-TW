package com.codecool.jiratests.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchIssueByUser extends JiraSoftware {
    private final String assigneeName;
    public SearchIssueByUser(WebDriver driver, String assignee) {
        super(driver);
        assigneeName = assignee;
    }

    @Override
    public void run() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement issuesMenu = driver.findElement(By.id("find_link"));
        issuesMenu.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issues_new_search_link_lnk")));
        WebElement searchIssues = driver.findElement(By.id("issues_new_search_link_lnk"));
        searchIssues.click();
        WebElement assigneeSearch = driver.findElement(By.xpath("//div[@data-id='assignee']"));
        assigneeSearch.click();
        WebElement searchByAssignee = driver.findElement(By.id("assignee-input"));
        searchByAssignee.sendKeys(assigneeName);
        searchByAssignee.sendKeys(Keys.ENTER);
    }
}
