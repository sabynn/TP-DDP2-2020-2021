package assignments.assignment4.frontend;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class HomeGUI {

    private Container mainPage;
    private CardLayout c;
    private static JFrame frame;
    private TambahIRSGUI panelAddIRS;
    private HapusIRSGUI panelHapusIRS;
    private RingkasanMahasiswaGUI panelRingkasanMhs;
    private RingkasanMataKuliahGUI panelRingkasanMk;

    public HomeGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // set layout contentPane frame dengan CardLayout
        HomeGUI.frame = frame;
        this.mainPage = frame.getContentPane();
        this.c = new CardLayout();
        mainPage.setLayout(c);

        // membuat dan mengatur label untuk judul
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Selamat datang di Sistem Akademik");
        titleLabel.setFont(SistemAkademikGUI.fontTitle);
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat Button
        JButton btnMahasiswa = new JButton("Tambah Mahasiswa");
        JButton btnMatkul = new JButton("Tambah Mata Kuliah");
        JButton btnAddIRS = new JButton("Tambah IRS");
        JButton btnHapusIRS = new JButton("Hapus IRS");
        JButton btnRingkasanMhs = new JButton("Lihat Ringkasan Mahasiswa");
        JButton btnRingkasanMk = new JButton("Lihat Ringkasan Mata Kuliah");

        // mengatur, menghias, dan menambahkan ActionEvent dari Button
        modifyButton(btnMahasiswa, "blue", "addMhs");
        modifyButton(btnMatkul, "pink", "addMk");
        modifyButton(btnAddIRS, "blue","addIRS");
        modifyButton(btnHapusIRS, "pink", "hapusIRS");
        modifyButton(btnRingkasanMhs, "blue", "ringkasanMhs");
        modifyButton(btnRingkasanMk, "pink", "ringkasanMk");

        // menampilkan gambar
        ImageIcon icn = new ImageIcon((Toolkit.getDefaultToolkit().getImage("./img/book.png"))
                .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JComponent imgLabel = new JLabel(icn);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat dan mengatur Panel
        JPanel cover = new JPanel();
        cover.setLayout(new BoxLayout(cover, BoxLayout.Y_AXIS));
        cover.setBackground(SistemAkademikGUI.blueColor);

        // menambahkan dan mengatur setiap komponen ke Panel cover
        cover.add(Box.createVerticalGlue());
        cover.add(titleLabel);
        cover.add(imgLabel);
        cover.add(btnMahasiswa);
        cover.add(btnMatkul);
        cover.add(btnAddIRS);
        cover.add(btnHapusIRS);
        cover.add(btnRingkasanMhs);
        cover.add(btnRingkasanMk);
        cover.add(Box.createVerticalGlue());

        // membuat Panel untuk halaman-halaman selanjutnya
        TambahMahasiswaGUI panelAddMhs = new TambahMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
        TambahMataKuliahGUI panelAddMk = new TambahMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelAddIRS = new TambahIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelHapusIRS = new HapusIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelRingkasanMhs = new RingkasanMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelRingkasanMk = new RingkasanMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);

        // menambahkan setiap panel ke contentPane dari frame
        mainPage.add(cover);
        mainPage.add(panelAddMhs);
        mainPage.add(panelAddMk);
        mainPage.add(panelAddIRS);
        mainPage.add(panelHapusIRS);
        mainPage.add(panelRingkasanMhs);
        mainPage.add(panelRingkasanMk);

        // menambahkan komponen ke CardLayout
        c.addLayoutComponent(cover, "homepage");
        c.addLayoutComponent(panelAddMhs, "addMhs");
        c.addLayoutComponent(panelAddMk, "addMk");
        c.addLayoutComponent(panelAddIRS, "addIRS");
        c.addLayoutComponent(panelHapusIRS, "hapusIRS");
        c.addLayoutComponent(panelRingkasanMhs, "ringkasanMhs");
        c.addLayoutComponent(panelRingkasanMk, "ringkasanMk");
    }

    public void modifyButton(JButton b, String color, String nextPage){
        // mengatur setiap button
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBackground(Color.WHITE);
        b.setMaximumSize(new Dimension(200, 20));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(SistemAkademikGUI.fontGeneral);
        b.setFocusPainted(false);

        // memberi warna Button sesuai argumen
        if (color.equals("blue")) {
            b.setForeground(SistemAkademikGUI.blueColor);
        }else{
            b.setForeground(SistemAkademikGUI.pinkColor);
        }

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // memperbarui isi DropDown
                if (nextPage.equals("addIRS")){
                    panelAddIRS.updateDropDown();
                }else if (nextPage.equals("hapusIRS")){
                    panelHapusIRS.updateDropDown();
                }else if (nextPage.equals("ringkasanMhs")){
                    panelRingkasanMhs.updateDropDown();
                } else if (nextPage.equals("ringkasanMk")){
                    panelRingkasanMk.updateDropDown();
                }
                // menampilkan komponen yang telah ditambahkan ke CardLayout sesuai argumen
                c.show(mainPage, nextPage);
            }
        });
    }

}
