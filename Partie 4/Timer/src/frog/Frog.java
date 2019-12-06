package frog;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.Case;
import util.Direction;

public class Frog implements IFrog {
	
	private Game game;
	private Case aCase;

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
		return null;
	}

	@Override
	public void move(Direction key) {
		switch (key){
			case up:
				if(aCase.ord < game.height) aCase = new Case(aCase.absc, aCase.ord + 1);
				break;
			case down:
				if(aCase.ord > 0) aCase = new Case(aCase.absc, aCase.ord - 1);
				break;
			case right:
				if(aCase.absc < game.width) aCase = new Case(aCase.absc + 1, aCase.ord);
				break;
			case left:
				if(aCase.absc > 0) aCase = new Case(aCase.absc -1, aCase.ord);
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
}
