package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

public class EnvInf implements IEnvironment {
    private ArrayList<Lane> lanes = new ArrayList<>();
    private ArrayList<Lane> lanesToDisplay;
    private Game game;

    public EnvInf(Game game){
        this.game = game;
        lanes.add(new Lane(game, 0, -1, game.randomGen.nextBoolean(), 0.0d));
        lanes.add(new Lane(game, 1, -1, game.randomGen.nextBoolean(), 0.0d));
        for (int i = 2; i < game.height; i++) {
            lanes.add(new Lane(game, i, game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1, game.randomGen.nextBoolean(), game.defaultDensity));
        }
        lanesToDisplay = lanes;
    }

    @Override
    public boolean isSafe(Case c) {
        if (c.ord == 0 || c.ord == game.height)
            return true;
        return lanesToDisplay.get(c.ord).isSafe(c);
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return (c.ord == game.height - 1);
    }

    @Override
    public void update() {
        int ordMin = game.getFrogOrd() - 1;
        if (ordMin + game.height > lanes.size()) lanes
                .add(new Lane(game, ordMin + game.height, game.randomGen.nextInt(game.minSpeedInTimerLoops) + 1, game.randomGen.nextBoolean(), game.defaultDensity));

        lanesToDisplay = new ArrayList<>();
        for (int i = 0; i < game.height; i++) {
            lanes.get(i + ordMin).setOrd(i);
            lanesToDisplay.add(lanes.get(i + ordMin));
        }
        for (int i = 0; i < lanesToDisplay.size(); i++) {
            lanesToDisplay.get(i).update();
        }
    }

    public boolean isTunnel(Case aCase){
        ArrayList<TunnelCase> tunnelCases = new ArrayList<>();
        ArrayList<ISpecialCase> specialCases = lanesToDisplay.get(aCase.ord).getSpecialCases();
        for (ISpecialCase specialCase :
                specialCases) {
            if (specialCase.getClass() == TunnelCase.class)
                tunnelCases.add((TunnelCase) specialCase);
        }

        for (TunnelCase tunnelCase :
                tunnelCases) {
            if (tunnelCase.absc == aCase.absc)
                return true;
        }

        return false;
    }

    @Override
    public boolean isBonus(Case aCase) {
        ArrayList<BonusCase> tunnelCases = new ArrayList<>();
        ArrayList<ISpecialCase> specialCases = lanesToDisplay.get(aCase.ord).getSpecialCases();
        for (ISpecialCase specialCase :
                specialCases) {
            if (specialCase.getClass() == BonusCase.class)
                tunnelCases.add((BonusCase) specialCase);
        }

        for (BonusCase tunnelCase :
                tunnelCases) {
            if (tunnelCase.absc == aCase.absc)
                return true;
        }

        return false;
    }
}
