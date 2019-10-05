package Persistence;

import Application.Game;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonSaver {

    public static void makeJson(Game game) {
        // create chap json object
        JsonObjectBuilder chapBuilder = Json.createObjectBuilder();
        chapBuilder.add("ChapLoc", game.getChap().t.toString());

        JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();
        for (Object o: game.getChap().inventory) {
            arrayBuilder1.add(o.toString());
        }
        chapBuilder.add("Inventory", arrayBuilder1);
        // create board json object
        JsonObjectBuilder boardBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder2 = Json.createArrayBuilder();
        for (int x = 0; x < game.getBoard().getBoard().length; x++) {
            for (int y = 0; y < game.getBoard().getBoard()[0].length; y++) {
                arrayBuilder2.add(game.getBoard().getLocation(x, y).toString());
            }
        }
        boardBuilder.add("BoardLayout", arrayBuilder2);
        // create game json object
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("Chap", chapBuilder)
                .add("Board", boardBuilder)
                .add("GameWon", game.isGameWon());

        JsonObject jsonObject = objectBuilder.build();

        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        String jsonString = "";
        // write game by json form into file
        try(Writer writer = new StringWriter()) {
            writerFactory.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
            writeToFile(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String content) throws IOException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        BufferedWriter writer = new BufferedWriter(new FileWriter("ChapSave" + formatter.format(date) + ".JSON"));
        writer.write(content);

        writer.close();
    }
}
