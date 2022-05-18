package Personal_Loan.Quote;

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

public class LoanQuoteMain {

    private String userData;
    private String Gender;
    private String username;
    private String password;
    private String surname;
    private String idNumber;
    private String intials;
    private String Title;
    private String message;
    private String runStatus;
    private String accountNo;
    private String clientType;
    private String accountOption;
    private String firstName;
    private String idType;
    private String dateIssued;
    private String Nationality;
    private String country;
    private String marriedStatus;
    private String Language;
    private String OccuptationStatus;
    private String ClientType1;
    private String refNo;
    private String address1;
    private String address2;
    private String city;
    private String town;
    private String areaCode;
    private String country1;
    private String email;
    private String countNo;
    private String occupation;
    private String companyName;
    private String companyAddress;
    private String companyTown;
    private String companyAreaCode;
    private String companyCity;
    private String MonthlyIncome;
    private String SourceOfIncome;
    private String NoDepandats;
    private String ResidentStatus;
    private String homeLangauge;
    private String Qualification;
    private String PostQualification;
    private String socialGrant;
    private String DateIdentified;
    private String empNo;
    private String DateVerified	;
    private String ProductType;
    private String methodDelivary;
    private String TaxNo;
    private String FOREIGNTAX;
    private String sourceFund;
    private String debitAccountNumber;

    private String debitDate;
    private String debitOrderType;
    private String frequency;
    private String loanAmount;





    public static boolean validTest = false;
    public LoanQouteJagacy jc = null;
    ExcelFunctions excel;


    int _userData = 0;
    int _username = 0;
    int _password = 0;
    int _RunStatus = 0;
    int _Results = 0;
    int _accountNo;
    int _Comment;
    int _debitAccountNumber;
    int _debitDate;
    int _debitOrderType;
    int _frequency;
    int _loanAmount;


    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";

    @Test
    public void TestCustomerBoarding()throws JagacyException, InterruptedException, IOException
    {

        String filePath = System.getProperty("user.dir")+"\\Data_File\\Personal_Loan\\QouteGeneration\\PersonalLoanQouteSheet.xlsx";
        ReportFolder.ReportDirectory();
        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();


            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("RunStatus");
            _userData = excel.columnsNames.indexOf("userData");
            _accountNo = excel.columnsNames.indexOf("AccountNumber");
            _debitAccountNumber =excel.columnsNames.indexOf("debitAccountNumber");
            _debitDate=excel.columnsNames.indexOf("debitDate");
            _debitOrderType=excel.columnsNames.indexOf("debitOrderType");
            _frequency=excel.columnsNames.indexOf("frequency");
           _loanAmount=excel.columnsNames.indexOf("loanAmount");
            int count = 0;
            for(int y = 1; y < ExcelFunctions.ScenarioCount; y++) {

                try {

                    password = excel.ReadCell(y, _password);
                    username = excel.ReadCell(y, _username);
                    runStatus = excel.ReadCell(y, _RunStatus);
                    userData = excel.ReadCell(y, _userData).trim();
                    accountNo = excel.ReadCell(y,_accountNo);
                    debitAccountNumber =excel.ReadCell(y,_debitAccountNumber);
                    debitDate = excel.ReadCell(y, _debitDate);
                    debitOrderType = excel.ReadCell(y, _debitOrderType).trim();
                    frequency = excel.ReadCell(y,_frequency);
                    loanAmount =excel.ReadCell(y,_loanAmount);


                    test = extent.startTest("Create an Account: ", "Test Case Scenarios");
                    test.assignAuthor("AUTHOR: Data Management Team");
                    test.assignCategory("Create Account");

                    if (runStatus.equalsIgnoreCase("RUN")) {
                        System.setProperty("sessionA.window", "true");
                        jc = new LoanQouteJagacy();
                        jc.open();

                        ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                        validTest = jc.userLogin(username, password);

                        if (validTest) {

                            message = jc.personalLoanAcceptQuote(userData,accountNo, frequency,debitDate,debitOrderType, loanAmount);

                            if(message.equalsIgnoreCase("passed")){
                                jc.close();
                                jc.open();
                                validTest = jc.userLogin(username, password);
                               message = jc.adddebitAccPart1(accountNo,debitAccountNumber,debitOrderType);

                               if(message.equalsIgnoreCase("passed")){

                                       jc.close();
                                       jc = new LoanQouteJagacy();
                                       jc.open();
                                       jc.waitForChange(1000);


                                      validTest = jc.userLogin(username, password);
                                       message =    jc.finalizeLoan(accountNo, debitAccountNumber,loanAmount,debitOrderType);
                                       if(message.equalsIgnoreCase("passed")){

                                           count+=1;
                                           System.out.println("count "+count);

                                   }

                               }




                            }


                            jc.close();
                        } else {
                            jc.close();
                            test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                            test.log(LogStatus.FAIL, "Invalid User Credintials...");
                            excel.WriteToCell("FAIL", "RUN", "0", message, y, _Results, _RunStatus, _accountNo, _Comment);
                            capture = sikuliScreen.saveScreenCapture(Create_Existing_Customer.ReportFolder.screenshortReportPath, "Screen");
                            String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                            test.log(LogStatus.INFO, test.addScreenCapture(Create_Existing_Customer.ReportFolder.screenshortReportPath + File.separator + screenshotName));

                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    jc.close();


                }
            }
        } catch (IOException e) {
            e.printStackTrace();

            throw new Error(e);
        }
    }

    @AfterTest
    public void AfterCustomerBoarding()
    {
        extent.endTest(test);
        extent.flush();
    }
}
