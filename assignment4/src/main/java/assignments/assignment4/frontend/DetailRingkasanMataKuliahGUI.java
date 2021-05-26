package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class DetailRingkasanMataKuliahGUI extends JPanel{
    public DetailRingkasanMataKuliahGUI(JFrame frame, MataKuliah mataKuliah, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Detail Ringkasan Mahasiswa");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // menggabungkan setaip detail dari mata kuliah
        String nama = "Nama: " + mataKuliah.getNama() + "<br><br>";
        String kode = "Kode: " + mataKuliah.getKode() + "<br><br>";
        String sks = "SKS: " + mataKuliah.getSKS() + "<br><br>";
        String jumlahMhs = "Jumlah mahasiswa: " + mataKuliah.getJumlahMahasiswa() + "<br><br>";
        String mhs = "Daftar Mahasiswa:<br>";


        if (mataKuliah.getJumlahMahasiswa() == 0){
            mhs += "Belum ada mahasiswa yang mengambil mata kuliah ini <br>";
        }else {
            // looping untuk cetak nama mata kuliah yang diambil Mahasiswa
            for (int i = 0; i < mataKuliah.getJumlahMahasiswa(); i++) {
                mhs += (i + 1) + ". " + mataKuliah.getDaftarMahasiswa()[i].getNama() + "<br>";
            }
        }

        // membuat dan mengatur label yang menampilkan ringkasan mahasiswa
        JLabel label = (new JLabel("<html><br><br>" + nama + kode + sks + jumlahMhs + mhs + "<br><br></html>"));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Century Gothic", Font.BOLD , 12));
        label.setForeground(new Color(133, 30, 62));
        label.setBackground(SistemAkademikGUI.pinkColorBg);
        label.setOpaque(true);

        // ActionEvent Button btnBack untuk kembali ke HomeGUI
        JButton btnBack = new JButton("Selesai");
        btnBack.setBackground(Color.WHITE);
        btnBack.setMaximumSize(new Dimension(200, 20));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setFont(SistemAkademikGUI.fontGeneral);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setForeground(new Color(133, 30, 62));
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
        this.add(label);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
        this.setBackground(new Color(133, 30, 62));
        
    }
}
