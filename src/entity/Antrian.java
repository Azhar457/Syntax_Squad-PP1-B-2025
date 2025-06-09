package src.entity;

import java.util.Date;

public class Antrian {
    private String noNota;
    private Date tglMasuk;
    private Date tglSelesai;
    private Pelanggan pelanggan;
    private String layanan;

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
