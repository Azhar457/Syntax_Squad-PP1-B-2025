package src.services;

import java.text.SimpleDateFormat;

import src.entity.Antrian;
import src.entity.Node;
import src.entity.Pelanggan;

import java.io.*;

public class AntrianMain {
    private Node headReguler, tailReguler;
    private Node headExpress, tailExpress;

    public void enqueue(Antrian data) {
        Node newNode = new Node(data);
        if (data.getLayanan().equalsIgnoreCase("Express")) {
            if (tailExpress == null) {
                headExpress = tailExpress = newNode;
            } else {
                tailExpress.setNext(newNode);
                tailExpress = newNode;
            }
        } else {
            if (tailReguler == null) {
                headReguler = tailReguler = newNode;
            } else {
                tailReguler.setNext(newNode);
                tailReguler = newNode;
            }
        }
    }

    public Antrian dequeue() {
        if (headExpress != null) {
            Antrian data = headExpress.getData();
            headExpress = headExpress.getNext();
            if (headExpress == null)
                tailExpress = null;
            return data;
        } else if (headReguler != null) {
            Antrian data = headReguler.getData();
            headReguler = headReguler.getNext();
            if (headReguler == null)
                tailReguler = null;
            return data;
        }
        return null;
    }

    public void bacaSemuaAntrianDariFile() {
        bacaAntrianDariFile("antrian-reguler.txt");
        bacaAntrianDariFile("antrian-express.txt");
    }

    private void bacaAntrianDariFile(String filename) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("//"))
                    continue;
                String[] parts = line.split(";");
                if (parts.length < 6)
                    continue;
                String noNota = parts[0];
                java.util.Date tglMasuk = sdf.parse(parts[1]);
                java.util.Date tglSelesai = sdf.parse(parts[2]);
                String nama = parts[3];
                double berat = Double.parseDouble(parts[4]);
                String layanan = parts[5];
                Pelanggan p = new Pelanggan(nama, berat);
                Antrian a = new Antrian(noNota, tglMasuk, tglSelesai, p, layanan);
                enqueue(a);
            }
        } catch (IOException e) {
            // File mungkin belum ada, abaikan
        } catch (Exception e) {
            System.out.println("Gagal baca file: " + e.getMessage());
        }
    }

    public void tampilkanSemua() {
        System.out.println("=== ANTRIAN EXPRESS ===");
        Node current = headExpress;
        int i = 1;
        while (current != null) {
            System.out.println("[" + i + "] " + current.getData().getPelanggan().getNama() + " - "
                    + current.getData().getNoNota());
            current = current.getNext();
            i++;
        }
        System.out.println("=== ANTRIAN REGULER ===");
        current = headReguler;
        i = 1;
        while (current != null) {
            System.out.println("[" + i + "] " + current.getData().getPelanggan().getNama() + " - "
                    + current.getData().getNoNota());
            current = current.getNext();
            i++;
        }
    }

    public void cetakStruk(Antrian data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String layanan = data.getLayanan();
        int hargaPerKg = layanan.equalsIgnoreCase("Express") ? 9000 : 6000;
        double berat = data.getPelanggan().getBerat();
        int total = (int) (berat * hargaPerKg);
        System.out.println("========== STRUK ==========");
        System.out.println("No Nota     : " + data.getNoNota());
        System.out.println("Pelanggan   : " + data.getPelanggan().getNama());
        System.out.println("Layanan     : " + layanan);
        System.out.println("Tgl Masuk   : " + sdf.format(data.getTglMasuk()));
        System.out.println("Est Selesai : " + sdf.format(data.getTglSelesai()));
        System.out.println("Berat       : " + berat + " Kg");
        System.out.println("Harga/Kg    : " + hargaPerKg);
        System.out.println("Total       : " + total);
        System.out.println("Status      : Belum Bayar");
        System.out.println("============================");
    }

    public void simpanKeFilePerLayanan(Antrian data) {
        String file = data.getLayanan().equalsIgnoreCase("Express") ? "antrian-express.txt" : "antrian-reguler.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (FileWriter fw = new FileWriter(file, true)) { // true = append
            fw.write(data.getNoNota() + ";" +
                    sdf.format(data.getTglMasuk()) + ";" +
                    sdf.format(data.getTglSelesai()) + ";" +
                    data.getPelanggan().getNama() + ";" +
                    data.getPelanggan().getBerat() + ";" +
                    data.getLayanan() + "\n");
        } catch (Exception e) {
            System.out.println("Gagal simpan ke file: " + e.getMessage());
        }
    }
    public void overwriteFilePerLayanan(String layanan) {
        String file = layanan.equalsIgnoreCase("Express") ? "antrian-express.txt" : "antrian-reguler.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (FileWriter fw = new FileWriter(file, false)) { // false = overwrite
            Node current = layanan.equalsIgnoreCase("Express") ? headExpress : headReguler;
            while (current != null) {
                if (current.getData().getLayanan().equalsIgnoreCase(layanan)) {
                    fw.write(current.getData().getNoNota() + ";" +
                            sdf.format(current.getData().getTglMasuk()) + ";" +
                            sdf.format(current.getData().getTglSelesai()) + ";" +
                            current.getData().getPelanggan().getNama() + ";" +
                            current.getData().getPelanggan().getBerat() + ";" +
                            current.getData().getLayanan() + "\n");
                }
                current = current.getNext();
            }
        } catch (Exception e) {
            System.out.println("Gagal overwrite file: " + e.getMessage());
        }
    }

    public void simpanSemuaAntrianKeFile() {
        overwriteFilePerLayanan("Reguler");
        overwriteFilePerLayanan("Express");
        System.out.println("Semua antrian berhasil disimpan ke file.");
    }

    public void pindahKeCatatan(Antrian data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (FileWriter fw = new FileWriter("catatan.txt", true)) {
            fw.write(data.getNoNota() + ";" + sdf.format(data.getTglMasuk()) + ";" +
                    sdf.format(data.getTglSelesai()) + ";" + data.getPelanggan().getNama() + ";" +
                    data.getPelanggan().getBerat() + ";" + data.getLayanan() + "\n");
        } catch (Exception e) {
            System.out.println("Gagal simpan ke catatan: " + e.getMessage());
        }
    }

    public void hapusDariFileAntrian(Antrian data) {
        String file = data.getLayanan().equalsIgnoreCase("Express") ? "antrian-express.txt" : "antrian-reguler.txt";
        try {
            File f = new File(file);
            if (!f.exists())
                return;
            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith(data.getNoNota() + ";")) {
                    sb.append(line).append("\n");
                }
            }
            br.close();
            FileWriter fw = new FileWriter(f, false);
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            System.out.println("Gagal hapus dari file antrian: " + e.getMessage());
        }
    }
}