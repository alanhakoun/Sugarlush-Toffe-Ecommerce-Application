package src.ShoppingManagement;
import src.UsersManagement.UsersDatabase;
import src.UsersManagement.Customer;

import java.util.Scanner;

public class cashPayment {
    Scanner in = new Scanner(System.in);
    private String phoneNumber;
    public cashPayment(){}
    public boolean Pay(String num) {
        if(verifyPhoneNum(num)){

        }

    }

    public double getAmount(Order order){
        return order.getAmount();
    }

    public double calcLoyaltyPoints(double amount){
        return 0.2*amount;
    }

    public boolean verifyPhoneNum(String number) {
        String regex = "(010|011|012|015)[0-9]+";
        boolean validNo = number.matches(regex);
        return validNo;
    }
}


