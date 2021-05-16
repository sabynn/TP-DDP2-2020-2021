package assignments.assignment3;

class MataKuliah {
    // data fields MataKuliah
    private String nama;
    private int kapasitas;
    private Dosen dosen;
    private Mahasiswa[] daftarMahasiswa;
    private int jumlahMahasiswa;

    // constructor MataKuliah
    public MataKuliah(String nama, int kapasitas) {
        this.nama = nama;
        this.kapasitas = kapasitas;
        this.daftarMahasiswa = new Mahasiswa[kapasitas];
    }

    // getter
    public int getKapasitas() {
        return kapasitas;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public int getJumlahMahasiswa() {
        return jumlahMahasiswa;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return daftarMahasiswa;
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        // menambahkan mahasiswa ke Arrays daftarMahasiswa dan menambahkan nilai jumlahMahasiswa
        daftarMahasiswa[jumlahMahasiswa++] = mahasiswa;
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        // menginisiasi Arrays baru untuk menampung mahasiswa selain mahasiswa yang drop matkul
        Mahasiswa[] temp = new Mahasiswa[kapasitas];
        int c = 0;
        // looping untuk memasukkan matkul selain mahasiswa yang didrop ke Arrays temp
        for (Mahasiswa m: this.daftarMahasiswa){
            if (m != null && !(m.equals(mahasiswa))) {
                temp[c++] = m;
            }
        }
        // mengubah reference Arrays mahasiswa ke temp
        this.daftarMahasiswa = temp;
        // mengurangi nilai jumlahMahasiswa
        this.jumlahMahasiswa--;
    }

    public void addDosen(Dosen dosen) {
        // set dosen sebagai dosen dari MataKuliah
        this.dosen = dosen;
    }

    public void dropDosen() {
        // menghapus dosen dan set sebagai null
        this.dosen = null;
    }

    public String toString() {
        // mengembalikan nama MataKuliah
        return this.nama;
    }
}