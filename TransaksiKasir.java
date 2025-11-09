import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ===== Class Barang =====
class Barang {
    private String id;
    private String nama;
    private double hargaSatuan;

    public Barang(String id, String nama, double hargaSatuan) {
        this.id = id;
        this.nama = nama;
        this.hargaSatuan = hargaSatuan;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public String getNama() {
        return nama;
    }
}

// ===== Class ItemTransaksi =====
class ItemTransaksi {
    private Barang barang;
    private int kuantitas;

    public ItemTransaksi(Barang barang, int kuantitas) {
        this.barang = barang;
        this.kuantitas = kuantitas;
    }

    public double getSubtotal() {
        return barang.getHargaSatuan() * kuantitas;
    }

    @Override
    public String toString() {
        return barang.getNama() + " x" + kuantitas + " = " + String.format("%.2f", getSubtotal());
    }
}

// ===== Class Transaksi =====
class Transaksi {
    private List<ItemTransaksi> items;
    private double total;
    private double bayar;
    private double kembalian;

    public Transaksi() {
        items = new ArrayList<>();
        total = 0.0;
    }

    public void tambahItem(ItemTransaksi item) {
        items.add(item);
    }

    public double hitungTotal() {
        total = 0.0;
        for (ItemTransaksi it : items) {
            total += it.getSubtotal();
        }
        return total;
    }

    public double prosesPembayaran(double bayar) {
        this.bayar = bayar;
        this.kembalian = bayar - hitungTotal();
        return kembalian;
    }

    public void cetakStruk() {
        System.out.println("===== STRUK BELANJA =====");
        for (ItemTransaksi it : items) {
            System.out.println(it.toString());
        }
        System.out.println("-------------------------");
        System.out.println("TOTAL     : " + String.format("%.2f", hitungTotal()));
        System.out.println("BAYAR     : " + String.format("%.2f", bayar));
        System.out.println("KEMBALIAN : " + String.format("%.2f", kembalian));
        System.out.println("=========================");
    }
}

// ===== Class Utama (Kasir) =====
public class TransaksiKasir {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Transaksi transaksi = new Transaksi();
        System.out.println("=== APLIKASI KASIR SEDERHANA ===");

        while (true) {
            System.out.print("Masukkan nama barang (atau 'selesai'): ");
            String nama = sc.nextLine();

            if (nama.equalsIgnoreCase("selesai"))
                break;

            System.out.print("Harga satuan: ");
            double harga = Double.parseDouble(sc.nextLine());

            System.out.print("Kuantitas: ");
            int qty = Integer.parseInt(sc.nextLine());

            Barang b = new Barang(String.valueOf(System.currentTimeMillis()), nama, harga);
            ItemTransaksi it = new ItemTransaksi(b, qty);
            transaksi.tambahItem(it);

            System.out.println("Item ditambahkan!\n");
        }

        double total = transaksi.hitungTotal();
        System.out.println("Total belanja: " + String.format("%.2f", total));

        System.out.print("Masukkan jumlah bayar: ");
        double bayar = Double.parseDouble(sc.nextLine());

        double kembalian = transaksi.prosesPembayaran(bayar);
        System.out.println("Kembalian: " + String.format("%.2f", kembalian));

        transaksi.cetakStruk();
        sc.close();
    }
}
