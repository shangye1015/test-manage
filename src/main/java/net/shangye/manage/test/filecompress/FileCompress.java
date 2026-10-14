package net.shangye.manage.test.filecompress;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class FileCompress {

    public static void main(String[] args) {
        String longText = LongText.text;

        String compressedFilePath = "C:\\Users\\10159\\Desktop\\compressed.txt"; // 压缩后文件路径
        String decompressedFilePath = "C:\\Users\\10159\\Desktop\\decompressed.txt"; // 解压缩后文件路径

        try {

            String s = Base64.getEncoder().encodeToString(longText.getBytes());
            System.out.println(s);

            byte[] decode = Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
            String s1 = new String(decode);
            System.out.println(s1);


//            // 压缩内容并写入压缩文件
//            compressToFile(longText, compressedFilePath);
//            System.out.println("File compressed to: " + compressedFilePath);
//
//            // 从压缩文件读取并解压缩内容
//            String decompressedContent = decompressFromFile(compressedFilePath);
//            System.out.println("Decompressed Content: " + decompressedContent);
//
//            // 将解压缩内容写回文件
//            writeFile(decompressedFilePath, decompressedContent);
//            System.out.println("Decompressed content written to: " + decompressedFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer =
                     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),
                             StandardCharsets.UTF_8))) {
            writer.write(content);
        }
    }

    public static void compressToFile(String content, String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filePath);
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fos)) {
            gzipOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static String decompressFromFile(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             GZIPInputStream gzipInputStream = new GZIPInputStream(fis);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            return byteArrayOutputStream.toString(StandardCharsets.UTF_8);
        }
    }
}
