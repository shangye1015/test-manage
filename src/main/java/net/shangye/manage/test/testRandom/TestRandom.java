package net.shangye.manage.test.testRandom;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRandom {
    public static void main(String[] args) {
        String filePath = "D:\\project\\test-manage\\src\\main\\java\\net\\shangye\\manage\\test\\testRandom\\bilibili.html";
        try {
            String content = Files.readString(Paths.get(filePath));
            String regex = "<div class=\"reaction-item__name\">\\s*.*?\\s*<div class=\"reaction-item__action\">";
            Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
            Matcher matcher = pattern.matcher(content);
            List<String> list = new ArrayList<>();
            int i = 1;
            while (matcher.find()) {
                String fullStr = matcher.group(0);
                String item = (i++)+"  :"+fullStr;
                System.out.println(item);
                list.add(item);
            }
            System.out.println("共"+i+"个");
            for (int j = 0; j < 20; j++) {
                int n = (int)(Math.random()*892);
                System.out.println(list.get(n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
