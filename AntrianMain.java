import java.text.SimpleDateFormat;
import java.io.*;

public class AntrianMain {
    public Node head, tail;

    // Ilona
    public void enqueue(Antrian data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    // Melda
    public void tampilkanAntrianPerLayanan(String layanan) {
        Node current = head;
        int i = 1;
        boolean kosong = true;
        while (current != null) {
            if (current.getData().getLayanan().equalsIgnoreCase(layanan)) {
                System.out.println(
                        "[" + i + "] " + current.getData().getPelanggan().getNama() + " - "
                                + current.getData().getNoNota());
                kosong = false;
            }
            current = current.getNext();
            i++;
        }
        if (kosong) {
            System.out.println("Antrian " + layanan + " kosong.");
        }
    }

    public Antrian dequeuePerLayanan(String layanan) {
        Node current = head, prev = null;
        while (current != null) {
            if (current.getData().getLayanan().equalsIgnoreCase(layanan)) {
                Antrian data = current.getData();
                if (prev == null) { // head
                    head = current.getNext();
                    if (head == null)
                        tail = null;
                } else {
                    prev.setNext(current.getNext());
                    if (current == tail)
                        tail = prev;
                }
                pindahKeCatatan(data);
                hapusDariFileAntrian(data);
                return data;
            }
            prev = current;
            current = current.getNext();
        }
        System.out.println("Antrian " + layanan + " kosong!");
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
            // Ubah di sini: parse tanggal dari string
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

    // Akmal
    public void tampilkanSemua() {
        if (head == null) {
            System.out.println("Antrian kosong.");
            return;
        }

        Node current = head;
        int i = 1;
        while (current != null) {
            System.out.println(
                    "[" + i + "] " + current.getData().getPelanggan().getNama() + " - " + current.getData().getNoNota()
                            + " - " + current.getData().getLayanan());
            current = current.getNext();
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

    // ini punya ilona
    // Simpan ke file sesuai layanan

    // Simpan Milda

    public void simpanKeFilePerLayanan(Antrian data) {
        overwriteFilePerLayanan("Reguler");
        overwriteFilePerLayanan("Express");
        System.out.println("Semua antrian berhasil disimpan ke file.");
    }

    // Azhar
    // Overwrite file antrian per layanan
    // Digunakan saat ada perubahan pada antrian
    public void overwriteFilePerLayanan(String layanan) {
        String file = layanan.equalsIgnoreCase("Express") ? "antrian-express.txt" : "antrian-reguler.txt";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (FileWriter fw = new FileWriter(file, false)) { // false = overwrite
            Node current = head;
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

    // Menyimpan semua Milda
    public void simpanSemuaAntrianKeFile() {
        if (head == null) {
            System.out.println("Tidak ada antrian untuk disimpan.");
            return;
        }

        Node current = head;
        int count = 0;

        while (current != null) {
            simpanKeFilePerLayanan(current.getData());
            current = current.getNext();
            count++;
        }

        System.out.println(count + " antrian berhasil disimpan ke file.");
    }

    // Pindahkan ke catatan/history saat dequeue melda
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