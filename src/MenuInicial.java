package src;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;

import src.components.GameObject;

// public class Janela extends JFrame{
public class MenuInicial extends JPanel {
    public List<GameObject> renderingList;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int HEIGHT = (int) screenSize.height;
    public static int WIDTH = (int) screenSize.width;

    private static String username;
    private static String password;
    public static String getUsername() {
        return username;
    }
    public static String getPassword() {
        return password;
    }

    private JFrame frame;
    public JFrame getFrame() {
        return frame;
    }
    // recebe uma função que é chamada quando o botão é clicado
    public MenuInicial(
        Consumer<JFrame> jogar
    ) {
        System.out.println(screenSize.width + "x" + screenSize.height);
        setSize(500,500);

        frame = new JFrame("Janela");
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setSize(WIDTH, HEIGHT);
        // frame.setSize(500,500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        setLayout(null);

        JTextField textField = new JTextField(20);
        textField.setBounds(250, 50, 100, 20);
    
        JButton button = new JButton("Button");
        button.setBounds(120, 50, 100, 20);

        button.addActionListener((e) -> {
            System.out.println("Button pressed");
            jogar.accept(frame);
        });
    

        add(textField);
        add(button);

        frame.setVisible(true);
        setVisible(true);
        frame.pack();
        frame.add(this);
    }
    // public MenuInicial() {
    //     setLayout(new FlowLayout());
    
    //     JTextField textField = new JTextField(20);
    //     JButton button = new JButton("Button");
    //     JCheckBox checkBox = new JCheckBox("Check Me!");
    
    //     add(textField);
    //     add(button);
    //     add(checkBox);
    // }
}
