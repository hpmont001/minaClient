package EasyClient_mina;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class Demo1ClientHandler implements IoHandler {
	private static Logger logger = Logger.getLogger(Demo1ClientHandler.class);
	int connectCloseCnt = 0;
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = message.toString();
		logger.info("客户端接收到的信息为：" + msg);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		logger.error("客户端发生异常...", cause);
	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		connectCloseCnt ++;
		logger.info("客户端断开连接数" + connectCloseCnt);
		
	}

	@Override
	public void sessionCreated(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionIdle(IoSession arg0, IdleStatus arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}


}
