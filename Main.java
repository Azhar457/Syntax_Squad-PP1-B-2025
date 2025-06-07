import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AntrianMain queue = new AntrianMain();
        queue.bacaSemuaAntrianDariFile(); // Membaca antrian dari file saat program dimulai
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== SISTEM ANTRIAN LAUNDRY =====");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Lihat Semua Antrian");
            System.out.println("3. Ambil Antrian (Dequeue)");
            System.out.println("4. Simpan Antrian ke File");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String nama = sc.nextLine();

                    System.out.print("Masukkan berat laundry (kg): ");
                    double berat = sc.nextDouble();
                    sc.nextLine(); // clear buffer

                    System.out.print("Jenis layanan (1 = Reguler, 2 = Express): ");
                    int jenis = sc.nextInt();
                    sc.nextLine();

                    String layanan = (jenis == 2) ? "Express" : "Reguler";
                    int hariProses = (jenis == 2) ? 1 : 3;

                    Date masuk = new Date();
                    Date selesai = new Date(masuk.getTime() + hariProses * 24 * 60 * 60 * 1000L);

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
                    String timePart = sdf.format(new Date());
                    String nota = "TRX/" + timePart + "-" + (int) (Math.random() * 1000);

                    Pelanggan pelanggan = new Pelanggan(nama, berat);
                    Antrian baru = new Antrian(nota, masuk, selesai, pelanggan, layanan);

                    // ini bagian aku queue saat masuk ke antrian-(express, reguler)
                    queue.enqueue(baru);
                    System.out.println("\nData antrian berhasil ditambahkan:");
                    System.out.println("(Belum disimpan ke file. Gunakan menu 4 untuk menyimpan.)");
                    break;

        
                    case 2:
    System.out.println("Lihat antrian mana?");
    System.out.println("1. Reguler");
    System.out.println("2. Express");
    System.out.print("Pilih: ");
    int lihatPilihan = sc.nextInt();
    sc.nextLine();
    if (lihatPilihan == 1) {
        queue.tampilkanAntrianPerLayanan("Reguler");
    } else if (lihatPilihan == 2) {
        queue.tampilkanAntrianPerLayanan("Express");
    } else {
        System.out.println("Pilihan tidak valid!");
    }
    break;


                case 3:
    System.out.println("Ambil antrian mana?");
    System.out.println("1. Reguler");
    System.out.println("2. Express");
    System.out.print("Pilih: ");
    int ambilPilihan = sc.nextInt();
    sc.nextLine();
    Antrian ambil = null;
    if (ambilPilihan == 1) {
        ambil = queue.dequeuePerLayanan("Reguler");
    } else if (ambilPilihan == 2) {
        ambil = queue.dequeuePerLayanan("Express");
    } else {
        System.out.println("Pilihan tidak valid!");
    }
    if (ambil != null) {
        queue.cetakStruk(ambil);
        System.out.println("Pesanan berhasil diambil dan dipindahkan ke catatan.");
    }
    break;


                case 4: //Milda
                    queue.simpanSemuaAntrianKeFile();
                    break;

                case 5:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 5);
        sc.close();
    }
}