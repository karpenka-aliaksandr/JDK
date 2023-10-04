package hw01;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;



public class ClientWindow extends JFrame{
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 550;

    private JPanel panBottom, panTop;
    private JButton btnLogin, btnSend;
    private JTextArea taLog;
    private JTextField tfAddress, tfPort, tfLogin, tfMessage;
    private JLabel lblNothing;
    private JPasswordField pfPassword;
    private JScrollPane sp;
    private boolean isConnectToServer;

    public static void main(String[] args) {
        new ClientWindow();
    }

    private ClientWindow(){
        isConnectToServer = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(WINDOW_POSX, WINDOW_POSY, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Chat client");
        setAlwaysOnTop(true);
        add(getPanelTop(), BorderLayout.NORTH);
        add(getPanelBottom(), BorderLayout.SOUTH);
        taLog = new JTextArea("Введите данные и подключитесь к серверу.\n");
        taLog.setEditable(false);
        taLog.setLineWrap(true);
        taLog.setWrapStyleWord(true);
        sp = new JScrollPane(taLog, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(sp);
        addListeners();
        setVisible(true);


    }
    private void addListeners() {
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
       
        tfMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
            
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isConnectToServer) {
                    taLog.append("Вы уже подключены к серверу.\n");
                } else {
                    isConnectToServer = true;
                    taLog.append("Подключение установлено.\n");
                }
            }
        });

        taLog.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                sp.getVerticalScrollBar().setValue(sp.getVerticalScrollBar().getMaximum());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
         
        });
        


    }

    private JComponent getPanelTop() {
        panTop = new JPanel(new GridLayout(2,3));
        tfAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("9999");
        lblNothing = new JLabel();
        tfLogin = new JTextField("login");
        pfPassword = new JPasswordField();
        btnLogin = new JButton("login");
        panTop.add(tfAddress);
        panTop.add(tfPort);
        panTop.add(lblNothing);
        panTop.add(tfLogin);
        panTop.add(pfPassword);
        panTop.add(btnLogin);
        return panTop;
    }

    private JComponent getPanelBottom() {
        panBottom = new JPanel(new BorderLayout());
        tfMessage = new JTextField();
        btnSend = new JButton("Send");
        panBottom.add(btnSend, BorderLayout.EAST);
        panBottom.add(tfMessage);
        return panBottom;
    }
    private void sendMessage(){
        if (isConnectToServer) {
            String message = tfMessage.getText();
            if (message.isEmpty()) {    
                taLog.append("! Нет данных для отправки !\n");
            } else {
                taLog.append(message+"\n");
                tfMessage.setText("");
            }
        } else {
            taLog.append("! Нет подключения к серверу, отправить сообщение невозможно !\n");
        }
    }


    

}
