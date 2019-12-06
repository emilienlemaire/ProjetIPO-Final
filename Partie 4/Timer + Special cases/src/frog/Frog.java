package frog;

import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import gameCommons.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case aCase;
	private Direction direction;
	private IEnvironment environment;

	public Frog(Game game){
		this.game = game;
		aCase = new Case(13, 0);
	}

	@Override
	public Case getPosition() {
		return aCase;
	}

	@Override
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void move(Direction key) {
		switch (key){
			case up:
				if(aCase.ord < game.height) aCase = new Case(aCase.absc, aCase.ord + 1);
				direction = Direction.up;
				break;
			case down:
				if(aCase.ord > 0) aCase = new Case(aCase.absc, aCase.ord - 1);
				direction = Direction.down;
				break;
			case right:
				if(aCase.absc < game.width) aCase = new Case(aCase.absc + 1, aCase.ord);
				direction = Direction.right;
				break;
			case left:
				if(aCase.absc > 0) aCase = new Case(aCase.absc -1, aCase.ord);
				direction = Direction.left;
				break;
		}
	}

	@Override
	public int getOrd() {
		return aCase.ord;
	}

	@Override
	public int getScore() {
		return 0;
	}

	@Override
	public void setEnvironment(IEnvironment environment) {

	}
}
