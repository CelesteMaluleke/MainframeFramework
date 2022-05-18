package Combi_Cards_With_Limits;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.awt.*;

public class DebitOrderJagacy  extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;

    public  DebitOrderJagacy() throws JagacyException
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
        userIdColumn = 25;
        message = this.readPosition(userIdRow,userIdColumn,25).trim();

        if(message.equalsIgnoreCase("CORRECT OR NO PASSWORD EN") || message.equalsIgnoreCase("ERID IS NOT DEFINED TO RA") || message.equalsIgnoreCase("ur USERID is already logg")) {
            return false;
        }else {

            userIdRow = 2;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, "/test mfs");
            this.writeKey(Key.ENTER);
            return true;
        }
    }

    public String createPersonalizedCardNo(String userData, String cardType, String Personalized, String ChequeAccountNo,String SavingsAccountNo, String creditCardAccountNo, String brand, String branch, String autoLink, String cardNo) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 41;
        this.writePosition(userIdRow, userIdColumn, cardType);

        userIdRow = 3;
        userIdColumn = 41;
        this.writePosition(userIdRow, userIdColumn, Personalized);

        if(ChequeAccountNo == null )
        {
            System.out.println("inside Savings: " + SavingsAccountNo);
            userIdRow = 6;
            userIdColumn = 41;
            this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);
        }else{
//            System.out.println("inside Cheques: " + ChequeAccountNo);
            userIdRow = 6;
            userIdColumn = 41;
            this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);
        }

        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        if(Personalized.equalsIgnoreCase("n")){

            //Check Account Type
            if(ChequeAccountNo == null)
            {
                userIdRow = 4;
                userIdColumn = 2;
                this.writePosition(userIdRow, userIdColumn, brand);
                System.out.println("Savings Brand");
            }else {
                userIdRow = 6;
                userIdColumn = 2;
                this.writePosition(userIdRow, userIdColumn, brand);
                System.out.println("Cheque Brand");
            }

            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 7;
            userIdColumn = 22;
            this.writePosition(userIdRow, userIdColumn, cardNo);

        }else{

            userIdRow = 4;
            userIdColumn = 2;
            this.writePosition(userIdRow, userIdColumn, brand);

            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            this.writeKey(Key.ENTER);
            this.waitForChange(30000);
        }

        userIdRow = 9;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, branch);

        if(ChequeAccountNo != null && SavingsAccountNo != null)
        {

            userIdRow = 12;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);

            userIdRow = 13;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);

        }else if(ChequeAccountNo != null)
        {
            System.out.println("Inside Cheque 2");
            userIdRow = 12;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, ChequeAccountNo);

        }else
        {
            System.out.println("Inside Savings 2");
            userIdRow = 13;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, SavingsAccountNo);

        }

        userIdRow = 19;
        userIdColumn = 20;
        this.writePosition(userIdRow, userIdColumn, autoLink);
        this.writeKey(Key.ENTER);

        this.waitForChange(30000);

        userIdRow = 0;
        userIdColumn = 1;
        message = this.readPosition(userIdRow, userIdColumn, 60).trim();
        System.out.println("Heading Message1: " + message + " Length: " + message.length());

        if(message.length() == 0){

            userIdRow = 23;
            userIdColumn = 70;
            message = this.readPosition(userIdRow, userIdColumn, 10).trim();
            System.out.println("Heading Message2: " + message);

            userIdRow = 1;
            userIdColumn = 23;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            System.out.println("Heading Message3: " + message);
            this.writeKey(Key.CLEAR);
            return message;

        }else{

            this.writeKey(Key.ENTER);
            this.waitForChange(30000);

            userIdRow = 23;
            userIdColumn = 70;
            message = this.readPosition(userIdRow, userIdColumn, 10).trim();
            System.out.println("Heading Message2: " + message);

            userIdRow = 1;
            userIdColumn = 23;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            System.out.println("Heading Message3: " + message);
            this.writeKey(Key.CLEAR);
            return message;

        }

    }

    public String ChangeLinmit(String userLimit, String cardNo, String FirstAmount, String secondAmount, String thirdAmount) throws JagacyException, AWTException {

        this.waitForChange(1000);
        Robot robot = new Robot();

        String newCardNumber = cardNo.replace("-","");
        System.out.println("Card Numbers: "+ cardNo);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, userLimit);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 2;
        userIdColumn = 25;
        this.writePosition(userIdRow, userIdColumn, cardNo);
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);

        userIdRow = 6;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn, FirstAmount);

        userIdRow = 7;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn, secondAmount);

        userIdRow = 8;
        userIdColumn = 27;
        this.writePosition(userIdRow, userIdColumn,thirdAmount);

        this.writeKey(Key.PF14);
        this.waitForChange(1000);

        userIdRow = 0;
        userIdColumn = 12;
        message = this.readPosition(userIdRow, userIdColumn, 25).trim();
        System.out.println("Message: " + message);

        return message;
    }

}
