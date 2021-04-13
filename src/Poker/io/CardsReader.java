
package Poker.io;

import Poker.components.Card;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class CardDeserializer implements JsonDeserializer<Card> {
    public Card deserialize(JsonElement json, Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String identifier = jsonObject.get("identifier").getAsString();
        String fancyName = jsonObject.get("fancyName").getAsString();
        int points = jsonObject.get("points").getAsInt();

        return new Card(identifier, fancyName, points);
    }
}

public class CardsReader {
    public static final String jsonPath = "src/Poker/res/cards.json";
    public static ArrayList<Card> getCards() {
        try {
            Reader fileReader = Files.newBufferedReader(Paths.get(CardsReader.jsonPath));
            Type CardListType = new TypeToken<ArrayList<Card>>() {}.getType();

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Card.class, new CardDeserializer());

            Gson gson = gsonBuilder.create();
            return gson.fromJson(fileReader, CardListType);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}