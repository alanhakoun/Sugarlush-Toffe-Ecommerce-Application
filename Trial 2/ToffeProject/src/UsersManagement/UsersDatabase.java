package src.UsersManagement;

import src.ShoppingManagement.Order;
import src.ShoppingManagement.OrderedProduct;
import src.ShoppingManagement.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Vector;

public class UsersDatabase {
    private Vector<Customer> customers;
    public UsersDatabase()
    {
        customers = new Vector<Customer>();
    }

    public void loadUsers(){
        String fileName = "C:\\projects\\test\\src\\src\\users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Vector<String> userInfo = new Vector<>();
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                for (String part : parts) {
                    userInfo.add(part.trim());
                }
                Customer customer = new Customer(userInfo.get(0),userInfo.get(1),userInfo.get(2), userInfo.get(3));
                if(userInfo.size()==7){
                    customer.setLoyaltyPoints(Double.parseDouble(userInfo.get(4)));
                }
                customers.add(customer);
                userInfo.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(){
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getUsername() + ' ' + customers.get(i).getPassword());
        }
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
