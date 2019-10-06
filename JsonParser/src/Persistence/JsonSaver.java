package Persistence;

import Application.Game;
import maze.*;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.*;
import java.lang.Object;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JsonSaver {

    public static void makeJson(Game game) {
        // create chap json object
        JsonObjectBuilder chapBuilder = Json.createObjectBuilder();
        chapBuilder.add("ChapLoc", game.getChap().getX() + " " + game.getChap().getY());

        JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();
        for (Object o: game.getChap().inventory) {
            if (o instanceof Key) {
                arrayBuilder1.add(((Key) o).getType() + " Color: " + ((Key) o).getColor().getRed() + " " +
                        ((Key) o).getColor().getGreen() + " " +
                        ((Key) o).getColor().getBlue());
            }
            else if (o instanceof Treasure) {
                arrayBuilder1.add(((Treasure) o).getType());
            }
        }
        chapBuilder.add("Inventory", arrayBuilder1);
        // create board json object
        JsonObjectBuilder boardBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder2 = Json.createArrayBuilder();
        for (int x = 0; x < game.getBoard().getBoard().length; x++) {
            for (int y = 0; y < game.getBoard().getBoard()[0].length; y++) {
                Tile currentTile = game.getBoard().getLocation(x, y);
                JsonObjectBuilder objBuilder = Json.createObjectBuilder();
                if (currentTile instanceof FreeTile && currentTile.getObject() != null) {
                    objBuilder.add("Type", currentTile.getObject().getType());
                }
                if (currentTile.getObject() instanceof Key) {
                    objBuilder.add("Color", ((Key) currentTile.getObject()).getColor().getRed() + " " + ((Key) currentTile.getObject()).getColor().getGreen()
                            + " " + ((Key) currentTile.getObject()).getColor().getBlue())
                            .add("Location", ((Key) currentTile.getObject()).getX() + " " + ((Key) currentTile.getObject()).getY());
                }
                JsonObjectBuilder tileBuilder = Json.createObjectBuilder()
                        .add("Object", objBuilder)
                        .add("Accessible", currentTile.isAccessible())
                        .add("Location", currentTile.x + " " + currentTile.y)
                        .add("Type", currentTile.getType());
                if (currentTile instanceof LockedDoor) {
                    tileBuilder.add("Color",
                            ((LockedDoor) currentTile).getColor().getRed() + " " +
                                    ((LockedDoor) currentTile).getColor().getGreen()
                            + " " + ((LockedDoor) currentTile).getColor().getBlue());
                }
                else if (currentTile instanceof infoField) {
                    tileBuilder.add("info", /*((infoField) tileBuilder).getInfo()*/" You sucks");
                }
                arrayBuilder2.add(tileBuilder);
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

    private static void writeToFile(String content) throws IOException {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        BufferedWriter writer = new BufferedWriter(new FileWriter("ChapSave" + formatter.format(date) + ".JSON"));
        writer.write(content);

        writer.close();
    }
}
