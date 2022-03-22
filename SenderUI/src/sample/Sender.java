package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {

    Socket socket = null;
    int PORT =3333;
    String ip = "";
    DataOutputStream _stream;
    @FXML
    TextArea chatwindow;
    @FXML
    TextArea msgbox;
    @FXML
    Button sendButton;
    public Boolean sendData(String _msg){

        try {


            _stream.writeUTF(_msg);
            chatwindow.setText(chatwindow.getText() + "\nme> " + _msg);
            return true;
        }catch (Exception err){
            return false;
        }
    }


    @Override
    public void run() {
        try {

            socket = new Socket(ip , PORT);
            sendButton.setDisable(false);
            chatwindow.setText(msgbox.getText() + "\n connection established");


             _stream= new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            DataInputStream _stream_in = new DataInputStream(socket.getInputStream());
            new Thread(()->{
                try {
                   while(true) {
                       String data = _stream_in.readUTF();
                       System.out.println(data);
                       chatwindow.setText(chatwindow.getText() +"\nfriend> "+data);
                   }
                }catch (Exception err){
                    System.out.println("no");
                }
            }).start();
            new Thread(()->{
                try {
                    while (true) {
                        System.out.print("msg ->");
                        String data = sc.nextLine();
                        _stream.writeUTF(data);
                        chatwindow.setText(chatwindow.getText() +"\nme> "+data);

                    }
                }catch (Exception err){

                }
            }).start();

            //            while(true){
//                Scanner s= new Scanner(System.in);
//                writer.write(s.nextLine());
//            }



        }catch (Exception err){
            System.out.println("Communication Error");
        }

    }

}
