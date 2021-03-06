package game.model.objects;

public class ShieldUp extends PowerUpItem
{
    private static final String shieldUPImagePath	= "res/visuals/powerups/PUshield.png";
	int 						time				= 10_000;
	
	public ShieldUp(int posX, int posY)
	{
        super(posX, posY, Explosion.timer, shieldUPImagePath);
	}

	public int getTime()
	{
		return this.time;
	}

	public void setTime(int time)
	{
		this.time = time;
	}
}
