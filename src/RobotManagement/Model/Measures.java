package RobotManagement.Model;

public class Measures {
	/*Création d'un conteneur de compteur
	 * Executes les proprio suivantes :
	 *  Nombre de commandes exécutés sur le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles rencontrés par le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles visibles par le robot
	 *  Distance parcouru par le robot depuis le lancement du simulateur
	 *  */
	
	private int nbrCommandes = 0;
	private int nbrObstaclesRencontres = 0;
	private int nbrObstaclesVisibles = 0;
	private int distanceParcourue = 0;
	
	public void addCommandes(){this.nbrCommandes += 1;}
	public int getNbrCommandes(){return nbrCommandes;}
	public void addObstaclesRencontres(){this.nbrObstaclesRencontres += 1;}	 
	public int getObstaclesRencontres(){return nbrObstaclesRencontres;}	
	public void addObstaclesVisibles(){this.nbrObstaclesVisibles += 1;}
	public int getObstaclesVisibles(){return nbrObstaclesVisibles;}	
	public void addDistanceParcourue(){this.distanceParcourue += 1;}
	public int getDistanceParcourue(){return distanceParcourue;}
	
	
	public void razMesures(){
		this.nbrCommandes = 0;
		this.nbrObstaclesRencontres = 0;
		this.nbrObstaclesVisibles = 0;
		this.distanceParcourue = 0;
	}
	@Override
	public String toString() {
		return "Measures [nbrCommandes=" + nbrCommandes
				+ ", nbrObstaclesRencontres=" + nbrObstaclesRencontres
				+ ", nbrObstaclesVisibles=" + nbrObstaclesVisibles
				+ ", distanceParcourue=" + distanceParcourue + "]";
	}
	
	
}
