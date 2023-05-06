package UsersManagement;

import java.util.Objects;
import java.util.Vector;

public class UsersDatabase {
    static private Vector<Customer> customers;
    public UsersDatabase()
    {
        customers = new Vector<Customer>();
    }
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
    public boolean authenticateUser(String mail, String password)
    {
        for(Customer customer : customers)
            if(Objects.equals(customer.getMail(), mail) && Objects.equals(customer.getPassword(), password))
                return true;
        return false;
    }
    public void writeData(String name, String pass, String mail, String number)
    {
        Customer customer = new Customer(name, pass, mail, number);
        customers.add(customer);
    }
    public boolean find(String mail)
    {
        for(Customer customer : customers)
            if(Objects.equals(customer.getMail(), mail))
                return true;
        return false;
    }
    public void updatePassword(String mail, String pass)
    {
        for(Customer customer : customers)
            if(Objects.equals(customer.getMail(), mail))
            {
                customer.setPassword(pass);
                return;
            }
    }
}
