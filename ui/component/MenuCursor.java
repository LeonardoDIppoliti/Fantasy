package it.unicam.cs.mpgc.rpg126043.ui.component;

import it.unicam.cs.mpgc.rpg126043.interfaces.InputState;
/** Vertical menu cursor, rotating from last to first item. */
public class MenuCursor {
    private int index;
    public void update(InputState inputState,int size){
        if(size==0) return;
        if (inputState.consumeUp()) {
            index= (index-1+size) %size;
        }
        if(inputState.consumeDown()) {
            index = (index+1)%size;
        }
    }
    public void resetIndex(){
        index=0;
    }
    public int getIndex() {
        return index;
    }
}
