package ru.wefunni.game.network.data;

import ru.wefunni.game.CellState;

public class CellStateDTOFactory {
    public static CellStateDTO create(CellState cellState) { //превращение в DTO
        if(cellState.isEmpty()) return CellStateDTO.EMPTY;
        if(cellState.isPlayer_1() && !cellState.isShaded()) return CellStateDTO.PLAYER1_CROSS;
        if(cellState.isPlayer_2() && !cellState.isShaded()) return CellStateDTO.PLAYER2_CROSS;
        if(cellState.isShaded() && cellState.isPlayer_1()) return CellStateDTO.PLAYER1_SHADED;
        if(cellState.isShaded() && cellState.isPlayer_2()) return CellStateDTO.PLAYER2_SHADED;
        return CellStateDTO.EMPTY;
    }

    public static CellState parse(CellStateDTO cellStateDTO) { //превращение в CellState
        if(cellStateDTO == CellStateDTO.EMPTY) return new CellState(true, false, null);
        if(cellStateDTO == CellStateDTO.PLAYER1_CROSS) return new CellState(false, false, CellState.PlayerType.PLAYER_1);
        if(cellStateDTO == CellStateDTO.PLAYER2_CROSS) return new CellState(false, false, CellState.PlayerType.PLAYER_2);
        if(cellStateDTO == CellStateDTO.PLAYER1_SHADED) return new CellState(false, true, CellState.PlayerType.PLAYER_1);
        if(cellStateDTO == CellStateDTO.PLAYER2_SHADED) return new CellState(false, true, CellState.PlayerType.PLAYER_2);
        return new CellState();
    }
}
