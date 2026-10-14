package net.shangye.manage.test.testencoding;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 测试读取文件编码类型
 */
public class TestEncoding {

    public static void main(String[] args) throws IOException {
        String filePath_Utf_8 = "C:\\Users\\10159\\Desktop\\utf-8.csv";
        String filePath_ANSI = "C:\\Users\\10159\\Desktop\\ANSI.csv";
        String filePath_GB2312 = "C:\\Users\\10159\\Desktop\\gb2312.csv";
        String enCodingUtf8 = detectEncoding(filePath_Utf_8);
        String enCodingANSI = detectEncoding(filePath_ANSI);
        String enCodingGb2312 = detectEncoding(filePath_GB2312);
        System.out.println("Detected Encoding: " + enCodingUtf8);
        System.out.println("Detected Encoding: " + enCodingANSI);
        System.out.println("Detected Encoding: " + enCodingGb2312);
    }

    public static String detectEncoding(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        byte[] fileContent = fis.readAllBytes();
        CharsetDetector detector = new CharsetDetector();
        detector.setText(fileContent);

        CharsetMatch match = detector.detect();
        if (match != null) {
            return match.getName(); // 返回检测到的编码
        } else {
            return "Unknown";
        }
    }
}

