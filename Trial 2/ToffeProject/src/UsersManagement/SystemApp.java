package src.UsersManagement;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Random;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

/**
 * Represents the user functionalities manager class. It’s the controller class that is responsible for managing the registration and accessing app features by its methods such as login and signup. In addition, it has a relationship between the database class to be able to read and write user/system info and connect return the result to the GUI.
 */
public class SystemApp {
    private int otp;
    static private UsersDatabase database;
    public SystemApp()
    {
        database = new UsersDatabase();
    }

    /**
     * function allowing the user to sign up to the application so he can use it and order.
     * @param name customer name .
     * @param pass customer password.
     * @param mail customer email.
     * @param number customer number.
     * @param address customer address.
     * @return true if email and pass & OTP is valid , false otherwise.
     * @throws IOException
     * @throws MessagingException
     */
    public boolean signup(String name, String pass, String mail, String number, String address) throws IOException, MessagingException {
        if(!validateInfo(pass, mail))
            return false;
        database.writeData(name, pass, mail, number, address);
        return true;
    }

    /**
     * allows the user to log in to his account and order etc..
     * @param mail customer registered email.
     * @param password customer registered pass.
     * @return true if email and pass is valid , false otherwise.
     */
    public boolean login(String mail, String password)
    {
        return database.authenticateUser(mail, password);
    }

    /**
     * validates email and password pattern entered by user.
     * @param pass customer pass.
     * @param mail customer email.
     * @return
     */
    public boolean validateInfo(String pass, String mail)
    {
        String EMAIL_PATTERN ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches() && passwordValidity(pass);
    }

    /**
     * validtes OTP sent on mail.
     * @param otp entered by user
     * @return true if the otp sent is matching the entered one, false otherwise.
     */
     public boolean OTPValidity(int otp)
    {
        return this.otp == otp;
    }

    /**
     * makes sure password is strong & valid.
     * @param pass entered pass by user
     * @return true if strong & valid, false otherwise.
     */
    public boolean passwordValidity(String pass)
    {
        // strong password contain at least:
        // 1 uppercase, 1 lowercase, 1 digit, and all at least 6 characters

        String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        return Pattern.compile(passPattern).matcher(pass).matches();
    }

    /**
     * used in log in to make sure if mail entered exists in data registered.
     * @param mail entered mail by customer.
     * @return true if exists , false otherwise.
     */
    public boolean mailExist(String mail)
    {
        return database.find(mail);
    }

    /**
     * function to allow the logged in user to reassign his password if forgot.
     * @param mail entered mail by customer.
     * @return true if the mail exists and teh otp is correct.
     * @throws MessagingException
     */
    public boolean forgetPassword(String mail) throws MessagingException {
        if (this.mailExist(mail)) {
            sendOTP(mail);
            return true;
        }
        return false;
    }

    /**
     * send OTP message to the mail in case of registration and forgot password.
     * @param mail entered by customer.
     * @throws MessagingException
     */
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

    /**
     * changes password in case forgot.
     * @param mail customers email.
     * @param pass customers pass.
     * @throws IOException
     */
    public void changePassword(String mail, String pass) throws IOException {
        UsersDatabase.updatePassword(mail, pass);
    }
}
