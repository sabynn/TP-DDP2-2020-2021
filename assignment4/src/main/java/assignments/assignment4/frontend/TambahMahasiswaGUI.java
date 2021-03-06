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
        JLabel labelNama = new JLabel("Nama");
        JLabel labelNPM = new JLabel("NPM");
        JButton btnSubmit = new JButton("Tambahkan");
        JButton btnBack = new JButton("Kembali");

        // mengatur label
        labelNama.setFont(SistemAkademikGUI.fontGeneral);
        labelNPM.setFont(SistemAkademikGUI.fontGeneral);
        labelNama.setForeground(Color.WHITE);
        labelNPM.setForeground(Color.WHITE);
        labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNPM.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mengatur dan menghias TextField
        modifyField(fieldNama, "white");
        modifyField(fieldNPM, "white");

        // mengatur dan menghias Button
        modifyButton(btnSubmit, "dark");
        modifyButton(btnBack, "blue");

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
                    clearTheField(fieldNama,fieldNPM);
                }else{
                    daftarMahasiswa.add(new Mahasiswa(nama, Long.parseLong(stringNpm)));
                    text = String.format("Mahasiswa %s-%s berhasil ditambahkan", stringNpm, nama);
                    clearTheField(fieldNama,fieldNPM);
                }
                JOptionPane.showMessageDialog(frame, text);
            }
        });

        // ActionEvent btnBack untuk kembali ke HomeGUI
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                clearTheField(fieldNama,fieldNPM);
                Container c = frame.getContentPane();
                CardLayout cl = (CardLayout) c.getLayout();
                cl.show(c, "homepage");
            }
        });

        // menambahkan dan mengatur setiap komponen ke Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(SistemAkademikGUI.darkBlue);
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(labelNama);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(fieldNama);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(labelNPM);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
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
        b.setFont(SistemAkademikGUI.fontButton);
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // memberi warna Button sesuai argumen
        if (color.equals("dark")) {
            b.setForeground(SistemAkademikGUI.darkBlue);
        }else{
            b.setForeground(SistemAkademikGUI.blue);
        }

        // mengubah warna button saat mouse hover
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            Color c;
            public void mouseEntered(MouseEvent e){
                c = b.getForeground();
                b.setBackground(Color.BLACK);
                b.setForeground(SistemAkademikGUI.blueBg);
            }
            public void mouseExited(MouseEvent e){
                b.setBackground(Color.WHITE);
                b.setForeground(c);
            }
        });
    }

    protected static void modifyField(JTextField f, String color){
        // mengatur setiap textField
        f.setFont(SistemAkademikGUI.fontGeneral);
        f.setMaximumSize(new Dimension(250, 30));
        f.setAlignmentX(Component.CENTER_ALIGNMENT);
        f.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        f.setOpaque(false);

        if (color.equals("white")){
            f.setForeground(Color.WHITE);
            f.setCaretColor(Color.WHITE);
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

    // mengosongkan semua field
    private void clearTheField(JTextField a, JTextField b){
        a.setText("");
        b.setText("");
    }
}
