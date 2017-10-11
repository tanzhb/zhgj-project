package com.congmai.zhgj.web.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class MessageTemplate {
	private static Map<String, MessageTemplate> templates = new HashMap<String, MessageTemplate>();
	private String messageType;
	private String messageChannel; //wechat, mail, sysMsg
	private String title;
	private String content;
	public String getTitle(Properties props){
		return composeMessage(title, props);
	}

	public MessageTemplate(String messageType, String messageChannel, String title, String content) {
		super();
		this.messageType = messageType;
		this.messageChannel = messageChannel;
		this.title = title;
		this.content = content;
	}

	public static void register(String messageType, String messageChannel, String title, String content){
		MessageTemplate template = new MessageTemplate(messageType,messageChannel, title, content);
		templates.put(messageType + messageChannel, template);
	}
	private static MessageTemplate getTemplate(String messageType, String messageChannel){
		return templates.get(messageType + messageChannel);
	}
	public static String getContent(String messageType, String messageChannel, Properties props){
		return composeMessage(getTemplate(messageType, messageChannel).content, props);
	}
	
	public static String getTitle(String messageType, String messageChannel, Properties props){
		return composeMessage(getTemplate(messageType, messageChannel).title, props);
	}
	
	private static String composeMessage(String template,Properties data){
        Iterator it =data.entrySet().iterator();
        while(it.hasNext()){
            Object o=it.next();
            template=template.replaceAll("\\$\\{"+o.toString().split("=",2)[0]+"}", o.toString().split("=",2)[1]);
        }
                                                                                                      
        return template;
    }
	
}
