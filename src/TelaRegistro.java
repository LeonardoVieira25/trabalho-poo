package src;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import src.components.GameObject;
import src.utils.XmlLoader;

public class TelaRegistro extends JPanel {
    public TelaRegistro(){
        JFrame frame = new JFrame("Registrar");

        // frame.setPreferredSize(new Dimension(400, 400));
        frame.setSize(400, 400);
        setSize(400, 400);
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setLayout(null);


        JLabel registerUserText = new JLabel("Username");
        registerUserText.setBounds(150,100,100,20);
        JTextField registerUsername = new JTextField(20);
        registerUsername.setBounds(150, 120, 100, 20);
        

        JLabel registerPasswordText = new JLabel("Password");
        registerPasswordText.setBounds(150, 140, 100, 20);
        JTextField registerPassword = new JTextField(20);
        registerPassword.setBounds(150, 160, 100, 20);

        JLabel confirmPasswordText = new JLabel("Confirmar senha");
        confirmPasswordText.setBounds(150, 180, 120, 20);
        JTextField confirmPassword = new JTextField(20);
        confirmPassword.setBounds(150, 200, 100, 20);

        JButton criarConta = new JButton("Registrar");
        criarConta.setBounds(150, 240, 100, 20);
        criarConta.addActionListener((event) -> {

            XmlLoader.registerPlayer(registerUsername.getText(), registerPassword.getText());
            
            new MenuInicial();
            frame.dispose();
        });

        frame.add(registerUserText);
        frame.add(registerUsername);
        frame.add(registerPasswordText);
        frame.add(registerPassword);
        frame.add(confirmPasswordText);
        frame.add(confirmPassword);
        frame.add(criarConta);

        frame.add(this);
        // frame.pack();
        frame.setVisible(true);
        setVisible(true);

    }
    
}
