package Personal_Loan.Scoring;

import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoanScoringMain {

    private String userData;
    private String username;
    private String password;
    private String message;
    private String runStatus;
    private String referenceNo;

    public static boolean validTest = false;
    private LoanScoringJagacy jc = null;
    ExcelFunctions excel;

    int _userData = 0;
    int _username = 0;
    int _password = 0;
    int _RunStatus = 0;
    int _Results = 0;
    int _Comment = 0;
    int _ReferenceNo = 0;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";

    @Test
    public void TestCustomerBoarding()throws JagacyException, InterruptedException, IOException
    {

        String filePath = System.getProperty("user.dir")+"\\Data_File\\Personal_Loan\\Scoring\\PersonalLoanScoringSheet.xlsx";
        ReportFolder.ReportDirectory();
        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("RunStatus");
            _Results = excel.columnsNames.indexOf("Results");
            _userData = excel.columnsNames.indexOf("userData");
            _ReferenceNo = excel.columnsNames.indexOf("ReferenceNo");
            _Comment = excel.columnsNames.indexOf("Comment");

            for(int y = 1; y < ExcelFunctions.ScenarioCount; y++) {

                password = excel.ReadCell(y,_password);
                username = excel.ReadCell(y,_username);
                runStatus = excel.ReadCell(y,_RunStatus);
                userData = excel.ReadCell(y,_userData);
                referenceNo = excel.ReadCell(y,_ReferenceNo);

                test = extent.startTest("Create an Account: ", "Test Case Scenarios");
                test.assignAuthor("AUTHOR: Data Management Team");
                test.assignCategory("Create Account");

                if(runStatus.equalsIgnoreCase("RUN"))
                {
                    System.setProperty("sessionA.window", "true");
                    jc = new LoanScoringJagacy();
                    jc.open();

                    ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                    validTest = jc.userLogin(username, password);

                   if(validTest)
                    {

                        message = jc.PersonLoanScoring(userData,referenceNo);

                        if(message.equalsIgnoreCase("") || message.equalsIgnoreCase("Application number invalid.") || message.equalsIgnoreCase("Additional loan amount excess MAX Loan Exposure") )
                          {
                            System.out.println("Empty");
                            test.log(LogStatus.INFO, message);
                            test.log(LogStatus.FAIL, "Account Creator");
                            excel.WriteToCell("Fail","RUN",message,y,_RunStatus, _Results,_Comment);
                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                            String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        }else{

                            test.log(LogStatus.INFO, message);
                            test.log(LogStatus.PASS, "Account Creator");
                            excel.WriteToCell("PASS","RUN" ,message,y,_RunStatus, _Results,_Comment);
                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                            String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                        }

                       jc.close();
                    } else {
                       test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                       test.log(LogStatus.FAIL, "Invalid User Credintials...");
                       excel.WriteToCell("FAIL","RUN",message,y,_Results,_RunStatus,_Comment);
                       capture = sikuliScreen.saveScreenCapture(Create_Existing_Customer.ReportFolder.screenshortReportPath,"Screen");
                       String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                       test.log(LogStatus.INFO, test.addScreenCapture(Create_Existing_Customer.ReportFolder.screenshortReportPath+File.separator+screenshotName));
                       jc.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void AfterCustomerBoarding()
    {
        extent.endTest(test);
        extent.flush();
    }
}
