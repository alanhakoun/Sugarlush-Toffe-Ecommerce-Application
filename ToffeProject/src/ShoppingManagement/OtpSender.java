package src.ShoppingManagement;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Random;

/**
 * It’s the class responsible for sending an OTP to the mobile number in the cash payment case to verify the customer's number. It’s sending SMS to numbers which only verified on Twilio.
 */
public class OtpSender {

    // Twilio Account SID and Auth Token
    private static final String ACCOUNT_SID = "AC5af32c5aaed7c16a471dfe97592a5c91";
    private static final String AUTH_TOKEN = "d42e325fe1ae680613ad67fbbf59b673";

    // Twilio phone number
    private static final String TWILIO_NUMBER = "+12707180828";

    private static int otpSent;

    // Generate random 6-digit OTP
    public static int generateOTP() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }

    // Send OTP to phone number using Twilio API
    public static void sendOtp(String phoneNumber) {
        // Generate OTP
        otpSent = generateOTP();

        // Create Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Create message
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(TWILIO_NUMBER),
                        "Your OTP is: " + otpSent)
                .create();
    }

    public static int getOtpSent() {
        return otpSent;
    }
}
