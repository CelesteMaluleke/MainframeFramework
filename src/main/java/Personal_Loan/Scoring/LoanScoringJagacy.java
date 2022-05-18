package Personal_Loan.Scoring;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.util.ArrayList;

public class LoanScoringJagacy extends Session3270
{

    private int userIdRow;
    private int userIdColumn;
    private String message;
    private String value;
    private  String data;
    private int valid = 0;

    public LoanScoringJagacy() throws JagacyException
    {
        super("sessionA","host3270.absa.co.za",993,"IBM-3279-5-E",true);
    }

    public boolean userLogin(String username,String password) throws JagacyException
    {
        waitForChange(10000);
        userIdRow = 22;
        userIdColumn = 26;
        this.writePosition(userIdRow, userIdColumn, "IMSV");
        this.writeKey(Key.ENTER);

        this.waitForChange(10000);
        userIdRow = 14;
        userIdColumn = 10;
        this.writePosition(userIdRow,userIdColumn,username);

        userIdRow = 16;
        userIdColumn = 11 ;
        this.writePosition(userIdRow, userIdColumn, password);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow =  23;
        userIdColumn = 22;
        message = this.readPosition(userIdRow,userIdColumn,40).trim();

        if(message.equalsIgnoreCase("INCORRECT OR NO PASSWORD ENTERED.") || message.equalsIgnoreCase("USERID IS NOT DEFINED TO RACF.") || message.equalsIgnoreCase("Your USERID is already logged on.")) {
            return false;
        }else {

            userIdRow = 2;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, "/test mfs");
            this.writeKey(Key.ENTER);
            return true;
        }
    }


    public String PersonLoanScoring(String userData, String referenceNo) throws JagacyException {
        this.waitForChange(1000);

        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn,userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 7;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,referenceNo);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 0;
        userIdColumn = 12;
        message =  this.readPosition(userIdRow,userIdColumn,30);
       // System.out.println(message);

        if(message.equalsIgnoreCase("Application number invalid.")){
            return message;
        }

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 4;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"02");

        //loanAmount
        userIdRow = 6;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"20000");

        //number of payment
        userIdRow = 11;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"22");

        //CREDIT INITIAL FEE
        userIdRow = 12;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"D");

        //credit Life
        userIdRow = 20;
        userIdColumn = 66;
        this.writePosition(userIdRow, userIdColumn,"N");

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 0;
        userIdColumn = 12;
        message =  this.readPosition(userIdRow,userIdColumn,47);

        if(message.equalsIgnoreCase("Additional loan amount excess MAX Loan Exposure"))
        {
            return message;
        }
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 9;
        userIdColumn = 58;
        this.writePosition(userIdRow, userIdColumn,"Y");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);
        //EXTRENAL
        userIdRow = 1;
        userIdColumn = 60;
        this.writePosition(userIdRow, userIdColumn,"N");

        //CURRENT ADDRESS SINCE
        userIdRow = 5;
        userIdColumn = 35;
        this.writePosition(userIdRow, userIdColumn,"05052018");

        //EMPLOYMENT STATUS SINCE
        userIdRow = 13;
        userIdColumn = 35;
        this.writePosition(userIdRow, userIdColumn,"05052018");

        //ACTUAL OCUPATION
        userIdRow = 11;
        userIdColumn = 35;
        this.writePosition(userIdRow, userIdColumn,"developer");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        //GREOSS
        userIdRow = 5;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"35000");

        //NET
        userIdRow = 10;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"25000");

        //NET
        userIdRow = 11;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"25000");

        //NET
        userIdRow = 12;
        userIdColumn = 38;
        this.writePosition(userIdRow, userIdColumn,"25000");

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        //FOOD
        userIdRow = 4;
        userIdColumn = 45;
        this.writePosition(userIdRow, userIdColumn,"2000");

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        //FOOD
        userIdRow = 6;
        userIdColumn = 36;
        this.writePosition(userIdRow, userIdColumn,"6500");

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        userIdRow = 0;
        userIdColumn = 12;
        message =  this.readPosition(userIdRow,userIdColumn,30);
        System.out.println(message);
        return message;
    }


}
