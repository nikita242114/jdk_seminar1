package org.example;

import java.io.*;

public class Server  {
    boolean isConnect;
    Listenerable action;
    StringBuilder textLog;
    //    String textMsg;
    private static final String fileName = "./src/main/java/org/example/log_file.txt";

    public Server(Listenerable action) {
        this.isConnect = false;
        this.action = action;
    }

    void login(){
        textLog =new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                textLog.append(line).append("\n");
            }
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }
        action.buttonAction(textLog.toString());
    }
    void sendChat(String textMsg){
        textLog.append("-").append(textMsg).append("\n");
        action.buttonAction(textMsg +"\n");
    }
    void logout(){
        try (FileWriter fw = new FileWriter(fileName)) {
            fw.write(textLog.toString());
            fw.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
