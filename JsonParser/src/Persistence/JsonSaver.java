package Persistence;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.*;
import java.util.Map;

public class JsonSaver {

    public static void makeJson(Game game) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("BoardArray", game.board.toString())
                .add("Chap", game.getCharacters().toString())
                .add("Time", game.getBoard().toString());

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
