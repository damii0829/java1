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

public class MenuTestProject extends JFrame {

    JTextArea textArea = new JTextArea();
    JFileChooser fileChooser = new JFileChooser();

    File currentFile;

    String[] iconFileNames = {
            "new", "open", "close", "white", "blue", "magenta"
    };

    Color[] colorNames = {
            Color.WHITE, Color.BLUE, Color.MAGENTA
    };

    JButton[] btns = new JButton[6];

    public MenuTestProject() {
        JScrollPane scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
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
            } else {
                btns[i].setBackground(colorNames[i - 3]);
                btns[i].setOpaque(true);
                btns[i].setContentAreaFilled(true);
            }

            btns[i].setToolTipText(iconFileNames[i]);

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

        String[] fileItems = {"New", "Open", "Save", "Close"};

        for (String name : fileItems) {
            JMenuItem menuItem = new JMenuItem(name);
            menuItem.addActionListener(fileListener);
            fileMenu.add(menuItem);
        }

        String[] colorItems = {"White", "Blue", "Magenta"};

        for (String name : colorItems) {
            JMenuItem menuItem = new JMenuItem(name);
            menuItem.addActionListener(backgroundListener);
            editMenu.add(menuItem);
        }

        SetFrame.setFrame(this, "간단한 메모장", 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private ImageIcon loadIcon(String name) {
        URL imageUrl = MenuTestProject.class.getResource(
                "/toolbar/" + name + ".png"
        );

        ImageIcon icon;

        if (imageUrl != null) {
            icon = new ImageIcon(imageUrl);
        } else {
            File imageFile = new File(
                    "src/toolbar/" + name + ".png"
            );

            if (!imageFile.isFile()) {
                imageFile = new File(
                        "toolbar/" + name + ".png"
                );
            }

            if (!imageFile.isFile()) {
                return null;
            }

            icon = new ImageIcon(imageFile.getAbsolutePath());
        }

        if (icon.getIconWidth() <= 0 || icon.getIconHeight() <= 0) {
            return null;
        }

        Image image = icon.getImage().getScaledInstance(
                24, 24, Image.SCALE_SMOOTH
        );

        return new ImageIcon(image);
    }

    ActionListener btnListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();

            if (button == btns[0]) {
                newFile();
            } else if (button == btns[1]) {
                openFile();
            } else if (button == btns[2]) {
                System.exit(0);
            }

            for (int i = 0; i < colorNames.length; i++) {
                if (button == btns[i + 3]) {
                    textArea.setBackground(colorNames[i]);
                    break;
                }
            }
        }
    };

    ActionListener fileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "New":
                    newFile();
                    break;

                case "Open":
                    openFile();
                    break;

                case "Save":
                    saveFile();
                    break;

                case "Close":
                    System.exit(0);
                    break;
            }
        }
    };

    ActionListener backgroundListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "White":
                    textArea.setBackground(Color.WHITE);
                    break;

                case "Blue":
                    textArea.setBackground(Color.BLUE);
                    break;

                case "Magenta":
                    textArea.setBackground(Color.MAGENTA);
                    break;
            }
        }
    };

    private void newFile() {
        textArea.setText("");
        currentFile = null;
    }

    private void openFile() {
        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();

        try {
            byte[] bytes = Files.readAllBytes(selectedFile.toPath());
            textArea.setText(new String(bytes, StandardCharsets.UTF_8));
            textArea.setCaretPosition(0);

            currentFile = selectedFile;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "파일을 열 수 없습니다.\n" + ex.getMessage(),
                    "열기 오류",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void saveFile() {
        File targetFile = currentFile;

        if (targetFile == null) {
            fileChooser.setSelectedFile(new File("memo.txt"));

            int result = fileChooser.showSaveDialog(this);

            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }

            targetFile = fileChooser.getSelectedFile();

            if (targetFile.exists()) {
                int answer = JOptionPane.showConfirmDialog(
                        this,
                        "이미 존재하는 파일입니다. 덮어쓰시겠습니까?",
                        "덮어쓰기 확인",
                        JOptionPane.YES_NO_OPTION
                );

                if (answer != JOptionPane.YES_OPTION) {
                    return;
                }
            }
        }

        try {
            Files.write(
                    targetFile.toPath(),
                    textArea.getText().getBytes(StandardCharsets.UTF_8)
            );

            currentFile = targetFile;

            JOptionPane.showMessageDialog(
                    this,
                    "파일을 저장했습니다."
            );
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "파일을 저장할 수 없습니다.\n" + ex.getMessage(),
                    "저장 오류",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuTestProject());
    }
}