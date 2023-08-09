package Model;

public class Barang {
    private String kategori;
    private String nama;
    private int stok;

    public Barang(String kategori, String nama, int stok) {
        this.kategori = kategori;
        this.nama = nama;
        this.stok = stok;
    }

    // Getter dan setter untuk kategori
    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    // Getter dan setter untuk nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter dan setter untuk stok
    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}