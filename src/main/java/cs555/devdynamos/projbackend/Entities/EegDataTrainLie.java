package cs555.devdynamos.projbackend.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EegDataTestLie {

    @Id
    private long rowNumber;

    private double AF3;
    private double T7;
    private double Pz;
    private double T8;
    private double AF4;

    public long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public double getAF3() {
        return AF3;
    }

    public void setAF3(double AF3) {
        this.AF3 = AF3;
    }

    public double getT7() {
        return T7;
    }

    public void setT7(double t7) {
        T7 = t7;
    }

    public double getPz() {
        return Pz;
    }

    public void setPz(double pz) {
        Pz = pz;
    }

    public double getT8() {
        return T8;
    }

    public void setT8(double t8) {
        T8 = t8;
    }

    public double getAF4() {
        return AF4;
    }

    public void setAF4(double AF4) {
        this.AF4 = AF4;
    }
}
