package Servletit;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LahettaSpostiServlet
 */
@WebServlet("/contact")
public class LahettaSpostiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LahettaSpostiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sender = request.getParameter("sender");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		receiveMail(sender, subject, message, request, response);
		
		
	}
	
	/**
	 * Saadaan palaute asiakkaalta palautelomakkeen kautta. Tulee ilmoitus kun palaute on l�hetetty tai ei ole viel�.
	 * @see receiveMail
	 * @param sender l�hett�j�n s�hk�posti, otetaan palautelomakkeesta.
	 * @param subject s�hk�postin aihe.
	 * @param message s�hk�postin viesti.
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws ServletException
	 * @throws IOException
	 * @return ei palautaa mit��n
	 */
	public void receiveMail(String sender, String subject, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		final String username ="your username";
		final String password ="your password";
		String destination ="your email";
		String host = "your smtp host";
		Properties pros = new Properties();
		pros.put("mail.smtp.auth", "true");
		pros.put("mail.smtp.starttls.enable","true");
		pros.put("mail.smtp.host", host);
		pros.put("mail.smtp.port", "2525");
		//pros.put("mail.smtp.socketFactory.class", javax.net.ssl.SSLSocketFactory.class.getName());
		
		
		Session session = Session.getInstance(pros, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		
		Message mms = new MimeMessage(session);
		        try {
					mms.setFrom(new InternetAddress(sender));
					mms.setSubject(subject);
					mms.setText(message);
					mms.setRecipients(Message.RecipientType.TO, InternetAddress.parse((destination)));
					Transport.send(mms);
					request.getRequestDispatcher("sucess_contact.jsp").forward(request, response);
				} catch (AddressException e) {
					request.getRequestDispatcher("errorSendMail.jsp").forward(request, response);
					e.printStackTrace();
				} catch (MessagingException e) {
					request.getRequestDispatcher("errorSendMail.jsp").forward(request, response);
					e.printStackTrace();
				}
	}
	
	
	/**
	 * L�het� vahvistus viesti asiakkaalle s�hk�postin kautta kun tilaus on valmis.
	 * @see sendMail
	 * @param to_email saajan s�hk�posti
	 * @param subject s�hk�postin aihe
	 * @param message viesti
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @throws ServletException
	 * @throws IOException
	 * @return palautaa oikein tai v��rin
	 */
	public boolean sendMail(String to_email, String subject, String message, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username ="your gmail account";
		String password ="your pass";
		String host = "smtp.gmail.com";
		String port = "465";
		Properties pros = new Properties();
		pros.put("mail.smtp.host",host);
		pros.put("mail.smtp.auth", "true");
		pros.put("mail.smtp.starttls.enable", "true");
		pros.put("mail.smtp.port", port);
		pros.put("mail.smtp.socketFactory.class", javax.net.ssl.SSLSocketFactory.class.getName());
		
		Session session = Session.getInstance(pros, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password); 
            } 
			} );
	   
		
		Message mms = new MimeMessage(session);
		try {
			
               mms.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
               mms.setSubject(subject);
		       mms.setText(message);
		       
		       Transport.send(mms);
		       
		} catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
		return true;
	
	}

}
