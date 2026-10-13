package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;

public class TextFieldAreapasswordTest extends JFrame{
    public TextFieldAreapasswordTest() {
        setTitle("ImageIcon설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextField textField = new JTextField("", 20);
        JTextArea area = new JTextArea("", 5, 20);

        JPasswordField passwordField = new JPasswordField(20);

        add(textField);
        add(area);
        add(passwordField);

        int w=250, h=250;
        int[] locations = CenterFrame.getLocation(w, h);
        setBounds(locations[0], locations[1], w, h);//setLocations(x, y) + setSize(w, h)
        setVisible(true);
    }

    public static void main(String[] args) {
        new TextFieldAreapasswordTest();
    }
}
