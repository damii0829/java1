package ai1013;

import center.CenterFrame2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlowLayoutTest extends JFrame {
    public FlowLayoutTest() {
        setTitle("FlowLayout 연습");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));

        ArrayList<JButton> list = new ArrayList<JButton>(5);

        Color[] colors = {
                Color.PINK,
                Color.YELLOW,
                Color.CYAN,
                Color.ORANGE,
                Color.GREEN
        };

        for (int i = 0; i < 5; i++) {
            JButton btn = new JButton("버튼" + (i + 1));
            list.add(btn);
            add(btn);

            Color originalColor = btn.getBackground();
            Color changeColor = colors[i];

            btn.addActionListener(e -> {
                if (btn.getBackground().equals(changeColor)) {
                    btn.setBackground(originalColor);
                } else {
                    btn.setBackground(changeColor);
                }
            });
        }

        int w = 250, h = 250;
        Point point = CenterFrame2.getLocation(w, h);
        setBounds(point.x, point.y, w, h);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlowLayoutTest();
    }
}
