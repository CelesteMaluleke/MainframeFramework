package Add_Secondary_Card;

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
import java.util.ArrayList;

public class AddSecondaryCardMain {

    public static void main(String[] args) throws IOException, JagacyException {

        String username;
        String password;
        String runStatus;
        String primaryCardNo;
        String secondaryCIF;
        String linkedCards;
        String message;

        boolean userLoggedIn = false;
        AddSecondaryCardJagacy addSecondaryCardJagacy = null;
        ExcelFunctions excel;

        int _username = 0;
        int _password = 0;
        int _runStatus =0;
        int _primaryCardNo = 0;
        int _secondaryCIF = 0;
        int _linkedCards = 0;


        ExtentReports extent =  null;
        ExtentTest test = null;


        String filePath = System.getProperty("user.dir")+"\\Data_File\\Add_Secondary_Card_Excel\\AddSecondaryCardSheet.xlsx";
        ReportFolder.ReportDirectory();

        try {

            extent = new ExtentReports(ReportFolder.fullReportPath, true);
            ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
            excel =  new ExcelFunctions();
//
            _username = excel.columnsNames.indexOf("Username");
            _password = excel.columnsNames.indexOf("Password");
            _runStatus = excel.columnsNames.indexOf("Run_Status");
            _primaryCardNo = excel.columnsNames.indexOf("Primary_Card_No");
            _secondaryCIF = excel.columnsNames.indexOf("Secondary_CIF");
            _linkedCards = excel.columnsNames.indexOf("Linked_Cards");



//
//            //Looping through the Excel Sheet
                for(int y = 1; y < ExcelFunctions.ScenarioCount; y++) {


                    password = excel.ReadCell(y, _password);
                    username = excel.ReadCell(y, _username);
                    runStatus = excel.ReadCell(y, _runStatus);
                    primaryCardNo = excel.ReadCell(y, _primaryCardNo);
                    secondaryCIF = excel.ReadCell(y, _secondaryCIF);
                    linkedCards = excel.ReadCell(y, _linkedCards);
                    if (runStatus.equalsIgnoreCase("RUN")) {


                        System.setProperty("sessionA.window", "true");
                        addSecondaryCardJagacy = new AddSecondaryCardJagacy();
                        addSecondaryCardJagacy.open();

//                        ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                        //Login
                        userLoggedIn = addSecondaryCardJagacy.userLogin(username, password);


                        if (userLoggedIn) {

                            ArrayList<String> linkedAccounts = addSecondaryCardJagacy.AddSecondaryCard(primaryCardNo, secondaryCIF);
                            if (linkedAccounts.size()<1) {
                                System.out.println("Failed: " );
                                addSecondaryCardJagacy.close();
                            } else {
                                System.out.println("Passed: "+linkedAccounts.toString());
                            }
                        } else {

                            addSecondaryCardJagacy.close();
                        }
                        addSecondaryCardJagacy.close();
                    }

                }

                System.out.println("DONE!!!!!!!!!!");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
