package com.customer;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.DatabaseConnection;

/**
 * Servlet implementation class AddCustomer
 */
@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		int id = rnd.nextInt(9000) + 10000;
		System.out.println("Customer Id  "+id);
		while (salt.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String code = salt.toString();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address");
		String pincode = request.getParameter("pincode");
		
		HttpSession hs=request.getSession();
		try {
			int addCustomer = DatabaseConnection.insertUpdateFromSqlQuery(
					"insert into tblcustomer(id,address,email,gender,name,password,phone,valid,pin_code)values('" + id
							+ "','" + address + "','" + email + "','" + gender + "','" + name + "','" + password + "','"
							+ mobile + "','" + code + "','" + pincode + "')");
			if (addCustomer > 0) {
				String message="Customer register successfully.";
				hs.setAttribute("success-message", message);
				response.sendRedirect("customer-register.jsp");
				

		        // Sender's email credentials
		        final String username = "yogtechsoft@gmail.com";
		        final String passwod = "aecnpsurqlnjziwd"; // Use App Password (not your Gmail password)

		        // Email properties
		        Properties props = new Properties();
		        props.put("mail.smtp.auth", "true");
		        props.put("mail.smtp.starttls.enable", "true");
		        props.put("mail.smtp.host", "smtp.gmail.com");
		        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		        props.put("mail.debug", "true");
		        props.put("mail.smtp.port", "465");

		        // Create a session with authentication
		        Session session = Session.getInstance(props, new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(username, passwod);
		            }
		        });

		        try {
		            // Compose the message
		            Message messag = new MimeMessage(session);
		            messag.setFrom(new InternetAddress(username));
		            messag.setRecipients(Message.RecipientType.TO,
		                    InternetAddress.parse("phalkea000@gmail.com"));
		            messag.setSubject("Test Email from Java");
		            messag.setText("Hello, Your User Name is " +name+ " Your Password Is " +password );

		            // Send the message
		            Transport.send(messag);

		            System.out.println("Email sent successfully!");

		        } catch (MessagingException e) {
		            e.printStackTrace();
		        }
			} else {
				String message="Customer registration fail";
				hs.setAttribute("fail-message", message);
				response.sendRedirect("customer-register.jsp");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
