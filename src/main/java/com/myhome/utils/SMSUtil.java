
package com.myhome.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SMSUtil {

	private static final Logger LOG = LoggerFactory.getLogger(SMSUtil.class);

	private static CloseableHttpClient client = HttpClients.createDefault();

	/**
	 * 生成验证码短信信息
	 * 
	 * @param code
	 * @return
	 */
	public static String getValidateMessage(Integer validationCode) {
		StringBuffer event = new StringBuffer("您的");
		event.append("手机");
		return event.append("验证码为：").append(validationCode).append("，请勿泄漏，30分钟内有效；如非本人授权或操作，请立即联系客服").toString();
	}

	/**
	 * 发送消息
	 * 
	 * @param phone
	 * @param message
	 * @return
	 */
	public static boolean send(String phone, String message, String title) {
		return sendSMS(phone, message, title);
	}

	/**
	 * 发送消息,参数传code
	 * 
	 * @param phone
	 * @param code
	 * @return
	 */
	public static boolean sendValidate(String phone, Integer code, String title) {
		return sendSMS(phone, getValidateMessage(code), title);
	}

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unused")
    private static boolean sendSMS(String phone, String content, String title) {
		if (null == phone || null == content)
			return false;
		try {
			HttpGet get = new HttpGet(getURL(phone, content, title));
			CloseableHttpResponse reponse = client.execute(get);
			HttpEntity entity = reponse.getEntity();
			
			return (entity != null && EntityUtils.toString(entity).contains("success:1")) ? true : false;
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

		return false;
	}

	private static String getURL(String phone, String content, String title) throws UnsupportedEncodingException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("http://121.40.60.163:8081/message/sendMsg?");
		buffer.append("&loginname=");
		buffer.append("192");
		buffer.append("&password=");
		buffer.append("gjw192192");
		buffer.append("&mobile=");
		buffer.append(phone);
		buffer.append("&content=");
		buffer.append(URLEncoder.encode(content.concat(""), "UTF-8"));
		buffer.append("&extNo=");
		return buffer.toString();
	}

	public static void main(String[] args) {
		// 移动 18873683530 13575775411
		// SMSUtil.sendSMS("18072841530","杭州构家网络科技有限公司","title");
		// 联调 18626865811
		System.out.println(SMSUtil.sendSMS("18873683530", "杭州构家网络科技有限公司", "title"));
		
	}
}
