package assignments.assignment3;

abstract class ElemenFasilkom {
    // data fields ElemenFasilkom
    private String tipe;
    
    private String nama;

    private int friendship;

    private ElemenFasilkom[] telahMenyapa = new ElemenFasilkom[100];

    private ElemenKantin[] telahMembeliMakanan = new ElemenKantin[100];

    private int jumlahTelahDisapa;

    private int jumlahTelahDibeli;

    // constructor ElemenFasilkom
    ElemenFasilkom(String tipe, String nama) {
        this.tipe = tipe;
        this.nama = nama;
    }

    // getter
    public String getTipe() {
        return tipe;
    }

    public int getFriendship() {
        return friendship;
    }

    public int getJumlahTelahDisapa() {
        return jumlahTelahDisapa;
    }

    public int getJumlahTelahDibeli() {
        return jumlahTelahDibeli;
    }

    public ElemenKantin[] getTelahMembeliMakanan() { return telahMembeliMakanan; }

    // setter friendship
    public void setFriendship(int newFriendship){
        // memastikan friendship bernilain antara 0-100
        if (newFriendship > 100){
            this.friendship = 100;
        } else if (newFriendship < 0){
            this.friendship = 0;
        }else{
            this.friendship = newFriendship;
        }
    }

    // method untuk cek apakah elemenFasilkom tertentu sudah disapa sebelumnya
    public boolean cekTelahDisapa(ElemenFasilkom elemen){
        for (ElemenFasilkom ef : telahMenyapa) {
            if (ef==null) break;
            else if (ef.equals(elemen)) return true;
        }
        return false;
    }

    public void menyapa(ElemenFasilkom elemenFasilkom) {
        if (!(cekTelahDisapa(elemenFasilkom))) {
            // menambahkan orang yang disapa ke dalam array telahMenyapa dan mencetak output
            telahMenyapa[jumlahTelahDisapa++] = elemenFasilkom;
            elemenFasilkom.telahMenyapa[elemenFasilkom.jumlahTelahDisapa++] = this;
            System.out.println(this.nama + " menyapa dengan " + elemenFasilkom);
        } else {
            // handle jika elemenFasilkom tertentu sudah disapa sebelumnya
            System.out.println("[DITOLAK] " + this.nama + " telah menyapa " + elemenFasilkom + " hari ini");
        }
    }

    public void resetMenyapa() {
        // menghapus daftar orang yang telah disapa dan menghapus daftar elemenKantin yang makanannya telah dibeli
        telahMenyapa = new ElemenFasilkom[100];
        telahMembeliMakanan = new ElemenKantin[100];
        // mereset jumlah orang yang telah disapa dan jumlah elemenKantin yang makanannya telah dibeli
        jumlahTelahDisapa = 0;
        jumlahTelahDibeli = 0;
    }

    public void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan) {
        // explicit casting untuk mengubah pejual dari ElemenFasilkom menjadi ElemenKantin
        ElemenKantin p = (ElemenKantin) penjual;
        // mendapatkan object Makanan
        Makanan makanan = p.getMakanan(namaMakanan);
        if (makanan != null){
            // menambahkan elemenKantin ke daftar elemenKantin yang makanannya telah dibeli dan mencetak output
            telahMembeliMakanan[jumlahTelahDibeli++] = p;
            System.out.printf("%s berhasil membeli %s seharga %d\n", pembeli, makanan, makanan.getHarga());
        }else{
            // handle output jika penjual tidak menjual makanan
            System.out.printf("[DITOLAK] %s tidak menjual %s\n", penjual, namaMakanan);
        }
    }

    public String toString() {
        // mengembalikan nama ElemenFasilkom
        return this.nama;
    }
}