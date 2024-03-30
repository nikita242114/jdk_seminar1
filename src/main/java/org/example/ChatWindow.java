package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ChatWindow extends JFrame implements Listenerable {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 100;

    private final JPanel panelMain =new JPanel(new GridLayout(3,2));
    private final JTextField ipAdress = new JTextField("127.0.0.1");
    private final JTextField port = new JTextField("8089");
    private final JTextField login = new JTextField("login");
    private final JPasswordField password = new JPasswordField("password");
    private final JButton btnLogin = new JButton("Login");
    private final JButton btnLogout = new JButton("Logout");


    private final JPanel panelButton = new JPanel(new GridLayout(1,2));
    private final JTextArea messege = new JTextArea("Введите сообщение");
    private final JButton send = new JButton("Отправить");
    private final JTextArea textInput = new JTextArea();

    final JTextArea log = new JTextArea();
    Server server;



    ChatWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat");
        setResizable(true);

        panelMain.add(ipAdress);
        panelMain.add(port);
        panelMain.add(login);
        panelMain.add(password);
        panelMain.add(btnLogin);
        panelMain.add(btnLogout);
        add(panelMain,BorderLayout.NORTH);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.login();

            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.logout();
            }
        });

        panelButton.add(messege);
        panelButton.add(send);
        messege.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    server.sendChat(messege.getText());
                }
            }
        });

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.sendChat(messege.getText());
            }
        });
        add(panelButton,BorderLayout.SOUTH);

        log.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(log);
        add(scrollPane);
        setVisible(true);
        server =new Server(this);

    }

    @Override
    public void buttonAction(String str) {
        log.append(str);
    }
}
