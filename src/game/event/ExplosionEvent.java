package game.event;

import game.model.Bomb;

import java.util.EventObject;

public class ExplosionEvent extends EventObject
{
    private Bomb bomb;

    public ExplosionEvent(Object source, Bomb bomb) {
        super(source);

        this.bomb = bomb;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }
}
