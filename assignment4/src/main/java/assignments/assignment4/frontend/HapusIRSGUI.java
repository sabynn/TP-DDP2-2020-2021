package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class HapusIRSGUI extends JPanel {

    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<MataKuliah> daftarMataKuliah;
    private JComboBox<Long> boxNPM = new JComboBox<>();
    private JComboBox<String> boxMatkul = new JComboBox<>();

    public HapusIRSGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        this.daftarMahasiswa = daftarMahasiswa;
        this.daftarMataKuliah = daftarMataKuliah;

        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Hapus IRS");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen Label dan Button
        JLabel labelNPM = new JLabel("Pilih NPM");
        JLabel labelNama = new JLabel("Pilih Nama Matkul");
        JButton btnSubmit = new JButton("Hapus");
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

        // mengatur dan menghias Button
        TambahMahasiswaGUI.modifyButton(btnSubmit, "blue");
        TambahMahasiswaGUI.modifyButton(btnBack, "pink");

        // ActionEvent Button btnSubmit untuk dropMatkul dan menampilkan pesan sesuai kondisi
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
                    text = m.dropMatkul(mk);
                }
                JOptionPane.showMessageDialog(frame, text);
                frame.requestFocus(); // change the focus to JFrame to receive KeyEvent
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
        this.setBackground(SistemAkademikGUI.pinkColorBg);
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

    // memperbarui ComboBox sesuai isi daftar terbaru
    public void updateDropDown(){
        boxNPM.removeAllItems();
        boxMatkul.removeAllItems();
        for(Long m: TambahIRSGUI.npmSorting(daftarMahasiswa)) {
            boxNPM.addItem(m);
        }

        for(String m: TambahIRSGUI.matkulSorting(daftarMataKuliah)) {
            boxMatkul.addItem(m);
        }
    }
}
