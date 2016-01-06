package com.company;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;

public class Check {

    public static void main(String[] args) {
	// write your code here

        System.out.println("hello world");

        check("pop.gmail.com", "pop3", "gemini88mill@gmail.com", "Magalhaes13");
    }

    public static void check(String host, String storeType, String user, String password){
        try{
            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");

            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop3 server.
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            //retrieve the messages from the folder in an array and print it.
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);

            for(int i = 0, n = messages.length; i < n; i++){
                Message message = messages[i];
                System.out.println("--------------------------------");
                System.out.println("Email Number: " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            //close the store and folder objects
            emailFolder.close(false);
            store.close();

        }catch(IOException | MessagingException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
