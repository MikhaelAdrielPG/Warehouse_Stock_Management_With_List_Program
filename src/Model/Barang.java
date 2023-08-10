package Model;

public class Barang {
    private String kategori;
    private String nama;

    public Barang(String kategori, String nama) {
        this.kategori = kategori;
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}