package UsersManagement;
import java.util.regex.Pattern;
import java.util.Random;

public class SystemApp {
    private int otp;
    static private UsersDatabase database;
    public boolean signup(String name, String pass, String mail, String number)
    {
        if(!validateInfo(mail, pass))
            return false;
        database.writeData(name, pass, mail, number);
        return true;
    }

    public boolean login(String mail, String password)
    {
        return database.authenticateUser(mail, password);
    }
    public void forgetPassword(){}
    public boolean validateInfo(String mail, String pass)
    {
        return Pattern.compile("^(.+)@(.+)$").matcher(mail).matches() && passwordValidity(pass);
    }
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
    public void changePassword(String mail, String pass)
    {
        database.updatePassword(mail, pass);
    }
}
