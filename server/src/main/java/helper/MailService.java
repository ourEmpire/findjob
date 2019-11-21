package helper;

import config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 *  from        发件人
 *  to          收件人
 *  password    发件人第三方软件发送授权码
 *  subject     邮件主题
 *  content     邮件内容
 */
@RestController
public class MailService {
    @Autowired
    MailConfig mc;
    public boolean sendSimpleMail(String to,int verify) {

        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth",String.valueOf(mc.getAuth()));
            props.setProperty("mail.smtp.host",mc.getHost());
            Session session = Session.getInstance(props);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mc.getUsername()));
            msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(to));
            msg.setSubject(mc.getSubject(),mc.getEncoding());
            msg.setContent(setContent(verify),mc.getType());
            Transport transport = session.getTransport();
            transport.connect(mc.getUsername(),mc.getPassword());
            transport.sendMessage(msg,msg.getAllRecipients());
            transport.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //设置发送内容
    private String setContent(int verify){
        return "<html><body>不要告诉任何人！验证码为<b>" + verify
                +"</b>欢迎您注册本网站，任何人索取验证码都是诈骗。</body></html>";
    }



}
