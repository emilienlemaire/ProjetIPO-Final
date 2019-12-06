package environment;

import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;

public class TunnelCase implements ISpecialCase {
    Game game;
    Lane lane;
    int absc;
    int length;

    TunnelCase(Game game, Lane lane){
        this.game = game;
        this.lane = lane;
        this.absc = game.randomGen.nextInt(game.width);
        this.length = game.randomGen.nextInt(3);
    }

    @Override
    public void addToGraphic() {
        for (int i = 0; i < length; i++) {
            game.getGraphic().add(new Element(absc + i, lane.getOrd(), Color.DARK_GRAY));
        }
    }

    @Override
    public int getAbsc() {
        return absc;
    }
}
