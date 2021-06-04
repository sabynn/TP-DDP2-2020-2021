package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class RingkasanMahasiswaGUI extends JPanel{

    ArrayList<Mahasiswa> daftarMahasiswa;
    JComboBox<Long> boxNPM = new JComboBox<>();

    public RingkasanMahasiswaGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        this.daftarMahasiswa = daftarMahasiswa;

        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Ringkasan Mahasiswa");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen Label dan Button
        JLabel labelNPM = (new JLabel("Pilih NPM"));
        JButton btnSubmit = new JButton("Lihat");
        JButton btnBack = new JButton("Kembali");

        // mengatur komponen dan menghias Button
        boxNPM.setMaximumSize(new Dimension(300, 20));
        boxNPM.setMaximumRowCount(4);
        boxNPM.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNPM.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNPM.setForeground(Color.WHITE);
        TambahMahasiswaGUI.modifyButton(btnSubmit, "dark");
        TambahMahasiswaGUI.modifyButton(btnBack, "blue");

        // mendapat ContentPane dari frame dan layoutnya
        Container cp = frame.getContentPane();
        CardLayout cl = (CardLayout) cp.getLayout();

        // ActionEvent Button btnSubmit untuk menampilkan DetailRingkasanMahasiswaGUI
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Object npm = boxNPM.getSelectedItem();
                if (npm == null){
                    String text = "Mohon isi seluruh field";
                    JOptionPane.showMessageDialog(frame, text);
                }else{
                    Mahasiswa m = getMahasiswa((long) npm);
                    JPanel panel = new DetailRingkasanMahasiswaGUI(frame, m, daftarMahasiswa, daftarMataKuliah);

                    // mengatur Panel agar dapat discroll
                    JScrollPane scrollPane = new JScrollPane(panel);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane.getVerticalScrollBar().setBackground(SistemAkademikGUI.darkBlue);

                    cl.addLayoutComponent(scrollPane, "mhs");
                    cp.add(scrollPane);
                    cl.show(cp, "mhs");
                }
            }
        });

        // ActionEvent Button btnBack untuk kembali ke HomeGUI
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cl.show(cp, "homepage");
            }
        });

        // menambahkan dan mengatur setiap komponen ke Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(labelNPM);
        this.add(boxNPM);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
        this.setBackground(SistemAkademikGUI.darkBlue);
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
        for(Long m: TambahIRSGUI.npmSorting(daftarMahasiswa)) {
            boxNPM.addItem(m);
        }
    }
}
