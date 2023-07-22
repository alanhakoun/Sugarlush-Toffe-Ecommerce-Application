package src.ShoppingManagement;

import java.util.Scanner;

/**
 * Represents cash payments and its methods including verifying the phone number using mobile OTP.
 */
public class cashPayment {
    Scanner in = new Scanner(System.in);
    private String phoneNumber;
    public cashPayment(){}

    /**
     * function pay makes sure of sending and verifyng the OTP as paying on delivery requires valid PhoneNumber.
     * @param num the phone number used for the payment
     * @return true if the payment is successful [valid phone and otp verify], false otherwise
     */
    public boolean Pay(String num) {
        if(validPhoneNum(num)){
            OtpSender.sendOtp("+2"+num);
            System.out.println("Enter otp sent to your num:\n");
            int otp = in.nextInt();
            if(verifyOTP(otp)){
                phoneNumber=num;
                return true;
            }
        }
        return false;
    }

    /**
     * function to check the validity of phone number entered.
     * @param number The phone number used for the payment
     * @return true if number is valid , false otherwise
     */
    public boolean validPhoneNum(String number) {
        String regex = "(010|011|012|015)[0-9]+";
        boolean validNo = number.matches(regex);
        return validNo;
    }

    /**
     *
     * @param num The phone number used for the payment
     * @return true if otp is valid , false otherwise.
     */
    public boolean verifyOTP(int num) {
        if(num == OtpSender.getOtpSent()){
            return true;
        }
        return false;
    }
}


