package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.scene.control.Button;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Controller {
    @FXML
    Label label;
    @FXML
    TextArea portbox;
    @FXML
    TextArea chatwindow;
    @FXML
    TextArea msgbox;
    @FXML
    Button listenbutton;
    @FXML
     Button sendButton;

    String buffer ="";
    Reciever reciever;
    @FXML
    public void change(){
        try {

            int PORT = Integer.parseInt(portbox.getText());
            buffer += "Listening on port " + portbox.getText() + "...";

            chatwindow.setText(buffer);
            reciever = new Reciever();
            reciever.PORT = PORT;
            reciever.start();
            reciever.chatwindow = chatwindow;
            reciever.msgbox = msgbox;
            reciever.sendButton  = sendButton;


        }catch (Exception err){
            System.out.println("error " +err.getMessage());
        }
        }
    @FXML
    public void sendMsg(){
        String data = msgbox.getText();
        if(data.length() > 0) {
            new Thread(() -> {
                System.out.println(data);
                reciever.sendData(data);
                msgbox.clear();

            }).start();
        }
    }
}
