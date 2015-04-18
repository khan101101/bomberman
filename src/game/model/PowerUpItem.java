package game.model;

import game.config.GameSettings;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class PowerUpItem extends GameObject implements IDestroyable
{
    private boolean			destroyed			= false;
    private int 			timer;
    
    public PowerUpItem(int posX, int posY, int flameTime)
    {
        super(posX, posY);
        timer = flameTime;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
    {
        g.drawImage(this.image, this.tileX * GameSettings.TILE_WIDTH, this.tileY * GameSettings.TILE_HEIGHT);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
    {
    	if (timer > 0) {
        	timer -= delta;
    	}
    }

	@Override
	public boolean destroy() {
		if (timer <= 0) {
			return this.destroyed = true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDestroyed() {
		return this.destroyed;
	}
}
