package com.aba.msgpush;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import util.HTMLFilter;

@ServerEndpoint(value = "/oneone/chat")
public class OnetoOneSocket {
    String id;
	private static final Log log = LogFactory.getLog(ChatAnnotation.class);
	private Session session;
    //��ȡmanger
    PtpMnager manger;
    //���췽������ֵ����manger�����ڹ���
    public OnetoOneSocket() {
        manger = PtpMnager.getManager();
    }
    public Session getSesstion()
    {
    	return this.session;
    }
    
    //��ʱ�Ĳ���
    @OnOpen
    public void start(Session session) {
    	 this.session = session;
    }
    @OnClose
    public void end() {
        String message = String.format("* %s %s",
                "", "����Ա�Ѿ����ߣ���ʱ�����ṩ����");
        manger.chat(this,message);
        manger.removeOOuser(this);
    }
    @OnMessage
    public void incoming(String message) {
        // Never trust the client
    	String[] str=message.split("%");
    	if(str[0].equals("ID"))
    	{
    		this.id = str[1];
    		manger.addOOuser(this, str[1]);
    	}
    	else {
    		String filteredMessage = String.format("%s: %s",
                    "", HTMLFilter.filter(str[1].toString()));
            manger.chat(this,filteredMessage);
		}
    }
    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }

}
