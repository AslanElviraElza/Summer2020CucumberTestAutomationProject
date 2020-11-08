package com.vytrack.pages;

import com.vytrack.utils.BrowserUtils;
import com.vytrack.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCalendarEventPage extends BasePage {

    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEventBtn;

    @FindBy(name = "oro_calendar_event_form[title]")
    private WebElement titleInputBox;

    @FindBy(id = "tinymce")
    private WebElement descriptionInputBox;

    public void enterTitle(String text) {
        BrowserUtils.enterText(titleInputBox, text);
    }

    public void clickOnCreateCalendarEvent() {
        BrowserUtils.clickOnElement(createCalendarEventBtn);
    }

    public void enterDescription(String text) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        //exit from all frames
        Driver.getDriver().switchTo().defaultContent();
        //wait for frame and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
        //enter text
//        BrowserUtils.enterText(descriptionInputBox, text);
        descriptionInputBox.sendKeys(text);
        //exit from the frame
        Driver.getDriver().switchTo().defaultContent();
    }

    public String getDataFromGeneralInfo(String parameterName) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        String xpath = "//label[text()='" + parameterName + "']/../div/div";
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return element.getText().trim();
    }

}