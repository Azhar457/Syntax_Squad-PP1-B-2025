import java.text.SimpleDateFormat;

public class AntrianMain { // a = b -> nilai a diset ke b
    private Node head, tail;

    public void enqueue(Antrian data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        System.out.println("Data masuk ke antrian.");
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
            System.out
                    .println("[" + i + "] " + current.data.getPelanggan().getNama() + " - " + current.data.getNoNota());
            current = current.next;
            i++;
        }
    }

    public void cetakStruk(Antrian data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

        String layanan = data.getLayanan();
        int hargaPerKg = layanan.equalsIgnoreCase("Express") ? 9000 : 7000;
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
}
