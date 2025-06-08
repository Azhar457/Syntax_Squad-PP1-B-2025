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
            System.out.println("1. Menu Tambah Antrian");
            System.out.println("2. Menu Lihat Semua Antrian");
            System.out.println("3. Menu Ambil Antrian");
            System.out.println("4. Simpan Antrian ke File");
            System.out.println("0. Keluar");
            System.out.println("===================================");
            System.out.println("");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (pilihan) {
                case 1:
                    int subPilihan;
                    do {
                        System.out.println("\n===== MENU TAMBAH ANTRIAN =====");
                        System.out.println("1. Tambah Antrian");
                        System.out.println("2. Simpan Antrian");
                        System.out.println("3. Kembali ke Menu Utama");
                        System.out.print("Pilih: ");
                        subPilihan = sc.nextInt();
                        sc.nextLine();

                        switch (subPilihan) {
                            case 1:
                                System.out.println("");
                                System.out.print("Masukkan nama pelanggan: ");
                                String nama = sc.nextLine();

                                System.out.print("Masukkan berat laundry (kg): ");
                                double berat = sc.nextDouble();
                                sc.nextLine();

                                System.out.print("Jenis layanan (1 = Reguler, 2 = Express): ");
                                int jenis = sc.nextInt();
                                sc.nextLine();

                                String layanan = (jenis == 2) ? "Express" : "Reguler";
                                int hariProses = (jenis == 2) ? 1 : 3;

                                Date masuk = new Date();
                                Date selesai = new Date(masuk.getTime() + hariProses * 24 * 60 * 60 * 1000L);

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
                                String timePart = sdf.format(new Date());
                                String nota = "TRX/" + timePart;
                                Pelanggan pelanggan = new Pelanggan(nama, berat);
                                Antrian baru = new Antrian(nota, masuk, selesai, pelanggan, layanan);

                                queue.enqueue(baru);
                                System.out.println("\nData antrian berhasil ditambahkan!");
                                break;
                            case 2:
                                queue.simpanSemuaAntrianKeFile();
                                break;
                            case 3:
                                System.out.println("Kembali ke menu utama...");
                                break;
                            default:
                                System.out.println("Pilihan tidak valid!");
                        }
                    } while (subPilihan != 3);
                    break;

                case 2:
                    System.out.println("===== MENU LIHAT ANTRIAN =====");
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
                    int subAmbil;
                    do {
                        System.out.println("\n===== MENU AMBIL ANTRIAN =====");
                        System.out.println("1. Ambil Antrian");
                        System.out.println("2. Kembali ke Menu Utama");
                        System.out.print("Pilih: ");
                        subAmbil = sc.nextInt();
                        sc.nextLine();
                        System.out.println("==============================");

                        switch (subAmbil) {
                            case 1:
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
                            case 2:
                                System.out.println("Kembali ke menu utama...");
                                break;
                            default:
                                System.out.println("Pilihan tidak valid!");
                        }
                    } while (subAmbil != 2);
                    break;
                case 4: // Milda
                    queue.simpanSemuaAntrianKeFile();
                    break;

                case 0:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }

        } while (pilihan != 0);
        sc.close();
    }
}