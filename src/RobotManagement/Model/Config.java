package RobotManagement.Model;

public abstract class Config {

	private double taux_chance;
	private int X_plateau;
	private int Y_plateau;
	private int nbreCase_plateau;
	
	/***
	 * Getter sur le nbre de case du plateau 
	 * 			
	 */
	public int getNbreCase_plateau() {
		return nbreCase_plateau;
	}

	/***
	 * setter sur le nbre de case du plateau 
	 * 			
	 */
	public void setNbreCase_plateau(int nbreCase_plateau) {
		this.nbreCase_plateau = nbreCase_plateau;
	}

	/***
	 * Getter sur le taux de chance 
	 * 			
	 */
	public double getTaux_chance() {
		return taux_chance;
	}

	/***
	 * setter sur le taux de chance 
	 * 			
	 */
	public void setTaux_chance(double taux_chance) {
		this.taux_chance = taux_chance;
	}

	/***
	 * getter sur la taille en abscisse X du plateau 
	 * 			
	 */
	
	public int getX_plateau() {
		return X_plateau;
	}

	/***
	 * setter sur la taille en abscisse X du plateau 
	 * 			
	 */
	public void setX_plateau(int x_plateau) {
		X_plateau = x_plateau;
	}

	/***
	 * getter sur la taille en ordonnée Y du plateau 
	 * 			
	 */
	public int getY_plateau() {
		return Y_plateau;
	}
	
	/***
	 * setter sur la taille en ordonnée Y du plateau 
	 * 			
	 */
	public void setY_plateau(int y_plateau) {
		Y_plateau = y_plateau;
	}
	
}