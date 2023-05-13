package src.UsersManagement;

import java.io.*;
import java.util.Objects;
import java.util.Vector;

/**
 * Represents the database of the user.
 */
public class UsersDatabase {
    private static Vector<Customer> customers = null;

    public UsersDatabase() {
        if (customers==null){
            customers = new Vector<>();
            loadUsers();
        }
    }

    /**
     * loads the users data from the file.
     */
    public static void loadUsers() {
        String fileName = "users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Vector<String> userInfo = new Vector<>();
            int cnt = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    userInfo.add(part.trim());
                    cnt++;
                }

                if(cnt == 5){
                    Customer customer = new Customer(userInfo.get(0), userInfo.get(1), userInfo.get(2), userInfo.get(3), userInfo.get(4));
                    customers.add(customer);
                    userInfo.clear();
                    cnt = 0;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * authenticate teh user while logging in .
     * @param mail customer entered mail.
     * @param password customer entered pass.
     * @return true if user is registered, false otherwise.
     */
    public static boolean authenticateUser(String mail, String password) {
        for (int i = 0; i < customers.size(); i++) {
            if((Objects.equals(customers.get(i).getMail(), mail))&&(Objects.equals(customers.get(i).getPassword(), password))){
                return true;
            }
        }
        return false;
    }

    /**
     * writes user data back into the file.
     * @param name customer name .
     * @param pass customer password.
     * @param mail customer email.
     * @param number customer number.
     * @param address customer address.
     * @throws IOException
     */
    public static void writeData(String name, String pass, String mail, String number, String address) throws IOException {
        Customer customer = new Customer(name, pass, mail, number, address);
        customers.add(customer);
        updateUserDatabase();
    }

    /**
     * searches for a customer in the database.
     * @param mail customer email.
     * @return true if found , false otherwise.
     */
    public static boolean find(String mail) {
        for (Customer customer : customers)
            if (Objects.equals(customer.getMail(), mail))
                return true;
        return false;
    }

    /**
     * updates the password after changing it.
     * @param mail customer email.
     * @param pass customer password.
     * @throws IOException
     */
    public static void updatePassword(String mail, String pass) throws IOException {
        for (Customer customer : customers)
            if (Objects.equals(customer.getMail(), mail)) {
                customer.setPassword(pass);
                updateUserDatabase();
                return;
            }
    }

    /**
     * updates the user info stored after editing it.
     * @throws IOException
     */
    public static void updateUserDatabase() throws IOException {
        if(customers==null){
            loadUsers();
        }
        String fileName = "users.txt";
        try (FileWriter fileWriter = new FileWriter(new File(fileName), false)) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < customers.size(); i++) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))) {
                writer.write(customers.get(i).getUsername() + ',');
                writer.newLine();
                writer.write(customers.get(i).getPassword() + ',');
                writer.newLine();
                writer.write(customers.get(i).getMail() + ',');
                writer.newLine();
                writer.write(customers.get(i).getPhoneNum() + ',');
                writer.newLine();
                writer.write(customers.get(i).getAddress() + ',');
                writer.newLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * gets the customer
     * @param mail customer email.
     * @return customer .
     */
    public Customer getCustomer(String mail){
        Customer customer = null;
        for (int i = 0; i < customers.size(); i++) {
            if(Objects.equals(customers.get(i).getMail(), mail)){
                customer = customers.get(i);
            }
        }
        return customer;
    }
}
