package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FictionPage {
    public WebDriver driver;
    private By searchBox = By.xpath("//input[@id='outlined-full-width']");
    private By SearchButton = By.xpath("//*[text()='Search']");
    private By bookName = By.xpath("//*[text()='The Adventures of Tom Sawyer']");
    private By fictonPageHeading =  By.xpath("//h1[text()='FICTION']");


    public FictionPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement fictonPageHeading() {
        return  driver.findElement(fictonPageHeading);
    }
    public WebElement searchBoxSelector() {
        return  driver.findElement(searchBox);
    }

    public WebElement SearchButtonSelector() {
        return  driver.findElement(SearchButton);
    }

    public WebElement bookNameSelector() {
        return  driver.findElement(bookName);
    }
}
