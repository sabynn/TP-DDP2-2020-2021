package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import assignments.assignment4.backend.*;

public class SistemAkademik {

    public static void main(String[] args) {
        new SistemAkademikGUI();
    }
}

class SistemAkademikGUI extends JFrame{

    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<Mahasiswa>();
    private static ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<MataKuliah>();
    public static Font fontButton = new Font("Century Gothic", Font.BOLD , 12);
    public static Font fontGeneral = new Font("Century Gothic", Font.BOLD , 13);
    public static Font fontTitle = new Font("Century Gothic", Font.BOLD, 23);
    public static Color darkBlue = new Color(7,71,119);
    public static Color blue = new Color(36, 140, 204);
    public static Color blueBg = new Color(154, 207, 255);

    public SistemAkademikGUI(){
        // Membuat Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        // set title dan visibility dari frame
        frame.setTitle("Administrator - Sistem Akademik");
        frame.setVisible(true);
        // menampilkan HomeGUI sebagai halaman default yang ditampilkan saat program pertama kali dijalankan
        new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
    }

}
