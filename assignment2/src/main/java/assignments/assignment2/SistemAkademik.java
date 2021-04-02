package assignments.assignment2;

import java.util.Scanner;

public class SistemAkademik {
    private static final int ADD_MATKUL = 1;
    private static final int DROP_MATKUL = 2;
    private static final int RINGKASAN_MAHASISWA = 3;
    private static final int RINGKASAN_MATAKULIAH = 4;
    private static final int KELUAR = 5;
    private static Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];
    private static MataKuliah[] daftarMataKuliah = new MataKuliah[100];
    
    private Scanner input = new Scanner(System.in);

    private Mahasiswa getMahasiswa(long npm) {
        // looping untuk mendapat mahasiswa dengan npm yang diinginkan
        for (Mahasiswa m:daftarMahasiswa){
            if (m.getNPM() == npm) return m;
        }
        return null;
    }

    private MataKuliah getMataKuliah(String namaMataKuliah) {
        // looping untuk mendapat MataKuliah yang sesuai dengan namaMataKuliah
        for (MataKuliah mk:daftarMataKuliah){
            if (mk.toString().equals(namaMataKuliah)) return mk;
        }
        return null;
    }

    private void addMatkul(){
        System.out.println("\n--------------------------ADD MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan ADD MATKUL : ");
        long npm = Long.parseLong(input.nextLine());

        // memanggil method getMahasiswa untuk mendapat Mahasiswa sesuai npm yang diinput
        Mahasiswa m = getMahasiswa(npm);

        System.out.print("Banyaknya Matkul yang Ditambah: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan nama matkul yang ditambah");
        for(int i=0; i<banyakMatkul; i++){
            System.out.print("Nama matakuliah " + i+1 + " : ");
            String namaMataKuliah = input.nextLine();

            // memanggil method getMataKuliah untuk mendapat Mata Kuliah sesuai nama Mata Kuliah yang diinput
            MataKuliah matkul = getMataKuliah(namaMataKuliah);
            // memanggil method add Matkul dari object mahasiswa
            m.addMatkul(matkul);
        }
        System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
    }

    private void dropMatkul(){
        System.out.println("\n--------------------------DROP MATKUL--------------------------\n");

        System.out.print("Masukkan NPM Mahasiswa yang akan melakukan DROP MATKUL : ");
        long npm = Long.parseLong(input.nextLine());

        // memanggil method getMahasiswa untuk mendapat Mahasiswa sesuai npm yang diinput
        Mahasiswa m = getMahasiswa(npm);

        if (m.getJumlahMatkul() != 0) {
            System.out.print("Banyaknya Matkul yang Di-drop: ");
            int banyakMatkul = Integer.parseInt(input.nextLine());
            System.out.println("Masukkan nama matkul yang di-drop:");
            for (int i = 0; i < banyakMatkul; i++) {
                System.out.print("Nama matakuliah " + i + 1 + " : ");
                String namaMataKuliah = input.nextLine();

                // memanggil method getMataKuliah untuk mendapat Mata Kuliah sesuai nama Mata Kuliah yang diinput
                MataKuliah matkul = getMataKuliah(namaMataKuliah);
                // memanggil method add Matkul dari object mahasiswa
                m.dropMatkul(matkul);
            }
            System.out.println("\nSilakan cek rekap untuk melihat hasil pengecekan IRS.\n");
        }
        // mencetak output ditolak jika Mahasiswa belum mengambil mata kuliah
        else System.out.println("[DITOLAK] Belum ada mata kuliah yang diambil.");
    }

    private void ringkasanMahasiswa(){
        System.out.print("Masukkan npm mahasiswa yang akan ditunjukkan ringkasannya : ");
        long npm = Long.parseLong(input.nextLine());

        // memanggil method getMahasiswa untuk mendapat Mahasiswa sesuai npm yang diinput
        Mahasiswa m = getMahasiswa(npm);
        // cetak output dengan memanggil method agar sesuai format
        System.out.println("\n--------------------------RINGKASAN--------------------------\n");
        System.out.println("Nama: " + m);
        System.out.println("NPM: " + npm);
        System.out.println("Jurusan: " + m.getJurusan());
        System.out.println("Daftar Mata Kuliah: ");

        // inisiasi variabel untuk melihat apakah mahasiswa sudah mengambil mata kuliah
        boolean adaMatkul = m.getJumlahMatkul() != 0;

        if (adaMatkul) {
            // looping untuk mencetak setiap mata kuliah yang diambil mahasiswa
            for (int i=0;i<m.getJumlahMatkul();i++){
                System.out.println((i+1) + ". " + m.getMatKul()[i]);
            }
        }
        // handle kasus jika belum ada mata kuliah yang diambil
        else System.out.println("Belum ada mata kuliah yang diambil");

        System.out.println("Total SKS: " + m.getTotalSKS());
        
        System.out.println("Hasil Pengecekan IRS:");

        // melakukan pengecekan IRS dengan memanggil method cekIRS jika mahasiswa sudah ambil mata kuliah
        if (adaMatkul) m.cekIRS();
        else System.out.println("IRS tidak bermasalah.");
    }

    private void ringkasanMataKuliah(){
        System.out.print("Masukkan nama mata kuliah yang akan ditunjukkan ringkasannya : ");
        String namaMataKuliah = input.nextLine();

        // memanggil method getMataKuliah untuk mendapat MataKuliah sesuai nama mata kuliah yg diinput
        MataKuliah mk = getMataKuliah(namaMataKuliah);

        // cetak output dengan memanggil method agar sesuai format
        System.out.println("\n--------------------------RINGKASAN--------------------------\n");
        System.out.println("Nama mata kuliah: " + namaMataKuliah);
        System.out.println("Kode: " + mk.getKode());
        System.out.println("SKS: " + mk.getSks());
        System.out.println("Jumlah mahasiswa: " + mk.getJumlahMahasiswa());
        System.out.println("Kapasitas: " + mk.getKapasitas());
        System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini: ");

        if (mk.getJumlahMahasiswa() != 0){
            // looping untuk mencetak setiap nama mahasiswa yang mengambil matkul
            for (int i = 0; i < mk.getJumlahMahasiswa(); i++) {
                System.out.println((i+1) + ". " + mk.getDaftarMahasiswa()[i]);
            }
        }
        // handle kasus jika tidak ada mahasiswa yang mengambil mata kuliah
        else System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini.");
    }

    private void daftarMenu(){
        int pilihan;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n----------------------------MENU------------------------------\n");
            System.out.println("Silakan pilih menu:");
            System.out.println("1. Add Matkul");
            System.out.println("2. Drop Matkul");
            System.out.println("3. Ringkasan Mahasiswa");
            System.out.println("4. Ringkasan Mata Kuliah");
            System.out.println("5. Keluar");
            System.out.print("\nPilih: ");
            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                continue;
            }
            System.out.println();
            if (pilihan == ADD_MATKUL) {
                addMatkul();
            } else if (pilihan == DROP_MATKUL) {
                dropMatkul();
            } else if (pilihan == RINGKASAN_MAHASISWA) {
                ringkasanMahasiswa();
            } else if (pilihan == RINGKASAN_MATAKULIAH) {
                ringkasanMataKuliah();
            } else if (pilihan == KELUAR) {
                System.out.println("Sampai jumpa!");
                exit = true;
            }
        }

    }

    private void run() {
        System.out.println("====================== Sistem Akademik =======================\n");
        System.out.println("Selamat datang di Sistem Akademik Fasilkom!");
        
        System.out.print("Banyaknya Matkul di Fasilkom: ");
        int banyakMatkul = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan matkul yang ditambah");
        System.out.println("format: [Kode Matkul] [Nama Matkul] [SKS] [Kapasitas]");

        for(int i=0; i<banyakMatkul; i++){
            String[] dataMatkul = input.nextLine().split(" ", 4);
            int sks = Integer.parseInt(dataMatkul[2]);
            int kapasitas = Integer.parseInt(dataMatkul[3]);

            // membuat instance MataKuliah sesuai constructor dan masukkan ke dalam Array daftarMataKuliah
            daftarMataKuliah[i] = new MataKuliah(dataMatkul[0], dataMatkul[1], sks, kapasitas);
        }

        System.out.print("Banyaknya Mahasiswa di Fasilkom: ");
        int banyakMahasiswa = Integer.parseInt(input.nextLine());
        System.out.println("Masukkan data mahasiswa");
        System.out.println("format: [Nama] [NPM]");

        for(int i=0; i<banyakMahasiswa; i++){
            String[] dataMahasiswa = input.nextLine().split(" ", 2);
            long npm = Long.parseLong(dataMahasiswa[1]);

            // membuat instance Mahasiswa dan masukkan ke dalam Array daftarMahasiswa
            daftarMahasiswa[i] = new Mahasiswa(dataMahasiswa[0], npm);
        }
        daftarMenu();
        input.close();
    }

    public static void main(String[] args) {
        SistemAkademik program = new SistemAkademik();
        program.run();
    }
    
}
