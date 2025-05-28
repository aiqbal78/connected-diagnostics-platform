package simulator;

import java.time.LocalDateTime;

public class DtcEvent {
    public String vin;
    public String dtcCode;
    public LocalDateTime timestamp;

    public DtcEvent(String vin, String dtcCode, LocalDateTime timestamp) {
        this.vin = vin;
        this.dtcCode = dtcCode;
        this.timestamp = timestamp;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getDtcCode() {
        return dtcCode;
    }

    public void setDtcCode(String dtcCode) {
        this.dtcCode = dtcCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    // Getters & Setters (or use public fields for simplicity)
}
