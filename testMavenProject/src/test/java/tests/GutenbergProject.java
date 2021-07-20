package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.FictionPage;
import pages.GutenbergHomePage;
import util.TestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GutenbergProject extends BaseTest {
    TestUtils utils = new TestUtils();
    GutenbergHomePage homePage;
    FictionPage FictionPage;
    ExtentReports mainReport;
    List <String> searchResultNames = new ArrayList<>();
    ExtentTest logger;


    @BeforeTest
    public void testHere() throws IOException {
        driver = initialiseDriver();
        driver.get(properties.getProperty("url"));
        mainReport = utils.startCollectingDataforReport();
        homePage = new GutenbergHomePage(driver);
        FictionPage = new FictionPage(driver);
    }

    @Test(priority=1)
    public void verifyProjectTitle() throws IOException, InterruptedException {
        logger = mainReport.createTest("verify Project Title");
        String abc = homePage.getProjectTitleSelector().getText();
        Assert.assertEquals(abc,"Gutenberg Project");//Get this from testData
    }
    @Test(priority=2)
    public void verifyNumberOfGenre() throws IOException,InterruptedException {
        logger = mainReport.createTest("verify Number Of Genre");
        int numOfGenre = homePage.getNumOfGenre();
        Assert.assertEquals(numOfGenre, 7);
    }
    @Test(priority=3)
    public void verifyFictionGenreIsAccessible() throws IOException {
        logger = mainReport.createTest("verify Fiction Genre Is Accessible");
        Assert.assertTrue(homePage.getFictionGenreSelector().isEnabled());
        homePage.getFictionGenreSelector().click();
    }
    @Test(priority = 4)
    public void verifyFictionPageTitle() throws IOException {
        logger = mainReport.createTest("verify Fiction Page Title");
        Assert.assertEquals(FictionPage.fictonPageHeading().getText(),"FICTION");
    }

    @Test(priority = 5)
    public void verifySearchBookFunctionality() throws IOException, InterruptedException {
        logger = mainReport.createTest("verify Search Book Functionality");
        FictionPage.searchBoxSelector().sendKeys("The Adventures of Tom Sawyer");
        FictionPage.SearchButtonSelector().click();
        List<WebElement> searchResult = driver.findElements(By.tagName("h5"));
        int numOfSearchResult = searchResult.size();
        for ( int i=0; i<numOfSearchResult;i++){
            String button = searchResult.get(i).getText();
            searchResultNames.add(i,button);
        }
        Thread.sleep(5000);
        for ( int i=0; i<searchResultNames.size();i++){
            String button = searchResultNames.get(i);
            System.out.println(searchResultNames.get(i)+ "this is " +i+"th");
            Assert.assertTrue(button.contains("The Adventures of Tom Sawyer"));
        }
    }
    @Test(priority = 6)
    public void verifyIfTheBookOpens() throws IOException {
        logger = mainReport.createTest("verify If The Book Opens");
        FictionPage.SearchButtonSelector().click();
        FictionPage.bookNameSelector().click();
    }
    @AfterMethod
    public void getResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        }
    }
    @AfterTest
    public void closeItBaby(){
        utils.stopCollectingDataforReport();
        driver.close();
    }
}