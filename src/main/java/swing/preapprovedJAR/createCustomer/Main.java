package swing.preapprovedJAR.createCustomer;

import Create_New_Customer_On_MainFrame.ExcelFunctions;
import Create_New_Customer_On_MainFrame.MainJagacyCustomer;

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
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {

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

    public static boolean validTest = false;
    private MainJagacyCustomer jc = null;
    ExcelFunctions excel;

    int _idNumber =0;
    int _surname = 0;
    int _intials = 0;
    int _userData = 0;
    int _Title = 0;
    int _username = 0;
    int _password = 0;
    int _RunStatus = 0;
    int _Gender= 0;
    int _Results = 0;
    int _clientType;
    int _accountOption;
    int _firstName;
    int _idType;
    int _dateIssued;
    int _Nationality;
    int _country;
    int _marriedStatus;
    int _Language;
    int _OccuptationStatus;
    int _ClientType1;
    int _refNo;
    int _address1;
    int _address2;
    int _city;
    int _town;
    int _areaCode;
    int _country1;
    int _email;
    int _countNo;
    int _occupation;
    int _companyName;
    int _companyAddress;
    int _companyTown;
    int _companyAreaCode;
    int _companyCity;
    int _MonthlyIncome;
    int _SourceOfIncome;
    int _NoDepandats;
    int _ResidentStatus;
    int _homeLangauge;
    int _Qualification;
    int _PostQualification;
    int _socialGrant;
    int _DateIdentified;
    int _empNo;
    int _DateVerified;
    int _ProductType;
    int _methodDelivary;
    int _TaxNo;
    int _FOREIGNTAX;
    int _sourceFund;
    int _accountNo;
    int _Comment;

    static ExtentReports extent =  null;
    static ExtentTest test = null;
    private Screen sikuliScreen = new Screen(0);
    private String capture= "";


    @Override
    public void start(Stage primaryStage) {
        final TextArea textArea = new TextArea();


        PrintStream ps = System.out;
        System.setOut(new PrintStream(new StreamCapturer("", new Consumer() {
            @Override
            public void appendText(final String text) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        if(text.contains("ERROR")){
                            textArea.setStyle("-fx-text-fill: red ;-fx-background-color: brown");
                        }else{
                            textArea.setStyle("-fx-text-fill: black ;-fx-background-color: brown");
                        }

                        textArea.appendText(text);
                    }
                });
            }
        }, ps)));

        BorderPane root = new BorderPane(textArea);

        Scene scene = new Scene(root, 1024, 750);

        primaryStage.setTitle("CREATING CUSTOMER/S");
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("========================================================================================");
                System.out.println("========================================================================================");
                System.out.println("CREATING NEW CUSTOMER");
                System.out.println("========================================================================================");
                System.out.println("========================================================================================");


                String filePath = System.getProperty("user.dir") + "\\CustumerOnBoarding.xlsx";
                try {
                    Create_New_Customer_On_MainFrame.ReportFolder.ReportDirectory();
                } catch (IOException e) {
                    System.out.println("========================================================================================");
                    System.out.println("[ERROR]: "+e.toString());
                    System.out.println("[ERROR]: file path mot found: "+filePath);
                    System.out.println("========================================================================================");
                }
                try {

                    extent = new ExtentReports(Create_New_Customer_On_MainFrame.ReportFolder.fullReportPath, true);
                    Create_New_Customer_On_MainFrame.ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
                    excel =  new Create_New_Customer_On_MainFrame.ExcelFunctions();

                    _username = excel.columnsNames.indexOf("Username");
                    _password = excel.columnsNames.indexOf("Password");
                    _RunStatus = excel.columnsNames.indexOf("RunStatus");
                    _Results = excel.columnsNames.indexOf("Results");
                    _userData = excel.columnsNames.indexOf("userData");
                    _idNumber = excel.columnsNames.indexOf("IdNumber");
                    _surname = excel.columnsNames.indexOf("Surname");
                    _intials = excel.columnsNames.indexOf("Intials");
                    _Title= excel.columnsNames.indexOf("Title");
                    _Gender = excel.columnsNames.indexOf("Gender");
                    _clientType = excel.columnsNames.indexOf("ClientType");
                    _accountOption  = excel.columnsNames.indexOf("OptionAccount");
                    _firstName  = excel.columnsNames.indexOf("FirstName");
                    _idType  = excel.columnsNames.indexOf("IdType");
                    _dateIssued  = excel.columnsNames.indexOf("DateIssued");
                    _Nationality  = excel.columnsNames.indexOf("Nationality");
                    _country  = excel.columnsNames.indexOf("CountryOfBirth");
                    _marriedStatus  = excel.columnsNames.indexOf("MarriedStatus");
                    _Language  = excel.columnsNames.indexOf("Language");
                    _OccuptationStatus = excel.columnsNames.indexOf("OccuptationStatus");
                    _ClientType1 = excel.columnsNames.indexOf("ClientType1");
                    _refNo  = excel.columnsNames.indexOf("RefNo");
                    _address1 = excel.columnsNames.indexOf("address1");
                    _address2 = excel.columnsNames.indexOf("address2");
                    _city = excel.columnsNames.indexOf("city");
                    _town = excel.columnsNames.indexOf("town");
                    _areaCode = excel.columnsNames.indexOf("areaCode");
                    _country1 = excel.columnsNames.indexOf("country");
                    _email = excel.columnsNames.indexOf("email");
                    _countNo = excel.columnsNames.indexOf("contactNo");
                    _occupation = excel.columnsNames.indexOf("Occupation");
                    _companyName = excel.columnsNames.indexOf("companyName");
                    _companyAddress = excel.columnsNames.indexOf("companyAddress");
                    _companyTown = excel.columnsNames.indexOf("companyTown");
                    _companyAreaCode = excel.columnsNames.indexOf("companyAreaCode");
                    _companyCity = excel.columnsNames.indexOf("companyCity");
                    _MonthlyIncome = excel.columnsNames.indexOf("MonthlyIncome");
                    _SourceOfIncome = excel.columnsNames.indexOf("SourceOfIncome");
                    _NoDepandats = excel.columnsNames.indexOf("NoDepandats");
                    _ResidentStatus = excel.columnsNames.indexOf("ResidentStatus");
                    _homeLangauge = excel.columnsNames.indexOf("homeLangauge");
                    _Qualification = excel.columnsNames.indexOf("Qualification");
                    _PostQualification = excel.columnsNames.indexOf("PostQualification");
                    _socialGrant = excel.columnsNames.indexOf("socialGrant") ;
                    _DateIdentified = excel.columnsNames.indexOf("DateIdentified") ;
                    _empNo = excel.columnsNames.indexOf("empNo");
                    _DateVerified = excel.columnsNames.indexOf("DateVerified") ;
                    _ProductType = excel.columnsNames.indexOf("ProductType");
                    _methodDelivary = excel.columnsNames.indexOf("methodDelivary");
                    _TaxNo = excel.columnsNames.indexOf("TaxNo");
                    _FOREIGNTAX = excel.columnsNames.indexOf("FOREIGNTAX");
                    _sourceFund = excel.columnsNames.indexOf("sourceFund");
                    _accountNo = excel.columnsNames.indexOf("AccountNo");
                    _Comment = excel.columnsNames.indexOf("Comment");

                    for(int y = 1; y < Create_New_Customer_On_MainFrame.ExcelFunctions.ScenarioCount; y++) {

                        password = excel.ReadCell(y, _password);
                        username = excel.ReadCell(y, _username);
                        runStatus = excel.ReadCell(y, _RunStatus);
                        userData = excel.ReadCell(y, _userData);
                        idNumber = excel.ReadCell(y, _idNumber);
                        surname = excel.ReadCell(y, _surname);
                        intials = excel.ReadCell(y, _intials);
                        Title = excel.ReadCell(y, _Title);
                        Gender = excel.ReadCell(y, _Gender);
                        clientType = excel.ReadCell(y, _clientType);
                        accountOption = excel.ReadCell(y, _accountOption);
                        firstName = excel.ReadCell(y, _firstName);
                        idType = excel.ReadCell(y, _idType);
                        dateIssued = excel.ReadCell(y, _dateIssued);
                        Nationality = excel.ReadCell(y, _Nationality);
                        country = excel.ReadCell(y, _country);
                        marriedStatus = excel.ReadCell(y, _marriedStatus);
                        Language = excel.ReadCell(y, _Language);
                        OccuptationStatus = excel.ReadCell(y, _OccuptationStatus);
                        ClientType1 = excel.ReadCell(y, _ClientType1);
                        refNo = excel.ReadCell(y, _refNo);
                        address1 = excel.ReadCell(y, _address1);
                        address2 = excel.ReadCell(y, _address2);
                        city = excel.ReadCell(y, _city);
                        town = excel.ReadCell(y, _town);
                        areaCode = excel.ReadCell(y, _areaCode);
                        country1 = excel.ReadCell(y, _country1);
                        email = excel.ReadCell(y, _email);
                        countNo = excel.ReadCell(y, _countNo);
                        occupation = excel.ReadCell(y, _occupation);
                        companyName = excel.ReadCell(y, _companyName);
                        companyAddress = excel.ReadCell(y, _companyAddress);
                        companyTown = excel.ReadCell(y, _companyTown);
                        companyAreaCode = excel.ReadCell(y, _companyAreaCode);
                        companyCity = excel.ReadCell(y, _companyCity);
                        MonthlyIncome = excel.ReadCell(y, _MonthlyIncome);
                        SourceOfIncome = excel.ReadCell(y, _SourceOfIncome);
                        NoDepandats = excel.ReadCell(y, _NoDepandats);
                        ResidentStatus = excel.ReadCell(y, _ResidentStatus);
                        homeLangauge = excel.ReadCell(y, _homeLangauge);
                        Qualification = excel.ReadCell(y, _Qualification);
                        PostQualification = excel.ReadCell(y, _PostQualification);
                        socialGrant = excel.ReadCell(y, _socialGrant);
                        DateIdentified = excel.ReadCell(y, _DateIdentified);
                        empNo = excel.ReadCell(y, _empNo);
                        DateVerified = excel.ReadCell(y, _DateVerified);
                        ProductType = excel.ReadCell(y, _ProductType);
                        methodDelivary = excel.ReadCell(y, _methodDelivary);
                        TaxNo = excel.ReadCell(y, _TaxNo);
                        FOREIGNTAX = excel.ReadCell(y, _FOREIGNTAX);
                        sourceFund = excel.ReadCell(y, _sourceFund);

                        test = extent.startTest("Create an Account: ", "Test Case Scenarios");
                        test.assignAuthor("AUTHOR: Data Management Team");
                        test.assignCategory("Create Account");

                        try {
                            if (runStatus.equalsIgnoreCase("RUN")) {
                                System.setProperty("sessionA.window", "true");
                                jc = new MainJagacyCustomer();
                                jc.open();

                                Create_New_Customer_On_MainFrame.ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                                validTest = jc.userLogin(username, password);

                                if (validTest) {

                                    message = jc.CustomerOnBoarding(userData, idNumber, surname, intials, Title, Gender, clientType, accountOption,
                                            firstName, idType, dateIssued, Nationality, country, marriedStatus, Language, OccuptationStatus, ClientType1, refNo,
                                            address1, address2, city, town, areaCode, country1, email, countNo, occupation, companyName, companyAddress, companyCity,
                                            companyTown, companyAreaCode, MonthlyIncome, SourceOfIncome, NoDepandats, ResidentStatus, homeLangauge, Qualification,
                                            PostQualification, socialGrant, DateIdentified, empNo, DateVerified, ProductType, methodDelivary, TaxNo, FOREIGNTAX, sourceFund);

                                    if (message.equalsIgnoreCase("") || message.equalsIgnoreCase("INVALID SITE FOR CAPTURE") || message.equalsIgnoreCase("ABSA ISLAMIC PRIVATE ACCOUNT MAY ONLY BE OPENED BY") || message.equalsIgnoreCase("ACC TYPE ONLY VALID FOR ABSA WEALTH SITES") || message.equalsIgnoreCase("ACCOUNT TYPE AND BANKING SECTOR INCOMPATIBLE") || message.equalsIgnoreCase("STRUCTURED LOAN MAY ONLY BE OPENED BY A PRIVATE BA") || message.equalsIgnoreCase("INVALID CATEGORY") || message.equalsIgnoreCase("client already exists") || message.equalsIgnoreCase("BIRTH DATE NOT THE SAME AS IN ID NUMBER") || message.equalsIgnoreCase("INVALID CHARACTERS IN NAME") || message.equalsIgnoreCase("DATE ISSUED CANNOT BE LESS THAN DATE OF BIRTH") || message.equalsIgnoreCase("ID NUMBER INVALID") || message.equalsIgnoreCase("CLIENT TYPE AND ACCOUNT TYPE INCOMPATIBLE") || message.equalsIgnoreCase("INCOMPATIBLE CATEGORY AND DATE OF BIRTH")) {

                                        test.log(LogStatus.INFO, message);
                                        test.log(LogStatus.FAIL, "Account Creator");
                                        excel.WriteToCell("Fail", "RUN", "0", message, y, _RunStatus, _Results, _accountNo, _Comment);
                                        capture = sikuliScreen.saveScreenCapture(Create_New_Customer_On_MainFrame.ReportFolder.screenshortReportPath, "Screen");
                                        String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                        test.log(LogStatus.INFO, test.addScreenCapture(Create_New_Customer_On_MainFrame.ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    } else {

                                        //System.out.println("Not Empty: " + y);
                                        String data = message.substring(0, 25).trim();
                                        int messageLeangth = message.length();
                                        String account = message.substring(25, messageLeangth);

                                        if (data.equalsIgnoreCase("CLIENT INFORMATION UPDATE")) {
                                            test.log(LogStatus.INFO, message);
                                            test.log(LogStatus.PASS, "Account Creator");
                                            excel.WriteToCell("PASS", "RUN", account, "Succesfully Created", y, _RunStatus, _Results, _accountNo, _Comment);
                                            capture = sikuliScreen.saveScreenCapture(Create_New_Customer_On_MainFrame.ReportFolder.screenshortReportPath, "Screen");
                                            String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                            test.log(LogStatus.INFO, test.addScreenCapture(Create_New_Customer_On_MainFrame.ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                        } else {
                                            test.log(LogStatus.INFO, message);
                                            test.log(LogStatus.FAIL, "Account Creator");
                                            excel.WriteToCell("Fail", "RUN", "0", message, y, _RunStatus, _Results, _accountNo, _Comment);
                                            capture = sikuliScreen.saveScreenCapture(Create_New_Customer_On_MainFrame.ReportFolder.screenshortReportPath, "Screen");
                                            String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                            test.log(LogStatus.INFO, test.addScreenCapture(Create_New_Customer_On_MainFrame.ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                        }
                                    }

                                    jc.close();
                                } else {
                                    test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                                    test.log(LogStatus.FAIL, "Invalid User Credintials...");
                                    excel.WriteToCell("FAIL", "RUN", "0", message, y, _Results, _RunStatus, _accountNo, _Comment);
                                    capture = sikuliScreen.saveScreenCapture(Create_Existing_Customer.ReportFolder.screenshortReportPath, "Screen");
                                    String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                    test.log(LogStatus.INFO, test.addScreenCapture(Create_Existing_Customer.ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                    jc.close();
                                }
                            }

                        } catch (Exception e) {
                            try {
                                jc.close();
                            } catch (JagacyException z) {
                                System.out.println("========================================================================================");
                                System.out.println("[ERROR]: "+z.toString());
                                System.out.println("========================================================================================");
                            }
                            System.out.println("========================================================================================");
                            System.out.println("[ERROR]: "+e.toString());
                            System.out.println("FAILED ID NUMBER: "+idNumber);
                            System.out.println("========================================================================================");

                        }
                    }

                    System.out.println("========================================================================================");
                    System.out.println("PROFILE CREATION COMPLETE");
                    System.out.println("========================================================================================");
                } catch (Exception e) {

                    System.out.println("========================================================================================");
                    System.out.println("[ERROR]: "+e.toString());
                    System.out.println("========================================================================================");
                }

            }
        });
        t.setDaemon(true);
        t.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public interface Consumer {

        public void appendText(String text);
    }

    public class StreamCapturer extends OutputStream {

        private StringBuilder buffer;
        private String prefix;
        private Consumer consumer;
        private PrintStream old;

        public StreamCapturer(String prefix, Consumer consumer, PrintStream old) {
            this.prefix = prefix;
            buffer = new StringBuilder(128);
            buffer.append("[").append(prefix).append("] ");
            this.old = old;
            this.consumer = consumer;
        }

        @Override
        public void write(int b) throws IOException {
            char c = (char) b;
            String value = Character.toString(c);
            buffer.append(value);
            if (value.equals("\n")) {
                consumer.appendText(buffer.toString());
                buffer.delete(0, buffer.length());
                buffer.append("[").append(prefix).append("] ");
            }
            old.print(c);
        }
    }

}