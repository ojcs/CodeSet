package sep.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SMTPMail {
	protected final MimeMessage message;
	protected final Multipart multipart;
	protected final Session session;

	public SMTPMail(String host) throws MessagingException {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		this.session = Session.getDefaultInstance(props);
		this.message = new MimeMessage(session);
		this.message.setContent(multipart = new MimeMultipart());
	}
	
	public void add(BodyPart part) throws MessagingException {
		multipart.addBodyPart(part);
		message.saveChanges();
	}

	public void addAttachment(File file) throws MessagingException, UnsupportedEncodingException {
		addAttachment(file, file.getName());
	}
	
	public void addAttachment(File file, String filename) throws MessagingException, UnsupportedEncodingException {
		BodyPart part = new MimeBodyPart();
		part.setDataHandler(new DataHandler(new FileDataSource(file)));
		part.setFileName(MimeUtility.encodeWord(filename, "UTF-8", null));
		add(part);
	}
	
	public void addText(CharSequence content) throws MessagingException {
		BodyPart part = new MimeBodyPart();
		part.setText(content.toString());
		add(part);
	}
	
	public void send(String host, String user, String password) throws MessagingException {
		Transport transport = session.getTransport("SMTP");
		transport.connect(host, user, password);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}
	
	/**
	 * 设置目标
	 * @param from 发件人地址
	 * @param to 收件人地址
	 * @param subject 邮件标题
	 */
	public void setTarget(String from, String to, String subject) throws AddressException, MessagingException {
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
	}
}