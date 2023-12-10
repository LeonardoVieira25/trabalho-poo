package src;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;

import src.components.GameObject;
import src.maps.Map0;
import src.maps.Map1;
import src.maps.Map2;
import src.maps.Maps;

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
    // Consumer<JFrame> jogar
    ) {
        System.out.println(screenSize.width + "x" + screenSize.height);

        frame = new JFrame("Janela");
        frame.setPreferredSize(new Dimension(400, 400));

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        setLayout(null);

        JLabel userText = new JLabel("Username");
        userText.setBounds(150, 100, 100, 20);
        JTextField username = new JTextField(20);
        username.setBounds(150, 120, 100, 20);

        JLabel passText = new JLabel("Password");
        passText.setBounds(150, 140, 100, 20);
        JTextField password = new JTextField(20);
        password.setBounds(150, 160, 100, 20);

        JButton login = new JButton("Login");
        login.setBounds(150, 200, 100, 20);

        login.addActionListener((e) -> {
            List<Maps> maps = new java.util.ArrayList<Maps>();
            MenuInicial.username = username.getText();
            MenuInicial.password = password.getText();
            if(MenuInicial.username.equals("") || MenuInicial.password.equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                return;
            };
            if(MenuInicial.username.equals("admin") && MenuInicial.password.equals("admin")){
                new TelaEditNiveis();
                frame.dispose();
                return;
            }
            try {
                maps.add(new Map0());
                maps.add(new Map1());
                maps.add(new Map2());
                Janela janela = new Janela();
                new Scene(janela, maps);
                frame.dispose();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos");
            }

        });

        JButton register = new JButton("Registrar");
        register.setBounds(150, 240, 100, 20);
        register.addActionListener((e) -> {
            new TelaRegistro();
            frame.dispose();
        });

        add(userText);
        add(passText);
        add(username);
        add(password);
        add(login);
        add(register);

        frame.setVisible(true);
        setVisible(true);
        frame.pack();
        frame.add(this);
    }
}
