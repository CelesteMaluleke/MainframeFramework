package Create_New_Customer_On_MainFrame;

import com.gargoylesoftware.htmlunit.javascript.host.media.MediaElementAudioSourceNode;
import com.jagacy.Key;
import com.jagacy.Session3270;
import com.jagacy.util.JagacyException;

import java.util.ArrayList;

public class MainJagacyCustomer extends Session3270
{

    private int userIdRow;
    private int userIdColumn;
    private String message;
    private String value;
    private  String data;
    private ArrayList<String> numbers;
    private int valid = 0;
    public  MainJagacyCustomer() throws JagacyException
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

    //Create Customer Onboarding
    public String CustomerOnBoarding(String userData, String idNumber, String surname, String intials, String Title, String Gender,
                                     String clientType, String accountOption, String firstName, String idType, String dateIssued, String Nationality, String country,
                                     String marriedStatus, String Language, String occuptationStatus, String clientType1, String refN0, String address1, String address2,
                                     String city, String town,  String areaCode,  String country1, String email, String countNo,String occupation, String companyName,
                                     String companyAddress, String companyCity, String companyTown, String companyAreaCode, String MonthlyIncome, String SourceOfIncome, String NoDepandats,
                                     String ResidentStatus, String homeLangauge, String Qualification, String PostQualification, String socialGrant, String DateIdentified, String empNo,
                                     String DateVerified, String ProductType, String methodDelivary , String TaxNo, String FOREIGNTAX , String sourceFund) throws JagacyException
    {
        this.waitForChange(1000);

        userIdRow = 2;
        userIdColumn = 1;
        this.writePosition(userIdRow, userIdColumn,userData);
        this.writeKey(Key.ENTER);
        this.waitForChange(1000);

        String date = idNumber.substring(0,6);
        String year = date.substring(0,2);
        String month = date.substring(2,4);
        String day = date.substring(4,6);
        String newYear = "";
        String valid = year.substring(0,1);

        if(valid.equalsIgnoreCase("0"))
        {
            newYear = "20" + year;
        }else
        {
            newYear = "19" + year;
        }

        String dateOfBirth = day+month+newYear;

        if(clientType.equalsIgnoreCase("N"))
        {
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 14;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, clientType);

            userIdRow = 8;
            userIdColumn = 35;
            this.writePosition(userIdRow, userIdColumn,"");
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

            if(message.equals("")){

                userIdRow = 2;
                userIdColumn = 10;
                this.writePosition(userIdRow, userIdColumn,accountOption);
                this.writeKey(Key.ENTER);
                this.waitForChange(1000);

                
                return "";
            }else {
                return message;
            }

        }else {


            this.writeKey(Key.ENTER);

            this.waitForChange(1000);

            userIdRow = 8;
            userIdColumn = 35;
            this.writePosition(userIdRow, userIdColumn, surname);

            userIdRow = 13;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, intials);

            userIdRow = 14;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, Title);

      /*  int dateLength = dateOfBirth.length();
        if(dateLength != 8)
        {
            String newDate = "0" + dateOfBirth;
            userIdRow = 15;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, newDate);
        }else
        {
            userIdRow = 15;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, dateOfBirth);
        }*/

            userIdRow = 15;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, dateOfBirth);

            userIdRow = 16;
            userIdColumn = 31;
            this.writePosition(userIdRow, userIdColumn, Gender);

            userIdRow = 19;
            userIdColumn = 36;
            this.writePosition(userIdRow, userIdColumn, clientType);
            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();



            if (message.equalsIgnoreCase("TYPE E AND F5/F6 OR TYPE M/A/N AND ENTER")) {
                message = "client already exists";
                return message;
            }


            userIdRow = 2;
            userIdColumn = 9;
            this.writePosition(userIdRow, userIdColumn, accountOption);

            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();
//            System.out.println("Messages: " + message);

            userIdRow = 2;
            userIdColumn = 57;
            this.writePosition(userIdRow, userIdColumn, refN0);

            userIdRow = 5;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, firstName);

            userIdRow = 10;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, idType);

            userIdRow = 11;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, idNumber);

            userIdRow = 11;
            userIdColumn = 58;
            this.writePosition(userIdRow, userIdColumn, dateIssued);

            userIdRow = 12;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, Nationality);

            userIdRow = 13;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, country);

            userIdRow = 15;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, marriedStatus);

            userIdRow = 17;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, Language);

            userIdRow = 18;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, occuptationStatus);

            userIdRow = 19;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, clientType1);

            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();



            if (message.equalsIgnoreCase("INVALID CHARACTERS IN NAME") || message.equalsIgnoreCase("DATE ISSUED CANNOT BE LESS THAN DATE OF BIRTH"))
            {
                return message;
            }
            if(message.equalsIgnoreCase("GENDER CODE INVALID TO ID NUMBER")){
                if(Gender.equalsIgnoreCase("M"))
                {
                    Gender = "f";
                    userIdRow = 8;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Gender);

                    Title = "03";
                    userIdRow = 7;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Title);
                }else
                {
                    Gender = "M";
                    userIdRow = 8;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Gender);

                    Title = "01";
                    userIdRow = 7;
                    userIdColumn = 23;
                    this.writePosition(userIdRow, userIdColumn, Title);
                }

                this.writeKey(Key.ENTER);
                this.waitForChange(10000);

                userIdRow = 22;
                userIdColumn = 8;
                message = this.readPosition(userIdRow, userIdColumn, 50).trim();


            }


            if(message.equalsIgnoreCase("BIRTH DATE NOT THE SAME AS IN ID NUMBER") || message.equalsIgnoreCase("ID NUMBER INVALID"))
            {
                return message;
            }

            userIdRow = 4;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, address1);

            userIdRow = 4;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, address1);

            userIdRow = 5;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, address2);

            userIdRow = 5;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, address2);

            userIdRow = 6;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 6;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, town);

            userIdRow = 7;
            userIdColumn = 18;
            this.writePosition(userIdRow, userIdColumn, city);

            userIdRow = 7;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, city);

            userIdRow = 8;
            userIdColumn = 19;
            this.writePosition(userIdRow, userIdColumn, areaCode);

            userIdRow = 8;
            userIdColumn = 50;
            this.writePosition(userIdRow, userIdColumn, areaCode);

            userIdRow = 9;
            userIdColumn = 26;
            this.writePosition(userIdRow, userIdColumn, country1);

            userIdRow = 10;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, email);

            userIdRow = 14;
            userIdColumn = 77;
            this.writePosition(userIdRow, userIdColumn, "Y");

            String newContacts = "0" + countNo;
            userIdRow = 15;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, countNo);

            userIdRow = 16;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 16;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");


            userIdRow = 17;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 17;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 18;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 18;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 19;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 19;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 20;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 20;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 21;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, "N");

            userIdRow = 21;
            userIdColumn = 68;
            this.writePosition(userIdRow, userIdColumn, "N");
            this.writeKey(Key.ENTER);
            this.waitForChange(10000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

//            System.out.println("Messages: " + message);


            userIdRow = 4;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, occupation);

        /*userIdRow = 5;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "13");

        userIdRow = 6;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "07");

        userIdRow = 7;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "33331");

        userIdRow = 8;
        userIdColumn = 23;
        this.writePosition(userIdRow, userIdColumn, "1038378");*/

            userIdRow = 13;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyName);

            userIdRow = 14;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyAddress);

            userIdRow = 15;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyTown);

            userIdRow = 16;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyCity);

            userIdRow = 17;
            userIdColumn = 30;
            this.writePosition(userIdRow, userIdColumn, companyAreaCode);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
//
//            System.out.println("Messages: " + message);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 4;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, MonthlyIncome);

            userIdRow = 5;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, SourceOfIncome);

            userIdRow = 6;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, NoDepandats);

            userIdRow = 7;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, ResidentStatus);

            userIdRow = 8;
            userIdColumn = 23;
            this.writePosition(userIdRow, userIdColumn, homeLangauge);

            userIdRow = 11;
            userIdColumn = 48;
            this.writePosition(userIdRow, userIdColumn, Qualification);

            userIdRow = 12;
            userIdColumn = 40;
            this.writePosition(userIdRow, userIdColumn, PostQualification);

            userIdRow = 13;
            userIdColumn = 39;
            this.writePosition(userIdRow, userIdColumn, socialGrant);

            userIdRow = 14;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, DateIdentified);

            userIdRow = 15;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, empNo);

            userIdRow = 16;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, DateVerified);
            userIdRow = 17;
            userIdColumn = 28;
            this.writePosition(userIdRow, userIdColumn, empNo);

            userIdRow = 18;
            userIdColumn = 17;
            this.writePosition(userIdRow, userIdColumn, ProductType);

            userIdRow = 20;
            userIdColumn = 22;
            this.writePosition(userIdRow, userIdColumn, empNo);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 50).trim();


            if(message.equalsIgnoreCase("CLIENT TYPE AND ACCOUNT TYPE INCOMPATIBLE") ||message.equalsIgnoreCase("INVALID SITE FOR CAPTURE")|| message.equalsIgnoreCase("ABSA ISLAMIC PRIVATE ACCOUNT MAY ONLY BE OPENED BY") || message.equalsIgnoreCase("INCOMPATIBLE CATEGORY AND DATE OF BIRTH") || message.equalsIgnoreCase("INVALID CATEGORY") || message.equalsIgnoreCase("ACC TYPE ONLY VALID FOR ABSA WEALTH SITES") || message.equalsIgnoreCase("ACCOUNT TYPE AND BANKING SECTOR INCOMPATIBLE")||message.equalsIgnoreCase("STRUCTURED LOAN MAY ONLY BE OPENED BY A PRIVATE BA"))
            {
                return message;
            }


            userIdRow = 8;
            userIdColumn = 54;
            this.writePosition(userIdRow, userIdColumn, methodDelivary);

            userIdRow = 11;
            userIdColumn = 45;
            this.writePosition(userIdRow, userIdColumn, TaxNo);

            userIdRow = 15;
            userIdColumn = 37;
            this.writePosition(userIdRow, userIdColumn, FOREIGNTAX);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

//            System.out.println("Messages: " + message);

            userIdRow = 12;
            userIdColumn = 33;
            this.writePosition(userIdRow, userIdColumn, sourceFund);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 22;
            userIdColumn = 8;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();

//            System.out.println("Messages: " + message);

            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            userIdRow = 11;
            userIdColumn = 21;
            String account = this.readPosition(userIdRow, userIdColumn, 25).trim();

            userIdRow = 14;
            userIdColumn = 1;
            message = this.readPosition(userIdRow, userIdColumn, 25).trim();
            this.writeKey(Key.ENTER);
            this.waitForChange(1000);

            message = message + account;
            System.out.println("========================================================================================");
            System.out.println("ID No: " + idNumber);
            System.out.println("Created Account No: " + account);
            System.out.println("========================================================================================");
            return message;

            //}
        }
    }

}
