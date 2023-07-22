package src.GUIManagement;

import src.ShoppingManagement.Product;
import src.UsersManagement.SystemApp;
import src.UsersManagement.Customer;
import src.UsersManagement.UsersDatabase;
import src.ShoppingManagement.ProudctsCatalog;
import src.ShoppingManagement.controller;

import javax.mail.MessagingException;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Illustrates the class that deals with the user interface of a customer and connects to application features. It has a direct relationship with the system app, controller and catalog.
 */
public class GUI {
    private static UsersDatabase usersDatabase;
    private static ProudctsCatalog catalog;
    private static Customer currentCustomer = null;
    private static controller controller;
    SystemApp app = new SystemApp();
    Scanner in = new Scanner(System.in);

    /**
     * display the whole catalog.
     */
    public static void displayAll() {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product : ProudctsCatalog.getProducts()) {
            System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
            System.out.println("    Description: " + product.getDescription());
        }
    }

    /**
     * Displays the products by category
     * @param category category to search with.
     */
    public static void displayByCategory(String category) {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product :  ProudctsCatalog.getProducts()) {
            if (product.getCategory().equals(category)) {
                System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
                System.out.println("    Description: " + product.getDescription());
            }
        }
    }

    /**
     * searches for a product by its name in the catalog
     * @param name name to search with.
     * @return product with the specific name given.
     */
    public static Product searchByName(String name) {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product :  ProudctsCatalog.getProducts()) {
            if (Objects.equals(product.getName(), name)) {
                System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
                System.out.println("    Description: " + product.getDescription());
                return product;
            }
        }
        return null;
    }

    /**
     * searches for a product by its name in the catalog
     * @param brand brand to search with.
     */
    public static void searchByBrand(String brand) {
        System.out.println("ID - Name - Quantity - Price - Category - Brand\n");
        for (Product product :  ProudctsCatalog.getProducts()) {
            if (Objects.equals(product.getBrand(), brand)) {
                System.out.println(product.getId() + " - " + product.getName() + " - " + product.getQuantityInStock() + " - " + product.getPrice() + " - " + product.getCategory() + " - " + product.getBrand());
                System.out.println("    Description: " + product.getDescription());
            }
        }
    }

    /**
     * This is the page the general customer would view : he could Sign up , login , view catalog or just Exit the program.
     * @throws IOException
     * @throws MessagingException
     */
    public void generalPage() throws IOException, MessagingException {
        while (true) {
            System.out.println("Welcome to our TOFFEE shop!\n" +
                    "Enter the number of the chosen option:\n" +
                    "1- Sign up.\n" +
                    "2- Log in.\n" +
                    "3- View catalog.\n" +
                    "4- Exit.\n");

            int choice = in.nextInt();
            if (choice == 1) {
                System.out.println("Enter your name:");
                String name = in.next();
                System.out.println("Enter your email:");
                String email = in.next();
                System.out.println("Enter your address:");
                String address = in.next();
                System.out.println("Enter your phone number: ");
                String phone = in.next();
                System.out.println("Enter your password \n" +
                        "(including at least 1 uppercase 1 lowercase 1 digit, and at least 6 characters):");
                String password = in.next();
                app.sendOTP(email);
                System.out.println("Check your email, and enter the OTP:");
                int otp = in.nextInt();
                if(app.OTPValidity(otp))
                {
                    if (app.signup(name, password, email, phone, address))
                        System.out.println("Successful Sign up!");
                    else
                        System.out.println("Failed to Sign up!\n" +
                                "Check your email or password validity.\n");
                }
                else
                    System.out.println("Verification Failed!\n");


            } else if (choice == 2) {
                System.out.println("Enter your email:");
                String email = in.next();
                System.out.println("Enter your password: \n" +
                        "If you forgot your password, enter 'f' letter: ");
                String password = in.next();


                if (password.equals("f")) {
                    if (app.forgetPassword(email)) {
                        System.out.println("Check your email, and enter the OTP:");
                        int otp = in.nextInt();
                        if (app.OTPValidity(otp)) {
                            System.out.println("Successful verification!\n" +
                                    "Enter the new password: ");
                            String newPassword = in.next();
                            app.changePassword(email, newPassword);
                        } else {
                            System.out.println("Verification Failed!\n");
                        }
                    } else {
                        System.out.println("You don't have account, Please sign up!");
                    }

                } else {
                    if (app.login(email, password)) {
                        System.out.println("Successful Log in!\n");
                        currentCustomer = usersDatabase.getCustomer(email);
                        break;
                    } else {
                        System.out.println("Wrong mail/password!\n");
                    }
                }
            } else if (choice == 3) {
                System.out.println("How do you want to view?\n" +
                        "Enter the number of the chosen option:\n" +
                        "1- Display all.\n" +
                        "2- Display by category.\n" +
                        "3- Search by name.\n" +
                        "4- Search by brand.\n");
                int choose = in.nextInt();
                if (choose == 1) {
                    displayAll();
                } else if (choose == 2) {
                    System.out.println("Enter Category:\n");
                    String category = in.next();
                    displayByCategory(category);
                } else if (choose == 3) {
                    System.out.println("Enter product name:\n");
                    String name = in.next();
                   searchByName(name);
                } else if(choose == 4){
                    System.out.println("Enter product brand:\n");
                    String brand = in.next();
                    searchByBrand(brand);
                }else{
                    System.out.println("Wrong choice\n");
                }
            } else if (choice == 4) {
                System.out.println("See you soon!\n");
                System.exit(0);
            } else {
                System.out.println("Wrong Choice!\n");
            }
        }
    }


    /**
     * This is adding to cart functions that add the product and its quantity after the customer is logged in
     * @param id product ID to add into cart .
     */
    public void addToCart(int id) {
        while (true) {
            System.out.println("Enter item quantity:\n");
            int qty = in.nextInt();
            int result = controller.addItem(ProudctsCatalog.getProducts().get((id - 1)).getName(), qty);
            if (result == 0) {
                System.out.println("Item added.\n");
                return;
            } else if (result == -1) {
                System.out.println("You cant add more than 50.\n");
            } else if (result == -2) {
                System.out.println("Not enough quantity in stock. only:" + ("" + (ProudctsCatalog.getProducts().get((id - 1)).getQuantityInStock())));
            } else if (result == -3) {
                System.out.println("Cant find item in catalog.\n");
            }
            System.out.println("Press 0 to cancel adding or any to continue:\n");
            int temp = in.nextInt();
            if (temp == 0) {
                return;
            }
        }
    }

    /**
     * As the customer is logged in he can view the following options and traverse through the application : Display catalog or cart , Loging out or exiting the program
     * @return
     */
    public int loggedinPage() {
        while (true) {
            System.out.println("What do you want to do?\n" +
                    "Enter the number of the chosen option:\n" +
                    "1- Display catalog.\n" +
                    "2- Display cart.\n" +
                    "3- Log out.\n" +
                    "4- Exit.\n");
            int choice = in.nextInt();


            if (choice == 1) {
                while (true) {
                    System.out.println("How do you want to view?\n" +
                            "Enter the number of the chosen option:\n" +
                            "1- Display all.\n" +
                            "2- Display by category.\n" +
                            "3- Search by name.\n" +
                            "4- Search by brand.\n" +
                            "5- Go back.\n");
                    int choose = in.nextInt();


                    if (choose == 1) {
                        displayAll();
                        while (true) {
                            System.out.println("Enter item ID that you want to add to cart or 0 to continue:\n");
                            int id = in.nextInt();
                            if (id != 0) {
                                addToCart(id);
                            } else {
                                break;
                            }
                        }


                    } else if (choose == 2) {
                        System.out.println("Enter Category:\n");
                        String category = in.next();
                        displayByCategory(category);
                        System.out.println("Enter item ID that you want to add to cart or 0 to continue:\n");
                        int id = in.nextInt();
                        if (id != 0) {
                            addToCart(id);
                        } else {
                            break;
                        }


                    } else if (choose == 3) {
                        System.out.println("Enter product name:\n");
                        String name = in.next();
                        searchByName(name);
                        System.out.println("Enter item ID that you want to add to cart or 0 to continue:\n");
                        int id = in.nextInt();
                        if (id != 0) {
                            addToCart(id);
                        } else {
                            break;
                        }


                    } else if(choose == 4){
                        System.out.println("Enter product brand:\n");
                        String brand = in.next();
                        searchByBrand(brand);
                        System.out.println("Enter item ID that you want to add to cart or 0 to continue:\n");
                        int id = in.nextInt();
                        if (id != 0) {
                            addToCart(id);
                        } else {
                            break;
                        }
                    }else if (choose == 5) {
                        break;


                    } else {
                        System.out.println("Wrong choice.\n" +
                                "Press 0 to go back or any to continue displaying cart:\n");
                        int temp = in.nextInt();
                        if (temp == 0) {
                            break;
                        }
                    }

                }


            } else if (choice == 2) {
                if (controller.getProducts() != null) {
                    for (int i = 0; i < controller.getProducts().size(); i++) {
                        System.out.println(controller.getProducts().get(i).getProduct().getName() + " - " + controller.getProducts().get(i).getQuantity() + " - " + controller.getProducts().get(i).getTotalPrice());
                    }

                    while (true) {
                        System.out.println("Choose what to do next:\n" +
                                "1- Place Order.\n" +
                                "2- Update ordered item quantity.\n" +
                                "3- Remove ordered item.\n" +
                                "4- Go back.\n");
                        int choose = in.nextInt();

                        if (choose == 1) {
                            if(controller.getProducts()==null){
                                System.out.println("Your cart is empty, cant place order.\n");
                            } else {
                                String address = currentCustomer.getAddress();
                                while (true) {
                                    System.out.println("Choose shipping address: \n" +
                                            "1- Your current address.\n" +
                                            "2- New address.\n");
                                    int aChoice = in.nextInt();
                                    if (aChoice == 1) {
                                        address = currentCustomer.getAddress();
                                        break;
                                    } else if (aChoice == 2) {
                                        System.out.println("Enter New shipping address: \n");
                                        address = in.next();
                                        break;
                                    } else {
                                        System.out.println("Wrong choice.\n" +
                                                "Press 0 to go back or any to re-specify address:\n");
                                        int temp = in.nextInt();
                                        if (temp == 0) {
                                            break;
                                        }
                                    }
                                }
                                System.out.println("Your order has been placed.\n");
                                return controller.placeOrder(address);
                            }



                        } else if (choose == 2) {
                            if (controller.getProducts() != null) {

                                while (true) {
                                    System.out.println("Enter item name:\n");
                                    String name = in.next();
                                    System.out.println("Enter new quantity:\n");
                                    int qty = in.nextInt();
                                    int result = controller.updateOrderQty(name, qty);
                                    if (result == 0) {
                                        System.out.println("Item quantity updated.\n");
                                        break;
                                    } else if (result == -1) {
                                        System.out.println("You cant add more than 50.\n");
                                    } else if (result == -2) {
                                        System.out.println("Not enough quantity in stock. only: " + ("" + (ProudctsCatalog.search(name).getQuantityInStock())));
                                    } else if (result == -3) {
                                        System.out.println("Cant find item in ordered items.\n");
                                    }
                                    System.out.println("Press 0 to cancel updating quantity or any to continue:\n");
                                    int temp = in.nextInt();
                                    if (temp == 0) {
                                        break;
                                    }

                                }
                            } else {
                                System.out.println("There are no items in your cart\n");
                            }


                        } else if (choose == 3) {
                            while (true) {
                                System.out.println("Enter item name:\n");
                                String name = in.next();
                                if (controller.removeItem(name)) {
                                    System.out.println("Item removed.\n");
                                    break;
                                } else {
                                    System.out.println("Cant find item.\n");
                                }
                                System.out.println("Press 0 to cancel removing item or any to continue:\n");
                                int temp = in.nextInt();
                                if (temp == 0) {
                                    break;
                                }
                            }


                        } else if (choose == 4) {
                            break;


                        } else {
                            System.out.println("Wrong choice.\n" +
                                    "Press 0 to go back or any to continue displaying cart:\n");
                            int temp = in.nextInt();
                            if (temp == 0) {
                                break;
                            }
                        }
                    }


                } else {
                    System.out.println("Cart is empty!\n");
                }


            } else if (choice == 3) {
                System.out.println("Logged out of " + currentCustomer.getUsername());
                currentCustomer = null;
                return 0;

            } else if (choice == 4) {
                System.out.println("See you soon!\n");
                System.exit(0);
            }
        }
    }

    /**
     * This is the order page , after placing the order, the customer could pay, cancel his order.
     * @param id Order ID to perform actions on it.
     */
    public void orderPage(int id) {
        while (true) {
            System.out.println("Choose what to do:\n" +
                    "1- Pay Order.\n" +
                    "2- Cancel ordered.\n" +
                    "3- Go back.\n");
            int choice = in.nextInt();
            // Please un comment this part of the code to test sending SMS OTP if you have a verified Towillio number.
            // else you won't be able to test this part.
            if (choice == 1) {
                /*if (controller.pay((currentCustomer.getPhoneNum()), id)) {
                    System.out.println("Your number has been verified.\n");*/
                    System.out.println("Your order is being shipped to: " + controller.getOrders().get(0).getAddress() + "\nOrder status: " + controller.getOrders().get(0).getOrderStatus());
                /*} else {
                    System.out.println("Failed to verify your number.");
                }*/

            } else if (choice == 2) {
                controller.cancelOrder(id);
                System.out.println("Your order has been canceled.");
            } else if (choice == 3) {
                return;
            } else {
                System.out.println("Wrong choice.\n" +
                        "Press 0 to go back or any to continue finishing your order:\n");
                int temp = in.nextInt();
                if (temp == 0) {
                    return;
                }
            }
        }
    }

    /**
     * This is the main funcyion that runs teh GUI functions of several pages .
     * @throws IOException
     * @throws MessagingException
     */
    public void view() throws IOException, MessagingException {
        usersDatabase = new UsersDatabase();
        catalog = new ProudctsCatalog();
        // registration and logging in to be put in GUI class after testing
        while (true) {
            if (currentCustomer != null) {

                controller = new controller(catalog, currentCustomer);
                int result = loggedinPage();
                if (result != 0) {
                    orderPage(result);
                }

            } else {
                generalPage();
            }
        }

    }
}
