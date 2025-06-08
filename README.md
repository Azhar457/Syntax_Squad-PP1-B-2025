# Syntax_Squad-PP1-B-2025

### Dibuat Untuk Memenuhi Tugas Besar Mata Kuliah Praktikum Pemograman 1

## Dokumentasi Penggunaan

1. **Jalankan Program**

   - Compile semua file `.java`:
     ```sh
     javac *.java
     ```
   - Jalankan program:
     ```sh
     java Main
     ```

2. **Menu Utama**

   - Setelah program berjalan, akan muncul menu:
     ```
     ===== SISTEM ANTRIAN LAUNDRY =====
     1. Menu Tambah Antrian
     2. Menu Lihat Semua Antrian
     3. Ambil Antrian (Dequeue)
     4. Simpan Antrian ke File
     0. Keluar
     Pilih menu:
     ```

3. **Menu Tambah Antrian**

   - Pilih menu 1, lalu akan muncul sub-menu:
     ```
     ===== MENU TAMBAH ANTRIAN =====
     1. Tambah Antrian
     2. Simpan Antrian
     3. Kembali ke Menu Utama
     Pilih:
     ```
   - Pilih 1 untuk menambah data pelanggan (nama, berat, layanan Reguler/Express).
   - Pilih 2 untuk menyimpan seluruh antrian ke file (overwrite).
   - Pilih 3 untuk kembali ke menu utama.

4. **Menu Lihat Semua Antrian**

   - Pilih menu 2, lalu pilih layanan:
     ```
     Lihat antrian mana?
     1. Reguler
     2. Express
     Pilih:
     ```
   - Daftar antrian sesuai layanan akan ditampilkan.

5. **Menu Ambil Antrian (Dequeue)**

   - Pilih menu 3, lalu akan muncul sub-menu:
     ```
     ===== MENU AMBIL ANTRIAN =====
     1. Dequeue Antrian
     2. Kembali ke Menu Utama
     Pilih:
     ```
   - Pilih 1, lalu pilih layanan (Reguler/Express) yang ingin diambil antriannya.
   - Antrian terdepan layanan tersebut akan dihapus dari daftar dan dipindahkan ke file catatan.
   - Pilih 2 untuk kembali ke menu utama.

6. **Simpan Antrian ke File**

   - Pilih menu 4 untuk menyimpan seluruh antrian ke file (`antrian-reguler.txt` dan `antrian-express.txt`).
   - File akan di-overwrite dengan data terbaru (tidak terjadi duplikasi).

7. **Keluar**
   - Pilih menu 0 untuk keluar dari program.

---

## Summary / Kesimpulan Fitur

- **Tambah Antrian:** Menambah data pelanggan ke antrian sesuai layanan (Reguler/Express) melalui sub-menu.
- **Lihat Antrian:** Melihat daftar antrian berdasarkan layanan (Reguler/Express).
- **Ambil Antrian:** Mengambil (menghapus) antrian terdepan sesuai layanan dan memindahkan ke catatan/history.
- **Simpan ke File:** Menyimpan seluruh antrian ke file, file akan di-overwrite agar tidak terjadi duplikasi.
- **Catatan:** Setiap antrian yang diambil akan dicatat di `catatan.txt` sebagai history.
- **Dukungan File Kosong:** Program tetap berjalan walau file antrian belum ada.
- **Antarmuka Menu Interaktif:** Pengguna memilih fitur melalui menu dan sub-menu yang jelas.

---

## Dokumentasi Eksekusi

> _(Tambahkan foto hasil eksekusi program di bagian ini sesuai kebutuhan Anda)_

---
