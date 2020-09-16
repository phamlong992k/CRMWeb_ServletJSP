package com.myClass.DAO;

import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

public class EmailDao extends javax.mail.Authenticator {
	private String mailhost = "smtp.gmail.com";; // "smtp.mail.yahoo.com"; //"smtp.gmail.com";
	private String user;
	private String password;
	private Session session;
	
	private int code=-1;
	public EmailDao(String user, String password) {
		this.user = user;
		this.password = password;
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", mailhost);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");
		
		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication()
		    {
		        return new PasswordAuthentication(user, password);
		    }
		});
		
	}
	public int EmailSend(String toEmail) {
		try {
			MimeMessage message= new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
			Random r= new Random();
			code=r.nextInt(1000);
			message.setSubject("Please confirm your email by use this code"+Calendar.getInstance());
			message.setText("This is your code: "+code);
			Transport.send(message);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return code;
	}
	
}
