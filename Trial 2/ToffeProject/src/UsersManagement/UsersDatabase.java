package src.UsersManagement;

import src.ShoppingManagement.Order;
import src.ShoppingManagement.OrderedProduct;
import src.ShoppingManagement.Product;

import java.io.*;
import java.util.Objects;
import java.util.Vector;

public class UsersDatabase {
    private static Vector<Customer> customers = null;

    public UsersDatabase() {
        if (customers==null){
            loadUsers();
        }
    }

    public static void loadUsers() {
        String fileName = "C:\\Users\\alanh\\Documents\\GitHub\\SW-Assignment-2\\Trial 2\\ToffeProject\\src\\UsersManagement\\users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Vector<String> userInfo = new Vector<>();
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                for (String part : parts) {
                    userInfo.add(part.trim());
                }
                Customer customer = new Customer(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3));
                customer.setBalance(Double.parseDouble(userInfo.get(4)));
                customer.setLoyaltyPoints(Double.parseDouble(userInfo.get(5)));
                customers.add(customer);
                userInfo.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print() {
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getUsername() + ' ' + customers.get(i).getPassword());
        }
    }

    public static boolean authenticateUser(String mail, String password) {
        for (Customer customer : customers)
            if (Objects.equals(customer.getMail(), mail) && Objects.equals(customer.getPassword(), password))
                return true;
        return false;
    }

    public static void writeData(String name, String pass, String mail, String number) {
        Customer customer = new Customer(name, pass, mail, number);
        customers.add(customer);
        updateUserDatabase();
    }

    public static boolean find(String mail) {
        for (Customer customer : customers)
            if (Objects.equals(customer.getMail(), mail))
                return true;
        return false;
    }

    public static void updatePassword(String mail, String pass) {
        for (Customer customer : customers)
            if (Objects.equals(customer.getMail(), mail)) {
                customer.setPassword(pass);
                updateUserDatabase();
                return;
            }
    }

    public static void updateUserDatabase() {
        if(customers==null){
            loadUsers();
        }
        for (int i = 0; i < customers.size(); i++) {
            String fileName = "C:\\Users\\alanh\\Documents\\GitHub\\SW-Assignment-2\\Trial 2\\ToffeProject\\src\\UsersManagement\\users.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(customers.get(i).getUsername() + ',');
                writer.newLine();
                writer.write(customers.get(i).getPassword() + ',');
                writer.newLine();
                writer.write(customers.get(i).getMail() + ',');
                writer.newLine();
                writer.write(customers.get(i).getPhoneNum() + ',');
                writer.newLine();
                writer.write("" + (customers.get(i).getBalance()) + ',');
                writer.newLine();
                writer.write("" + (customers.get(i).getLoyaltyPoints()) + ',');
                writer.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        }
    }

    public Customer getCustomer(String mail){
        Customer customer = null;
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getMail()==mail){
                customer = customers.get(i);
            }
        }
        return customer;
    }
}
