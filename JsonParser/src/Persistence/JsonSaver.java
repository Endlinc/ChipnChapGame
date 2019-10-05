package Persistence;

import Application.Game;
import maze.Tile;

import javax.json.*;
import java.io.*;

public class JsonSaver {

    public static void makeJson(Game game) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("ChapLoc", game.getChap().t.toString());

        JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();
        for (Object o: game.getChap().inventory) {
            arrayBuilder1.add(o.toString());
        }
        JsonArrayBuilder arrayBuilder2 = Json.createArrayBuilder();
        for (int x = 0; x < game.getBoard().getBoard().length; x++) {
            for (int y = 0; y < game.getBoard().getBoard()[0].length; y++) {
                arrayBuilder2.add(game.getBoard().getLocation(x, y).toString());
            }
        }
        objectBuilder.add("Inventory", arrayBuilder1);
        objectBuilder.add("BoardLayout", arrayBuilder2);


        JsonObject jsonObject = objectBuilder.build();
        String jsonString = "";
        Writer writer = new StringWriter();
        Json.createWriter(writer).write(jsonObject);
        jsonString = writer.toString();
        System.out.println(jsonString);


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
