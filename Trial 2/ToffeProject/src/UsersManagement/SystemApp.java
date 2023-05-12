package src.UsersManagement;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Random;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class SystemApp {
    private int otp;
    static private UsersDatabase database;
    public SystemApp()
    {
        database = new UsersDatabase();
    }
    public boolean signup(String name, String pass, String mail, String number) throws IOException {
        if(!validateInfo(pass, mail))
            return false;
        database.writeData(name, pass, mail, number);
        return true;
    }
    public boolean login(String mail, String password)
    {
        return database.authenticateUser(mail, password);
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

    public boolean mailExist(String mail)
    {
        return database.find(mail);
    }
    public boolean forgetPassword(String mail) throws MessagingException {
        if (this.mailExist(mail)) {
            sendOTP(mail);
            return true;
        }
        return false;
    }

    public void sendOTP(String mail) throws MessagingException {
        Random random = new Random();
        otp = random.nextInt(1000000);      // 6 digit otp

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port",465);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.auth", true);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("sasooelbihery@gmail.com","bnwqzbztwdkyngjz");
            }
        });

        String text = "Your OTP is: " + otp;
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress("sasooelbihery@gmail.com"));

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));

        message.setSubject("Verification");

        message.setText(text);

        Transport.send(message);
    }
    public void changePassword(String mail, String pass) throws IOException {
        UsersDatabase.updatePassword(mail, pass);
    }
}
