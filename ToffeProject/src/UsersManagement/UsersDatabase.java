package UsersManagement;

import java.util.Vector;

public class UsersDatabase {
    private Vector<Customer> customers;
    public UsersDatabase()
    {
        customers = new Vector<Customer>();
    }
    public void addCustomer(Customer customer)
    {
        customers.add(customer);
    }
}
