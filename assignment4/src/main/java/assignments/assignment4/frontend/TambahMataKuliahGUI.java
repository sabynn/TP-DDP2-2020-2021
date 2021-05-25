package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahMataKuliahGUI extends JPanel{

    public TambahMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Tambah Mata Kuliah");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen TextField, Label, dan Button
        JTextField fieldKode = new JTextField();
        JTextField fieldNama = new JTextField();
        JTextField fieldSKS= new JTextField();
        JTextField fieldKapasitas = new JTextField();
        JLabel labelKode = new JLabel("Kode Mata Kuliah:");
        JLabel labelNama= new JLabel("Nama Mata Kuliah:");
        JLabel labelSKS= new JLabel("SKS");
        JLabel labelKapasitas = new JLabel("Kapasitas:");
        JButton btnSubmit = new JButton("Tambahkan");
        JButton btnBack = new JButton("Kembali");

        // mengatur ukuran maksimum TextField
        fieldKode.setMaximumSize(new Dimension(300, 30));
        fieldNama.setMaximumSize(new Dimension(300, 30));
        fieldSKS.setMaximumSize(new Dimension(300, 30));
        fieldKapasitas.setMaximumSize(new Dimension(300, 30));

        // mengatur komponen menjadi CENTER
        fieldKode.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldSKS.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldKapasitas.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelKode.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSKS.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelKapasitas.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mengatur dan menghias Button
        TambahMahasiswaGUI.modifyButton(btnSubmit, "blue" );
        TambahMahasiswaGUI.modifyButton(btnBack, "pink");

        // ActionEvent Button btnSubmit untuk menampilkan pesan sesuai kondisi
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String kode = fieldKode.getText().trim();
                String nama = fieldNama.getText().trim();
                String sks = fieldSKS.getText().trim();
                String kapasitas = fieldKapasitas.getText().trim();
                String text;

                if (kode.equals("") || nama.equals("") || sks.equals("") || kapasitas.equals("")){
                    text = "Mohon isi seluruh field";
                } else if(cekMataKuliah(daftarMataKuliah, nama )) {
                    text = String.format("Mata Kuliah %s sudah pernah ditambahkan sebelumnya", nama);
                }else{
                    daftarMataKuliah.add(new MataKuliah(kode, nama, Integer.parseInt(sks), Integer.parseInt(kapasitas)));
                    text = String.format("Mata Kuliah %s berhasil ditambahkan", nama);
                }
                // menampilkan pesan sesuai kondisi
                JOptionPane.showMessageDialog(frame, text);
            }
        });

        // ActionEvent Button btnBack untuk kembali ke HomeGUI
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fieldKode.setText("");
                fieldNama.setText("");
                fieldSKS.setText("");
                fieldKapasitas.setText("");
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
        this.add(labelKode);
        this.add(fieldKode);
        this.add(labelNama);
        this.add(fieldNama);
        this.add(labelSKS);
        this.add(fieldSKS);
        this.add(labelKapasitas);
        this.add(fieldKapasitas);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
        this.setBackground(SistemAkademikGUI.blueColor);
    }

    // cek apakah mata kuliah dengan nama tertentu telah terdaftar
    public boolean cekMataKuliah(ArrayList<MataKuliah> daftarMataKuliah, String namaMatkul){
        for (MataKuliah matkul: daftarMataKuliah){
            if (matkul.getNama().equals(namaMatkul)){
                return true;
            }
        }
        return false;
    }
}
