package ru.wefunni.game.network.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.wefunni.game.CellState;

public abstract class GameEvent {

    protected EventType eventType;

    public GameEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    // Enum for event types
    public enum EventType {
        GAME_START,
        CELL_CHANGE,
        GAME_WIN
    }

    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(GameEvent.class, new GameEventTypeAdapter());
        Gson gson = builder.create();

        // Example serialization
        GameEvent event = new GameWinEvent(CellState.PlayerType.PLAYER_1); // Use any subclass of GameEvent
        String json = gson.toJson(event);
        System.out.println(json);

        // Example deserialization
        GameEvent deserializedEvent = gson.fromJson(json, GameEvent.class);
        System.out.println(((GameWinEvent)deserializedEvent).getPlayerType()); // Should print GameWinEvent
    }


}


