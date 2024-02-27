package com.codecool.jiratests.model;

import org.openqa.selenium.WebDriver;

public abstract class jiraSoftware {
    protected final WebDriver driver;

    public jiraSoftware(WebDriver driver) {
        this.driver = driver;
    }

    abstract public void run();
}
