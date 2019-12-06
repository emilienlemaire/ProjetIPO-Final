package frog;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;
import gameCommons.IFrog;
import util.Direction;

public class FrogInf implements IFrog {
    private Case aCase;
    private Game game;
    private int ord;
    private int score = 0;
    private Direction direction = Direction.up;
    private IEnvironment environment;
    private int bonus = 0;

    public FrogInf(Game game){
        this.game = game;
        aCase = new Case(13, 1);
        ord = 1;
    }

    @Override
    public int getOrd() {
        return ord;
    }

    @Override
    public int getScore() {
        return score + bonus;
    }

    @Override
    public void setEnvironment(IEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public Case getPosition() {
        return aCase;
    }

    @Override
    public Direction getDirection(){
        return direction;
    }

    @Override
    public void move(Direction key) {
        Case bCase;
		switch (key){
			case up:
			    bCase = new Case(aCase.absc, aCase.ord + 1);
			    if (!environment.isTunnel(bCase)){
				    ord++;
				    if (ord - 1 > score) score = ord-1;
				    direction = Direction.up;
				    if (environment.isBonus(aCase))
				        bonus++;
				    break;
			    }
			case down:
			    bCase = new Case(aCase.absc, aCase.ord - 1);
			    if (!environment.isTunnel(bCase)) {
                    if (ord > 1) ord--;
                    direction = Direction.down;
                    if (environment.isBonus(bCase))
                        bonus++;
                    break;
                }
			case right:
			    bCase = new Case(aCase.absc + 1, aCase.ord);
			    if (!environment.isTunnel(bCase)) {
                    if (aCase.absc < game.width) aCase = new Case(aCase.absc + 1, aCase.ord);
                    direction = Direction.right;
                    if (environment.isBonus(bCase))
                        bonus++;
                    break;
                }
			case left:
			    bCase = new Case(aCase.absc, aCase.ord + 1);
			    if (!environment.isTunnel(bCase)) {
                    if (aCase.absc > 0) aCase = new Case(aCase.absc - 1, aCase.ord);
                    direction = Direction.left;
                    if (environment.isBonus(bCase))
                        bonus++;
                    break;
                }
		}
    }
}
