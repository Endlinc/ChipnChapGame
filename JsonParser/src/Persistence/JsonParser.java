package Persistence;

import javax.json.*;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;

public class JsonParser {
    public String byJsonObject(Game game) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // Object builder add stuff.
        objectBuilder.add("Char Array",game.getCharacters().toString())
                .add("Board", game.getBoard().toString());

        JsonObject jsonObject = objectBuilder.build();

        String jsonString = null;
        try {
            Writer writer = new StringWriter();
            Json.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public String byJsonArray(Game game) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Character c: game.getCharacters()) {
            arrayBuilder.add(c);
        }
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder().add("Characters", arrayBuilder);
        JsonObject jsonObject = objectBuilder.build();

        String jsonString = null;
        try {
            Writer writer = new StringWriter();
            Json.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
