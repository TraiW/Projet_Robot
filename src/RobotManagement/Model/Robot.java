package RobotManagement.Model;

public class Robot{
	private int xInit,yInit,x,y;
	private Enum_Orientation_Robot orientationInit, orientation;
	private Env env_decouvert=null;
	private Measures mesures = null;
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
			this.mesures = new Measures();
			this.mesures.razMesures();
		}
	public Measures getMeasures(){
		return this.mesures;
	}
	public void setMeasures(Measures mes){
		this.mesures=mes;
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
	public MatriceVision getMatVision() { return this.matriceVision;}
	public void setMatVision(MatriceVision mat) {this.matriceVision = mat;}
	public Enum_Orientation_Robot getOrientationInit() {return orientationInit;}
	public void setOrientationInit(Enum_Orientation_Robot orientationInit){this.orientationInit = orientationInit;}
	public Enum_Orientation_Robot getOrientation() {return orientation;}
	public void setOrientation(Enum_Orientation_Robot orientation) {this.orientation = orientation;}
	public Env getEnv_decouvert() {return env_decouvert;}
	public void setEnv_decouvert(Env env_decouvert) {
		this.env_decouvert = env_decouvert;
		this.RemoveMask();
	}
	
	public boolean deplacement(int x, int y, Enum_Orientation_Robot orient){
		boolean retour=false;
		this.mesures.addCommandes();
		//ajouter nbr obstacles visibles
		this.mesures.RAZObstaclesVisibles();
		if (DeplacementEtreValide(x, y)) {
			this.mesures.addDistanceParcourue();
			
			//System.out.println("X = "+x + " Y = "+y);
			Case[][] tab=env_decouvert.getTableauEnv();
			tab[this.x][this.y].setParcouru();
			setX(x);
			setY(y);
			tab[this.x][this.y].setRobot();
			this.setOrientation(orient);

			//System.out.println("Xapres= "+this.x + " Y = "+this.y);

			retour=true;
		}
		return retour;
	}
	
	public boolean DeplacementEtreValide(int x, int y) {
		boolean retour=false;
		if (x >= 0 && x < this.env_decouvert.getX_plateau() && y >= 0 && y < this.env_decouvert.getY_plateau()) {
			if ( (x==this.x && (y==this.y+1 || y==this.y-1)) 
					|| (y==this.y && (x==this.x+1 || x==this.x-1)) ) {
				if (this.env_decouvert.getTableauEnv()[x][y].isVide() 
						|| this.env_decouvert.getTableauEnv()[x][y].isParcouru()) {
					retour = true;
				} else {
					System.out.println("Déplacement impossible : Obstacle");
				}
			}else{
				System.out.println("Déplacement impossible : 1 case par 1 case");
				System.out.println("***************************On ne devrait pas rentrer là !***************************");
			}
		} else {
			System.out.println("Coordonnées hors plateau");
		}
		return retour;
	}
	
	public void RemoveMask()
	{
		MatriceVision mat=this.matriceVision;
		switch(this.orientation)
		{	//de base orientation sud
			case N:
				mat=matriceVision.rotation(this.orientation);
				break;
			case E:
				mat=matriceVision.rotation(this.orientation);
				break;
			case W:
				mat=matriceVision.rotation(this.orientation);
				break;
			default:
				break;	
		}
		for (int i=0;i<mat.getNbLignes();i++){
			for (int j=0;j<mat.getNbColonnes();j++){
				if(mat.getMat()[i][j]!=0)
				{
					switch(this.orientation){
						case N:
							if(this.env_decouvert.coordEtreDansPlateau(this.x-1+j, this.y-i)){
								this.env_decouvert.getTableauEnv()[this.x-1+j][this.y-i].setMasquage(false);
								if(this.env_decouvert.getTableauEnv()[this.x-1+j][this.y-i].isObstacle()){
									this.mesures.addObstaclesVisibles();
									this.mesures.addObstaclesRencontres();
								}
							}
							break;
						case S:
							if(this.env_decouvert.coordEtreDansPlateau(this.x-1+j, this.y+i)){
								this.env_decouvert.getTableauEnv()[this.x-1+j][this.y+i].setMasquage(false);
								if(this.env_decouvert.getTableauEnv()[this.x-1+j][this.y+i].isObstacle()){
									this.mesures.addObstaclesVisibles();
									this.mesures.addObstaclesRencontres();
								}
							}
							break;
						case E:
							if(this.env_decouvert.coordEtreDansPlateau(this.x+j, this.y-i+1)){
								this.env_decouvert.getTableauEnv()[this.x+j][this.y-i+1].setMasquage(false);
								if(this.env_decouvert.getTableauEnv()[this.x+j][this.y-i+1].isObstacle()){
									this.mesures.addObstaclesVisibles();
									this.mesures.addObstaclesRencontres();
								}
							}
							break;
						case W:
							if(this.env_decouvert.coordEtreDansPlateau(this.x-j, this.y-i+1)){
								this.env_decouvert.getTableauEnv()[this.x-j][this.y-i+1].setMasquage(false);
								if(this.env_decouvert.getTableauEnv()[this.x-j][this.y-i+1].isObstacle()){
									this.mesures.addObstaclesVisibles();
									this.mesures.addObstaclesRencontres();
								}
							}
							break;
						default:
							break;
					}
				}
			}
		}
	}
	
	public boolean isFrontMasked(Enum_Orientation_Robot orient){
		boolean retour=false;
		switch (orient){
			case N:
				if(this.env_decouvert.coordEtreDansPlateau(this.x, this.y-1))
					retour=this.env_decouvert.getTableauEnv()[this.x][this.y-1].isMasquage();
				break;
			case S:
				if(this.env_decouvert.coordEtreDansPlateau(this.x, this.y+1))
					retour=this.env_decouvert.getTableauEnv()[this.x][this.y+1].isMasquage();
				break;
			case E:
				if(this.env_decouvert.coordEtreDansPlateau(this.x+1, this.y))
					retour=this.env_decouvert.getTableauEnv()[this.x+1][this.y].isMasquage();
				break;
			case W:
				if(this.env_decouvert.coordEtreDansPlateau(this.x-1, this.y))
					retour=this.env_decouvert.getTableauEnv()[this.x-1][this.y].isMasquage();
				break;
				
			default:
				break;	
		}
		return retour;
	}
	
	
	
	@Override
	public String toString() {
		return "Robot [xInit=" + this.xInit + ", yInit=" + this.yInit + ", x=" + this.x
				+ ", y=" + this.y + ", orientationInit=" + this.orientationInit
				+ ", orientation=" + this.orientation + ", env_decouvert="
				+ this.env_decouvert + ", matriceVision=" + this.matriceVision + "]";
	}
	
	public static void main(String[] args) {
		//Measures mesures1 = new Measures();
		//System.out.println("addCommandes : "+mesures1.getNbrCommandes()+";" );
		
	}
	
}