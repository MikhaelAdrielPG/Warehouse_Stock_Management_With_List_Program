package Controller;

import Model.Barang;
import View.Menu;
import Model.Transaksi;
import java.util.*;

// Definisi kelas utama Controller.ManajemenStokGudang
public class ManajemenStokGudang {
    // List untuk menyimpan stok barang dan log perubahan
    public List<Barang> stokGudang = new ArrayList<>();
    public List<Transaksi> transaksiGudang = new ArrayList<>();
    public List<String> logPerubahan = new ArrayList<>();
    public Scanner scanner = new Scanner(System.in);
    public Menu menu = new Menu();

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
            case 6 -> lihatTransaksi();
            case 7 -> tampilkanLogPerubahan();
            case 0 -> {} // Keluar
            default -> System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
        }
    }

    public void tampilkanDaftarKategori() {
        System.out.println("=== Daftar Kategori ===");

        // Daftar kategori yang sudah ditampilkan sebelumnya
        List<String> kategoriTampil = new ArrayList<>();

        for (Barang barang : stokGudang) {
            String kategori = barang.getKategori();

            // Jika kategori belum ditampilkan sebelumnya, tambahkan ke daftar dan tampilkan
            if (!kategoriTampil.contains(kategori)) {
                System.out.println("- " + kategori);
                kategoriTampil.add(kategori);
            }
        }

        System.out.println("-----------------------");
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

        // Periksa apakah input kategori kosong
        if (kategoriBaru.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
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
            // Tambahkan kategori baru ke stokGudang dan catat perubahan
            stokGudang.add(new Barang(kategoriBaru, null)); // Inisialisasi dengan null dan 0
            System.out.println("Kategori '" + kategoriBaru + "' berhasil ditambahkan.");
            logPerubahan.add("+ Tambah Kategori: " + kategoriBaru);
        }

        // Tampilkan daftar kategori setelah operasi
        tampilkanDaftarKategori();
    }

    // Fungsi untuk menghapus kategori
    public void hapusKategori() {
        // Menampilkan daftar kategori sebelum penghapusan
        tampilkanDaftarKategori();

        // Meminta pengguna memasukkan nama kategori yang akan dihapus
        System.out.print("Masukkan nama kategori yang akan dihapus: ");
        String kategoriHapus = scanner.nextLine();

        // Periksa apakah input kategori kosong
        if (kategoriHapus.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
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
        // Menampilkan daftar kategori sebelum penggantian
        tampilkanDaftarKategori();

        // Meminta input dari pengguna untuk nama kategori yang akan diganti dan nama kategori baru
        System.out.print("Masukkan nama kategori yang akan diganti: ");
        String kategoriLama = scanner.nextLine();
        System.out.print("Masukkan nama kategori baru: ");
        String kategoriBaru = scanner.nextLine();

        // Periksa apakah input nama kategori lama kosong
        if (kategoriLama.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Periksa apakah input nama kategori baru kosong
        if (kategoriBaru.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Variabel untuk menandai apakah penggantian berhasil dilakukan
        boolean updated = false;

        // Mencari barang dengan nama kategori yang sesuai dan mengganti namanya dengan kategoriBaru
        for (Barang barang : stokGudang) {
            if (barang.getKategori().equalsIgnoreCase(kategoriLama)) {
                // Mengganti nama kategori barang
                barang.setKategori(kategoriBaru);
                updated = true;
            }
        }

        // Menampilkan pesan berdasarkan apakah penggantian berhasil atau tidak
        if (updated) {
            System.out.println("Nama kategori berhasil diganti.");
            // Menambahkan catatan perubahan ke logPerubahan
            logPerubahan.add("* Ganti Nama Kategori: " + kategoriLama + " -> " + kategoriBaru);
        } else {
            System.out.println("Kategori '" + kategoriLama + "' tidak ditemukan.");
        }
    }

    // Fungsi untuk menambahkan stok barang
    public void tambahStok() {
        // Meminta input dari pengguna untuk nama barang
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();

        // Periksa apakah input nama barang kosong
        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Mencari barang yang sesuai dengan nama barang
        Barang barangToUpdate = null;
        Transaksi transaksiToUpdate = null;
        for (Barang barang : stokGudang) {
            for (Transaksi transaksi : transaksiGudang) {
                // Memeriksa apakah terdapat transaksi dan barang dengan nama yang sesuai
                if (transaksi.getNama().equalsIgnoreCase(namaBarang)
                        && barang.getNama() != null
                        && barang.getNama().equalsIgnoreCase(namaBarang)
                        && barang.getNama().equalsIgnoreCase(transaksi.getNama())) {
                    barangToUpdate = barang;
                    transaksiToUpdate = transaksi;
                    break;
                }
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

                    // Memeriksa apakah jumlah stok valid
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

            // Menambahkan jumlah stok yang dimasukkan ke transaksi stok yang ada
            int sum = Integer.parseInt(transaksiToUpdate.getStok()) + jumlahStok;
            transaksiToUpdate.setStok(String.valueOf(sum));

            // Menambahkan transaksi baru ke transaksiGudang
            transaksiGudang.add(new Transaksi(barangToUpdate.getNama(), "+" + sum));

            // Menampilkan pesan berhasil dan mencatat perubahan ke logPerubahan
            System.out.println("Stok berhasil ditambahkan.");
            logPerubahan.add("+ Tambah Stok: " + namaBarang + " (" + jumlahStok + ")");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    // Fungsi untuk mengurangi stok barang
    public void kurangiStok() {
        // Meminta input dari pengguna untuk nama barang
        System.out.print("Masukkan nama barang: ");
        String namaBarang = scanner.nextLine();

        // Periksa apakah input nama barang kosong
        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Mencari barang yang sesuai dengan nama barang
        Barang barangToUpdate = null;
        Transaksi transaksiToUpdate = null;
        for (Barang barang : stokGudang) {
            for (Transaksi transaksi : transaksiGudang) {
                // Memeriksa apakah terdapat transaksi dan barang dengan nama yang sesuai
                if (transaksi.getNama().equalsIgnoreCase(namaBarang)
                        && barang.getNama() != null
                        && barang.getNama().equalsIgnoreCase(namaBarang)
                        && barang.getNama().equalsIgnoreCase(transaksi.getNama())) {
                    barangToUpdate = barang;
                    transaksiToUpdate = transaksi;
                    break;
                }
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

                    // Memeriksa apakah jumlah stok valid dan tidak melebihi stok yang ada
                    if (jumlahStok < 0) {
                        System.out.println("Jumlah stok harus lebih dari atau sama dengan 0.");
                    } else if (jumlahStok > Integer.parseInt(transaksiToUpdate.getStok())) {
                        System.out.println("Jumlah stok yang akan dikurangi melebihi stok yang ada.");
                    } else {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Masukan tidak valid. Masukkan angka.");
                    scanner.nextLine(); // Membersihkan input
                }
            }

            // Mengurangi jumlah stok yang dimasukkan dari transaksi stok yang ada
            int minus = Integer.parseInt(transaksiToUpdate.getStok()) - jumlahStok;
            transaksiToUpdate.setStok(String.valueOf(minus));

            // Menambahkan transaksi baru ke transaksiGudang dengan tanda minus
            transaksiGudang.add(new Transaksi(barangToUpdate.getNama(), "-" + minus));

            // Menampilkan pesan berhasil dan mencatat perubahan ke logPerubahan
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

        // Periksa apakah input nama barang kosong
        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Variabel untuk menandai apakah barang ditemukan
        boolean found = false;

        // Mencari barang dengan nama yang sesuai dan menampilkan informasi jika ditemukan
        for (Barang barang : stokGudang) {
            for (Transaksi transaksi : transaksiGudang) {
                // Memeriksa apakah terdapat barang dan transaksi dengan nama yang sesuai
                if (barang.getNama() != null
                        && barang.getNama().equalsIgnoreCase(namaBarang)
                        && transaksi.getNama().equalsIgnoreCase(namaBarang)) {
                    System.out.println(" === Hasil Pencarian === ");
                    System.out.println("Nama Kategori: " + barang.getKategori());
                    System.out.println("Nama Barang: " + barang.getNama());
                    System.out.println("Stok Barang: " + transaksi.getStok());
                    System.out.println("-----------------------------");
                    found = true;
                    break; // Keluar dari loop jika barang ditemukan
                }
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

        // Periksa apakah input nama barang lama kosong
        if (namaBarangLama.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Periksa apakah input nama barang baru kosong
        if (namaBarangBaru.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Variabel untuk menandai apakah penggantian berhasil dilakukan
        boolean updated = false;

        // Mencari barang dengan nama barang lama yang sesuai,
        // kemudian mengganti namanya dengan yang baru
        for (Barang barang : stokGudang) {
            for (Transaksi transaksi : transaksiGudang) {
                // Memeriksa apakah terdapat barang dan transaksi dengan nama yang sesuai
                if (barang.getNama() != null
                        && barang.getNama().equalsIgnoreCase(namaBarangLama)
                        && transaksi.getNama().equalsIgnoreCase(namaBarangLama)) {
                    // Mengganti nama barang dan transaksi dengan yang baru
                    barang.setNama(namaBarangBaru);
                    transaksi.setNama(namaBarangBaru);
                    updated = true;
                    break; // Keluar dari loop jika nama barang berhasil diganti
                }
            }
        }

        // Menampilkan pesan berdasarkan apakah penggantian berhasil atau tidak
        if (updated) {
            System.out.println("Nama barang berhasil diganti.");
            // Menambahkan catatan perubahan ke logPerubahan
            logPerubahan.add("* Ganti Nama Barang: " + namaBarangLama + " -> " + namaBarangBaru);
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    // Fungsi untuk menambahkan nama barang dalam kategori tertentu
    public void tambahNamaBarang() {
        // Menampilkan daftar kategori sebelum menambahkan nama barang
        tampilkanDaftarKategori();

        // Meminta input dari pengguna untuk nama kategori
        System.out.print("Masukkan nama kategori: ");
        String kategori = scanner.nextLine();

        // Periksa apakah input nama kategori kosong
        if (kategori.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
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
                stokGudang.add(new Barang(kategori, namaBarang));
                transaksiGudang.add(new Transaksi(namaBarang, String.valueOf(jumlahStok)));
                System.out.println("Barang '" + namaBarang + "' dalam kategori '" + kategori + "' berhasil ditambahkan.");
                // Menambahkan informasi perubahan ke logPerubahan
                logPerubahan.add("+ Tambah Barang: " + namaBarang + " (" + jumlahStok + ")");
            } else {
                System.out.println("Stok awal harus lebih dari 0.");
            }
        } else {
            System.out.println("Nama barang tidak boleh kosong.");
        }

        // Menampilkan stok setelah penambahan barang
        lihatStok();
    }

    // Fungsi untuk menghapus barang berdasarkan nama
    public void hapusBarang() {
        // Meminta pengguna memasukkan nama barang yang akan dihapus
        System.out.print("Masukkan nama barang yang akan dihapus: ");
        String namaBarang = scanner.nextLine();

        // Periksa apakah input nama barang kosong
        if (namaBarang.isEmpty()) {
            System.out.println("Input tidak boleh kosong. Silakan coba lagi.");
            return; // Menghentikan eksekusi lebih lanjut jika input kosong
        }

        // Inisialisasi variabel untuk melacak apakah barang ditemukan atau tidak
        boolean barangFound = false;

        // Iterasi melalui daftar stok gudang
        for (int i = 0; i < stokGudang.size(); i++) {
            // Mendapatkan objek Barang dari daftar stok gudang
            Barang barang = stokGudang.get(i);

            // Memeriksa apakah objek Barang sesuai dengan kriteria nama yang dimasukkan pengguna
            if (barang.getNama() != null && barang.getNama().equalsIgnoreCase(namaBarang)) {
                // Menghapus objek Barang dari daftar stok gudang
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
            System.out.println("Barang '" + namaBarang + "' tidak ditemukan.");
        }
    }

    // Fungsi untuk melihat transaksi barang di gudang
    public void lihatTransaksi() {
        // Menampilkan judul untuk transaksi barang di gudang
        System.out.println("=== Transaksi Barang Di Gudang ===");

        // Menampilkan header kolom "Data" dan "Stok"
        System.out.println("Data" + "\t\t\t\t\t" + "Stok");

        // Iterasi melalui daftar transaksiGudang
        for (Transaksi transaksi : transaksiGudang) {
            // Memeriksa apakah nilai stok cocok dengan pola yang menunjukkan transaksi penambahan atau pengurangan
            if (transaksi.getStok().matches("^[+-].*")) {
                /*
                * Jadi, secara keseluruhan,
                * kode regex "^[+-].*" ini akan mencocokkan string yang dimulai dengan tanda plus atau tanda minus,
                * dan diikuti oleh nol atau lebih karakter apa pun. Dengan kata lain,
                * ini akan mencocokkan string yang memiliki awalan seperti +abc, -xyz, +123, -abc123, dan sebagainya.
                * */
                // Menampilkan nama dan nilai stok transaksi
                System.out.println( transaksi.getNama() + "\t\t\t\t\t" +  transaksi.getStok());
            }
        }

        // Menampilkan garis pemisah
        System.out.println("---------------------------------");
    }

    // Fungsi untuk menampilkan stok barang di gudang
    public void lihatStok() {
        // Menampilkan judul informasi stok barang
        System.out.println("=== Stok Barang di Gudang ===");

        // Menginisialisasi variabel untuk menandai apakah ada barang yang ditampilkan
        boolean hasItems = false;

        // Iterasi melalui setiap barang dalam stokGudang
        for (Barang barang : stokGudang) {
            // Memeriksa apakah nama barang tidak null
            if (barang.getNama() != null) {
                // Menampilkan kategori dan nama barang
                System.out.println("Kategori: " + barang.getKategori());
                System.out.println("  Nama Barang: " + barang.getNama());
            }

            // Iterasi melalui setiap transaksi dalam transaksiGudang
            for (Transaksi transaksi : transaksiGudang) {
                // Memeriksa apakah nama barang tidak null dan stok barang lebih dari 0
                if (barang.getNama() != null
                        && barang.getNama().equalsIgnoreCase(transaksi.getNama())
                        && !transaksi.getStok().matches("^[+-].*")) {
                    /*
                    * Jadi, secara keseluruhan,
                    * bagian ini dari kode akan menghasilkan true
                    * jika string transaksi.getStok() tidak sesuai dengan pola regex "^[+-].*",
                    * yaitu jika string tersebut tidak dimulai dengan tanda plus atau tanda minus,
                    * atau mengandung nol karakter setelah tanda plus atau tanda minus.
                    * */
                    // Menampilkan informasi stok barang
                    System.out.println("  Stok Barang: " + transaksi.getStok());
                }

                // Menandai bahwa ada barang yang ditampilkan
                hasItems = true;
            }

            // Menampilkan baris kosong antara setiap barang
            System.out.println();
        }

        // Menampilkan pesan jika tidak ada barang yang ditampilkan
        if (!hasItems) {
            System.out.println("Tidak ada barang di gudang.");
        }

        // Menampilkan garis pemisah
        System.out.println("---------------------------");
    }

    // Fungsi untuk menampilkan log perubahan stok
    public void tampilkanLogPerubahan() {
        // Menampilkan judul untuk log perubahan stok
        System.out.println("=== Log Perubahan Stok ===");

        // Iterasi melalui setiap catatan perubahan dalam logPerubahan dan menampilkannya
        for (String log : logPerubahan) {
            System.out.println(log);
        }

        // Menampilkan garis pemisah setelah semua catatan perubahan ditampilkan
        System.out.println("=========================");
    }
}