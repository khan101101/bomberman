package game.state;

import game.BombermanGame;
import game.menu.MainMenu;
import game.menu.Menu.Action;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


public class MainMenuState extends BombermanGameState
{
		
	private Image background				= null;
	private MainMenu menu 					= null;
	
    public MainMenuState () {
        super(BombermanGameState.MAIN_MENU);    
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	
    	background = new Image("res/visuals/backgrounds/menuBackground.png");
    	
    	menu = new MainMenu(((BombermanGame)game).getDefaultGameRoundConfig(),
    			((BombermanGame)game).getMapConfigs(),
    			((BombermanGame)game).getPlayerConfigs(),
    			((BombermanGame)game).getInputConfigurations());
    	menu.init();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
    	
    	background.draw(0, 0);
    	menu.render(container, game, graphics);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        
        menu.update(container, game, delta);
        
        if (menu.getActualAction() != Action.NO_ACTION) {
        	
        	Action currentAction = menu.getActualAction();
        	menu.setActualAction(Action.NO_ACTION);
        	
        	switch (currentAction) {
        	
        		case EXIT_GAME: 
        			((BombermanGame)game).exitGame();
        			break;
        		
        		case START_GAME_ROUND:
        			((GameRoundState)game.getState(GAME_ROUND)).setGameRoundConfig(menu.getGameRoundConfig());
		        	game.enterState(GAME_ROUND);
		        	break;
        			
        		default: break;
        	}
        }        
    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
    	menu.reset();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
    	container.getInput().clearKeyPressedRecord();
    }
    
}
