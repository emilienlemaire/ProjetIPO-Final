package environment;

import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;

public class TrapCase implements ISpecialCase {
    private Game game;
    private int absc;
    private Lane lane;

    TrapCase(Game game, Lane lane) {
        this.game = game;
        absc = game.randomGen.nextInt(game.width);
        this.lane = lane;
        addToGraphic();
    }

    @Override
    public void addToGraphic() {
        game.getGraphic().add(new Element(absc, lane.getOrd(), Color.RED));
    }

    @Override
    public int getAbsc() {
        return absc;
    }
}
