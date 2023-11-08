package ru.wefunni.game.network.event;

import ru.wefunni.game.CellState;
import ru.wefunni.game.Vector2Int;
import ru.wefunni.game.network.data.CellStateDTO;

public class CellChangeEvent extends GameEvent {

    private Vector2Int cellPosition;
    private CellStateDTO cellState;
    public CellChangeEvent(Vector2Int cellPosition, CellStateDTO cellState) {
        super(EventType.CELL_CHANGE); //вызываем конструктор супер класса
        this.cellPosition = cellPosition;
        this.cellState = cellState;
    }

    public Vector2Int getCellPosition() {
        return cellPosition;
    }

    public CellStateDTO getCellState() {
        return cellState;
    }
}