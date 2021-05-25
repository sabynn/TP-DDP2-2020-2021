package assignments.assignment4.frontend;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;

import assignments.assignment4.backend.*;

public class SistemAkademik {
    
    public static void main(String[] args) { 
        new SistemAkademikGUI();
    }
}

class SistemAkademikGUI extends JFrame{
    private static ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<Mahasiswa>();
    private static ArrayList<MataKuliah> daftarMataKuliah = new ArrayList<MataKuliah>();
    public static Font fontGeneral = new Font("Century Gothic", Font.BOLD , 10);
    public static Font fontTitle = new Font("Century Gothic", Font.BOLD, 20);
    public static Color blueColor = new Color(154, 207, 255);
    public static Color pinkColor = new Color(246, 172, 189);

    public SistemAkademikGUI(){
        // Membuat Frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        // set title dan visibility dari frame
        frame.setTitle("Administrator - Sistem Akademik");
        frame.setVisible(true);
        // menampilkan HomeGUI sebagai halaman default yang ditampilkan saat program pertama kali dijalankan
        new HomeGUI(frame, daftarMahasiswa, daftarMataKuliah);
    }

}
