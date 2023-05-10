package UsersManagement;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Random;


public class SystemApp {
    private int otp;
    static private UsersDatabase database;
    public SystemApp()
    {
        database = new UsersDatabase();
        fillCustomers();
    }
    public boolean signup(String name, String pass, String mail, String number)
    {
        if(!validateInfo(mail, pass))
            return false;
        database.writeData(name, pass, mail, number);
        return true;
    }
    public boolean login(String mail, String password)
    {
        return database.authenticateUser(password, mail);
    }
    public boolean validateInfo(String pass, String mail)
    {
        String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches() && passwordValidity(pass);
    }
     public boolean OTPValidity(int otp)
    {
        return this.otp == otp;
    }
    public boolean passwordValidity(String pass)
    {
        // strong password contain at least:
        // 1 uppercase, 1 lowercase, 1 digit, and all at least 6 characters

        String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        return Pattern.compile(passPattern).matcher(pass).matches();
    }

    public void fillCustomers()    /// used for testing
    {
        database.writeData("Mohamed", "12345", "m@gmail.com", "01234567890");
        database.writeData("Ahmed", "98765", "A@gmail.com", "01034567890");
        database.writeData("Smith", "24680", "S@gmail.com", "01134567890");
    }
    public void forgetPassword(){}
    public boolean mailExist(String mail)
    {
        return database.find(mail);
    }
    public void sendOTP()
    {
        Random random = new Random();
        otp = random.nextInt(1000000);      // 6 digit otp
        //TODO: send otp

    }
    public void changePassword(String mail, String pass)
    {
        database.updatePassword(mail, pass);
    }
    //TODO: forgetPassword
}
