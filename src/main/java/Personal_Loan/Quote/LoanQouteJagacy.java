package Personal_Loan.Quote;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;

public class LoanQouteJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message;
    private String value;
    private String data;
    private ArrayList<String> numbers;
    private int valid = 0;
    public static ChromeDriver driver;
    public Boolean status;
    public LoanQouteJagacy jc = null;

    public LoanQouteJagacy() throws JagacyException {
        super("sessionA", "host3270.absa.co.za", 993, "IBM-3279-5-E", true);
    }


    public boolean userLogin(String username, String password) throws JagacyException {
        waitForChange(1000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "IMSV");
        this.writeKey(Key.ENTER);

        this.waitForChange(10000);
        userIdRow = 14;
        userIdColumn = 10;
        this.writePosition(userIdRow, userIdColumn, username);

        userIdRow = 16;
        userIdColumn = 11;
        this.writePosition(userIdRow, userIdColumn, password);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 23;
        userIdColumn = 22;
        message = this.readPosition(userIdRow, userIdColumn, 40).trim();

        if (message.equalsIgnoreCase("INCORRECT OR NO PASSWORD ENTERED.") || message.equalsIgnoreCase("USERID IS NOT DEFINED TO RACF.") || message.equalsIgnoreCase("Your USERID is already logged on.")) {
            return false;
        } else {

            userIdRow = 2;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, "/test mfs");
            this.writeKey(Key.ENTER);
            return true;
        }
    }

    public boolean userLoginAlternative(String username, String password, LoanQouteJagacy jc) throws JagacyException {
        jc.waitForChange(1000);
        userIdRow = 22;
        userIdColumn = 26;
        jc.writePosition(userIdRow, userIdColumn, "IMSV");
        jc.writeKey(Key.ENTER);

        jc.waitForChange(10000);
        userIdRow = 14;
        userIdColumn = 10;
        jc.writePosition(userIdRow, userIdColumn, username);

        userIdRow = 16;
        userIdColumn = 11;
        jc.writePosition(userIdRow, userIdColumn, password);
        jc.writeKey(Key.ENTER);
        jc.waitForChange(30000);

        userIdRow = 23;
        userIdColumn = 22;
        message = jc.readPosition(userIdRow, userIdColumn, 40).trim();

        if (message.equalsIgnoreCase("INCORRECT OR NO PASSWORD ENTERED.") || message.equalsIgnoreCase("USERID IS NOT DEFINED TO RACF.") || message.equalsIgnoreCase("Your USERID is already logged on.")) {
            return false;
        } else {

            userIdRow = 2;
            userIdColumn = 2;
            jc.writePosition(userIdRow, userIdColumn, "/test mfs");
            jc.writeKey(Key.ENTER);
            return true;
        }
    }

    //Update mpp
    public void checkMpp() throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, "mpp");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);
        this.previousScreen();

        userIdRow = 0;
        userIdColumn = 7;
        this.writePosition(userIdRow, userIdColumn, "pf13");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "Y");

        userIdRow = 7;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "99 3270");

        userIdRow = 8;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "8198");

//        userIdRow = 9;
//        userIdColumn = 25;
//        this.writePosition(userIdRow, userIdColumn, "1212");

        userIdRow = 10;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "123456");

        userIdRow = 11;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "123456");

        userIdRow = 12;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "034");

        userIdRow = 13;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "034");

        userIdRow = 14;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "ABS");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);
    }


    //Generate and accept quote
    public String personalLoanAcceptQuote(String userData, String accountNo, String frequency, String debitDate, String debitOrderType, String loanAmount) throws JagacyException, InterruptedException {
////
//        checkMpp();
        String AccountNumber = accountNo.substring(accountNo.length() - 10);

        String quoteNumber;
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 3;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, AccountNumber);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        userIdRow = 14;
        userIdColumn = 61;
        this.writePosition(userIdRow, userIdColumn, "N");

        userIdRow = 15;
        userIdColumn = 45;
        this.writePosition(userIdRow, userIdColumn, loanAmount);

        userIdRow = 16;
        userIdColumn = 45;
        this.writePosition(userIdRow, userIdColumn, loanAmount);

        userIdRow = 17;
        userIdColumn = 45;
        this.writePosition(userIdRow, userIdColumn, loanAmount);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        userIdRow = 5;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "n");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        userIdRow = 6;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, debitDate);




        String debitFrequency;

       switch (frequency){
           case "Monthly": debitFrequency ="01";
           break;
           case "Bi-Monthly": debitFrequency ="02";
           break;
           case "Weekly": debitFrequency ="04";
           break;
           default: debitFrequency ="01";
       }

        userIdRow = 7;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, debitFrequency);

        userIdRow = 8;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "12");

        userIdRow = 9;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "01");


        if(debitOrderType.equalsIgnoreCase("Due")) {
            userIdRow = 14;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "Y");

        }else {
            userIdRow = 14;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, "N");
        }
        userIdRow = 15;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "N");


        userIdRow = 16;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "Y");

        userIdRow = 17;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, "N");

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 0;
        userIdColumn = 55;
        quoteNumber = this.readPosition(userIdRow, userIdColumn, 5).trim();


        if(quoteNumber == null || quoteNumber.length() == 0) {
            return "failed";
        }
        try {
            java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
            System.setProperty("webdriver.chrome.silentOutput", "true");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\java\\Personal_Loan\\Quote\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://wasuat.absa.co.za/qmswf/");

            driver.switchTo().frame("main");
            Thread.sleep(2000);
            driver.findElement(By.name("LoginName")).sendKeys("ABTS777");
            driver.findElement(By.name("password")).sendKeys("Leheadsoft8");
            driver.findElement(By.name("button_processLogin")).click();
            Thread.sleep(2000);

            driver.findElement(By.name("quoteNo")).sendKeys(quoteNumber);

            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input[value='accept']")).click();

            driver.findElement(By.name("button_continueBtn")).click();


            driver.findElement(By.name("physicalStorageRefVal")).sendKeys("test");

            driver.findElement(By.name("button_acceptButton")).click();

            status = driver.findElement(By.xpath("//p[contains(text(), 'Quote " + quoteNumber + " has been accepted successfully')]")).isDisplayed();

            driver.close();
        } catch (Exception e) {
            driver.close();
        }
        if (status) {


            return "passed";
        }else {
            return "failed";
        }




    }

    //add debit order
    public String
    adddebitAccPart1(String accountNo, String debitAccountNumber, String debitOrderType) throws JagacyException {

        String AccountNumber = accountNo.substring(accountNo.length() - 10);
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, "stl  qms");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        userIdRow = 3;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, AccountNumber);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 11;
        userIdColumn = 17;
        this.writePosition(userIdRow, userIdColumn, "000");

        userIdRow = 11;
        userIdColumn = 64;
        this.writePosition(userIdRow, userIdColumn, "02");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 8;
        userIdColumn = 11;
        this.writePosition(userIdRow, userIdColumn, "n");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        userIdRow = 0;
        userIdColumn = 61;
        this.writePosition(userIdRow, userIdColumn, "y ");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 16;
        userIdColumn = 41;
        this.writePosition(userIdRow, userIdColumn, "appr");

        userIdRow = 19;
        userIdColumn = 41;
        this.writePosition(userIdRow, userIdColumn, "21");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        if(debitOrderType.equalsIgnoreCase("Due")) {
            userIdRow = 5;
            userIdColumn = 35;
            this.writePosition(userIdRow, userIdColumn, debitAccountNumber);


            this.writeKey(Key.ENTER);
            this.waitForChange(1000);


            this.close();
            return "passed";
        }else {
            return "passed";
        }
    }

    //finalize loan
    public String finalizeLoan(String accountNo, String debitAccountNumber, String loanAmount, String debitOrderType) throws JagacyException, InterruptedException {

        String AccountNumber = accountNo.substring(accountNo.length() - 10);


        if(debitOrderType.equalsIgnoreCase("Due")){


            this.waitForChange(1000);
            userIdRow = 2;
            userIdColumn = 1;
            this.writePosition(userIdRow, userIdColumn, "epsu");
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);



            userIdRow = 7;
            userIdColumn = 1;
            this.writePosition(userIdRow, userIdColumn, "s");
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);



            userIdRow = 5;
            userIdColumn = 35;
            this.writePosition(userIdRow, userIdColumn, debitAccountNumber);


            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 1;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, AccountNumber);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 3;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "21");

            userIdRow = 5;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "f");

            userIdRow = 8;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "f");

            userIdRow = 13;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "f");

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 5;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, "reference");

            userIdRow = 6;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, debitAccountNumber);


            userIdRow = 12;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, loanAmount);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 14;
            userIdColumn = 22;
            this.writePosition(userIdRow, userIdColumn, "n");




            this.writeKey(Key.ENTER);
            this.waitForChange(1000);
            System.out.println(AccountNumber);
            return "passed";
        }else{

            this.waitForChange(1000);
            userIdRow = 2;
            userIdColumn = 1;
            this.writePosition(userIdRow, userIdColumn, "stl  cont");
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);


            userIdRow = 1;
            userIdColumn = 25;
            this.writePosition(userIdRow, userIdColumn, AccountNumber);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 3;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "21");

            userIdRow = 5;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "f");

            userIdRow = 8;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "f");

            userIdRow = 13;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, "f");

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 5;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, "reference");

            userIdRow = 6;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, debitAccountNumber);


            userIdRow = 12;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, loanAmount);
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            Thread.sleep(3000);

            userIdRow = 14;
            userIdColumn = 22;
            this.writePosition(userIdRow, userIdColumn, "n");
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);
            System.out.println(AccountNumber);
            return "passed";


        }





    }
}

