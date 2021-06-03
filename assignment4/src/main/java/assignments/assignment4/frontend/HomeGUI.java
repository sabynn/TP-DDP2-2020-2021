package assignments.assignment4.frontend;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class HomeGUI extends JPanel{

    private Container mainPage;
    private CardLayout cl;
    private TambahIRSGUI panelAddIRS;
    private HapusIRSGUI panelHapusIRS;
    private RingkasanMahasiswaGUI panelRingkasanMhs;
    private RingkasanMataKuliahGUI panelRingkasanMk;

    public HomeGUI(JFrame frame, ArrayList<Mahasiswa> daftarMahasiswa, ArrayList<MataKuliah> daftarMataKuliah){
        // set layout contentPane frame dengan CardLayout
        this.mainPage = frame.getContentPane();
        this.cl = new CardLayout();
        mainPage.setLayout(cl);

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

        // menampilkan gambar
        ImageIcon icn = new ImageIcon((Toolkit.getDefaultToolkit().getImage("./img/book.png"))
                .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
        JComponent imgLabel = new JLabel(icn);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat dan mengatur Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(SistemAkademikGUI.blueColor);

        // menambahkan dan mengatur setiap komponen ke Panel cover
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(imgLabel);
        modifyButton(btnMahasiswa, "blue", "addMhs");
        modifyButton(btnMatkul, "pink", "addMk");
        modifyButton(btnAddIRS, "blue","addIRS");
        modifyButton(btnHapusIRS, "pink", "hapusIRS");
        modifyButton(btnRingkasanMhs, "blue", "ringkasanMhs");
        modifyButton(btnRingkasanMk, "pink", "ringkasanMk");
        this.add(Box.createVerticalGlue());

        // membuat Panel untuk halaman-halaman selanjutnya
        TambahMahasiswaGUI panelAddMhs = new TambahMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
        TambahMataKuliahGUI panelAddMk = new TambahMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelAddIRS = new TambahIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelHapusIRS = new HapusIRSGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelRingkasanMhs = new RingkasanMahasiswaGUI(frame, daftarMahasiswa, daftarMataKuliah);
        this.panelRingkasanMk = new RingkasanMataKuliahGUI(frame, daftarMahasiswa, daftarMataKuliah);

        // menambahkan setiap panel ke contentPane dari frame
        mainPage.add(this);
        mainPage.add(panelAddMhs);
        mainPage.add(panelAddMk);
        mainPage.add(panelAddIRS);
        mainPage.add(panelHapusIRS);
        mainPage.add(panelRingkasanMhs);
        mainPage.add(panelRingkasanMk);

        // menambahkan komponen ke CardLayout
        cl.addLayoutComponent(this, "homepage");
        cl.addLayoutComponent(panelAddMhs, "addMhs");
        cl.addLayoutComponent(panelAddMk, "addMk");
        cl.addLayoutComponent(panelAddIRS, "addIRS");
        cl.addLayoutComponent(panelHapusIRS, "hapusIRS");
        cl.addLayoutComponent(panelRingkasanMhs, "ringkasanMhs");
        cl.addLayoutComponent(panelRingkasanMk, "ringkasanMk");
    }

    public void modifyButton(JButton b, String color, String nextPage){
        // mengatur setiap button
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBackground(Color.WHITE);
        b.setPreferredSize(new Dimension(200, 30));
        b.setMaximumSize(new Dimension(200, 30));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(SistemAkademikGUI.fontButton);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.add(b);

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
                cl.show(mainPage, nextPage);
            }
        });

        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                b.setBackground(Color.BLACK);
            }
            public void mouseExited(MouseEvent e){
                b.setBackground(Color.WHITE);
            }
        });
    }

}
