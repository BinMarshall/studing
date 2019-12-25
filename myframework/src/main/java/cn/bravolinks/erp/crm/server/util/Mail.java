package cn.bravolinks.erp.crm.server.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.sinosoft.sepmis.util.ExceptionMessage;

public class Mail {
	//异常处理类
//	private ExceptionMessage exceptionMessage = null;
	//记录日志工具类
	private Log log = LogFactory.getLog(this.getClass());
	
	/** mime邮件对象 */
	private MimeMessage mimeMsg;
	/** 邮件会话对象 */
	private Session session;
	/** 系统属性 */
	private Properties props;
	/** smtp是否需要验证 */
	private boolean needAuth = false;
	/** smtp认证用户名 */
	private String userName;
	/** smtp认证密码 */
	private String password;
	/** Multipart对象，邮件内容，标题，附件内容均添加到其中后再生成MimeMessage对象 */
	private Multipart mp;
	
	/**
	 * Construtor
	 * @param smtp 邮件发送服务器
	 * @author liuhongshuai 2015-03-30
	 */
	public Mail(String smtp){
		setSmtpHost(smtp);   
        createMimeMessage();
	}
	
	/**
	 * 设置邮件发送服务器
	 * @param hostName String
	 */
	public void setSmtpHost(String hostName){
		log.info("设置系统属性：mail.smtp.host = " + hostName);
		if(props == null){
			//获得系统属性对象
			props = System.getProperties();
			//设置SMTP主机
			props.put("mail.smtp.host", hostName);
			props.put("mail.smtp.port", "25");
		}
	}
	
	/**
	 * 创建MIME邮件对象
	 * @return
	 */
	public boolean createMimeMessage(){
		try{
			log.info("准备获取邮件会话对象!");
			//获得邮件会话对象
			session = Session.getDefaultInstance(props, null);
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("获取邮件会话对象时发生错误!" + e);
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
		
		//准备创建MIME邮件对象
		try{
			//创建MIME邮件对象
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();
			return true;
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("创建MIME邮件对象失败!");
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 设置SMTP是否需要验证
	 * @param need
	 */
	public void setNeedAuth(boolean need){
		log.info("设置smtp身份认证:mail.smtp.auth = " + need);
		//if(props == null) props = System.getProperties();
		if(need){
			props.put("mail.smtp.auth", "true");
		}else{
			props.put("mail.smtp.auth", "false");
		}
	}
	
	/**
	 * 设置用户名和密码
	 * @param name
	 * @param pass
	 */
	public void setNamePass(String name, String pass){
		userName = name;
		password = pass;
	}
	
	/**
	 * 设置邮件主题
	 * @param mailSubject
	 * @return
	 */
	public boolean setSubject(String mailSubject){
		log.info("设置邮件主题!");
		try{
			mimeMsg.setSubject(mailSubject);
			return true;
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("设置邮件主题失败!");
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 设置邮件正文
	 * @param mailBody String
	 */
	public boolean setBody(String mailBody){
		try{
			BodyPart bp = new MimeBodyPart();
			bp.setContent("" + mailBody,"text/html;charset=GBK");
			mp.addBodyPart(bp);
			
			log.info("设置邮件正文");
			return true;
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("设置邮件正文失败!");
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 设置发信人
	 * @param from String
	 */
	public boolean setFrom(String from){
		try{
			//设置发送人
			mimeMsg.setFrom(new InternetAddress(from));
			log.info("设置邮件发送人");
			return true;
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("设置邮件发送人失败!");
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 设置收信人
	 * @param to String
	 */
	public boolean setTo(String to){
		if(to == null) return false;
		try{
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("设置邮件收信人失败!");
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 设置抄送人
	 * @param copytoString
	 */
	public boolean setCopyTo(String copyto){
		if(copyto == null) return false;
		try{
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[])InternetAddress.parse(copyto));
			
			log.info("设置抄送人");
			return true;
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("设置邮件抄送人失败!");
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 发送邮件
	 */
	public boolean sendOut(){
		try{
			mimeMsg.setContent(mp);
			mimeMsg.saveChanges();
			log.info("正在发送邮件...");
			
			session  = Session.getDefaultInstance(props, null);
			//打开java Mail debug模式
			session.setDebug(true);
			Transport transport = session.getTransport("smtp");
			transport.connect((String)props.get("mail.smtp.host"), userName, password);
			transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
			//设置抄送人
			//transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.CC));
			
			log.info("邮件发送成功!");
			//关闭连接
			transport.close();
			return true;
		}catch(MessagingException em){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("发送邮件失败!");
//			this.exceptionMessage.setError(em.toString());
			log.error(em.toString());
			return false;
		}catch(Exception e){
//			this.exceptionMessage.setClassName(this.getClass().getName());
//			this.exceptionMessage.setMessage("发送邮件失败!");
//			this.exceptionMessage.setError(e.toString());
			log.error(e.toString());
			return false;
		}
	}
	
	/**
	 * 调用sendOut方法完成邮件发送
	 * @param smtp
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param userName
	 * @param password
	 * @param boolean
	 */
	public static boolean send(String smtp, String from, String to, String subject, String content, String userName, String password){
		Mail theMail = new Mail(smtp);
		//需要验证
		theMail.setNeedAuth(true);
		
		if(!theMail.setSubject(subject)) return false;
		if(!theMail.setBody(content)) return false;
		if(!theMail.setTo(to)) return false;
		if(!theMail.setFrom(from)) return false;
		theMail.setNamePass(userName, password);
		if(!theMail.sendOut()) return false;
		return true;
	}

	
	/** getter and setter */
//	public ExceptionMessage getExceptionMessage() {
//		return exceptionMessage;
//	}
//
//	public void setExceptionMessage(ExceptionMessage exceptionMessage) {
//		this.exceptionMessage = exceptionMessage;
//	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public MimeMessage getMimeMsg() {
		return mimeMsg;
	}

	public void setMimeMsg(MimeMessage mimeMsg) {
		this.mimeMsg = mimeMsg;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Multipart getMp() {
		return mp;
	}

	public void setMp(Multipart mp) {
		this.mp = mp;
	}

	public boolean isNeedAuth() {
		return needAuth;
	}
	
}
