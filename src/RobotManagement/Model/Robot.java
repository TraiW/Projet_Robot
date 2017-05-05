package RobotManagement.Model;

public class Robot {
	private int xInit,yInit,x,y;
	private Enum_Orientation_Robot orientationInit, orientation;
	private Env env_decouvert;
	private Measures mesures;
	private Config conf;
	//capteur de vision ?
	
	public Robot(int xInit, int yInit, int x, int y,
			Enum_Orientation_Robot orientationInit, Enum_Orientation_Robot orientation,
				Env env_decouvert) {
			super();
			this.xInit = xInit;
			this.yInit = yInit;
			this.x = x;
			this.y = y;
			this.orientationInit = orientationInit;
			this.orientation = orientation;
			this.env_decouvert = env_decouvert;
		}

	public int getxInit() {return xInit;}
	public void setxInit(int xInit) {this.xInit = xInit;}
	public int getyInit() {return yInit;}
	public void setyInit(int yInit) {this.yInit = yInit;}
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public Enum_Orientation_Robot getOrientationInit() {return orientationInit;}
	public void setOrientationInit(Enum_Orientation_Robot orientationInit){this.orientationInit = orientationInit;}
	public Enum_Orientation_Robot getOrientation() {return orientation;}
	public void setOrientation(Enum_Orientation_Robot orientation) {this.orientation = orientation;}
	public Env getEnv_decouvert() {return env_decouvert;}
	public void setEnv_decouvert(Env env_decouvert) {this.env_decouvert = env_decouvert;}
	
	public void deplacement(int x, int y){
		mesures.addCommandes();
		//ajouter nbr obstacles visibles
		if (DeplacementEtreValide(x, y)) {
			mesures.addDistanceParcourue();					
			setX(x);
			setY(y);
		}
	}
	
	public boolean DeplacementEtreValide(int x, int y) {
		boolean retour=false;
		if (x >= 0 && x <= conf.getX_plateau() && y >= 0 && y <= conf.getY_plateau()) {
			if ((x == this.x + 1 || x == this.x - 1) || (y == this.y + 1 || y == this.y - 1)) {
				if (env_decouvert.getTableauEnv()[x][y].etat_case==Enum_Etat_Case.vide) {
					retour = true;
				} else {
					mesures.addObstaclesRencontres();
					System.out.println("Déplacment impossible : Obstacle");
				}
			}
		} else {
			System.out.println("Coordonnées hors plateau");
		}
		return retour;
	}
	
}