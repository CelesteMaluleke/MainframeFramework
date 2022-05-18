package Combi_Cards_With_Limits;

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

public class DebitOrderMain {

    private String userData;
    private String runStatus;
    private String username;
    private String password;
    private String cardType;
    private String personalized;
    private String chequeAccountNo;
    private String creditCardAccountNo;
    private String savingsAccountNo;
    private String brand;
    private String branch;
    private String cardNo;
    private String autoLink;
    private String result;
    private String ChechPoint;
    private  String message;

    private boolean userLoggedIn = false;
    private DebitOrderJagacy debitOrderJagacy = null;
    ExcelFunctions excel;


    int _username = 0;
    int _password = 0;
    int _RunStatus = 0;
    int _userData = 0;
    int _Results = 0;
    int _CardType = 0;
    int _Personalized = 0;
    int _AccountNo = 0;
    int _CreditAccountNo = 0;
    int _SavingsAccountNo = 0;
    int _ChequeAccountNo = 0;
    int _Brand = 0;
    int _Branch = 0;
    int _CardN0 = 0;
    int _AutoLink = 0;


    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";


    @Test
    public void mainDebitOrder() throws JagacyException, InterruptedException, IOException
    {
        String filePath = System.getProperty("user.dir")+"\\Data_File\\Combi_Cards_With_Limits_Excel\\DebitCardLimitsSheet.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();

            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("Run_Status");
            _Results = excel.columnsNames.indexOf("Results");
            _userData = excel.columnsNames.indexOf("User_Data");
            _ChequeAccountNo = excel.columnsNames.indexOf("Cheque_Account_No");
            _CreditAccountNo =  excel.columnsNames.indexOf("CreditCard_Account_No");
            _SavingsAccountNo =  excel.columnsNames.indexOf("Savings_Account_No");
            _AutoLink = excel.columnsNames.indexOf("Auto_Link");
            _Branch = excel.columnsNames.indexOf("Branch");
            _Brand = excel.columnsNames.indexOf("Brand");
            _CardN0 = excel.columnsNames.indexOf("Card_No");
            _CardType = excel.columnsNames.indexOf("Card_Type");
            _Personalized = excel.columnsNames.indexOf("Personalized");

            //Looping through the Excel Sheet
            for(int y = 1; y < ExcelFunctions.ScenarioCount; y++)
            {
                System.out.println("Y: " + y);

                password = excel.ReadCell(y,_password);
                username = excel.ReadCell(y,_username);
                runStatus = excel.ReadCell(y,_RunStatus);
                userData = excel.ReadCell(y,_userData);
                chequeAccountNo = excel.ReadCell(y,_ChequeAccountNo);
                creditCardAccountNo = excel.ReadCell(y,_CreditAccountNo);
                savingsAccountNo = excel.ReadCell(y,_SavingsAccountNo);
                autoLink = excel.ReadCell(y, _AutoLink);
                branch = excel.ReadCell(y,_Branch);
                brand = excel.ReadCell(y, _Brand);
                cardType = excel.ReadCell(y, _CardType);
                cardNo = excel.ReadCell(y,_CardN0);
                personalized = excel.ReadCell(y,_Personalized);

                if(runStatus.equalsIgnoreCase("NO RUN"))
                {

                    //Passing Jagacy Properties and Opening
                    System.setProperty("sessionA.window", "true");
                    debitOrderJagacy  = new DebitOrderJagacy();
                    debitOrderJagacy.open();

                    ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                    //Login
                    userLoggedIn = debitOrderJagacy.userLogin(username, password);

                    //
                    if(userLoggedIn){

                        //ChechPoint = debitOrderJagacy.createPersonalizedCardNo(userData,cardType,personalized,chequeAccountNo, savingsAccountNo, creditCardAccountNo,"s",branch,autoLink, cardNo);

                        test = extent.startTest("Cards ", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Assign Card Number:");

                        System.out.println("Card Limits: " + cardNo);
                        message = debitOrderJagacy.ChangeLinmit("cmmi lmts",cardNo,"2000","2000","2000");

                        System.out.println("Card Limits: " + message);
                        test.log(LogStatus.INFO, ChechPoint);
                        test.log(LogStatus.PASS, "Data Management Team");
                        excel.WriteToCell("PASS","RUN",ChechPoint,y,_RunStatus, _Results, _CardN0);
                        capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                        String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                        test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                       /* if(ChechPoint != null)
                        {
                            if(ChechPoint.equalsIgnoreCase("CMMI  NEW  Additional Combi Card, Card Fee will be charged"))
                            {
                                System.out.println("Failed");
                                test.log(LogStatus.INFO, ChechPoint);
                                test.log(LogStatus.FAIL, "Data Management Team");
                                excel.WriteToCell("FAIL","RUN","0-Card Number already assigned",y,_RunStatus, _Results, _CardN0);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                debitOrderJagacy.close();

                            }else{
                                System.out.println("Passed: " + cardNo);

                                message = debitOrderJagacy.ChangeLinmit("cmmi lmts",ChechPoint,"2000","2000","2000");
                                test.log(LogStatus.INFO, ChechPoint);
                                test.log(LogStatus.PASS, "Data Management Team");
                                excel.WriteToCell("PASS","RUN",ChechPoint,y,_RunStatus, _Results, _CardN0);
                                capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                                String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                                test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                                debitOrderJagacy.close();
                            }

                        }else{
                            System.out.println("Failed");
                            test.log(LogStatus.INFO, ChechPoint);
                            test.log(LogStatus.FAIL, "Data Management Team");
                            excel.WriteToCell("FAIL","RUN","0",y,_RunStatus, _Results,_CardN0);
                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath,"Screen");
                            String screenshotName =  capture.split("\\\\")[capture.split("\\\\").length - 1];
                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath+File.separator+screenshotName));
                            debitOrderJagacy.close();
                        }*/
                        debitOrderJagacy.close();
                    }else{
                        test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                        test.log(LogStatus.FAIL, "Invalid User Credintials...");
                        debitOrderJagacy.close();
                    }

                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @AfterTest
    public void afterTestDebitOrder()
    {
        extent.endTest(test);
        extent.flush();
    }
}
