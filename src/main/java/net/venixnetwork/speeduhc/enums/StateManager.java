package net.venixnetwork.speeduhc.enums;

/**
 * Created by Ryan on 09/02/2017.
 */
public class StateManager {

    private static StateManager ins;
    private GameState gameState = GameState.PREPARING;

    public static StateManager getIns(){
        if (ins == null){
            ins = new StateManager();
        }
        return ins;
    }


    public GameState getGameState(){ return gameState;}
    public void setGameState(GameState gameState){ this.gameState = gameState;}
}
