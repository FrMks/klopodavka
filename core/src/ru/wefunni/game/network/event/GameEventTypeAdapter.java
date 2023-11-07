package ru.wefunni.game.network.event;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GameEventTypeAdapter extends TypeAdapter<GameEvent> {

    @Override
    public void write(JsonWriter out, GameEvent value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        // Defer the serialization to the context
        Gson gson = new Gson();
        JsonObject jsonObject = (JsonObject) gson.toJsonTree(value);
        jsonObject.addProperty("eventType", value.getEventType().name());
        gson.toJson(jsonObject, out);
    }

    @Override
    public GameEvent read(JsonReader in) throws IOException {
        // Defer the deserialization to the context
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(in, JsonObject.class);

        String eventTypeStr = jsonObject.get("eventType").getAsString();
        GameEvent.EventType eventType = GameEvent.EventType.valueOf(eventTypeStr);

        Class<? extends GameEvent> eventClass = getClassForType(eventType);
        return gson.fromJson(jsonObject, eventClass);
    }

    private Class<? extends GameEvent> getClassForType(GameEvent.EventType eventType) {
        switch (eventType) {
            case GAME_START:
                return GameStartEvent.class;
            case CELL_CHANGE:
                return CellChangeEvent.class;
            case GAME_WIN:
                return GameWinEvent.class;
            default:
                throw new IllegalArgumentException("Unknown event type: " + eventType);
        }
    }
}
