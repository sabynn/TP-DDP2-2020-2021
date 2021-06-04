package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class RingkasanMataKuliahGUI extends JPanel{

    private ArrayList<MataKuliah> daftarMataKuliah;
    private JComboBox<String> boxMatkul = new JComboBox<>();

    public RingkasanMataKuliahGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        this.daftarMataKuliah = daftarMataKuliah;

        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Ringkasan Mata Kuliah");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat komponen Label dan Button
        JLabel labelNama = new JLabel("Pilih Nama Matkul");
        JButton btnSubmit = new JButton("Lihat");
        JButton btnBack = new JButton("Kembali");

        // mengatur komponen dan menghias Button
        labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);
        boxMatkul.setMaximumRowCount(4);
        boxMatkul.setMaximumSize(new Dimension(300, 20));
        boxMatkul.setAlignmentX(Component.CENTER_ALIGNMENT);
        TambahMahasiswaGUI.modifyButton(btnSubmit, "dark");
        TambahMahasiswaGUI.modifyButton(btnBack, "blue");

        // mendapat ContentPane dari frame dan layoutnya
        Container cp = frame.getContentPane();
        CardLayout cl = (CardLayout) cp.getLayout();

        // ActionEvent Button btnSubmit untuk menampilkan DetailRingkasanMataKuliahGUI atau menampilkan pesan
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Object nama = boxMatkul.getSelectedItem();
                if ( nama == null){
                    String text = "Mohon isi seluruh field";
                    JOptionPane.showMessageDialog(frame, text);
                }else{
                    MataKuliah mk = getMataKuliah((String) nama);
                    JPanel panel = new DetailRingkasanMataKuliahGUI(frame, mk, daftarMahasiswa, daftarMataKuliah);

                    // mengatur Panel agar dapat discroll
                    JScrollPane scrollPane = new JScrollPane(panel);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scrollPane.getVerticalScrollBar().setBackground(SistemAkademikGUI.darkBlue);

                    cl.addLayoutComponent(scrollPane, "mk");
                    cp.add(scrollPane);
                    cl.show(cp, "mk");
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
        this.add(labelNama);
        this.add(boxMatkul);
        this.add(Box.createRigidArea(new Dimension(0, 40)));
        this.add(btnSubmit);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(btnBack);
        this.add(Box.createVerticalGlue());
        this.setBackground(SistemAkademikGUI.blueBg);
    }

    private MataKuliah getMataKuliah(String nama) {
        for (MataKuliah mataKuliah : daftarMataKuliah) {
            if (mataKuliah.getNama().equals(nama)){
                return mataKuliah;
            }
        }
        return null;
    }

    // memperbarui ComboBox sesuai isi daftar terbaru
    public void updateDropDown(){
        boxMatkul.removeAllItems();
        for(String m: TambahIRSGUI.matkulSorting(daftarMataKuliah)) {
            boxMatkul.addItem(m);
        }
    }
}
