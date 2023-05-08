import UsersManagement.SystemApp;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        // registration and logging in to be put in GUI class after testing
        SystemApp app = new SystemApp();
        while(true) {
            System.out.println("Welcome to our TOFFEE shop!\n" +
                    "Enter the number of the chosen option:\n" +
                    "1- Sign up.\n" +
                    "2- Log in.");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            if (choice == 1) {
                System.out.println("Enter your name:");
                String name = in.next();
                System.out.println("Enter your email:");
                String email = in.next();
                System.out.println("Enter your phone number: ");
                String phone = in.next();
                System.out.println("Enter your password \n" +
                        "(including at least 1 uppercase 1 lowercase 1 digit, and at least 6 characters):");
                String password = in.next();
                if (app.signup(name, email, password, phone))
                    System.out.println("Successful Sign up!");
                else
                    System.out.println("Failed to Sign up!\n" +
                            "Check your email or password validity.\n");
            }
            else if(choice == 2)
            {
                System.out.println("Enter your email:");
                String email = in.next();
                System.out.println("Enter your password: ");
                String password = in.next();
                if (app.login(email, password))
                {
                    System.out.println("Successful Log in!\n");
                    break;
                }
                else
                    System.out.println("Failed to Log in!\n" +
                            "Check your email or password validity.\n");
            }
            else
                System.out.println("Wrong Choice!\n");

        }
    }
}