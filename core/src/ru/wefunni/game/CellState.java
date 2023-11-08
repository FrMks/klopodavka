package ru.wefunni.game;

import com.badlogic.gdx.utils.Null;

public class CellState {
    private boolean isEmpty;
    private boolean isShaded;

    private PlayerType player = null;

    public CellState() {
        this.isEmpty = true;
        this.isShaded = false;
        this.player = null;
    }

    public CellState(boolean isEmpty, boolean isShaded, PlayerType player) {
        this.isEmpty = isEmpty;
        this.isShaded = isShaded;
        this.player = player;
    }

    public boolean canUseAsPatch(PlayerType player) {
        if(isEmpty) {
            return false;
        }
        if(!isShaded) {
            return player == this.player;
        }
        return player != this.player;
    }
    public boolean isPlayer_1() {
        return !isEmpty && player == PlayerType.PLAYER_1;
    }
    public boolean isPlayer_2() {
        return !isEmpty && player == PlayerType.PLAYER_2;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isShaded() {
        return isShaded;
    }

    public void makeEmpty() {
        this.isEmpty = true;
        this.isShaded = false;
        this.player = null;
    }

    public void setCross(PlayerType player) {
        this.isEmpty = false;
        this.isShaded = false;
        this.player = player;
    }

    public void shade() {
        this.isEmpty = false;
        this.isShaded = true;
    }

    public enum PlayerType {
        PLAYER_1,
        PLAYER_2
    }

    public CellState copy() {
        CellState cellState = new CellState(isEmpty, isShaded, player);
        return cellState;
    }

    public boolean isPlayer(PlayerType player) {
        return  player == this.player;
    }

}
