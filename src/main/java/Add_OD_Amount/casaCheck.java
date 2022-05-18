package Add_OD_Amount;

import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.sikuli.script.Screen;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class casaCheck {

    public static void main(String[] args) throws IOException, JagacyException {

        String userData;
        String username;
        String password;
        String result;
        String comment;
        String runStatus;
        String accountNo;
        String overDraftAmount;
        String message;
        String idNumber;

        boolean userLoggedIn = false;
        AddODJagacy preApproved = null;
        ExcelFunctions excel;

        int _username = 0;
        int _password = 0;
        int _RunStatus = 0;
        int _userData = 0;
        int _Results = 0;
        int _accountNo = 0;
        int _Comment = 0;
        int _idNumber = 0;
        int _IdType = 0;
        int _Id_UserData = 0;
        int _OverDraftAmount = 0;

        ExtentReports extent = null;
        ExtentTest test = null;
        Screen sikuliScreen = new Screen(0);
        String capture = "";

        String filePath = System.getProperty("user.dir") + "\\Data_File\\Add_OD_Amount_Excel\\casaCheck.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel = new ExcelFunctions();

            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _RunStatus = excel.columnsNames.indexOf("Run_Status");
            _idNumber = excel.columnsNames.indexOf("IdNumber");


                //Looping through the Excel Sheet
                for (int y = 1; y < ExcelFunctions.ScenarioCount; y++) {

                    password = excel.ReadCell(y, _password);
                    username = excel.ReadCell(y, _username);
                    runStatus = excel.ReadCell(y, _RunStatus);
                    idNumber = excel.ReadCell(y, _idNumber);


                    if (runStatus.equalsIgnoreCase("RUN")) {

                        System.setProperty("sessionA.window", "true");
                        preApproved = new AddODJagacy();
                        preApproved.open();

                        ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                        //Login
                        userLoggedIn = preApproved.userLogin(username, password);

                        //
                        if (userLoggedIn) {

                            test = extent.startTest("Add OD:", "Test Case Scenarios");
                            test.assignAuthor("AUTHOR: Data Management Team");
                            test.assignCategory("Add OD:");


                            message = preApproved.checkCasa(idNumber);

                            System.out.println("ID Number: " + idNumber);
                            System.out.println("message: " + message);


                        } else {
                            System.out.print("Couldnt log in.....");
                        }
                    } else {

                    }

                    preApproved.close();
//                    Thread.sleep(50000);
                }
        }catch (Exception e){

        }
    }
}
