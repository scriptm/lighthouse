package session;

import device.Device;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Admin on 01.10.2015.
 */
@Component
public class SessionRepository {

    private static final List<Session> sessions = new ArrayList<Session>();
    private static final List<Device> devices = new ArrayList<Device>();

    @PostConstruct
    public void initData() {

        Session tmpSession = new Session();
        tmpSession.setSessionId(1);
        tmpSession.setSessionName("4u12ygqfo707d23er23");
        tmpSession.setDateCreated(1443680565);
        tmpSession.setDateUpdated(1443680575);
        tmpSession.setDeviceId(1);
        tmpSession.setIsClosed(0);

        sessions.add(tmpSession);

        Device tmpDevice = new Device();
        tmpDevice.setDeviceId(1);
        tmpDevice.setImei("testImei");
        tmpDevice.setCdma("testCdma");
        tmpDevice.setAndroidId("testAndroidId");

        devices.add(tmpDevice);
    }

    public String findSession(String imei, String cdma, String androidId){

        Device resDevice = null;
        for (Device device : devices) {
            if (imei.equals(device.getImei()) && cdma.equals(device.getCdma()) && androidId.equals(device.getAndroidId())) {
                resDevice = device;
            }
        }

        Session resSession = null;
        if(resDevice != null)
            for(Session session : sessions) {
                if( session.getDeviceId() == resDevice.getDeviceId())
                    resSession = session;
            }

        return resSession.getSessionName();
    }

}