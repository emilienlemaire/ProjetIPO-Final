package environment;

import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;

public class SlipperyCase implements ISpecialCase {
    Game game;
    Lane lane;
    int absc;

    SlipperyCase(Game game, Lane lane){
        this.game = game;
        this.lane = lane;
        absc = game.randomGen.nextInt(game.width);
    }

    @Override
    public void addToGraphic() {
        game.getGraphic().add(new Element(absc, lane.getOrd(), Color.ORANGE));
    }

    @Override
    public int getAbsc() {
        return absc;
    }
}
