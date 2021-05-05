package assignments.assignment3;

class Makanan {

    // data field Makanan
    private String nama;

    private long harga;

    Makanan(String nama, long harga) {
        // Constructor Makanan
        this.nama = nama;
        this.harga = harga;
    }

    // getter
    public long getHarga() {
        return harga;
    }

    public String toString() {
        // mengembalikan
        return this.nama;
    }
}