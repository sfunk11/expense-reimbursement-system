package com.revature.project1.main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.revature.project1.models.User;
 
 
public class SendEmail
{
	
	
   public static void main(User user)
   {   
      // email ID of Recipient.
      String recipient = user.getEmail();
 
      // email ID of  Sender.
      String sender = "thesamanthafunk@gmail.com";
 
      // using host as localhost
      String host = "127.0.0.1";
 
      // Getting system properties
      Properties properties = System.getProperties();
 
      // Setting up mail server
      properties.setProperty("smtp.gmail.com", host);
 
      // creating session object to get properties
      Session session = Session.getDefaultInstance(properties);
      
     String messageContent = "<h2> Welcome to Samco! </h2>"
    		 + "<hr><br><br><h5> Your new username is: " + user.getUsername() + "</h5>"
    		  + "<h5> Your temporary password is: " + user.getPassword() + "</h5>";
      
 
      try
      {
    	  Transport transport = session.getTransport("smtps");
          transport.connect ("smtp.gmail.com", 465, "thesamanthafunk@gmail.com", "Dubhghall1!");
          
    	  // MimeMessage object.
         MimeMessage message = new MimeMessage(session);
 
         // Set From Field: adding senders email to from field.
         message.setFrom(new InternetAddress(sender));
 
         // Set To Field: adding recipient's email to from field.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
 
  
         // Set Subject: subject of the email
         message.setSubject("Samco ERS Login Information");
 
         // set body of the email.
         message.setContent(messageContent,"text/html");
 
         // Send email.
         transport.sendMessage(message, message.getAllRecipients());
         System.out.println("Mail successfully sent");
         
         transport.close(); 
      }
      catch (MessagingException mex)
      {
         mex.printStackTrace();
      }
   }
}