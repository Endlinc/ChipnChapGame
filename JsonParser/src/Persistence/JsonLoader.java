package Persistence;

import java.io.*;
import java.nio.file.Path;

public class JsonLoader {

    public static void importJson(String path) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
            StringBuilder strBuilder = new StringBuilder();
            String content = "";
            String holder = null;
            while ((holder = bf.readLine()) != null) {
                content = content + holder;
            }

            content = strBuilder.toString();
            System.out.println("===============");
            System.out.println(content);
            System.out.println("===============");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
