package ai1103;

import center.SetFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.swing.*;

public class MenuTest extends JFrame {
    JTextArea textArea = new JTextArea();
    JFileChooser fileChooser = new JFileChooser();
    JFrame frame;
    String[] iconFileNames = {"new", "open", "close", "white", "blue", "magenta"};
    Color[] colorNames = {Color.WHITE, Color.BLUE, Color.MAGENTA};
    JButton[] btns = new JButton[6];

    public MenuTest() {
        frame = this;
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        JToolBar toolBar = new JToolBar();
        add("North", toolBar);

        for (int i = 0; i < btns.length; i++) {
            btns[i] = new JButton();

            if (i < 3) {
                ImageIcon icon = loadIcon(iconFileNames[i]);

                if (icon != null) {
                    btns[i].setIcon(icon);
                } else {
                    btns[i].setText(iconFileNames[i]);
                }

                btns[i].setToolTipText(iconFileNames[i]);
            } else {
                btns[i].setBackground(colorNames[i - 3]);
                btns[i].setOpaque(true);
                btns[i].setContentAreaFilled(true);
            }

            Dimension size = new Dimension(32, 32);
            btns[i].setPreferredSize(size);
            btns[i].setMinimumSize(size);
            btns[i].setMaximumSize(size);
            btns[i].setMargin(new Insets(0, 0, 0, 0));
            btns[i].setFocusable(false);

            btns[i].addActionListener(btnListener);
            toolBar.add(btns[i]);
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem closeMenuItem = new JMenuItem("Close");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(closeMenuItem);

        newMenuItem.addActionListener(fileListener);
        openMenuItem.addActionListener(fileListener);
        closeMenuItem.addActionListener(fileListener);

        JMenuItem whiteMenuItem = new JMenuItem("White");
        JMenuItem blueMenuItem = new JMenuItem("Blue");
        JMenuItem magentaMenuItem = new JMenuItem("Magenta");

        editMenu.add(whiteMenuItem);
        editMenu.add(blueMenuItem);
        editMenu.add(magentaMenuItem);

        whiteMenuItem.addActionListener(backgroundListener);
        blueMenuItem.addActionListener(backgroundListener);
        magentaMenuItem.addActionListener(backgroundListener);

        SetFrame.setFrame(this, "메뉴만들기", 500, 500);
    }

    // 이미지 불러오기
    private ImageIcon loadIcon(String name) {
        // src/toolbar의 이미지가 실행 경로에 포함된 경우
        URL imageUrl = MenuTest.class.getResource(
                "/toolbar/" + name + ".png"
        );

        ImageIcon icon;

        if (imageUrl != null) {
            icon = new ImageIcon(imageUrl);
        } else {
            // 프로젝트 폴더를 기준으로 직접 불러오기
            File imageFile = new File(
                    "src/toolbar/" + name + ".png"
            );

            if (!imageFile.isFile()) {
                imageFile = new File(
                        "toolbar/" + name + ".png"
                );
            }

            if (!imageFile.isFile()) {
                System.err.println(
                        "이미지 파일을 찾을 수 없습니다: " + name + ".png"
                );
                return null;
            }

            icon = new ImageIcon(imageFile.getAbsolutePath());
        }

        if (icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            System.err.println(
                    "이미지를 읽을 수 없습니다: " + name + ".png"
            );
            return null;
        }

        Image image = icon.getImage().getScaledInstance(
                24, 24, Image.SCALE_SMOOTH
        );

        return new ImageIcon(image);
    }

    // 툴바 버튼 이벤트 처리
    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton eBtn = (JButton) e.getSource();

            if (eBtn == btns[0]) {
                textArea.setText("");
            } else if (eBtn == btns[1]) {
                openFile();
            } else if (eBtn == btns[2]) {
                System.exit(0);
            }

            for (int i = 0; i < colorNames.length; i++) {
                if (eBtn == btns[i + 3]) {
                    textArea.setBackground(colorNames[i]);
                    break;
                }
            }
        }
    };

    // File 메뉴 이벤트 처리
    ActionListener fileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "New":
                    textArea.setText("");
                    break;

                case "Open":
                    openFile();
                    break;

                case "Close":
                    System.exit(0);
                    break;
            }
        }
    };

    // Edit 메뉴 이벤트 처리
    ActionListener backgroundListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "White":
                    textArea.setBackground(colorNames[0]);
                    break;

                case "Blue":
                    textArea.setBackground(colorNames[1]);
                    break;

                case "Magenta":
                    textArea.setBackground(colorNames[2]);
                    break;
            }
        }
    };

    // 파일 선택 후 내용을 텍스트 영역에 표시
    private void openFile() {
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                textArea.setText(readFile(selectedFile));
                textArea.setCaretPosition(0);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // UTF-8 파일 읽기
    private String readFile(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuTest());
    }
}