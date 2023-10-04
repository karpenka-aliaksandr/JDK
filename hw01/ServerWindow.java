package hw01;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ServerWindow extends JFrame{
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_POSX = 500;
    private static final int WINDOW_POSY = 550;

    private static final int PORT = 9999;
    static ExecutorService executeIt = Executors.newFixedThreadPool(50);

    private JPanel panBottom;
    private JButton btnStart, btnStop;
    private JTextArea taLog;
    private JScrollPane sp;
    private boolean isServerWorking;
    private Thread serverThread;

    //private ArrayList ServerSocket server

    public static void main(String[] args) {
        new ServerWindow();
    }
    private ServerWindow(){
        isServerWorking = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(WINDOW_POSX, WINDOW_POSY, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        add(getPanelBottom(), BorderLayout.SOUTH);
        taLog = new JTextArea("Server stopped.\n");
        taLog.setEditable(false);
        taLog.setLineWrap(true);
        taLog.setWrapStyleWord(true);
        sp = new JScrollPane(taLog, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(sp);
        addListeners();
        setVisible(true);


    }
    private void addListeners() {
        //ListenerThread lt = new ListenerThread();
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    try {
                        //lt.disable();  
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }
                    
                    taLog.append("Server stopped.\n");
                } else {
                    taLog.append("The server has already stopped.\n");
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    isServerWorking = true;
                    taLog.append("Server started.\n");
                    // serverThread = new Thread(new MultiThreadServer());
                    // serverThread.start();
                    try {
                        //lt.enable();  
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }
                } else {
                    taLog.append("The server has already started.\n");
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

    private JComponent getPanelBottom() {
        panBottom = new JPanel(new GridLayout(1,2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        panBottom.add(btnStart);
        panBottom.add(btnStop);
        return panBottom;
    }

    // class ListenerThread extends Thread {
    //     private ServerSocket serverSocket;

    //     public void enable() throws IOException
    //     {
    //         serverSocket = new ServerSocket(1337);
    //         start();
    //     }

    //     public void disable() throws IOException
    //     {
    //         serverSocket.close();
    //         interrupt();
    //     }

    //     @Override
    //     public void run()
    //     {
    //         try
    //         {
    //             serverSocket.accept();
    //         }
    //         catch (IOException ex)
    //         {
    //             ex.printStackTrace();
    //         }
    //     }
    // }





    // class MultiThreadServer implements Runnable{    
        
    //     @Override
    //     public void run() {
    //         // стартуем сервер на порту 9999 и инициализируем переменную для обработки консольных команд с самого сервера
    //         try {
    //             ServerSocket server = new ServerSocket(PORT);
    //             taLog.append("Server socket created on port: " + Integer.toString(PORT) + "\n");    
    //             // стартуем цикл при условии что серверный сокет не закрыт
    //             executeIt.execute(new MonoThreadClientHandler(server));
    //             while (isServerWorking) {};
    
    //         // закрытие пула нитей после завершения работы всех нитей
    //             executeIt.shutdown();
    //             server.close();
    //             System.out.println("server stopped");
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    // public class MonoThreadClientHandler implements Runnable {
    //     private ServerSocket server;
    //     private Socket client;
    
    //     public MonoThreadClientHandler(ServerSocket server) {
    //         this.server = server;
    //     }
    
    //     @Override
    //     public void run() {
    
    //         try {
    //             client = server.accept();
    //             executeIt.execute(new MonoThreadClientHandler(server));
    //             // инициируем каналы общения в сокете, для сервера
    
    //             // канал записи в сокет следует инициализировать сначала канал чтения для избежания блокировки выполнения программы на ожидании заголовка в сокете
    //             DataOutputStream out = new DataOutputStream(client.getOutputStream());
    
    // // канал чтения из сокета
    //             DataInputStream in = new DataInputStream(client.getInputStream());
    //             System.out.println("DataInputStream created");
    
    //             System.out.println("DataOutputStream  created");
    //             ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //             // основная рабочая часть //
    //             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //             // начинаем диалог с подключенным клиентом в цикле, пока сокет не
    //             // закрыт клиентом
    //             while (!client.isClosed()) {
    //                 System.out.println("Server reading from channel");
    
    //                 // серверная нить ждёт в канале чтения (inputstream) получения
    //                 // данных клиента после получения данных считывает их
    //                 String entry = in.readUTF();
    
    //                 // и выводит в консоль
    //                 System.out.println("READ from clientDialog message - " + entry);
    
    //                 // инициализация проверки условия продолжения работы с клиентом
    //                 // по этому сокету по кодовому слову - quit в любом регистре
    //                 if (entry.equalsIgnoreCase("quit")) {
    
    //                     // если кодовое слово получено то инициализируется закрытие
    //                     // серверной нити
    //                     System.out.println("Client initialize connections suicide ...");
    //                     out.writeUTF("Server reply - " + entry + " - OK");
    //                     Thread.sleep(3000);
    //                     break;
    //                 }
    
    //                 // если условие окончания работы не верно - продолжаем работу -
    //                 // отправляем эхо обратно клиенту
    
    //                 System.out.println("Server try writing to channel");
    //                 out.writeUTF("Server reply - " + entry + " - OK");
    //                 System.out.println("Server Wrote message to clientDialog.");
    
    //                 // освобождаем буфер сетевых сообщений
    //                 out.flush();
    
    //                 // возвращаемся в началло для считывания нового сообщения
    //             }
    
    //             ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //             // основная рабочая часть //
    //             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //             // если условие выхода - верно выключаем соединения
    //             System.out.println("Client disconnected");
    //             System.out.println("Closing connections & channels.");
    
    //             // закрываем сначала каналы сокета !
    //             in.close();
    //             out.close();
    
    //             // потом закрываем сокет общения с клиентом в нити моносервера
    //             client.close();
    
    //             System.out.println("Closing connections & channels - DONE.");
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         } catch (InterruptedException e) {
    //             // TODO Auto-generated catch block
    //             e.printStackTrace();
    //         }
    //     }
    // }

}
