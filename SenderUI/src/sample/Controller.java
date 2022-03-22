package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {

    @FXML
    Label label;
    @FXML
    TextField ipbox;
    @FXML
    TextArea chatwindow;
    @FXML
    TextArea msgbox;
    @FXML
    Button sendButton;
    Sender sender;

    @FXML
    public void change(){
        try {
            System.out.println(ipbox.getText());
            String[] parts = ipbox.getText().split(":");
            String ip = parts[0];
            int PORT = Integer.parseInt(parts[1]);
            sender = new Sender();
            sender.ip = ip;
            sender.PORT = PORT;
            sender.chatwindow = chatwindow;
            sender.msgbox = msgbox;
            sender.sendButton = sendButton;
            sender.start();
            //chatwindow.setText("connecting to " + ip  + ":"+ parts[1]);
            //chatwindow.setText(msgbox.getText() + "\n connection made");


        }catch(Exception err){
            System.out.println("error");
        }
        //trun on reciever
    }
    @FXML
    public void sendMsg(){
        String msgToSend = msgbox.getText();
        if(msgToSend.length() > 0) {
            new Thread(() -> {
                sender.sendData(msgToSend);
                msgbox.clear();

            }).start();
        }
    }
}
