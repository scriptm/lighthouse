package device;

/**
 * Created by Admin on 01.10.2015.
 */
public class Device {

    private int deviceId;
    private String imei;
    private String cdma;
    private String androidId;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCdma() {
        return cdma;
    }

    public void setCdma(String cdma) {
        this.cdma = cdma;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }
}
