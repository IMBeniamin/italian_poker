
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

/**
 * Internal deserializer class that is used when reading the cards.json file to create the objects of the specified type.
 * Interface from the Gson library.
 */
class CardDeserializer implements JsonDeserializer<Card> {
    /**
     * Custom implementation for the deserializer function.
     * This is necessary because the default deserializer is based on the object's attributes and whether they are transient or not
     * @param json JsonElement that references the file
     * @param typeOfT Type of the object that should be created
     * @param context Deserializer's context
     * @return The newly created object
     * @throws JsonParseException Exception thrown when the Json could not be deserialized
     */
    public Card deserialize(JsonElement json, Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String identifier = jsonObject.get("identifier").getAsString();
        String fancyName = jsonObject.get("fancyName").getAsString();
        int points = jsonObject.get("points").getAsInt();

        return new Card(identifier, fancyName, points);
    }
}

/**
 * Class that implements the deserialization from the Gson library
 */
public class CardsReader {
    public static final String jsonPath = "src/Poker/res/cards.json";

    /**
     * Reads data from the json file and stores it in a list.
     * @return List of cards read from the json file
     */
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