package EasyClient_mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class IotCodecFactory implements ProtocolCodecFactory {

	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return new ClientDecoder();
	}

	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return new ClientEncoder();
	}
}

