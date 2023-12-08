package src;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import src.components.GameObject;

// public class Janela extends JFrame{
public class MenuInicial extends JPanel {
    public List<GameObject> renderingList;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int HEIGHT = (int) screenSize.height;
    public static int WIDTH = (int) screenSize.width;

    // recebe uma função que é chamada quando o botão é clicado
    public MenuInicial(
        Runnable jogar
    ) {
        System.out.println(screenSize.width + "x" + screenSize.height);
        
        JFrame frame = new JFrame("Janela");
        frame.setPreferredSize(new Dimension(400, 400));
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        setLayout(null);



        JTextField username = new JTextField(20);
        username.setBounds(150, 120, 100, 20);
        JLabel userText = new JLabel("Username");
        userText.setBounds(150,100,100,20);
        

        JTextField password = new JTextField(20);
        password.setBounds(150, 160, 100, 20);
        JLabel passText = new JLabel("Password");
        passText.setBounds(150,140,100,20);


        JButton login = new JButton("Login");
        login.setBounds(150, 200, 100, 20);

        login.addActionListener((e) -> {
            jogar.run();
            frame.dispose();
        });

        add(userText);
        add(passText);
        add(username);
        add(password);
        add(login);

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
