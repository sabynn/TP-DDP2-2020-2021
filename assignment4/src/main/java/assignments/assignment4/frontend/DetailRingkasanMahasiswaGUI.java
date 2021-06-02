package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class DetailRingkasanMahasiswaGUI extends JPanel{

    public DetailRingkasanMahasiswaGUI(JFrame frame, Mahasiswa mahasiswa, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Detail Ringkasan Mahasiswa");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // menggabungkan setaip detail dari mahasiswa
        String nama = "Nama: " + mahasiswa.getNama() + "<br><br>";
        String npm = "NPM: " + mahasiswa.getNpm() + "<br><br>";
        String jurusan = "Jurusan: " + mahasiswa.getJurusan() + "<br><br>";
        String matkul = "Daftar Mata Kuliah:<br>";

        if (mahasiswa.getBanyakMatkul() == 0){
            matkul += "Belum ada mata kuliah yang diambil <br><br>";
        }else {
            for (int i = 0; i < mahasiswa.getBanyakMatkul(); i++) {
                matkul += (i + 1) + ". " + mahasiswa.getMataKuliah()[i].getNama() + "<br>";
            }
            matkul += "<br>";
        }

        String sks = "Total SKS: " + mahasiswa.getTotalSKS()+ "<br><br>";
        String cekIRS = "Hasil Pengecekan IRS:<br>";
        mahasiswa.cekIRS();

        if (mahasiswa.getBanyakMasalahIRS() == 0){
           cekIRS += "IRS tidak bermasalah" + "<br>";
        }else {
            for (int i = 0; i < mahasiswa.getBanyakMasalahIRS(); i++) {
                cekIRS += (i + 1) + ". " + mahasiswa.getMasalahIRS()[i] + "<br>";
            }
        }

        // membuat dan mengatur label yang menampilkan ringkasan mahasiswa
        JLabel label = new JLabel("<html>" + nama + npm + jurusan + matkul + sks + cekIRS + "</html>");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Century Gothic", Font.BOLD , 12));
        label.setForeground(new Color(0, 91, 150));
        label.setBackground(SistemAkademikGUI.blueColor);
        label.setOpaque(true);

        // ActionEvent Button btnBack untuk kembali ke HomeGUI
        JButton btnBack = new JButton("Selesai");
        btnBack.setBackground(Color.WHITE);
        btnBack.setMaximumSize(new Dimension(200, 20));
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setFont(SistemAkademikGUI.fontButton);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setForeground(new Color(0, 91, 150));
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
        this.setBackground(new Color(0, 91, 150));
    }
}
