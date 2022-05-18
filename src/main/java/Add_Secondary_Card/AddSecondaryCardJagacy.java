package Add_Secondary_Card;

import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddSecondaryCardJagacy extends Session3270 {

    private int userIdRow;
    private int userIdColumn;
    private String message = null;
    private String[] readData;
    private String value = null;
    private ArrayList<String> accountInformation = new ArrayList<>();

    public AddSecondaryCardJagacy() throws JagacyException
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

    public ArrayList<String> AddSecondaryCard(String primaryCardNo, String secondaryCIF) throws JagacyException {
        this.waitForChange(1000);
        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn, "tcsc");
        this.writeKey(Key.ENTER);
        this.waitForChange(30000);



        userIdRow = 0;
        userIdColumn = 18;
        this.writePosition(userIdRow, userIdColumn, "cons");
        this.waitForChange(1000);


        userIdRow = 3;
        userIdColumn = 11;
        this.writePosition(userIdRow, userIdColumn, primaryCardNo);
        this.writeKey(Key.ENTER);
        this.waitForChange(2000);


        userIdRow = 0;
        userIdColumn = 18;
        this.writePosition(userIdRow, userIdColumn, "ctsu");
        this.writeKey(Key.ENTER);
        this.waitForChange(2000);

        userIdRow = 12;
        userIdColumn = 17;
        this.writePosition(userIdRow, userIdColumn, secondaryCIF);

        userIdRow = 13;
        userIdColumn = 17;
        this.writePosition(userIdRow, userIdColumn, "s");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        userIdRow = 12;
        userIdColumn = 48;
        this.writePosition(userIdRow, userIdColumn, "888");

        userIdRow = 5;
        userIdColumn = 35;
        this.writePosition(userIdRow, userIdColumn, "888");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        userIdRow = 1;
        userIdColumn = 45;
        message = this.readPosition(userIdRow, userIdColumn, 160).trim();

        if(message.contains("1085 DUP TENANT TYPE INDICATOR")){
            userIdRow = 13;
            userIdColumn = 17;
            this.writePosition(userIdRow, userIdColumn, "a");
            this.writeKey(Key.ENTER);
            this.waitForChange(3000);
        }
        userIdRow = 13;
        userIdColumn = 29;
        this.writePosition(userIdRow, userIdColumn, "b");

        userIdRow = 14;
        userIdColumn = 29;
        this.writePosition(userIdRow, userIdColumn, "f");

        userIdRow = 16;
        userIdColumn = 29;
        this.writePosition(userIdRow, userIdColumn, "8614");


        userIdRow = 17;
        userIdColumn = 29;
        this.writePosition(userIdRow, userIdColumn, "1055782");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);
        this.writeKey(Key.PF3);

        userIdRow = 1;
        userIdColumn = 45;
        message = this.readPosition(userIdRow, userIdColumn, 160).trim();
        this.waitForChange(2000);

        userIdRow = 0;
        userIdColumn = 18;
        this.writePosition(userIdRow, userIdColumn, "cppr");
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);


        readData = this.readScreen();

        for(int x = 0; x < readData.length; x++)

        {
            if(readData[x].contains("NWD") || readData[x].contains("NUD") || readData[x].contains("AAA")) {
                value = readData[x].substring(1, 70);
                System.out.println(value);
                accountInformation.add(value);
            }
        }


//        if(message.contains("1085 DUP TENANT TYPE INDICATOR"))

        return accountInformation;
    }




}
