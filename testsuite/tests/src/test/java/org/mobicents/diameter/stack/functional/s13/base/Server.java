package org.mobicents.diameter.stack.functional.s13.base;

import org.jdiameter.api.Answer;
import org.jdiameter.api.IllegalDiameterStateException;
import org.jdiameter.api.InternalException;
import org.jdiameter.api.NetworkReqListener;
import org.jdiameter.api.OverloadException;
import org.jdiameter.api.Request;
import org.jdiameter.api.RouteException;
import org.jdiameter.api.s13.ServerS13Session;
import org.jdiameter.api.s13.events.JMEIdentityCheckAnswer;
import org.jdiameter.api.s13.events.JMEIdentityCheckRequest;
import org.jdiameter.client.api.ISessionFactory;
import org.mobicents.diameter.stack.functional.Utils;
import org.mobicents.diameter.stack.functional.s13.AbstractServer;

public class Server extends AbstractServer {

	protected boolean receivedECR;
	protected boolean sentECA;

	protected JMEIdentityCheckRequest request;

	public void sendMEIdentityCheckAnswer() throws Exception {
		if (!receivedECR || request == null) {
			fail("Did not receive ECR or answer already sent.", null);
			throw new Exception("Did not receive ECR or answer already sent. Request: " + this.request);
		}

		JMEIdentityCheckAnswer eca = super.createECA(request, 2001);

		super.serverS13Session.sendMEIdentityCheckAnswer(eca);

		this.sentECA = true;
		request = null;
		Utils.printMessage(log, super.stack.getDictionary(), eca.getMessage(), true);
	}


	/* (non-Javadoc)
	 * @see org.mobicents.diameter.stack.functional.TBase#processRequest(org.jdiameter.api.Request)
	 */
	@Override
	public Answer processRequest(Request request) {
		int code = request.getCommandCode();
		if (code != JMEIdentityCheckRequest.code) {
			fail("Received Request with code not used by S13!. Code[" + request.getCommandCode() + "]", null);
			return null;
		}
		if (super.serverS13Session != null) {
			// do fail?
			fail("Received Request in base listener, not in app specific!" + code, null);
		} else {
			try {

				super.serverS13Session = ((ISessionFactory) this.sessionFactory).getNewAppSession(request.getSessionId(), getApplicationId(), ServerS13Session.class, (Object) null);
				((NetworkReqListener) this.serverS13Session).processRequest(request);

			} catch (Exception e) {
				e.printStackTrace();
				fail(null, e);
			}
		}
		return null;
  }
	@Override
	public void doMEIdentityCheckRequestEvent(ServerS13Session session, JMEIdentityCheckRequest request) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException {
		if (this.receivedECR) {
			fail("Received ECR more than once", null);
			return;
		}
		this.receivedECR = true;
		this.request = request;
		}


	public boolean isReceivedECR() {
		return receivedECR;
	}

	public boolean isSentECA() {
		return sentECA;
	}

	@Override
	protected int getEquipmentStatus() {
		// (0) Whitelisted
		// (1) Blacklisted
		// (2) Greylisted
		return 2;
	}
}
