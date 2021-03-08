package assignments.assignment1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ExtractNPMTest {
    
    @Test
    public void testValidateCorrectOne() {
        assertEquals(ExtractNPM.validate(20022808200017L), true);
    }

    @Test
    public void testValidateCorrectTwo() {
        assertEquals(ExtractNPM.validate(19011403200038L), true);
    }

    @Test
    public void testValidateFalseOne() {
        assertEquals(ExtractNPM.validate(20021907200237L), false);
    }

    @Test
    public void testValidateFalseTwo() {
        assertEquals(ExtractNPM.validate(190214032000380L), false);
    }

    @Test
    public void testValidateFalseThree() {
        assertEquals(ExtractNPM.validate(20041907200626L), false);
    }
    
    @Test
    public void testExtractCorrectOne() {
        String expected = "Tahun masuk: 2020\n";
        expected += "Jurusan: Sistem Informasi\n";
        expected += "Tanggal Lahir: 28-8-2000";
        assertEquals(ExtractNPM.extract(20022808200017L), expected);
    }

    @Test
    public void testExtractCorrectTwo() {
        String expected = "Tahun masuk: 2019\n";
        expected += "Jurusan: Ilmu Komputer\n";
        expected += "Tanggal Lahir: 14-3-2000";
        assertEquals(ExtractNPM.extract(19011403200038L), expected);
    }

}