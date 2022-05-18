package swing.preapprovedJAR.addOD;

import Add_OD_Amount.AddODJagacy;
import Add_OD_Amount.ReportFolder;
import Create_New_Customer_On_MainFrame.ExcelFunctions;
import Create_New_Customer_On_MainFrame.MainJagacyCustomer;
import com.jagacy.util.JagacyException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.sikuli.script.Screen;

import javax.swing.*;
import java.io.*;

public class Main extends Application {


    String userData;
    String username;
    String password;
    String result;
    String comment;
    String runStatus;
    String accountNo;
    String overDraftAmount;
    String message;

    boolean userLoggedIn = false;
    AddODJagacy preApproved = null;
    Add_OD_Amount.ExcelFunctions excel;

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

    ExtentReports extent =  null;
    ExtentTest test = null;
    Screen sikuliScreen = new Screen(0);
    String capture= "";

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

        primaryStage.setTitle("ADDING OVERDRAFT");
        primaryStage.setScene(scene);
        primaryStage.show();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("========================================================================================");
                System.out.println("========================================================================================");
                System.out.println("ADD OVERDRAFT TO ACCOUNT");
                System.out.println("========================================================================================");
                System.out.println("========================================================================================");


                String filePath = System.getProperty("user.dir") + "\\AddODSheet.xlsx";
                try {
                    Create_New_Customer_On_MainFrame.ReportFolder.ReportDirectory();
                } catch (IOException e) {
                    System.out.println("========================================================================================");
                    System.out.println("[ERROR]: "+e.toString());
                    System.out.println("[ERROR]: file path mot found: "+filePath);
                    System.out.println("========================================================================================");
                }

                try {

                    extent = new ExtentReports(ReportFolder.fullReportPath, true);
                    Add_OD_Amount.ExcelFunctions.input_document = new FileInputStream(String.valueOf(new File(filePath)));
                    excel = new Add_OD_Amount.ExcelFunctions();

                    _username = excel.columnsNames.indexOf("Username");
                    _password = excel.columnsNames.indexOf("Password");
                    _RunStatus = excel.columnsNames.indexOf("Run_Status");
                    _Results = excel.columnsNames.indexOf("Results");
                    _userData = excel.columnsNames.indexOf("User_Data");
                    _Comment = excel.columnsNames.indexOf("Comment");
                    _accountNo = excel.columnsNames.indexOf("Account_No");
                    _OverDraftAmount = excel.columnsNames.indexOf("OverDraftAmount");


                        //Looping through the Excel Sheet
                        for (int y = 1; y < Add_OD_Amount.ExcelFunctions.ScenarioCount; y++) {


                            try {
                                password = excel.ReadCell(y, _password);
                                username = excel.ReadCell(y, _username);
                                runStatus = excel.ReadCell(y, _RunStatus);
                                userData = excel.ReadCell(y, _userData);
                                accountNo = excel.ReadCell(y, _accountNo);
                                overDraftAmount = excel.ReadCell(y, _OverDraftAmount);

                                if (runStatus.equalsIgnoreCase("RUN")) {

                                    System.setProperty("sessionA.window", "true");
                                    preApproved = new AddODJagacy();
                                    preApproved.open();

                                    Add_OD_Amount.ExcelFunctions.output_document = new FileOutputStream(String.valueOf(new File(filePath)));

                                    //Login
                                    userLoggedIn = preApproved.userLogin(username, password);

                                    //
                                    if (userLoggedIn) {

                                        test = extent.startTest("Add OD:", "Test Case Scenarios");
                                        test.assignAuthor("AUTHOR: Data Management Team");
                                        test.assignCategory("Add OD:");

                                        preApproved.checkMpp();

                                        message = preApproved.AddOD(userData, accountNo, "1", "2", overDraftAmount);

                                        if (message.equalsIgnoreCase("INCREASE NOT ALLOWED IF NO OVERDRAFT EXISTS") || message.equalsIgnoreCase("NOT AUTHORIZED TO PROCESS OVERDRAFT") || message.equalsIgnoreCase("ACCOUNT NUMBER DOES NOT EXIST") || message.equalsIgnoreCase("NO RAC-SITE FOR ACCOUNT DOMICILE") || message.equalsIgnoreCase("TRANSACTION NOT ALLOWED - UNDER DEBT COUNSELLING")) {

                                            System.out.println("Failed");
                                            test.log(LogStatus.INFO, message);
                                            test.log(LogStatus.FAIL, "Data Management Team");
                                            excel.WriteToCell("FAIL", "NO RUN", message, y, _RunStatus, _Results, _Comment);
                                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                            String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                            preApproved.close();

                                        } else {

                                            System.out.println("Passed: " + message + " : " + y);
                                            test.log(LogStatus.INFO, message);
                                            test.log(LogStatus.PASS, "Data Management Team");
                                            excel.WriteToCell("pass", "RUN", message, y, _RunStatus, _Results, _Comment);
                                            capture = sikuliScreen.saveScreenCapture(ReportFolder.screenshortReportPath, "Screen");
                                            String screenshotName = capture.split("\\\\")[capture.split("\\\\").length - 1];
                                            test.log(LogStatus.INFO, test.addScreenCapture(ReportFolder.screenshortReportPath + File.separator + screenshotName));
                                            preApproved.close();
                                        }

                                        preApproved.close();
                                    } else {
                                        test = extent.startTest("Add OD:", "Test Case Scenarios");
                                        test.assignAuthor("AUTHOR: Data Management Team");
                                        test.assignCategory("Add OD:");
                                        test.log(LogStatus.INFO, "CORRECT OR NO PASSWORD..");
                                        test.log(LogStatus.FAIL, "Invalid User Credintials...");
                                        excel.WriteToCell("Failed", "NO RUN", "Invalid User Credintials...", y, _RunStatus, _Results, _Comment);
                                        preApproved.close();
                                    }
                                }
                            } catch (JagacyException e) {
                                System.out.println("========================================================================================");
                                System.out.println("[ERROR]: "+e.toString());
                                System.out.println("FAILED Account number: "+accountNo);
                                System.out.println("========================================================================================");
                            } catch (FileNotFoundException e) {
                                System.out.println("========================================================================================");
                                System.out.println("[ERROR]: "+e.toString());
                                System.out.println("FAILED Account numberR: "+accountNo);
                                System.out.println("========================================================================================");
                            } catch (IOException e) {
                                System.out.println("========================================================================================");
                                System.out.println("[ERROR]: "+e.toString());
                                System.out.println("FAILED Account number: "+accountNo);
                                System.out.println("========================================================================================");
                            }
                        }

                            extent.endTest(test);
                    extent.flush();
                    System.out.println("========================================================================================");
                    System.out.println("ADDING OVERDRAFT COMPLETE");
                    System.out.println("========================================================================================");
                } catch (Exception ex) {

                    System.out.println("========================================================================================");
                    System.out.println("[ERROR]: "+ex.toString());
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