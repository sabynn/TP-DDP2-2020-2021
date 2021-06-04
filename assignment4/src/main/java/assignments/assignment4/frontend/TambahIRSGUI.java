package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahIRSGUI extends JPanel{

    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private JComboBox<Long> boxNPM = new JComboBox<>();
    private JComboBox<String> boxMatkul = new JComboBox<>();

    public TambahIRSGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;

        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Tambah IRS");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen Label dan Button
        JLabel labelNPM = new JLabel("Pilih NPM");
        JLabel labelNama = new JLabel("Pilih Nama Matkul");
        JButton btnSubmit = new JButton("Tambahkan");
        JButton btnBack = new JButton("Kembali");

        // mengatur tampilan dari ComboBox dan membuat komponen menjadi CENTER
        boxNPM.setMaximumSize(new Dimension(300, 20));
        boxMatkul.setMaximumSize(new Dimension(300, 20));
        boxNPM.setMaximumRowCount(4);
        boxMatkul.setMaximumRowCount(4);
        boxNPM.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxMatkul.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNPM.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNPM.setForeground(Color.WHITE);
        labelNama.setForeground(Color.WHITE);

        // mengatur dan menghias Button
        TambahMahasiswaGUI.modifyButton(btnSubmit, "dark");
        TambahMahasiswaGUI.modifyButton(btnBack, "blue");

        // ActionEvent Button btnSumbit untuk addMatkul menampilkan pesan sesuai kondisi
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Object npm = boxNPM.getSelectedItem();
                Object nama = boxMatkul.getSelectedItem();
                String text;
                if (npm == null || nama == null){
                    text = "Mohon isi seluruh field";
                }else{
                    Mahasiswa m = getMahasiswa((long) npm);
                    MataKuliah mk = getMataKuliah((String) nama);
                    text = m.addMatkul(mk);
                }
                JOptionPane.showMessageDialog(frame, text);
            }
        });

        // ActionEvent Button btnBack untuk kembali ke HomeGUI
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Container c = frame.getContentPane();
                CardLayout cl = (CardLayout) c.getLayout();
                cl.show(c, "homepage");
            }
        });

        // menambahkan dan mengatur setiap komponen ke Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(labelNPM);
        this.add(boxNPM);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(labelNama);
        this.add(boxMatkul);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
        this.setBackground(SistemAkademikGUI.darkBlue);
    }

    private MataKuliah getMataKuliah(String nama) {
        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.getNama().equals(nama)){
                return mataKuliah;
            }
        }
        return null;
    }

    private Mahasiswa getMahasiswa(long npm) {
        for (Mahasiswa mahasiswa : daftarMahasiswa) {
            if (mahasiswa.getNpm() == npm){
                return mahasiswa;
            }
        }
        return null;
    }


    // mengembalikan Arrays berisi npm yg sudah terurut
    protected static Long[] npmSorting(ArrayList<Mahasiswa> daftarMahasiswa){
        int len = daftarMahasiswa.toArray().length;
        Long[] arrayNpm = new Long[len];

        // mengisi Arrays dengan npm mahasiswa
        for (int i = 0; i < len; i++){
            arrayNpm[i] = daftarMahasiswa.get(i).getNpm();
        }

        // mengurutkan npm dari kecil ke besar
        for(int i = 1; i < arrayNpm.length; i++){
            long temp = arrayNpm[i];
            int j = i-1;
            while (j >= 0 && (arrayNpm[j] > temp)){
                arrayNpm[j+1] = arrayNpm[j--];
            }
            arrayNpm[j+1] = temp;
        }
        return arrayNpm;
    }

    // mengembalikan Arrays berisi nama mata kuliah yg sudah terurut
    protected static String[] matkulSorting(ArrayList<MataKuliah> daftarMataKuliah){
        int len = daftarMataKuliah.toArray().length;
        String[] arrayMatkul = new String[len];

        // mengisi Arrays dengan nama mata kuliah
        for (int i = 0; i < len; i++){
            arrayMatkul[i] = daftarMataKuliah.get(i).getNama();
        }

        // mengurutkan nama mata kuliah berdasarkan alphabetical order
        for(int i = 1; i < arrayMatkul.length; i++){
            String temp = arrayMatkul[i];
            int j = i-1;
            while (j >= 0 && (arrayMatkul[j].compareTo(temp)>0)){
                arrayMatkul[j+1] = arrayMatkul[j];
                j--;
            }
            arrayMatkul[j+1] = temp;
        }
        return arrayMatkul;
    }

    // memperbarui ComboBox sesuai isi daftar terbaru
    protected void updateDropDown(){
        boxNPM.removeAllItems();
        boxMatkul.removeAllItems();
        for(Long m: npmSorting(daftarMahasiswa)) {
            boxNPM.addItem(m);
        }

        for(String m: matkulSorting(daftarMataKuliah)) {
            boxMatkul.addItem(m);
        }
    }
}
