package psoft.backend.projeto.email;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp {

    private Properties props;
    private Session session;

    public JavaMailApp() {
        this.props = new Properties();
        this.session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ajude.api20192@gmail.com", "ajudeapi12345678");
            }
        });
    }

    private void configProps() {
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
    }

    public void enviaMensagem(String destinatario) {
        configProps();
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ajude.api20192@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress //Destinatário(s)
                    .parse(destinatario);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Boas-Vindas!");//Assunto
            message.setText("Seja bem-vindo(a) à plataforma AJuDE!\nLink para acesso ao sistema: https://danilomfreitas.github.io/");
            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}