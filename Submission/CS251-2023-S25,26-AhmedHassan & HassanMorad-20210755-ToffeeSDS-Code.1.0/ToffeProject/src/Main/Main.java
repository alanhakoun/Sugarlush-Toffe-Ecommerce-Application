package src.Main;
import src.GUIManagement.GUI;

import javax.mail.MessagingException;
import java.io.IOException;

public class Main {
    /**
     * @param args
     * @throws IOException
     * @throws MessagingException
     */
    public static void main(String[] args) throws IOException, MessagingException {
        GUI viewer = new GUI();
        viewer.view();
    }
}
