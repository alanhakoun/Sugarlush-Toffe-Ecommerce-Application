package src;
import src.GUIManagement.GUI;
import src.ShoppingManagement.ProudctsCatalog;

import javax.mail.MessagingException;
import java.io.IOException;
/*import UsersManagement.UsersDatabase;
import UsersManagement.SystemApp;
import javax.xml.catalog.Catalog;
import java.util.Scanner;
import java.util.stream.Stream;*/


public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        GUI viewer = new GUI();
        viewer.view();
    }
}