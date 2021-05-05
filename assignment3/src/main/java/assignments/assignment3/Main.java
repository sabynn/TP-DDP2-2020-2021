package assignments.assignment3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    // data field Main

    private static ElemenFasilkom[] daftarElemenFasilkom = new ElemenFasilkom[100];

    private static MataKuliah[] daftarMataKuliah = new MataKuliah[100];

    private static int totalMataKuliah = 0;

    private static int totalElemenFasilkom = 0;

    private static ElemenFasilkom getElemenFasilkom(String namaElemen){
        // looping untuk mendapatkan object ElemenFasilkom sesuai namaElemen
        for (int i = 0;i<totalElemenFasilkom;i++){
            if (daftarElemenFasilkom[i].toString().equals(namaElemen)){
                return daftarElemenFasilkom[i];
            }
        }
        return null;
    }

    private static MataKuliah getMataKuliah(String namaMatkul){
        // looping untuk mendapatkan object MataKuliah sesuai namaMatkul
        for (int i = 0;i<totalMataKuliah;i++){
            if (daftarMataKuliah[i].toString().equals(namaMatkul)){
                return daftarMataKuliah[i];
            }
        }
        return null;
    }

    private static void addMahasiswa(String nama, long npm) {
        // membuat objek Mahasiswa dan menambahkan objek  ke dalam daftarElemenFasilkom
        daftarElemenFasilkom[totalElemenFasilkom++] = new Mahasiswa(nama, npm);
        System.out.println(nama + " berhasil ditambahkan");
    }

    private static void addDosen(String nama) {
        // membuat objek Dosen dan menambahkan objek  ke dalam daftarElemenFasilkom
        daftarElemenFasilkom[totalElemenFasilkom++] = new Dosen(nama);
        System.out.println(nama + " berhasil ditambahkan");
    }

    private static void addElemenKantin(String nama) {
        // membuat objek ElemenKantin dan menambahkan objek  ke dalam daftarElemenFasilkom
        daftarElemenFasilkom[totalElemenFasilkom++] = new ElemenKantin(nama);
        System.out.println(nama + " berhasil ditambahkan");
    }

    private static void menyapa(String objek1, String objek2) {
        // handle jika kedua objek sama
        if (objek1.equals(objek2)){
            System.out.println("[DITOLAK] Objek yang sama tidak bisa saling menyapa");
        }else{
            // mendapatkan objek ElemenFasilkom sesuai nama objek
            ElemenFasilkom elemen1 = getElemenFasilkom(objek1);
            ElemenFasilkom elemen2 = getElemenFasilkom(objek2);
            // memanggil method menyapa milik ElemenFasilkom
            elemen1.menyapa(elemen2);
        }
    }

    private static void addMakanan(String objek, String namaMakanan, long harga) {
        // mendapatkan objek ElemenFasilkom sesuai nama objek
        ElemenFasilkom penjual = getElemenFasilkom(objek);

        // cek apakah penjual merupakan ElemenKantin
        if (penjual.getTipe().equals("ElemenKantin")) {
            // casting untuk mengubah ElemenFasilkom menjadi ElemenKantin  dan memanggil method setMakanan
            ((ElemenKantin) penjual).setMakanan(namaMakanan, harga);
        }else{
            System.out.println("[DITOLAK] " + objek + " bukan merupakan elemen kantin");
        }
    }

    private static void membeliMakanan(String objek1, String objek2, String namaMakanan) {
        // mendapatkan objek ElemenFasilkom sesuai nama objek
        ElemenFasilkom penjual = getElemenFasilkom(objek2);

        // handle jika penjual bukan merupakan ElemenKantin
        if (!(penjual.getTipe().equals("ElemenKantin"))) {
            System.out.println("[DITOLAK] Hanya elemen kantin yang dapat menjual makanan");
        }
        // handle jika objek1 sama dengan objek2
        else if(objek1.equals(objek2)){
            System.out.println("[DITOLAK] Elemen kantin tidak bisa membeli makanan sendiri");
        } else{
            // objek1 membeli makanan objek2 dengan memanggil method membeliMakanan
            ElemenFasilkom pembeli = getElemenFasilkom(objek1);
            pembeli.membeliMakanan(pembeli, penjual, namaMakanan);
        }
    }

    private static void createMatkul(String nama, int kapasitas) {
        // membuat objek MataKuliah dengan parameter nama dan kapasitas dan menyimpannya ke list daftarMataKuliah
        daftarMataKuliah[totalMataKuliah++] = new MataKuliah(nama, kapasitas);
        System.out.printf("%s berhasil ditambahkan dengan kapasitas %d\n", nama, kapasitas );
    }

    private static void addMatkul(String objek, String namaMataKuliah) {
        // mendapatkan objek ElemenFasilkom sesuai nama objek
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen.getTipe().equals("Mahasiswa")){
            // mendapatkan objek MataKuliah sesuai namaMataKuliah
            MataKuliah matkul = getMataKuliah(namaMataKuliah);
            // casting untuk mengubah ElemenFasilkom menjadi Mahasiswa  dan memanggil method addMatkul
            ((Mahasiswa) elemen).addMatkul(matkul);

        }
        // handle jika ElemenFasilkom bukan Mahasiswa
        else{
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat menambahkan matkul");
        }

    }

    private static void dropMatkul(String objek, String namaMataKuliah) {
        // mendapatkan objek ElemenFasilkom sesuai nama objek
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen.getTipe().equals("Mahasiswa")){
            // mendapatkan objek MataKuliah sesuai namaMataKuliah
            MataKuliah matkul = getMataKuliah(namaMataKuliah);
            // casting untuk mengubah ElemenFasilkom menjadi Mahasiswa  dan memanggil method dropMatkul
            ((Mahasiswa) elemen).dropMatkul(matkul);
        }
        // handle jika ElemenFasilkom bukan Mahasiswa
        else{
            System.out.println("[DITOLAK] Hanya mahasiswa yang dapat drop matkul");
        }
    }

    private static void mengajarMatkul(String objek, String namaMataKuliah) {
        // mendapatkan objek ElemenFasilkom sesuai nama objek
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen.getTipe().equals("Dosen")){
            // mendapatkan objek MataKuliah sesuai namaMataKuliah
            MataKuliah matkul = getMataKuliah(namaMataKuliah);
            // casting untuk mengubah ElemenFasilkom menjadi Dosen dan memanggil method mengajarMataKuliah
            ((Dosen) elemen).mengajarMataKuliah(matkul);
        }
        // handle jika ElemenFasilkom bukan Dosen
        else{
            System.out.println("[DITOLAK] Hanya dosen yang dapat mengajar matkul");
        }
    }

    private static void berhentiMengajar(String objek) {
        // mendapatkan objek ElemenFasilkom sesuai nama objek
        ElemenFasilkom elemen = getElemenFasilkom(objek);
        if (elemen.getTipe().equals("Dosen")){
            // casting untuk mengubah ElemenFasilkom menjadi Dosen dan memanggil method dropMataKuliah
            ((Dosen) elemen).dropMataKuliah();
        }
        // handle jika ElemenFasilkom bukan Dosen
        else{
            System.out.println("[DITOLAK] Hanya dosen yang dapat berhenti mengajar");
        }
    }

    private static void ringkasanMahasiswa(String objek) {
        // mendapatkan objek ElemenFasilkom sesuai nama objek
        ElemenFasilkom elemen = getElemenFasilkom(objek);

        // handle jika objek bukan Mahasiswa
        if (!(elemen.getTipe().equals("Mahasiswa"))){
            System.out.println("[DITOLAK] " + objek + " bukan merupakan seorang mahasiswa");
        }else{
            // memanggil method getMahasiswa untuk mendapat Mahasiswa sesuai npm yang diinput
            Mahasiswa m = (Mahasiswa) elemen;
            // cetak output dengan memanggil method agar sesuai format
            System.out.println("Nama: " + m);
            System.out.println("Tanggal lahir: " + m.extractTanggalLahir(m.getNpm()));
            System.out.println("Jurusan: " + m.extractJurusan(m.getNpm()));
            System.out.println("Daftar Mata Kuliah:");

            if (((Mahasiswa) elemen).getJumlahMatkul() == 0){
                System.out.println("Belum ada mata kuliah yang diambil");
            }else {
                // looping untuk cetak nama mata kuliah yang diambil Mahasiswa
                for (int i = 0; i < m.getJumlahMatkul(); i++) {
                    System.out.println((i + 1) + ". " + m.getDaftarMataKuliah()[i]);
                }
            }
        }
    }

    private static void ringkasanMataKuliah(String namaMataKuliah) {
        // memanggil method getMataKuliah untuk mendapat MataKuliah sesuai nama mata kuliah yg diinput
        MataKuliah mk = getMataKuliah(namaMataKuliah);

        // cetak output dengan memanggil method agar sesuai format
        System.out.println("Nama mata kuliah: " + namaMataKuliah);
        System.out.println("Jumlah mahasiswa: " + mk.getJumlahMahasiswa());
        System.out.println("Kapasitas: " + mk.getKapasitas());
        System.out.println("Dosen pengajar: " + ((mk.getDosen() != null) ? mk.getDosen() : "Belum ada"));
        System.out.println("Daftar mahasiswa yang mengambil mata kuliah ini:");

        if (mk.getJumlahMahasiswa() != 0){
            // looping untuk mencetak setiap nama mahasiswa yang mengambil matkul
            for (int i = 0; i < mk.getJumlahMahasiswa(); i++) {
                System.out.println((i+1) + ". " + mk.getDaftarMahasiswa()[i]);
            }
        }
        // handle kasus jika tidak ada mahasiswa yang mengambil mata kuliah
        else System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini");
    }

    private static void nextDay() {
        for (ElemenFasilkom elemen: daftarElemenFasilkom){
            if(elemen != null){
                // cek apakah elemen bertipe Mahasiswa
                if (elemen.getTipe().equals("Mahasiswa")) {
                    // explicit casting untuk mengubah ElemenFasilkom menjadi Mahasiswa
                    Mahasiswa m = (Mahasiswa) elemen;
                    // looping Arrays daftarMataKuliah milik Mahasiswa
                    for (MataKuliah mk : m.getDaftarMataKuliah()) {
                        if (mk == null) break;
                        else {
                            // mendapat Dosen pengajar MataKuliah
                            Dosen d = mk.getDosen();
                            // cek apakah Mahasiswa telah menyapa Dosen
                            if (m.cekTelahDisapa(d)) {
                                // menambah friendship dari Mahasiswa dan Dosen sebesar 2
                                elemen.setFriendship(elemen.getFriendship() + 2);
                                d.setFriendship(d.getFriendship() + 2);
                            }
                        }
                    }
                }

                // cek apakah ElemenFasilkom pernah membeli Makanan
                if (elemen.getJumlahTelahDibeli() != 0){
                    // looping Arrays telahMembeliMakanan milik ElemenFasilkom
                    for (ElemenKantin ek: elemen.getTelahMembeliMakanan()){
                        if (ek == null) break;
                        else {
                            // menambah friendship dari ElemenFasilkom dan ElemenKantin sebesar 1
                            elemen.setFriendship(elemen.getFriendship() + 1);
                            ek.setFriendship(ek.getFriendship() + 1);
                        }
                    }
                }

                // cek apakah ElemenFasilkom menyapa lebih dari setengah total elemenFasilkom(tdk termasuk diri sendiri)
                if (elemen.getJumlahTelahDisapa() >= (totalElemenFasilkom-1)/2){
                    // menambah friendship ElemenFasilkom sebesar 10
                    elemen.setFriendship(elemen.getFriendship() + 10);
                }else{
                    // mengurangi friendship ElemenFasilkom sebesar 5
                    elemen.setFriendship(elemen.getFriendship() - 5);
                }
                // reset terhadap Array telahMenyapa dan telahMembeliMakanan dari ElemenFasilkom
                elemen.resetMenyapa();
            } else{
                break;
            }
        }
        // cetak sesuai format dan memanggil friendshipRanking
        System.out.println("Hari telah berakhir dan nilai friendship telah diupdate");
        friendshipRanking();
    }

    private static void friendshipRanking() {
        // sort Arrays berdasarkan friendship tertinggi hingga rendah,jika friendship sama diurutkan secara alphabetical
        Arrays.sort(daftarElemenFasilkom,
                Comparator.nullsLast(((Comparator.comparing(ElemenFasilkom::getFriendship))
                .reversed())
                .thenComparing(ElemenFasilkom::toString)));

        // counter untuk urutan ranking
        int c = 1;
        // looping untuk mencetak ranking dari nilai friendship
        for (ElemenFasilkom elemen: daftarElemenFasilkom){
            if (elemen != null) System.out.printf("%d. %s(%d)\n", c++, elemen, elemen.getFriendship());
            else break;
        }
    }

    private static void programEnd() {
        // cetak sesuai format saat menghentikan program dan memanggil method friendshipRanking
        System.out.println("Program telah berakhir. Berikut nilai terakhir dari friendship pada Fasilkom :");
        friendshipRanking();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        while (true) {
            String in = input.nextLine();
            if (in.split(" ")[0].equals("ADD_MAHASISWA")) {
                addMahasiswa(in.split(" ")[1], Long.parseLong(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_DOSEN")) {
                addDosen(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("ADD_ELEMEN_KANTIN")) {
                addElemenKantin(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("MENYAPA")) {
                menyapa(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("ADD_MAKANAN")) {
                addMakanan(in.split(" ")[1], in.split(" ")[2], Long.parseLong(in.split(" ")[3]));
            } else if (in.split(" ")[0].equals("MEMBELI_MAKANAN")) {
                membeliMakanan(in.split(" ")[1], in.split(" ")[2], in.split(" ")[3]);
            } else if (in.split(" ")[0].equals("CREATE_MATKUL")) {
                createMatkul(in.split(" ")[1], Integer.parseInt(in.split(" ")[2]));
            } else if (in.split(" ")[0].equals("ADD_MATKUL")) {
                addMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("DROP_MATKUL")) {
                dropMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("MENGAJAR_MATKUL")) {
                mengajarMatkul(in.split(" ")[1], in.split(" ")[2]);
            } else if (in.split(" ")[0].equals("BERHENTI_MENGAJAR")) {
                berhentiMengajar(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MAHASISWA")) {
                ringkasanMahasiswa(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("RINGKASAN_MATKUL")) {
                ringkasanMataKuliah(in.split(" ")[1]);
            } else if (in.split(" ")[0].equals("NEXT_DAY")) {
                nextDay();
            } else if (in.split(" ")[0].equals("PROGRAM_END")) {
                programEnd();
                break;
            }
        }
    }
}