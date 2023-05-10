package src.ShoppingManagement;
import java.util.*;

public abstract class payment {
    // private double amount ;
    private double shippingCost = 20.0 ;
    private OrderedProduct price ;
    public payment(){}
    public double getAmount () {
        return price.getTotalPrice() + shippingCost;
    }

    public int calcLoyaltyPoints(double price){
        double loyaltyPoints = price * 0.05;
        return (int) loyaltyPoints;
    }
}

