package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Scanner;

public class Reciever extends Thread {
    ServerSocket serverSocket;
    Socket socket;
    int PORT =3333;
    private DataOutputStream _stream_out;
    @FXML
    TextArea chatwindow;
    @FXML
    TextArea msgbox;
    @FXML
    Button sendButton;
    BufferedReader reader;
    BufferedReader Kreader;
    public Boolean sendData(String _msg){

        try {


            _stream_out.writeUTF(_msg);
            chatwindow.setText(chatwindow.getText() + "\nme> " + _msg);
            return true;
        }catch (Exception err){
            return false;
        }
        }
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("listening on " + Integer.toString(PORT) + "...");
            socket = serverSocket.accept();

            System.out.println("established");
            chatwindow.setText("connnection established");
            sendButton.setDisable(false);
            DataInputStream _stream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
             _stream_out = new DataOutputStream(socket.getOutputStream());
            new Thread(()->{
                try {
                    while(true) {
                        System.out.print("msg > ");
                        String data = scanner.nextLine();
                        _stream_out.writeUTF(data);
                        chatwindow.setText(chatwindow.getText() + "\nme> " + data);
                    }
                    }catch(Exception err){
                    System.out.println("no");
                }
            }).start();
//
            new Thread(()-> {
        try {
            while (true) {
                String data = _stream.readUTF();
                System.out.println(data);
                chatwindow.setText(chatwindow.getText() + "\nfriend>" + data);
            }
        }catch (Exception err){
            System.out.println(err.getMessage());
        }
        }).start();
                //              Kreader = new BufferedReader(new InputStreamReader(System.in));
//      String str;
//           do{
//               str = reader.readLine();
//                System.out.println(str);
//           }while(str != null);


        }catch (Exception err){

        }

    }
}
