package src;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import src.components.GameObject;

public class TelaRegistro extends JPanel {
    public TelaRegistro(){
        JFrame registrador = new JFrame("Registrar");
        
        setLayout(null);

        registrador.setPreferredSize(new Dimension(400, 400));
        
        registrador.setResizable(false);
        registrador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrador.setLocationRelativeTo(null);

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
        criarConta.addActionListener((event) -> {
            registrador.dispose();
            
        });

        registrador.add(registerUserText);
        registrador.add(registerUsername);
        registrador.add(registerPasswordText);
        registrador.add(registerPassword);
        registrador.add(confirmPasswordText);
        registrador.add(confirmPassword);

        registrador.setVisible(true);
        setVisible(true);
        registrador.pack();
        registrador.add(this);

    }
    
}
