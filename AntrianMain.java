import java.text.SimpleDateFormat;
import java.io.*;

public class AntrianMain {
    Node head, tail;

    // Ilona
    public void enqueue(Antrian data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

    }

    public Antrian dequeue() {
        if (head == null) {
            System.out.println("Antrian kosong!");
            return null;
        }
        Antrian data = head.data;
        head = head.next;
        if (head == null)
            tail = null;
        pindahKeCatatan(data);
        hapusDariFileAntrian(data);
        return data;
    }

    public void tampilkanSemua() {
        if (head == null) {
            System.out.println("Antrian kosong.");
            return;
        }

        Node current = head;
        int i = 1;
        while (current != null) {
            System.out.println("[" + i + "] " + current.data.getPelanggan().getNama() + " - " + current.data.getNoNota()
                    + " - " + current.data.getLayanan());
            current = current.next;
            i++;
        }
    }

    public void cetakStruk(Antrian data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

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

    // Simpan ke file sesuai layanan
    public void simpanKeFilePerLayanan(Antrian data) {
        String file = data.getLayanan().equalsIgnoreCase("Express") ? "antrian-express.txt" : "antrian-reguler.txt";
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(data.getNoNota() + ";" +
                    data.getTglMasuk().getTime() + ";" +
                    data.getTglSelesai().getTime() + ";" +
                    data.getPelanggan().getNama() + ";" +
                    data.getPelanggan().getBerat() + ";" +
                    data.getLayanan() + "\n");
        } catch (Exception e) {
            System.out.println("Gagal simpan file: " + e.getMessage());
        }
    }

    // Menyimpan semua antrian aktif ke file sesuai layanan
public void simpanSemuaAntrianKeFile() {
    if (head == null) {
        System.out.println("Tidak ada antrian untuk disimpan.");
        return;
    }

    Node current = head;
    int count = 0;

    while (current != null) {
        simpanKeFilePerLayanan(current.data);
        current = current.next;
        count++;
    }

    System.out.println(count + " antrian berhasil disimpan ke file.");
}


    // Pindahkan ke catatan/history saat dequeue
    public void pindahKeCatatan(Antrian data) {
        try (FileWriter fw = new FileWriter("catatan.txt", true)) {
            fw.write(data.getNoNota() + ";" +
                    data.getTglMasuk().getTime() + ";" +
                    data.getTglSelesai().getTime() + ";" +
                    data.getPelanggan().getNama() + ";" +
                    data.getPelanggan().getBerat() + ";" +
                    data.getLayanan() + "\n");
        } catch (Exception e) {
            System.out.println("Gagal simpan ke catatan: " + e.getMessage());
        }
    }

    // Hapus dari file antrian setelah dequeue
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