package Persistence;

import Application.Game;

import java.io.*;
import java.nio.file.Path;

public class JsonLoader {

    public static void importJson(String path) {
        try {
//            System.out.println(new File("./").getAbsolutePath());
            BufferedReader bf = new BufferedReader(new FileReader(new File(path)));
            String content = "";
            String holder = null;
            while ((holder = bf.readLine()) != null) {
                content = content + "\n" + holder;
            }

            System.out.println("===============");
            System.out.println(content);
            System.out.println("===============");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Game reloadGame (String content) {

        return null;
    }
}
