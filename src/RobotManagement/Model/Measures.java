package RobotManagement.Model;

public class Measures {
	/*Création d'un conteneur de compteur
	 * Executes les proprio suivantes :
	 *  Nombre de commandes exécutés sur le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles rencontrés par le robot depuis le lancement du simulateur
	 *  Nombre d’obstacles visibles par le robot
	 *  Distance parcouru par le robot depuis le lancement du simulateur
	 *  */
	
	private static int nbrCommandes = 0;
	private static int nbrObstaclesRencontres = 0;
	private static int nbrObstaclesVisibles = 0;
	private static int distanceParcourue = 0;
	
	public void addCommandes(){Measures.nbrCommandes += 1;}
	public int getNbrCommandes(){return nbrCommandes;}
	public void addObstaclesRencontres(){Measures.nbrObstaclesRencontres += 1;}	 
	public int getObstaclesRencontres(){return nbrObstaclesRencontres;}	
	public void addObstaclesVisibles(){Measures.nbrObstaclesVisibles += 1;}
	public int getObstaclesVisibles(){return nbrObstaclesVisibles;}	
	public void RAZObstaclesVisibles(){Measures.nbrObstaclesRencontres = 0;}	
	public void addDistanceParcourue(){Measures.distanceParcourue += 1;}
	public int getDistanceParcourue(){return distanceParcourue;}
	
	
	public void razMesures(){
		Measures.nbrCommandes = 0;
		Measures.nbrObstaclesRencontres = 0;
		Measures.nbrObstaclesVisibles = 0;
		Measures.distanceParcourue = 0;
	}
	@Override
	public String toString() {
		return "Measures [nbrCommandes=" + nbrCommandes
				+ ", nbrObstaclesRencontres=" + nbrObstaclesRencontres
				+ ", nbrObstaclesVisibles=" + nbrObstaclesVisibles
				+ ", distanceParcourue=" + distanceParcourue + "]";
	}
	
	
}
