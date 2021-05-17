package assignments.assignment3;

public class Makanan {
    // data fields Makanan
    private String nama;
    private long harga;

    public Makanan(String nama, long harga) {
        // Constructor Makanan
        this.nama = nama;
        this.harga = harga;
    }

    // getter
    public long getHarga() {
        return harga;
    }

    public String toString() {
        // mengembalikan nama makanan
        return this.nama;
    }
}