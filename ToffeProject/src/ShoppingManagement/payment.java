package ShoppingManagement;
import java.util.*;

public abstract class payment {
    private double amount ;
    public payment(double amount ){
        this.amount = amount ;
    }
    public double getAmount () {
        return amount;
    }

}
