package session;

import fortress.lighthouse.session.service.v1.GetSessionRequest;
import fortress.lighthouse.session.service.v1.GetSessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
/**
 * Created by Admin on 01.10.2015.
 */
@Endpoint
public class SessionEndpoint {
    private static final String NAMESPACE_URI = "http://fortress/lighthouse/session/service/v1";

    private SessionRepository sessionRepository;

    @Autowired
    public SessionEndpoint(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSessionRequest")
    @ResponsePayload
    public GetSessionResponse getSessionResponse(@RequestPayload GetSessionRequest request) {
        GetSessionResponse response = new GetSessionResponse();
        response.setSessionId(sessionRepository.findSession(request.getDeviceId().getImei(), request.getDeviceId().getAndroidId(), request.getDeviceId().getCdma()));

        return response;
    }
}
