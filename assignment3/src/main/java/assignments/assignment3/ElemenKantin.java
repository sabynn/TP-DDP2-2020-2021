package assignments.assignment3;

class ElemenKantin extends ElemenFasilkom {
    // data fields ElemenKantin
    private Makanan[] daftarMakanan = new Makanan[10];

    private int jumlahMakanan;

    ElemenKantin(String nama) {
        // constructor ElemenKantin
        super("ElemenKantin", nama);
    }

    public Makanan getMakanan(String makanan){
        // looping untuk mendapat Makanan sesuai dengan nama makanan yang diinginkan
        for (Makanan m: daftarMakanan){
            if(m == null) break;
            else if (m.toString().equals(makanan)) return m;
        }
        return null;
    }

    public void setMakanan(String nama, long harga) {
        // handle jika makanan sudah pernah didaftarkan
        if (getMakanan(nama) != null){
            System.out.printf("[DITOLAK] %s sudah pernah terdaftar\n", nama);
        } else {
            //membuat objek Makanan dengan parameter nama dan harga dan memasukkan ke dalam daftarMakanan
            daftarMakanan[jumlahMakanan++] = new Makanan(nama, harga);
            System.out.printf("%s telah mendaftarkan makanan %s dengan harga %d\n", this, nama, harga);
        }
    }
}