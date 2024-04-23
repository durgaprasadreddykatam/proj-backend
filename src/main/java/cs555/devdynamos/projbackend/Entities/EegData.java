package cs555.devdynamos.projbackend.domain;
import java.util.UUID;
public class EegData {



    private UUID sessionId;

    private double AF3;
    private double T7;
    private double Pz;
    private double T8;
    private double AF4;

    public EegData(UUID sessionId, double AF3, double t7, double pz, double t8, double AF4) {
        this.sessionId = sessionId;
        this.AF3 = AF3;
        T7 = t7;
        Pz = pz;
        T8 = t8;
        this.AF4 = AF4;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "EegData{" +
                "sessionId=" + sessionId +
                ", AF3=" + AF3 +
                ", T7=" + T7 +
                ", Pz=" + Pz +
                ", T8=" + T8 +
                ", AF4=" + AF4 +
                '}';
    }
}
