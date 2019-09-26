package Persistence;

import javax.json.*;
import java.io.*;
import java.util.Map;

public class JsonSaver {


        try {
            writeToFile(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("ChapSave.JSON"));
        writer.write(content);

        writer.close();
    }
}
