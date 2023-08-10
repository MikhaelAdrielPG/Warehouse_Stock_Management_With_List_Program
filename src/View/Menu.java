package View;

public class Menu {

    // Menampilkan menu utama
    public void tampilkanMenuUtama() {
        System.out.println("=== Program Stok Gudang ===");
        System.out.println("1. Kelola Kategori");
        System.out.println("2. Kelola Barang");
        System.out.println("3. Kelola Stok");
        System.out.println("4. Cari Barang");
        System.out.println("5. Tampilkan Stok Barang");
        System.out.println("6. Tampilkan Transaksi");
        System.out.println("7. Tampilkan Log Perubahan");
        System.out.println("0. Keluar");
        System.out.print("Pilihan: ");
    }

    // Menampilkan menu Kelola Kategori
    public void tampilkanMenuKategori() {
        System.out.println("=== Kelola Kategori ===");
        System.out.println("1. Tambah Kategori");
        System.out.println("2. Ganti Nama Kategori");
        System.out.println("3. Hapus Kategori");
        System.out.println("0. Kembali");
        System.out.print("Pilihan: ");
    }

    // Menampilkan menu Kelola Model.Barang
    public void tampilkanMenuBarang() {
        System.out.println("=== Kelola Barang ===");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Ganti Nama Barang");
        System.out.println("3. Hapus Barang");
        System.out.println("0. Kembali");
        System.out.print("Pilihan: ");
    }

    // Menampilkan menu Kelola Stok
    public void tampilkanMenuStok() {
        System.out.println("=== Kelola Stok ===");
        System.out.println("1. Tambah Stok Barang");
        System.out.println("2. Kurangi Stok Barang");
        System.out.println("0. Kembali");
        System.out.print("Pilihan: ");
    }
}