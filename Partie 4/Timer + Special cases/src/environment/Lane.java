package environment;

import java.util.ArrayList;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IFrog;

public class Lane {
    private Game game;
    private int ord;
    private int speed;
    private ArrayList<Car> cars = new ArrayList<>();
    private boolean leftToRight;
    private double density;
    private int horloge;
    private ArrayList<ISpecialCase> specialCases = new ArrayList<>();

    public Lane(Game game, int ord, int speed, boolean leftToRight, double density) {
        this.game = game;
        this.ord = ord;
        this.speed = speed;
        this.leftToRight = leftToRight;
        this.density = density;
        horloge = 0;

        mayAddCar();
        for (int i = 0; i < game.width; i++) {
            mayAddCase();
        }
    }

    public Lane(Lane lane, int ord) {
        this.game = lane.game;
        this.ord = ord;
        this.speed = lane.speed;
        this.leftToRight = lane.leftToRight;
        this.density = lane.density;
        this.horloge = lane.horloge;

        cars = lane.cars;

        for (Car car :
                cars) {
            car.setOrd(ord);
        }

        update();

    }

    public void update() {
        for (ISpecialCase specialCase :
                specialCases) {
            if (specialCase.getClass() != TunnelCase.class)
                specialCase.addToGraphic();
        }

        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).update(horloge == speed);
            if (cars.get(i).shouldBeDeleted()) {
                cars.remove(i);
            }
        }

        for (ISpecialCase specialCase :
                specialCases) {
            if (specialCase.getClass() == TunnelCase.class)
                specialCase.addToGraphic();
        }

        if (horloge == speed) {
            horloge = 0;
            mayAddCar();
        } else horloge++;
    }

    /*
     * Fourni : mayAddCar(), getFirstCase() et getBeforeFirstCase()
     */

    /**
     * Ajoute une voiture au début de la voie avec probabilité égale é la
     * densité, si la premiére case de la voie est vide
     */
    private void mayAddCar() {
        if (isSafe(getFirstCase()) && isSafe(getBeforeFirstCase())) {
            if (game.randomGen.nextDouble() < density) {
                cars.add(new Car(game, getBeforeFirstCase(), leftToRight));
            }
        }
    }


    private void mayAddCase() {
        if (game.randomGen.nextFloat() < density/(float)10){
            int type = game.randomGen.nextInt(4);
            ISpecialCase specialCase;
            switch (type){
                case 0:
                    specialCase = new TrapCase(game, this);
                    specialCases.add(specialCase);
                case 1:
                    specialCase = new SlipperyCase(game, this);
                    specialCases.add(specialCase);
                    break;
                case 2:
                    specialCase = new TunnelCase(game, this);
                    specialCases.add(specialCase);
                    break;
                case 3:
                    specialCase = new BonusCase(game, this);
                    specialCases.add(specialCase);
                    break;
            }
        }
    }

    public boolean isSafe(Case firstCase) {
        for (Car car :
                cars) {
            if (!car.isSafe(firstCase))
                return false;
        }

        for (ISpecialCase specialCase :
                specialCases) {
            if (specialCase.getAbsc() == firstCase.absc){
                if (specialCase.getClass() == TrapCase.class)
                    return false;
                if (specialCase.getClass() == SlipperyCase.class){
                    IFrog frog = game.getFrog();
                    frog.move(frog.getDirection());
                }
            }
        }
        return true;
    }

    private Case getFirstCase() {
        if (leftToRight) {
            return new Case(0, ord);
        } else
            return new Case(game.width - 1, ord);
    }

    private Case getBeforeFirstCase() {
        if (leftToRight) {
            return new Case(-1, ord);
        } else
            return new Case(game.width, ord);
    }

    public void setOrd(int ord) {
        this.ord = ord;
        for (Car car :
                cars) {
            car.setOrd(ord);
        }
    }

    public int getOrd() {
        return ord;
    }

    public ArrayList<ISpecialCase> getSpecialCases() {
        return specialCases;
    }
}
