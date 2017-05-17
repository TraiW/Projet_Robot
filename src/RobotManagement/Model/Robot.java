package RobotManagement.Model;

public class Robot{
	private int xInit,yInit,x,y;
	private Enum_Orientation_Robot orientationInit, orientation;
	private Env env_decouvert=null;
	private Measures mesures = new Measures();
	MatriceVision matriceVision = null;
	//private Config conf;
	//capteur de vision ?
	
	public Robot(int xInit, int yInit,
			Enum_Orientation_Robot orientationInit, Env env_decouvert, MatriceVision mat) {
			super();
			this.xInit = xInit;
			this.yInit = yInit;
			this.x = xInit;
			this.y = yInit;
			this.orientationInit = orientationInit;
			this.orientation = orientationInit;
			this.env_decouvert = env_decouvert;
			this.matriceVision= mat;
		}
	public Measures getMeasures(){
		return mesures;
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

	public boolean deplacement(int x, int y, Enum_Orientation_Robot orient){
		boolean retour=false;
//		mesures.addCommandes();
		//ajouter nbr obstacles visibles
		if (DeplacementEtreValide(x, y)) {
//			mesures.addDistanceParcourue();					
			
			Case[][] tab=env_decouvert.getTableauEnv();
			tab[this.x][this.y].setParcouru();
			setX(x);
			setY(y);
			tab[this.x][this.y].setRobot();
			this.setOrientation(orient);
			retour=true;
		}
		return retour;
	}
	
	public boolean DeplacementEtreValide(int x, int y) {
		boolean retour=false;
		if (x >= 0 && x <= env_decouvert.getX_plateau() && y >= 0 && y <= env_decouvert.getY_plateau()) {
			if ( (x==this.x && (y==this.y+1 || y==this.y-1)) 
					|| (y==this.y && (x==this.x+1 || x==this.x-1)) ) {
				if (env_decouvert.getTableauEnv()[x][y].isVide() 
						|| env_decouvert.getTableauEnv()[x][y].isParcouru()) {
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
	
	public void RemoveMask(Enum_Orientation_Robot oRobot)
	{
		switch(oRobot)
		{	//de base orientation sud
			case N:
				matriceVision=matriceVision.rotation(oRobot);
				break;
			case E:
				matriceVision=matriceVision.rotation(oRobot);
				break;
			case W:
				matriceVision=matriceVision.rotation(oRobot);
				break;
				
			default:
				break;	
		}	
		for (int i=0;i<this.matriceVision.getNbLignes();i++){
			for (int j=0;j<this.matriceVision.getNbColonnes();j++){
				if(matriceVision.getMat()[i][j]!=0)
				{
					switch(oRobot){
						case N:
							if(this.env_decouvert.coordEtreDansPlateau(this.x-1+j, this.y-i)){
								//System.out.println("démasque NORD x: "+(this.x-1+j)+" y: "+  (this.y-i));
								this.env_decouvert.getTableauEnv()[this.x-1+j][this.y-i].setMasquage(false);
							}
							break;
						case S:
							if(this.env_decouvert.coordEtreDansPlateau(this.x-1+j, this.y+i)){
								//System.out.println("démasque SUD x: "+(this.x-1+j)+" y: "+  (this.y+i));
								this.env_decouvert.getTableauEnv()[this.x-1+j][this.y+i].setMasquage(false);
								}
							break;
						case E:
							if(this.env_decouvert.coordEtreDansPlateau(this.x+j, this.y-i+1)){
								//System.out.println("démasque EST x: "+(this.x+j)+" y: "+  (this.y-i+1));
								this.env_decouvert.getTableauEnv()[this.x+j][this.y-i+1].setMasquage(false);
							}
							break;
						case W:
							if(this.env_decouvert.coordEtreDansPlateau(this.x-j, this.y-i+1)){
								//System.out.println("démasque WEST x: "+(this.x-j)+" y: "+  (this.y-i+1));
								this.env_decouvert.getTableauEnv()[this.x-j][this.y-i+1].setMasquage(false);
							}
							break;
						default:
							break;
					}
				}
			}
		}

	}
	
	public static void main(String[] args) {
		//Measures mesures1 = new Measures();
		//System.out.println("addCommandes : "+mesures1.getNbrCommandes()+";" );
		
	}
	
}