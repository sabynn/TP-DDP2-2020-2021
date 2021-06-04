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
        JLabel titleLabel1 = new JLabel();
        titleLabel1.setText("Selamat datang");
        titleLabel1.setFont(SistemAkademikGUI.fontTitle);
        titleLabel1.setForeground(Color.white);
        titleLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel2 = new JLabel();
        titleLabel2.setText("di Sistem Akademik");
        titleLabel2.setFont(SistemAkademikGUI.fontTitle);
        titleLabel2.setForeground(Color.white);
        titleLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);

        // membuat Button
        JButton btnMahasiswa = new JButton("Tambah Mahasiswa");
        JButton btnMatkul = new JButton("Tambah Mata Kuliah");
        JButton btnAddIRS = new JButton("Tambah IRS");
        JButton btnHapusIRS = new JButton("Hapus IRS");
        JButton btnRingkasanMhs = new JButton("Lihat Ringkasan Mahasiswa");
        JButton btnRingkasanMk = new JButton("Lihat Ringkasan Mata Kuliah");

        // menampilkan gambar
        ImageIcon icn = new ImageIcon("./img/city.gif");
        ImageIcon setIcn = new ImageIcon(icn.getImage().getScaledInstance(450, 1000, Image.SCALE_DEFAULT));
        JComponent imgLabel = new JLabel(setIcn);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // mengatur Panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(SistemAkademikGUI.darkBlue);

        // menambahkan dan mengatur setiap komponen ke Panel
        this.add(Box.createVerticalGlue());
        this.add(titleLabel1);
        this.add(titleLabel2);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        modifyButton(btnMahasiswa, "dark", "addMhs");
        modifyButton(btnMatkul, "blue", "addMk");
        modifyButton(btnAddIRS, "dark","addIRS");
        modifyButton(btnHapusIRS, "blue", "hapusIRS");
        modifyButton(btnRingkasanMhs, "dark", "ringkasanMhs");
        modifyButton(btnRingkasanMk, "blue", "ringkasanMk");
        this.add(Box.createVerticalGlue());

        // membuat Panel cover untuk menggabungkan gambar dengan Panel label dan button
        JPanel cover = new JPanel(new BorderLayout());
        cover.add(imgLabel, BorderLayout.WEST);
        cover.add(this);

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
        cl.addLayoutComponent(cover, "homepage");
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
        b.setPreferredSize(new Dimension(350, 40));
        b.setMaximumSize(new Dimension(350, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(SistemAkademikGUI.fontButton);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.add(b);

        // memberi warna Button sesuai argumen
        if (color.equals("blue")) {
            b.setForeground(SistemAkademikGUI.blue);
        }else{
            b.setForeground(SistemAkademikGUI.darkBlue);
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
}
