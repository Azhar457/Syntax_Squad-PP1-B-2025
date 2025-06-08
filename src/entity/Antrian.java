package entity;
import java.util.Date;

public class Antrian { // [NoNota, TglMasuk, TglSelesai, [Nama, berat], Layanan]
    private String noNota;
    private Date tglMasuk;
    private Date tglSelesai;
    private Pelanggan pelanggan;// [Nama, Berat]
    private String layanan;
    // 1 = Reguler, 2 = Express

    public Antrian(String noNota, Date tglMasuk, Date tglSelesai, Pelanggan pelanggan, String layanan) {
        this.noNota = noNota;
        this.tglMasuk = tglMasuk;
        this.tglSelesai = tglSelesai;
        this.pelanggan = pelanggan;
        this.layanan = layanan;
    }

    public String getNoNota() {
        return noNota;
    }

    public String getLayanan() {
        return layanan;
    }

    public Date getTglMasuk() {
        return tglMasuk;
    }

    public Date getTglSelesai() {
        return tglSelesai;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }
}
