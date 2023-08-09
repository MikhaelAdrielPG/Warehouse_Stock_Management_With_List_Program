package WarehouseManagement;

import Model.Barang;
import MenuView.Menu;

import java.text.SimpleDateFormat;
import java.util.*;

// Definisi kelas utama Controller.ManajemenStokGudang
public class ManajemenStokGudang {
    // List untuk menyimpan stok barang dan log perubahan
    static List<Barang> stokGudang = new ArrayList<>();
    static List<String> logPerubahan = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static Menu menu = new Menu();

    // Fungsi utama program
    public void mainProgram() {
        int pilihan = -1;

        // Loop menu utama
        while (pilihan != 0) {
            menu.tampilkanMenuUtama();
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan karakter newline
                prosesPilihanUtama(pilihan);
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid. Silakan masukkan angka.");
                scanner.nextLine(); // Membersihkan input
            }
        }

        System.out.println("Terima kasih telah menggunakan program ini!");
    }

    // Memproses pilihan menu utama
    public void prosesPilihanUtama(int pilihan) {
        switch (pilihan) {
            case 1 -> prosesMenuKategori();
            case 2 -> prosesMenuBarang();
            case 3 -> prosesMenuStok();
            case 4 -> cariBarang();
            case 5 -> lihatStok();
            case 6 -> tampilkanLogPerubahan();
            case 0 -> {} // Keluar
            default -> System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
        }
    }

    // Menampilkan daftar kategori yang ada
    public void tampilkanDaftarKategori() {
        System.out.println("=== Daftar Kategori ===");
        for (Barang barang : stokGudang) {
            System.out.println("- " + barang.getKategori());
        }
        System.out.println("=======================");
    }


    // Menu Kelola Kategori
    public void prosesMenuKategori() {
        int pilihan = -1;

        while (pilihan != 0) {
            menu.tampilkanMenuKategori();
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan karakter newline
                prosesPilihanKategori(pilihan);
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid. Silakan masukkan angka.");
                scanner.nextLine(); // Membersihkan input
            }
        }
    }

    // Memproses pilihan menu Kelola Kategori
    public void prosesPilihanKategori(int pilihan) {
        switch (pilihan) {
            case 1 -> tambahKategori();
            case 2 -> gantiNamaKategori();
            case 3 -> hapusKategori();
            case 0 -> {} // Kembali
            default -> System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
        }
    }

    // Menu Kelola Model.Barang
    public void prosesMenuBarang() {
        int pilihan = -1;

        while (pilihan != 0) {
            menu.tampilkanMenuBarang();
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan karakter newline
                prosesPilihanBarang(pilihan);
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid. Silakan masukkan angka.");
                scanner.nextLine(); // Membersihkan input
            }
        }
    }

    // Memproses pilihan menu Kelola Model.Barang
    public void prosesPilihanBarang(int pilihan) {
        switch (pilihan) {
            case 1 -> tambahNamaBarang();
            case 2 -> gantiNamaBarang();
            case 3 -> hapusBarang();
            case 0 -> {} // Kembali
            default -> System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
        }
    }

    // Menu Kelola Stok
    public void prosesMenuStok() {
        int pilihan = -1;

        while (pilihan != 0) {
            menu.tampilkanMenuStok();
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membersihkan karakter newline
                prosesPilihanStok(pilihan);
            } catch (InputMismatchException e) {
                System.out.println("Masukan tidak valid. Silakan masukkan angka.");
                scanner.nextLine(); // Membersihkan input
            }
        }
    }

    // Memproses pilihan menu Kelola Stok
    public void prosesPilihanStok(int pilihan) {
        switch (pilihan) {
            case 1 -> tambahStok();
            case 2 -> kurangiStok();
            case 0 -> {} // Kembali
            default -> System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
        }
    }

    // Fungsi untuk menambahkan kategori baru
    public void tambahKategori() {
        // Meminta input dari pengguna untuk nama kategori baru
        System.out.print("Masukkan nama kategori baru: ");
        String kategoriBaru = scanner.nextLine();

        if (kategoriBaru.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        // Periksa apakah kategori sudah ada dalam stokGudang
        boolean kategoriExists = false;
        for (Barang barang : stokGudang) {
            if (barang.getKategori().equalsIgnoreCase(kategoriBaru)) {
                kategoriExists = true;
                break;
            }
        }

        // Jika kategori sudah ada, tampilkan pesan, jika tidak,
        // tambahkan kategori baru ke stokGudang
        if (kategoriExists) {
            System.out.println("Kategori '" + kategoriBaru + "' sudah ada.");
        } else {
            stokGudang.add(new Barang(kategoriBaru, null, 0)); // Inisialisasi dengan null dan 0
            System.out.println("Kategori '" + kategoriBaru + "' berhasil ditambahkan.");
            logPerubahan.add("+ Tambah Kategori: " + kategoriBaru);
        }

        tampilkanDaftarKategori();
    }

    // Fungsi untuk menghapus kategori
    public void hapusKategori() {
        tampilkanDaftarKategori();
        // Meminta pengguna memasukkan nama kategori yang akan dihapus
        System.out.print("Masukkan nama kategori yang akan dihapus: ");
        String kategoriHapus = scanner.nextLine();

        if (kategoriHapus.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        // Menginisialisasi variabel untuk menandai apakah kategori ditemukan
        boolean kategoriFound = false;

        // Melakukan iterasi melalui stokGudang untuk mencari barang dalam kategori yang akan dihapus
        for (int i = 0; i < stokGudang.size(); i++) {
            // Mengambil barang pada indeks i dari stokGudang
            Barang barang = stokGudang.get(i);
            // Memeriksa apakah kategori barang saat ini sama dengan kategori yang akan dihapus
            if (barang.getKategori().equalsIgnoreCase(kategoriHapus)) {
                // Menghapus barang dari stokGudang
                stokGudang.remove(i);
                // Mengurangi nilai i untuk mengakomodasi penghapusan elemen
                i--;
                // Menandai bahwa kategori ditemukan
                kategoriFound = true;
            }
        }

        // Menampilkan pesan berdasarkan apakah kategori ditemukan atau tidak
        if (kategoriFound) {
            System.out.println("Kategori '" + kategoriHapus + "' berhasil dihapus beserta semua barang di dalamnya.");
            // Menambahkan informasi perubahan ke logPerubahan
            logPerubahan.add("- Hapus Kategori: " + kategoriHapus);
        } else {
            System.out.println("Kategori '" + kategoriHapus + "' tidak ditemukan.");
        }
    }

    // Fungsi untuk mengganti nama kategori
    public void gantiNamaKategori() {
        tampilkanDaftarKategori();
        // Meminta input dari pengguna untuk nama kategori yang akan diganti dan nama kategori baru
        System.out.print("Masukkan nama kategori yang akan diganti: ");
        String kategoriLama = scanner.nextLine();
        System.out.print("Masukkan nama kategori baru: ");
        String kategoriBaru = scanner.nextLine();

        if (kategoriLama.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        if (kategoriBaru.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        boolean updated = false;

        // Mencari barang dengan nama kategori yang sesuai dan mengganti namanya dengan kategoriBaru
        for (Barang barang : stokGudang) {
            if (barang.getKategori().equalsIgnoreCase(kategoriLama)) {
                barang.setKategori(kategoriBaru);
                updated = true;
            }
        }

        // Jika kategori berhasil diubah, tambahkan catatan perubahan
        if (updated) {
            System.out.println("Nama kategori berhasil diganti.");
            logPerubahan.add("* Ganti Nama Kategori: " + kategoriLama + " -> " + kategoriBaru);
        } else {
            System.out.println("Kategori '" + kategoriLama + "' tidak ditemukan.");
        }
    }

    // Fungsi untuk menambahkan stok barang
    public void tambahStok() {
        // Meminta input dari pengguna untuk nama kategori dan nama barang
        System.out.print("Masukkan nama kategori: ");
        String kategori = scanner.nextLine();
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();

        if (kategori.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        // Mencari barang yang sesuai dengan nama kategori dan nama barang
        Barang barangToUpdate = null;
        for (Barang barang : stokGudang) {
            if (barang.getKategori().equalsIgnoreCase(kategori) && barang.getNama() != null && barang.getNama().equalsIgnoreCase(namaBarang)) {
                barangToUpdate = barang;
                break;
            }
        }

        // Jika barang ditemukan, meminta input jumlah stok yang akan ditambahkan
        if (barangToUpdate != null) {
            int jumlahStok;
            while (true) {
                try {
                    System.out.print("Masukkan jumlah stok yang akan ditambahkan: ");
                    jumlahStok = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan karakter newline

                    if (jumlahStok < 0) {
                        System.out.println("Jumlah stok harus lebih dari atau sama dengan 0.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Masukan tidak valid. Masukkan angka.");
                    scanner.nextLine(); // Membersihkan input
                }
            }

            // Menambahkan jumlah stok yang dimasukkan ke stok barang yang ada
            barangToUpdate.setStok(barangToUpdate.getStok() + jumlahStok); // Update stok value
            System.out.println("Stok berhasil ditambahkan.");
            logPerubahan.add("+ Tambah Stok: " + namaBarang + " (" + jumlahStok + ")");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    // Fungsi untuk mengurangi stok barang
    public void kurangiStok() {
        // Meminta input dari pengguna untuk nama kategori dan nama barang
        System.out.print("Masukkan nama kategori: ");
        String kategori = scanner.nextLine();
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();

        if (kategori.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        // Mencari barang yang sesuai dengan nama kategori dan nama barang
        Barang barangToUpdate = null;
        for (Barang barang : stokGudang) {
            if (barang.getKategori().equalsIgnoreCase(kategori) && barang.getNama() != null && barang.getNama().equalsIgnoreCase(namaBarang)) {
                barangToUpdate = barang;
                break;
            }
        }

        // Jika barang ditemukan, meminta input jumlah stok yang akan dikurangi
        if (barangToUpdate != null) {
            int jumlahStok;
            while (true) {
                try {
                    System.out.print("Masukkan jumlah stok yang akan dikurangi: ");
                    jumlahStok = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan karakter newline
                    if (jumlahStok < 0) {
                        System.out.println("Jumlah stok harus lebih dari atau sama dengan 0.");
                    } else if (jumlahStok > barangToUpdate.getStok()) {
                        System.out.println("Jumlah stok yang akan dikurangi melebihi stok yang ada.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Masukan tidak valid. Masukkan angka.");
                    scanner.nextLine(); // Membersihkan input
                }
            }

            // Mengurangi jumlah stok yang dimasukkan dari stok barang yang ada
            barangToUpdate.setStok(barangToUpdate.getStok() - jumlahStok); // Update stok value
            System.out.println("Stok berhasil dikurangi.");
            logPerubahan.add("- Kurangi Stok: " + namaBarang + " (" + jumlahStok + ")");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    // Fungsi untuk mencari barang berdasarkan nama
    public void cariBarang() {
        // Meminta input dari pengguna untuk nama barang yang akan dicari
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();

        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        boolean found = false;

        // Mencari barang dengan nama yang sesuai dan menampilkan informasi jika ditemukan
        for (Barang barang : stokGudang) {
            if (barang.getNama() != null && barang.getNama().equalsIgnoreCase(namaBarang)) {
                System.out.println("Nama Kategori: " + barang.getKategori());
                System.out.println("Nama Barang: " + barang.getNama());
                System.out.println("Stok Barang: " + barang.getStok());
                found = true;
                break;
            }
        }

        // Jika barang tidak ditemukan, tampilkan pesan
        if (!found) {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    // Fungsi untuk mengganti nama barang
    public void gantiNamaBarang() {
        // Meminta input dari pengguna untuk nama barang lama dan nama barang baru
        System.out.print("Masukkan nama barang yang akan diganti: ");
        String namaBarangLama = scanner.nextLine();
        System.out.print("Masukkan nama barang baru: ");
        String namaBarangBaru = scanner.nextLine();

        if (namaBarangLama.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        if (namaBarangBaru.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        boolean updated = false;

        // Mencari barang dengan nama barang lama yang sesuai, kemudian mengganti namanya dengan yang baru
        for (Barang barang : stokGudang) {
            if (barang.getNama() != null && barang.getNama().equalsIgnoreCase(namaBarangLama)) {
                barang.setNama(namaBarangBaru);
                updated = true;
                break;
            }
        }

        // Jika nama barang berhasil diganti, tambahkan catatan perubahan
        if (updated) {
            System.out.println("Nama barang berhasil diganti.");
            logPerubahan.add("* Ganti Nama Barang: " + namaBarangLama + " -> " + namaBarangBaru);
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    public void tambahNamaBarang() {
        // Meminta input dari pengguna untuk nama kategori
        System.out.print("Masukkan nama kategori: ");
        String kategori = scanner.nextLine();

        if (kategori.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        // Periksa apakah kategori sudah ada dalam stokGudang
        boolean kategoriExists = false;
        for (Barang barang : stokGudang) {
            if (barang.getKategori().equalsIgnoreCase(kategori)) {
                kategoriExists = true;
                break;
            }
        }

        // Jika kategori tidak ditemukan, tampilkan pesan dan kembalikan
        if (!kategoriExists) {
            System.out.println("Kategori '" + kategori + "' tidak ditemukan.");
            return;
        }

        // Meminta input dari pengguna untuk nama barang yang akan ditambahkan
        System.out.print("Masukkan nama barang yang akan ditambahkan: ");
        String namaBarang = scanner.nextLine();

        // Mencari barang dengan nama yang sesuai di dalam kategori yang ditentukan
        boolean barangExists = false;
        for (Barang barang : stokGudang) {
            if (barang.getKategori().equalsIgnoreCase(kategori) && barang.getNama() != null && barang.getNama().equalsIgnoreCase(namaBarang)) {
                barangExists = true;
                break;
            }
        }

        // Jika barang sudah ada dalam kategori tersebut, tampilkan pesan
        if (barangExists) {
            System.out.println("Barang '" + namaBarang + "' sudah ada dalam kategori '" + kategori + "'.");
            return;
        }

        // Tambahkan barang baru dalam kategori jika nama tidak kosong dan stok awal > 0
        if (!namaBarang.isEmpty()) {
            // Meminta input dari pengguna untuk jumlah stok awal
            System.out.print("Masukkan jumlah stok awal untuk " + namaBarang + ": ");
            int jumlahStok = scanner.nextInt();
            scanner.nextLine(); // Membersihkan karakter newline setelah memasukkan int

            // Memeriksa apakah jumlah stok awal lebih dari 0
            if (jumlahStok > 0) {
                // Menambahkan barang baru ke dalam stokGudang
                stokGudang.add(new Barang(kategori, namaBarang, jumlahStok));
                System.out.println("Barang '" + namaBarang + "' dalam kategori '" + kategori + "' berhasil ditambahkan.");
                // Menambahkan informasi perubahan ke logPerubahan
                logPerubahan.add("+ Tambah Barang: " + namaBarang + " (" + jumlahStok + ")");
            } else {
                System.out.println("Stok awal harus lebih dari 0.");
            }
        } else {
            System.out.println("Nama barang tidak boleh kosong.");
        }
    }

    // Fungsi untuk menghapus barang berdasarkan nama
    public void hapusBarang() {
        // Meminta pengguna memasukkan nama kategori
        System.out.print("Masukkan nama kategori: ");
        String kategori = scanner.nextLine();

        // Meminta pengguna memasukkan nama barang yang akan dihapus
        System.out.print("Masukkan nama barang yang akan dihapus: ");
        String namaBarang = scanner.nextLine();

        if (kategori.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menampilkan pesan dan menghentikan eksekusi lebih lanjut
        }

        // Inisialisasi variabel untuk melacak apakah barang ditemukan atau tidak
        boolean barangFound = false;

        // Iterasi melalui daftar stok gudang
        for (int i = 0; i < stokGudang.size(); i++) {
            // Mendapatkan objek Model.Barang dari daftar stok gudang
            Barang barang = stokGudang.get(i);

            // Memeriksa apakah objek Model.Barang sesuai dengan kriteria kategori dan nama yang dimasukkan pengguna
            if (barang.getKategori().equalsIgnoreCase(kategori) && barang.getNama() != null && barang.getNama().equalsIgnoreCase(namaBarang)) {
                // Menghapus objek Model.Barang dari daftar stok gudang
                stokGudang.remove(i);

                // Mengurangi nilai i untuk menyesuaikan indeks setelah penghapusan
                i--;

                // Menandai bahwa barang telah ditemukan dan dihapus
                barangFound = true;

                // Menambahkan catatan perubahan ke logPerubahan
                logPerubahan.add("- Hapus Barang: " + namaBarang);
            }
        }

        // Menampilkan pesan berdasarkan apakah barang ditemukan dan dihapus atau tidak
        if (barangFound) {
            System.out.println("Barang '" + namaBarang + "' berhasil dihapus.");
        } else {
            System.out.println("Barang '" + namaBarang + "' tidak ditemukan dalam kategori '" + kategori + "'.");
        }
    }

    // Mendefinisikan method untuk menampilkan stok barang di gudang
    public void lihatStok() {
        // Menampilkan judul informasi stok barang
        System.out.println("=== Stok Barang di Gudang ===");

        // Menginisialisasi variabel untuk menandai apakah ada barang yang ditampilkan
        boolean hasItems = false;

        // Iterasi melalui setiap barang dalam stokGudang
        for (Barang barang : stokGudang) {
            // Memeriksa apakah nama barang tidak null dan stok barang lebih dari 0
            if (barang.getNama() != null && barang.getStok() > 0) {
                // Menampilkan informasi
                System.out.println("Kategori: " + barang.getKategori());
                System.out.println("  Nama Barang: " + barang.getNama());
                System.out.println("  Stok Barang: " + barang.getStok());
                System.out.println("-----------------------------");
                // Menandai bahwa ada barang yang ditampilkan
                hasItems = true;
            }
        }

        // Menampilkan pesan jika tidak ada barang yang ditampilkan
        if (!hasItems) {
            System.out.println("Tidak ada barang di gudang.");
        }
    }

    // Fungsi untuk menampilkan log perubahan stok
    public void tampilkanLogPerubahan() {
        System.out.println("=== Log Perubahan Stok ===");

        // Membuat objek SimpleDateFormat untuk mengatur format tanggal dan waktu
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        // Menampilkan setiap catatan perubahan dalam logPerubahan beserta timestamp
        for (String log : logPerubahan) {
            String timestamp = dateFormat.format(new Date());
            System.out.println(timestamp + " | " + log);
        }
        System.out.println("=========================");
    }
}