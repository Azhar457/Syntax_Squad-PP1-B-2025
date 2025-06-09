package src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import src.entity.Antrian;
import src.entity.Pelanggan;
import src.services.AntrianMain;

public class Main {
    public static void main(String[] args) {
        AntrianMain antrian = new AntrianMain();
        antrian.bacaSemuaAntrianDariFile();
        Scanner sc = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n===== SISTEM ANTRIAN LAUNDRY =====");
            System.out.println("1. Menu Tambah Antrian");
            System.out.println("2. Lihat Semua Antrian");
            System.out.println("3. Ambil Antrian");
            System.out.println("4. Simpan Antrian ke File");
            System.out.println("0. Keluar");
            System.out.println("===================================");
            System.out.println("");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine();

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
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
                                String timePart = sdf.format(new Date());
                                String nota = "TRX/" + timePart;
                                Pelanggan pelanggan = new Pelanggan(nama, berat);
                                Antrian baru = new Antrian(nota, masuk, selesai, pelanggan, layanan);
                                antrian.enqueue(baru);
                                System.out.println("\nData antrian berhasil ditambahkan!");
                                break;
                            case 2:
                                antrian.simpanSemuaAntrianKeFile();
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
                    antrian.tampilkanSemua();
                    System.out.println("Semua antrian telah ditampilkan.");
                    break;
                case 3:
                    Antrian ambil = antrian.dequeue();
                    if (ambil != null) {
                        antrian.cetakStruk(ambil);
                        antrian.pindahKeCatatan(ambil);
                        antrian.hapusDariFileAntrian(ambil);
                        System.out.println("Antrian berhasil diambil");
                    } else {
                        System.out.println("Antrian kosong!");
                    }
                    break;
                case 4: // Milda
                    antrian.simpanSemuaAntrianKeFile();
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
