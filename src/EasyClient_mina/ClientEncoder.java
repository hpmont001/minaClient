package EasyClient_mina;

//import java.nio.charset.Charset;
//import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
public class ClientEncoder implements ProtocolEncoder {
	//private Charset charset = Charset.forName("UTF-8");

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		//CharsetEncoder ce = charset.newEncoder();
		//buf.putString(message.toString(), ce);
		// buf.put(message.toString().getBytes(charset));
		//buf.put((byte) '\r');
		//buf.put((byte) '\n');
		buf.put((byte[])message);
		buf.flip();
		out.write(buf);
	}

	public void dispose(IoSession session) throws Exception {

	}
}

