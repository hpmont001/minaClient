package EasyClient_mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.util.NoopFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import EasyClient_mina.testMonitor;

import EasyClient_mina.IotCodecFactory;

public class MinaClient01 {
	private static Logger logger = Logger.getLogger(MinaClient01.class);
	private static String Log4jP = "log4j.properties";

	private static String HOST = "119.23.70.7";
	private static int PORT = 8090;


	private static void initLog4jProperties()  
	  {  
	      //δ���ʱ��ȡ����  
//	      String file = this.getClass().getClassLoader()  
//	                  .getResource(Log4jP).getFile();  
//	       if(new java.io.File(file).exists())  
//	          {  
//	               PropertyConfigurator.configure(file);  
//	                  System.out.println("δ���ʱ��ȡ����");  
//	           return;  
//	          }  
	            
	      //��ȡjar���������ļ�  
		String file = System.getProperty("user.dir") +"/"+Log4jP;      
	       if(new java.io.File(file).exists())  
	      {  
	           PropertyConfigurator.configure(file); 
	              System.out.println("��ȡjar���������ļ�");  
	          return;  
	      }  
	       else{
	    	   
				JOptionPane.showMessageDialog(null, "��ȡjar���������ļ�ʧ��"+file, "����", JOptionPane.WARNING_MESSAGE);

	       }
	             
	        
	  }
	public static void main(String[] args) {
		// ����һ���������Ŀͻ��˳���
		initLog4jProperties();
		IoConnector connector = new NioSocketConnector();
		// �������ӳ�ʱʱ��
		connector.setConnectTimeout(30000);
		// ��ӹ�����
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new IotCodecFactory()));
		// ���ҵ���߼���������
		connector.setHandler(new Demo1ClientHandler());
		IoSession session = null;
//		try {
//			ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));// ��������
//			future.awaitUninterruptibly();// �ȴ����Ӵ������
//			session = future.getSession();// ���session
//			byte txBuffer[] = {0x55,(byte)0xaa,0x0f,0x34,0x36,0x30,0x30,0x34,0x30,0x34,0x34,0x34,0x34,0x38,0x37,0x30,0x30,0x30};
//			
//			session.write(txBuffer);// ������Ϣ
//		} catch (Exception e) {
//			logger.error("�ͻ��������쳣...", e);
//		}

		//session.getCloseFuture().awaitUninterruptibly();// �ȴ����ӶϿ�
		//connector.dispose();
		
		int i;
		int connectOkCnt = 0;
		int connectFailCnt = 0;
		JTextArea ta_info2Pc = new JTextArea();
		new testMonitor(ta_info2Pc);
		for(i = 0; i < 1000;i ++){
			try {
				ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));// ��������
				future.awaitUninterruptibly();// �ȴ����Ӵ������
				session = future.getSession();// ���session
				byte txBuffer[] = {0x55,(byte)0xaa,0x0f,0x34,0x36,0x30,0x30,0x34,0x30,0x34,0x34,0x34,0x34,0x38,0x37,0x30,0x30,0x30};
				connectOkCnt ++;
				session.write(txBuffer);// ������Ϣ
			} catch (Exception e) {
				//logger.error("�ͻ��������쳣...", e);
				connectFailCnt ++;
			}
			ta_info2Pc.append("�ͻ��˳ɹ���������" + connectOkCnt + '\n');
			ta_info2Pc.append("�ͻ���ʧ����������" + connectFailCnt + '\n');
			logger.info("�ͻ��˳ɹ���������" + connectOkCnt);
			logger.info("�ͻ���ʧ����������" + connectFailCnt);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
