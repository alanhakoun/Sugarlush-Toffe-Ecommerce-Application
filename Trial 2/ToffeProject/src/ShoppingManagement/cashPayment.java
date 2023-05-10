package src.ShoppingManagement;

public class cashPayment extends payment {
  //  private double cashAmount ;
    private String phoneNumber;
    public cashPayment(){}
    public void Pay() {
        System.out.println("Amount to be payed in cash is :" + getAmount() + "EG pounds.");
    }

    public boolean verifyPhoneNum(String number) {
        String regex = "(010|011|012|015)[0-9]+";
        boolean validNo = number.matches(regex);
        return validNo;
    }
}


