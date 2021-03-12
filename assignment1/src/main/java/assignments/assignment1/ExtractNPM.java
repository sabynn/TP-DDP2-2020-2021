package assignments.assignment1;

import java.util.Scanner;

public class ExtractNPM {
    // Method untuk mengecek kevalidan input npm
    public static  boolean validate(long npm) {
        // mendapatkan nilai tahun lahir dan kode jurusan dari input npm
        int birthYear = (int) ((npm/10E+6) / 10E+4);
        int major = (int) ((npm / 10E+9) % 100);
        // mengecek kevalidan panjang input
        boolean validLength = String.valueOf(npm).length() == 14;

        // mengembalikan boolean sesuai kevalidan input npm
        if (!validLength) return validLength;

        // mengecek kevalidan panjang umur, kode jurusan, dan kode npm
        boolean validAge = ((2021 - birthYear) >= 15);
        boolean validMajor = (major == 1) || (major == 2) || (major == 3) || (major == 11) || (major == 12);
        boolean validComputation = validateComputation(npm / 10, npm % 10);
        return validAge && validMajor && validComputation;
    }

    // Method untuk mengecek kevalidan nilai kode npm berdasarkan hasil komputasi
    public static boolean validateComputation(long n, long c) {
        // memecah 13 digit npm ke dalam array bertipe char
        char[] nums = String.valueOf(n).toCharArray();
        long result = 0;

        // looping untuk mengakses digit per index dalam array
        for (int i=0;i<=nums.length/2;i++){
            int num;
            if (i==6) {
                // mendapat nilai digit ke-7 yg akan langsung diteruskan ke penambahan tanpa perkalian dgn digit lain
                num = nums[i] - '0';
            }
            else {
                // melakukan perkalian antardigit sesuai aturan komputasi
                num = nums[nums.length-(i + 1)] - '0';
                num *= (nums[i] - '0');
            }
            // menambahkan semua hasil perkalian antardigit sebelumnya
            result += num;
        }

        // jika hasil komputasi masih >=10 ,dilakukan penjumlahan antardigit
        if (result >= 10) result= (result/10) + (result%10);
        // mengembalikan boolean sesuai kesamaan hasil komputasi dengan kode npm dari input
        return result == c;
    }

    // Method untuk ekstraksi input menjadi output sesuai ketentuan
    public static String extract(long npm) {
        // mendapatkan nilai tahun masuk dan menambahkannya dgn 2000 agar sesuai format tahun
        int year = (int) (npm / 10E+11);
        year += 2000;

        // mendapatkan nilai kode jurusan
        int major = (int) ((npm / 10E+9) % 100);
        // mendapatkan nama jurusan sesuai kode jurusan
        String theMajor = "";
        if (major == 1) theMajor = "Ilmu Komputer";
        else if (major == 2) theMajor = "Sistem Informasi";
        else if (major == 3) theMajor = "Teknologi Informasi";
        else if (major == 11) theMajor = "Teknik Telekomunikasi";
        else if (major == 12) theMajor = "Teknik Elektro";

        // mendapatkan nilai tanggal lahir lengkap
        long birthDate = (long) ((npm / 100) % 10E+7);
        // memisahkan nilai tanggal lahir lengkap menjadi tanggal, bulan, dan tahun lahir
        int birthDay = (int) (birthDate/10E+5);
        int birthMonth = (int) ((birthDate/10E+3) % 10);
        int birthYear = (int) (birthDate % 10E+3);

        // mengatur format output detail data mahasiswa dan mengembalikan nilainya
        String output = "Tahun masuk: " + year + "\n";
        output += "Jurusan: " + theMajor + "\n";
        output += String.format("Tanggal Lahir: %02d-%02d-%d", birthDay, birthMonth, birthYear);
        return output;
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        boolean exitFlag = false;

        while (!exitFlag) {
            long npm = input.nextLong();
            if (npm < 0) {
                exitFlag = true;
                break;
            }
            // mengecek kebenaran input dengan memanggil method validate
            boolean validation = validate(npm);
            // mencetak detail tahun masuk, jurusan, dan tgl lahir dengan memanggil method extract jika npm valid
            if (validation) System.out.println(extract(npm));
            else System.out.println("NPM tidak valid!");
        }
        input.close();
    }
}