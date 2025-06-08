package entity;
public class Pelanggan {
    private String nama;
    private double berat;

    public Pelanggan(String nama, double berat) {
        this.nama = nama;
        this.berat = berat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getBerat() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat = berat;
    }
}
