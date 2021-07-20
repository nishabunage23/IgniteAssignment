package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestUtils {
    ExtentReports mainReport;

    public ExtentReports startCollectingDataforReport(){
        String Path = System.getProperty("user.dir")+"/reports/index.html";
        ExtentSparkReporter reports =  new ExtentSparkReporter(Path);
        reports.config().setReportName("Gutenberg Project");//Take this from property file
        reports.config().setTheme(Theme.STANDARD);

        mainReport = new ExtentReports();
        mainReport.attachReporter(reports);
        mainReport.setSystemInfo("QAName","Nisha");
        return mainReport;
    }
    public void createTest(String testName){
        mainReport.createTest(testName);
    }
    public void stopCollectingDataforReport(){
        mainReport.flush();
    }
}
