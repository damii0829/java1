package ai1013;

import center.CenterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxImageTest extends JFrame {
    JLabel lbl = new JLabel();
    public ComboBoxImageTest() {
        setTitle("ImageIcon설정 컴포넌트");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        String[] items = {"강아지", "고양이", "토끼", "미어캣", "코알라"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.addItemListener(comboListener);

        add(comboBox);
        add(lbl);

        lbl.setIcon(new ImageIcon("pets/dog.jpg"));

        int w = 350, h = 350;
        int[] locations = CenterFrame.getLocation(w, h);
        setBounds(locations[0], locations[1], w, h);
        setVisible(true);
    }

    ItemListener comboListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ImageIcon icon = null;
                String selectedItem = (String) e.getItem();

                switch (selectedItem) {
                    case "강아지":
                        icon = new ImageIcon("pets/dog.jpg");
                        break;
                    case "고양이":
                        icon = new ImageIcon("pets/cat.jpg");
                        break;
                    case "토끼":
                        icon = new ImageIcon("pets/rabbit.jpg");
                        break;
                    case "미어캣":
                        icon = new ImageIcon("pets/meerkat.jpg");
                        break;
                    case "코알라":
                        icon = new ImageIcon("pets/koala.jpg");
                        break;
                }

                lbl.setIcon(icon);
            }
        }
    };

    public static void main(String[] args) {
        new ComboBoxImageTest();
    }
}
