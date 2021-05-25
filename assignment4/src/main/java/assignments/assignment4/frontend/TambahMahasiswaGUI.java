package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class TambahMahasiswaGUI extends JPanel{

    public TambahMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Tambah Mahasiswa");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen TextField, Label, dan Button
        JTextField fieldNama = new JTextField();
        JTextField fieldNPM = new JTextField();
        JLabel labelNama = new JLabel("Nama:");
        JLabel labelNPM = (new JLabel("NPM:"));
        JButton btnSubmit = new JButton("Tambahkan");
        JButton btnBack = new JButton("Kembali");

        // mengatur ukuran maks dari TextField dan membuat komponen menjadi CENTER
        fieldNama.setMaximumSize(new Dimension(300, 30));
        fieldNPM.setMaximumSize(new Dimension(300, 30));
        fieldNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldNPM.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNPM.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mengatur dan menghias Button
        modifyButton(btnSubmit, "blue");
        modifyButton(btnBack, "pink");

        // ActionEvent Button btnSubmit untuk menampilkan pesan sesuai kondisi
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String nama = fieldNama.getText().trim();
                String stringNpm = fieldNPM.getText().trim();
                String text;

                if (nama.equals("") || stringNpm.equals("")){
                    text = "Mohon isi seluruh field";
                } else if(cekMahasiswa(daftarMahasiswa, Long.parseLong(stringNpm))) {
                    text = String.format("NPM %s sudah pernah ditambahkan sebelumnya", stringNpm);
                }else{
                    daftarMahasiswa.add(new Mahasiswa(nama, Long.parseLong(stringNpm)));
                    text = String.format("Mahasiswa %s-%s berhasil ditambahkan", stringNpm, nama);
                }
                JOptionPane.showMessageDialog(frame, text);
            }
        });

        // ActionEvent btnBack untuk kembali ke HomeGUI
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fieldNama.setText("");
                fieldNPM.setText("");
                Container c = frame.getContentPane();
                CardLayout cl = (CardLayout) c.getLayout();
                cl.show(c, "homepage");
            }
        });

        // menambahkan dan mengatur setiap komponen ke Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(SistemAkademikGUI.blueColor);
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(labelNama);
        this.add(fieldNama);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(labelNPM);
        this.add(fieldNPM);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
    }

    protected static void modifyButton(JButton b, String color){
        // mengatur setiap button
        b.setBackground(Color.WHITE);
        b.setMaximumSize(new Dimension(200, 20));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(SistemAkademikGUI.fontGeneral);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // memberi warna Button sesuai argumen
        if (color.equals("blue")) {
            b.setForeground(SistemAkademikGUI.blueColor);
        }else{
            b.setForeground(SistemAkademikGUI.pinkColor);
        }
    }


    // cek apakah mahasiswa dengan npm tertentu telah terdaftar
    protected boolean cekMahasiswa(ArrayList<Mahasiswa> daftarMahasiswa, long npm){
        for (Mahasiswa m: daftarMahasiswa){
            if (m.getNpm() == npm){
                return true;
            }
        }
        return false;
    }
}