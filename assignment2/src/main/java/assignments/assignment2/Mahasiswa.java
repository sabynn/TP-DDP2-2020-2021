package assignments.assignment2;


public class Mahasiswa {
    private MataKuliah[] mataKuliah = new MataKuliah[10];
    private String[] masalahIRS;
    private int totalSKS;
    private String nama;
    private String jurusan;
    private long npm;
    private int jumlahMatkul;

    public Mahasiswa(String nama, long npm){
        // constructor untuk inisiasi atribut Mahasiswa
        this.nama = nama;
        this.npm = npm;
        this.masalahIRS = new String[20];
    }

    // getter untuk mengembalikan nilai dari atribut Mahasiswa
    public long getNPM(){
        return npm;
    }

    public MataKuliah[] getMatKul(){
        return mataKuliah;
    }

    public int getJumlahMatkul(){
        return jumlahMatkul;
    }

    // method untuk menghitung total SKS dan mereturn nilainya
    public int getTotalSKS(){
        this.totalSKS = 0;
        for (MataKuliah mk: mataKuliah) {
            if (mk != null) this.totalSKS += mk.getSks();
            // menghentikan looping jika isi Array null
            else break;
        }
        return this.totalSKS;
    }

    // method untuk mengembalikan nama jurusan sesuai kode jurusan mahasiswa
    public String getJurusan(){
        int kodeJurusan = (int) (this.npm / 10E+9) % 100;
        if (kodeJurusan == 1) this.jurusan = "Ilmu Komputer";
        else if (kodeJurusan == 2) this.jurusan = "Sistem Informasi";
        return this.jurusan;
    }

    public String[] getMasalahIRS() {
        cekIRS();
        return masalahIRS;
    }

    // method untuk cek apakah mata kuliah tertentu diambil oleh mahasiswa
    public boolean cekMatkulDiambil(MataKuliah mk){
        for (MataKuliah m: this.mataKuliah){
            if (mk.equals(m)) {
                return true;
            }
        }
        return false;
    }

    // method untuk menambahkan mata kuliah
    public void addMatkul(MataKuliah matkul){
        // handle output jika matkul sudah diambil sebelumnya
        if (this.cekMatkulDiambil(matkul)){
            System.out.println("[DITOLAK] " + matkul +" telah diambil sebelumnya.");
        }
        // handle output jika kapasitas matkul sudah penuh
        else if (matkul.getJumlahMahasiswa() == matkul.getKapasitas()) {
            System.out.println("[DITOLAK] " + matkul + " telah penuh kapasitasnya.");
        }
        // handle output jika matkul yang diambil sudah 10
        else if(this.jumlahMatkul == 10){
            System.out.println("[DITOLAK] Maksimal mata kuliah yang diambil hanya 10.");
        }
        else {
            // menambahkan matkul ke Array mataKuliah dan menambahkan nilai jumlahMatkul
            this.mataKuliah[this.jumlahMatkul++] = matkul;
            // menambahkan mahasiswa ke Matakuliah dengan memanggil method addMahasiswa
            matkul.addMahasiswa(this);
        }
    }

    public void dropMatkul(MataKuliah matkul){
        if (this.cekMatkulDiambil(matkul)){
            // menginisiasi Arrays baru untuk menampung matkul selain matkul yang didrop
            MataKuliah[] temp = new MataKuliah[10];
            int c = 0;
            // looping untuk memasukkan matkul selain matkul yang didrop ke Arrays temp
            for (MataKuliah mk: this.mataKuliah){
                if (mk != null && !(mk.equals(matkul))) {
                    temp[c++] = mk;
                }
            }
            // mengubah reference Arrays mataKuliah ke temp
            this.mataKuliah = temp;
            // mengurangi nilai jumlahMatkul
            this.jumlahMatkul--;
            //menghilangkan mahasiswa dari matakuliah dengan memanggil method dropMahasiswa
            matkul.dropMahasiswa(this);
        }
        // handle output jika matkul belum pernah diambil
        else System.out.println("[DITOLAK] " + matkul + " belum pernah diambil.");
    }

    public void cekIRS(){
        // assign singkatanJurusan sesuai jurusan mahasiswa
        String singkatanJurusan = "";
        if (jurusan.equals("Ilmu Komputer")) singkatanJurusan = "IK";
        else if (jurusan.equals("Sistem Informasi")) singkatanJurusan = "SI";

        // counter untuk mengatur index Arrays masalahIRS
        int c = 1;

        // penambahan String ke masalahIRS jika sks melebihi 24
        if (totalSKS > 24) {
            this.masalahIRS[c-1] = (c++) + ". SKS yang Anda ambil lebih dari 24";
        }

        //looping untuk menambahkan String ke masalahIRS jika mata kuliah yang diambil tidak sesuai jurusan
        for (MataKuliah mk: this.mataKuliah){
            if (mk == null) break;
            else if (!(mk.getKode().equals(singkatanJurusan)) && !(mk.getKode().equals("CS"))){
                this.masalahIRS[c-1] = (c++) + ". Mata Kuliah " + mk + " tidak dapat diambil jurusan " + singkatanJurusan;
            }
        }
    }

    // mengembalikan nama Mahasiswa
    public String toString() {
        return this.nama;
    }

}
