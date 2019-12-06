package environment;

import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;

public class BonusCase implements ISpecialCase{
    Game game;
    Lane lane;
    int absc;

    BonusCase(Game game, Lane lane){
        this.game = game;
        this.lane = lane;
        absc = game.randomGen.nextInt(game.width);
    }

    @Override
    public void addToGraphic() {
        game.getGraphic().add(new Element(absc, lane.getOrd(), Color.CYAN));
    }

    @Override
    public int getAbsc() {
        return absc;
    }
}
