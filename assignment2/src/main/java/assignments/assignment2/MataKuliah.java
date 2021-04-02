package assignments.assignment2;

public class MataKuliah {
    private String kode;
    private String nama;
    private int sks;
    private int kapasitas;
    private Mahasiswa[] daftarMahasiswa;
    private int jumlahMahasiswa;

    public MataKuliah(String kode, String nama, int sks, int kapasitas){
        // constructor untuk inisiasi atribut nama dan npm mahasiswa
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.kapasitas = kapasitas;
        this.daftarMahasiswa = new Mahasiswa[kapasitas];
    }

    // getter untuk mengembalikan nilai dari atribut Mahasiswa
    public String getKode(){
        return this.kode;
    }

    public int getSks(){
        return this.sks;
    }

    public int getKapasitas(){
        return this.kapasitas;
    }

    public Mahasiswa[] getDaftarMahasiswa(){
        return this.daftarMahasiswa;
    }

    public int getJumlahMahasiswa(){
        return this.jumlahMahasiswa;
    }

    public void addMahasiswa(Mahasiswa mahasiswa) {
        // menambahkan mahasiswa ke Arrays daftarMahasiswa dan menambahkan nilai jumlahMahasiswa
        this.daftarMahasiswa[this.jumlahMahasiswa++] = mahasiswa;
    }

    public void dropMahasiswa(Mahasiswa mahasiswa) {
        // menginisiasi Arrays baru untuk menampung mahasiswa selain mahasiswa yang drop matkul
        Mahasiswa[] temp = new Mahasiswa[kapasitas];
        int c = 0;
        // looping untuk memasukkan mahasiswa selain mahasiswa yang drop matkul ke Arrays temp
        for (Mahasiswa m: this.daftarMahasiswa){
            if (m != null && !(m.equals(mahasiswa))) {
                temp[c++] = m;
            }
        }
        // mengubah reference Arrays mataKuliah ke temp
        this.daftarMahasiswa = temp;
        // mengurangi nilai jumlahMahasiswa
        this.jumlahMahasiswa--;
    }

    // mengembalikan nama Mata Kuliah
    public String toString() {
        return this.nama;
    }
}
