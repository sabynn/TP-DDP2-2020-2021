package assignments.assignment3;

import java.util.Arrays;

abstract class ElemenFasilkom {
    // data fields ElemenFasilkom
    private String tipe;
    
    private String nama;

    private int friendship;

    private ElemenFasilkom[] telahMenyapa = new ElemenFasilkom[100];

    private int jumlahTelahDisapa;

    // constructor ElemenFasilkom
    public ElemenFasilkom(String tipe, String nama) {
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
    private boolean cekTelahDisapa(ElemenFasilkom elemen){
        for (ElemenFasilkom ef : telahMenyapa) {
            if (ef==null) break;
            else if (ef.equals(elemen)) return true;
        }
        return false;
    }

    //
    private boolean cekMahasiswaDanDosen(ElemenFasilkom e){
        // cek Mahasiswa menyapa Dosen
        if (this.getTipe().equals("Mahasiswa") && e.getTipe().equals("Dosen")) {
            if (((Dosen) e).getMataKuliah() != null){
                // cek apakah Dosen mengajar MataKuliah yang diambil Mahasiswa
                return Arrays.asList((((Mahasiswa) this).getDaftarMataKuliah())).contains(((Dosen) e).getMataKuliah());
            }
        }// cek Dosen menyapa Mahasiswa
        else if (this.getTipe().equals("Dosen") && e.getTipe().equals("Mahasiswa")) {
            if (((Dosen) this).getMataKuliah() != null) {
                // cek apakah Dosen mengajar MataKuliah yang diambil Mahasiswa
                return Arrays.asList((((Mahasiswa) e).getDaftarMataKuliah())).contains(((Dosen) this).getMataKuliah());
            }
        }
        // return false jika ElemenFasilkom yang saling menyapa bukan Mahasiswa dan Dosen
        return false;
    }

    public void menyapa(ElemenFasilkom elemenFasilkom) {
        if (!(cekTelahDisapa(elemenFasilkom))) {
            // menambahkan orang yang disapa ke dalam array telahMenyapa dan mencetak output
            telahMenyapa[jumlahTelahDisapa++] = elemenFasilkom;
            elemenFasilkom.telahMenyapa[elemenFasilkom.jumlahTelahDisapa++] = this;
            System.out.println(this.nama + " menyapa dengan " + elemenFasilkom);

            // menambah friendship dari Mahasiswa dan Dosen yang terhubung matkul yang sama jika saling menyapa
            if (this.cekTelahDisapa(elemenFasilkom) && this.cekMahasiswaDanDosen(elemenFasilkom)){
                this.friendship += 2;
                elemenFasilkom.friendship += 2;
            }

        } else {
            // handle jika elemenFasilkom tertentu sudah disapa sebelumnya
            System.out.println("[DITOLAK] " + this.nama + " telah menyapa " + elemenFasilkom + " hari ini");
        }
    }

    public void resetMenyapa() {
        // menghapus daftar orang yang telah disapa dan menghapus daftar elemenKantin yang makanannya telah dibeli
        telahMenyapa = new ElemenFasilkom[100];
        // mereset jumlah orang yang telah disapa dan jumlah elemenKantin yang makanannya telah dibeli
        jumlahTelahDisapa = 0;
    }

    public void membeliMakanan(ElemenFasilkom pembeli, ElemenFasilkom penjual, String namaMakanan) {
        // explicit casting untuk mengubah penjual dari ElemenFasilkom menjadi ElemenKantin
        ElemenKantin p = (ElemenKantin) penjual;
        // mendapatkan object Makanan
        Makanan makanan = p.getMakanan(namaMakanan);
        if (makanan != null){
            // cetak output sesuai format serta menambah friendship dari pembeli dan penjual sebesar 1
            System.out.printf("%s berhasil membeli %s seharga %d\n", pembeli, makanan, makanan.getHarga());
            pembeli.friendship++;
            penjual.friendship++;
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