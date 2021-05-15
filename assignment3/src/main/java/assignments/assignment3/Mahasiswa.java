package assignments.assignment3;

class Mahasiswa extends ElemenFasilkom {
    // data fields Mahasiswa
    private MataKuliah[] daftarMataKuliah = new MataKuliah[10];

    private long npm;

    private String tanggalLahir;

    private String jurusan;

    private int jumlahMatkul;

    public Mahasiswa(String nama, long npm) {
        /* TODO: implementasikan kode Anda di sini */
        super("Mahasiswa", nama);
        this.npm = npm;
    }

    // getter
    public int getJumlahMatkul() {
        return jumlahMatkul;
    }

    public long getNpm() {
        return npm;
    }

    public MataKuliah[] getDaftarMataKuliah() {
        return daftarMataKuliah;
    }

    // method untuk cek apakah mata kuliah tertentu diambil oleh mahasiswa
    private boolean cekMatkulDiambil(MataKuliah mk){
        for (MataKuliah m: this.daftarMataKuliah){
            if (mk.equals(m)) {
                return true;
            }
        }
        return false;
    }

    public void addMatkul(MataKuliah mataKuliah) {
        // handle output jika matkul sudah diambil sebelumnya
        if (this.cekMatkulDiambil(mataKuliah)){
            System.out.println("[DITOLAK] " + mataKuliah +" telah diambil sebelumnya");
        }
        // handle output jika kapasitas matkul sudah penuh
        else if (mataKuliah.getJumlahMahasiswa() == mataKuliah.getKapasitas()) {
            System.out.println("[DITOLAK] " + mataKuliah + " telah penuh kapasitasnya");
        }
        else {
            // menambahkan matkul ke Array mataKuliah dan menambahkan nilai jumlahMatkul
            daftarMataKuliah[jumlahMatkul++] = mataKuliah;
            // menambahkan mahasiswa ke Matakuliah dengan memanggil method addMahasiswa
            mataKuliah.addMahasiswa(this);
            System.out.printf("%s berhasil menambahkan mata kuliah %s\n", this, mataKuliah);
        }
    }

    public void dropMatkul(MataKuliah mataKuliah) {
        /* TODO: implementasikan kode Anda di sini */
        if (this.cekMatkulDiambil(mataKuliah)){
            MataKuliah[] temp = new MataKuliah[10];
            int c = 0;
            // looping untuk memasukkan matkul selain matkul yang didrop ke Arrays temp
            for (MataKuliah mk : this.daftarMataKuliah) {
                if (mk != null && !(mk.equals(mataKuliah))) {
                    temp[c++] = mk;
                }
            }
            // mengubah reference Arrays mataKuliah ke temp
            this.daftarMataKuliah = temp;
            // mengurangi nilai jumlahMatkul
            this.jumlahMatkul--;
            //menghilangkan mahasiswa dari matakuliah dengan memanggil method dropMahasiswa
            mataKuliah.dropMahasiswa(this);
            System.out.printf("%s berhasil drop mata kuliah %s\n", this, mataKuliah);
        }
        // handle output jika matkul belum pernah diambil
        else System.out.println("[DITOLAK] " + mataKuliah + " belum pernah diambil");
    }

    public String extractTanggalLahir(long npm) {
        // mendapatkan nilai tanggal lahir lengkap
        long birthDate = (long) ((npm / 100) % 10E+7);

        // memisahkan nilai tanggal lahir lengkap menjadi tanggal, bulan, dan tahun lahir
        int birthDay = (int) (birthDate/10E+5);
        int birthMonth = (int) ((birthDate/10E+3) % 100);
        int birthYear = (int) (birthDate % 10E+3);

        // mengembalikan tanggal lahir sesuai format
        this.tanggalLahir = String.format("%d-%d-%d", birthDay, birthMonth, birthYear);
        return this.tanggalLahir;
    }

    // method untuk mengembalikan nama jurusan sesuai kode jurusan mahasiswa
    public String extractJurusan(long npm) {
        int kodeJurusan = (int) (npm / 10E+9) % 100;
        if (kodeJurusan == 1) this.jurusan = "Ilmu Komputer";
        else if (kodeJurusan == 2) this.jurusan = "Sistem Informasi";
        return this.jurusan;
    }
}