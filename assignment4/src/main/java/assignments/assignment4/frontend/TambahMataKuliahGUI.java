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
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen TextField, Label, dan Button
        JTextField fieldKode = new JTextField();
        JTextField fieldNama = new JTextField();
        JTextField fieldSKS= new JTextField();
        JTextField fieldKapasitas = new JTextField();
        JLabel labelKode = new JLabel("Kode Mata Kuliah");
        JLabel labelNama= new JLabel("Nama Mata Kuliah");
        JLabel labelSKS= new JLabel("SKS");
        JLabel labelKapasitas = new JLabel("Kapasitas");
        JButton btnSubmit = new JButton("Tambahkan");
        JButton btnBack = new JButton("Kembali");

        //  mengatur font label dan mengatur label menjadi CENTER
        labelKode.setFont(SistemAkademikGUI.fontGeneral);
        labelNama.setFont(SistemAkademikGUI.fontGeneral);
        labelSKS.setFont(SistemAkademikGUI.fontGeneral);
        labelKapasitas.setFont(SistemAkademikGUI.fontGeneral);
        labelKode.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSKS.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelKapasitas.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mengatur dan menghias TextField
        TambahMahasiswaGUI.modifyField(fieldKode, "black");
        TambahMahasiswaGUI.modifyField(fieldNama, "black");
        TambahMahasiswaGUI.modifyField(fieldSKS, "black");
        TambahMahasiswaGUI.modifyField(fieldKapasitas, "black");

        // mengatur dan menghias Button
        TambahMahasiswaGUI.modifyButton(btnSubmit, "dark" );
        TambahMahasiswaGUI.modifyButton(btnBack, "blue");

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
                    clearTheField(fieldKode, fieldNama, fieldSKS, fieldKapasitas);
                }else{
                    daftarMataKuliah.add(new MataKuliah(kode, nama, Integer.parseInt(sks), Integer.parseInt(kapasitas)));
                    text = String.format("Mata Kuliah %s berhasil ditambahkan", nama);
                    clearTheField(fieldKode, fieldNama, fieldSKS, fieldKapasitas);
                }
                // menampilkan pesan sesuai kondisi
                JOptionPane.showMessageDialog(frame, text);
            }
        });

        // ActionEvent Button btnBack untuk kembali ke HomeGUI
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearTheField(fieldKode, fieldNama, fieldSKS, fieldKapasitas);
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
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(fieldKode);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(labelNama);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(fieldNama);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(labelSKS);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(fieldSKS);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(labelKapasitas);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(fieldKapasitas);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
        this.setBackground(SistemAkademikGUI.blueBg);
    }

    // cek apakah mata kuliah dengan nama tertentu telah terdaftar
    protected boolean cekMataKuliah(ArrayList<MataKuliah> daftarMataKuliah, String namaMatkul){
        for (MataKuliah matkul: daftarMataKuliah){
            if (matkul.getNama().equals(namaMatkul)){
                return true;
            }
        }
        return false;
    }

    // mengosongkan semua field
    private void clearTheField(JTextField a, JTextField b, JTextField c, JTextField d){
        a.setText("");
        b.setText("");
        c.setText("");
        d.setText("");
    }
}
