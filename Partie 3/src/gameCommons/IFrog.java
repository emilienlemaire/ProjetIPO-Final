package gameCommons;

import util.Direction;

public interface IFrog {
	
	/**
	 * Donne la position actuelle de la grenouille
	 * @return
	 */
	public Case getPosition();
	
	/**
	 * Donne la direction de la grenouille, c'est é dire de son dernier mouvement
	 * @return
	 */
	public Direction getDirection();
	
	/**
	 * Déplace la grenouille dans la direction donnée et teste la fin de partie
	 * @param key
	 */
	public void move(Direction key);

	public int getOrd();

	public int getScore();

}
