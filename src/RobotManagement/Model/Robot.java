package RobotManagement.Model;

public class Robot extends Config {
	private int xInit,yInit,x,y;
	private Enum_Orientation_Robot orientationInit, orientation;
	private Env env_decouvert=null;
	private Measures mesures = new Measures();
	//private Config conf;
	//capteur de vision ?
	
	public Robot(int xInit, int yInit,
			Enum_Orientation_Robot orientationInit, Env env_decouvert) {
			super();
			this.xInit = xInit;
			this.yInit = yInit;
			this.x = xInit;
			this.y = yInit;
			this.orientationInit = orientationInit;
			this.orientation = orientationInit;
			this.env_decouvert = env_decouvert;
		}

	//getters and setters
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
//		mesures.addCommandes();
		//ajouter nbr obstacles visibles
		if (DeplacementEtreValide(x, y)) {
//			mesures.addDistanceParcourue();					
			
			Case[][] tab=env_decouvert.getTableauEnv();
			tab[this.x][this.y].setParcouru();
			setX(x);
			setY(y);
			tab[this.x][this.y].setRobot();
		}
	}
	
	public boolean DeplacementEtreValide(int x, int y) {
		boolean retour=false;
		if (x >= 0 && x <= env_decouvert.getX_plateau() && y >= 0 && y <= env_decouvert.getY_plateau()) {
			if ( (x==this.x && (y==this.y+1 || y==this.y-1)) 
					|| (y==this.y && (x==this.x+1 || x==this.x-1)) ) {
				if (env_decouvert.getTableauEnv()[x][y].etat_case==Enum_Etat_Case.vide 
						|| env_decouvert.getTableauEnv()[x][y].etat_case==Enum_Etat_Case.parcouru) {
					retour = true;
				} else {
					mesures.addObstaclesRencontres();
					System.out.println("Déplacment impossible : Obstacle");
				}
			}else{
				System.out.println("Déplacment impossible : 1 case par 1 case");
			}
		} else {
			System.out.println("Coordonnées hors plateau");
		}
		return retour;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Measures mesures1 = new Measures();

		int i=0;
		for(i=0;i<10;i++)
		{
			mesures1.addCommandes();

		}
		System.out.println("addCommandes : "+mesures1.getNbrCommandes()+";" );
	}
	
}