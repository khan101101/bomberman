package game.model;

import game.config.GameSettings;
import game.debug.Debugger;

import game.event.ExplosionEvent;
import javafx.beans.Observable;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

import javax.swing.event.EventListenerList;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends GameObject implements IDestroyable
{
    private static final String             path 				= "res/visuals/bomb/bomb.png";
    private static final int	            animationInteval	= 40;

    private SpriteSheet			            bombSheet;
    private Animation			            animationBurn;
    private int					            range;
    private int					            timer;
    private int					            time;
    private boolean				            exploded;
    private EventListenerList               listeners           = new EventListenerList();
    
    /**
     * Directions for the calculation of the blast, UP,LEFT,DOWN,RIGHT
     */
    private int blastDirection[][] = {{1, 0, -1, 0}, {0, 1, 0, -1}};

    public Bomb(int tileX, int tileY, int bombRange, int bombTimer)
    {
        super(tileX, tileY);
        this.timer		            = bombTimer;
        this.range		            = bombRange;
        this.time		            = 0;
        this.exploded	            = false;
        loadImage();
    }

    public int getRange()
    {
        return range;
    }

    public void setRange(int range) 
    {
        this.range = range;
    }

    public int getTimer() 
    {
        return timer;
    }

    public void setTimer(int timer) 
    {
        this.timer = timer;
    }

    @Override
    public boolean destroy()
    {
//        if(!isExploding)
//        {
//            explode();
//            return true;
//        }
//        else
            return false;
    }

    /**
     * Calculates the explosion.
     * spreads in each blastDirection for its range
     */
    private void explode()
    {
//        for(int direction=0;direction<blastDirection[0].length;direction++){
//            for(int r=1;r<=range;r++){
//                //Debugger.log("(" + getTileX() + blastDirection[0][direction] * r + "," + getTileY() + blastDirection[1][direction] * r + ")");
//                /*If we hit a collision blast won't spread any longer in this direction*/
//                if(oldBombermanMap.isBlocked(getTileX() + blastDirection[0][direction] * r, getTileY() + blastDirection[1][direction] * r)) {
//                    oldBombermanMap.destroy(getTileX() + blastDirection[0][direction]*r,getTileY() +blastDirection[1][direction]*r);
//                    break;
//                }else
//                    oldBombermanMap.destroy(getTileX() + blastDirection[0][direction]*r,getTileY()+blastDirection[1][direction]*r);
//            }
//        }
//        player.removeBomb();
//        oldBombermanMap.removeGameObject(getTileX(), getTileY(), this);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
    {
        animationBurn.draw(tileX * GameSettings.TILE_HEIGHT, tileY * GameSettings.TILE_WIDTH);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
    {
    	if(time >= timer && !this.exploded)
    	{
    		this.setExploded();
    	}
    	
    	time += delta;
    }
    
    public void setExploded()
    {
    	this.exploded = true;
        this.notifyExploded();
    }
    
    public boolean isExploded()
    {
    	return this.exploded;
    }

    public int getBombRange()
    {
    	return this.range;
    }
    
    private void loadImage()
    {
        try
        {
            bombSheet		= new SpriteSheet(path, 64, 64);
            animationBurn	= new Animation(bombSheet, animationInteval);
        }
        catch (SlickException e)
        {
            //TODO
        }
    }

    public void addAdListener(ExplosionListener listener)
    {
        listeners.add(ExplosionListener.class, listener);
    }

    public void removeAdListener(ExplosionListener listener)
    {
        listeners.remove(ExplosionListener.class, listener);
    }

    protected synchronized void notifyExploded ()
    {
        ExplosionEvent e = new ExplosionEvent(this, this);

        for (ExplosionListener l : listeners.getListeners(ExplosionListener.class))
        {
            l.exploded(e);
        }
    }
}