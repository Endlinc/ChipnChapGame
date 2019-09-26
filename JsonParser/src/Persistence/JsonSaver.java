package Persistence;

import javax.json.*;
import java.io.*;
import java.util.Map;

public class JsonSaver {

    public static void makeJson(Game game) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Chap", game.board.toString());

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for ()

        JsonObject jsonObject = objectBuilder.build();
        String jsonString = "";
        Writer writer = new StringWriter();
        Json.createWriter(writer).write(jsonObject);
        jsonString = writer.toString();



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
