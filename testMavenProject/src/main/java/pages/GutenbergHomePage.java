package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class GutenbergHomePage {
    public WebDriver driver;
    private By projectTitle = By.xpath("//h1[@class='headingDiv']");
    private By fictionGenre = By.xpath("//*[text()='FICTION']");
    private By genre = By.tagName("button");

    //
    List<WebElement> buttons;
    List <String> genreNames;
    int num;

    public GutenbergHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getProjectTitleSelector() {
        return  driver.findElement(projectTitle);
    }

    public WebElement getFictionGenreSelector() {
        return  driver.findElement(fictionGenre);
    }

    public List<WebElement> getListOfGenreSelector() {
        buttons =  driver.findElements(genre);
        return buttons;
    }

    public int getNumOfGenre(){
        num = getListOfGenreSelector().size();
        for ( int i=0; i<num;i++){
            String button = buttons.get(i).getText();
            System.out.println(button);
            //genreNames.add(button);
        }
        return num;
    }
}
