package src.ShoppingManagement;

import java.util.Scanner;

public class cashPayment {
    Scanner in = new Scanner(System.in);
    private String phoneNumber;
    public cashPayment(){}
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

    public boolean validPhoneNum(String number) {
        String regex = "(010|011|012|015)[0-9]+";
        boolean validNo = number.matches(regex);
        return validNo;
    }

    public boolean verifyOTP(int num) {
        if(num == OtpSender.getOtpSent()){
            return true;
        }
        return false;
    }
}


