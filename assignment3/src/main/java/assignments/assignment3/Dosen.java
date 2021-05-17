package assignments.assignment3;

public class Dosen extends ElemenFasilkom {
    // data field Dosen
    private MataKuliah mataKuliah;

    // constructor Dosen
    public Dosen(String nama) {
        super("Dosen", nama);
    }

    // getter
    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void mengajarMataKuliah(MataKuliah mataKuliah) {
        // handle jika dosen sudah mengajar mata kuliah
        if (this.mataKuliah != null){
            System.out.printf("[DITOLAK] %s sudah mengajar mata kuliah %s\n", this, this.mataKuliah);
        }
        // handle jika mata kuliah sudah memiliki dosen
        else if (mataKuliah.getDosen() != null){
            System.out.printf("[DITOLAK] %s sudah memiliki dosen pengajar\n", mataKuliah);
        }
        // mendaftarkan objek Dosen ke dalam mataKuliah dan mencetak output
        else {
            this.mataKuliah = mataKuliah;
            mataKuliah.addDosen(this);
            System.out.printf("%s mengajar mata kuliah %s\n", this, mataKuliah);
        }
    }

    public void dropMataKuliah() {
        // handle jika dosen tidak mengajar matkul apapun
        if (this.mataKuliah == null){
            System.out.printf("[DITOLAK] %s sedang tidak mengajar mata kuliah apapun\n", this);
        }
        // melepaskan mata kuliah yang diajarkan dosen dan mencetak output
        else{
            this.mataKuliah.dropDosen();
            System.out.printf("%s berhenti mengajar %s\n", this, mataKuliah);
            this.mataKuliah = null;
        }
    }

}