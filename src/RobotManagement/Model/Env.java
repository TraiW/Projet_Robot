package RobotManagement.Model;

import java.util.Formatter;
import java.util.Locale;
import java.util.Random;

public class Env extends Config {
	
	private Case[][]tableauEnv;
		
	public Env(){
		this.tableauEnv = new Case[getX_plateau()][getY_plateau()];
		
		setTaux_chance(getTaux_chance());
		setX_plateau(getX_plateau());
		setY_plateau(getY_plateau());
		setNbreCase_plateau(getNbreCase_plateau());		
//		GenerationEnv();
	}
	/***
	 * getter et setter sur le tableau environnement 
	 * 			
	 */
	public Case[][] getTableauEnv(){return tableauEnv;}
	public void setTableauEnv(Case[][] tableauEnv){this.tableauEnv = tableauEnv;}
	
	public boolean coordEtreDansPlateau(int x,int y){
		boolean retour=false;
		if((x>=0 && x<this.getX_plateau()) && (y>=0 && y<this.getY_plateau()))
			retour=true;
		return retour;
	}
	/***
	 * methode de génération d'environnement 
	 * 			creation de la matrice
	 * 			ajout d'obstacle de manière aléatoire en fonction du taux de chance et du nombre de case
	 */
	public void GenerationEnv(){
		int i=0,j=0,x=0,y=0;
		int nbreObstacle=0;
		Case c;
		Random aleatoire=new Random();
		nbreObstacle=aleatoire.nextInt((int) (getNbreCase_plateau()*(getTaux_chance()/100)));
		
		for(i=0;i<getX_plateau();i++){
			for(j=0;j<getY_plateau();j++){
				c = new Case(Enum_Etat_Case.vide,true);
				this.tableauEnv[i][j]=c;            
			}
		}
		
		for(i=0;i<nbreObstacle;i++){
			do{
				x=aleatoire.nextInt(getX_plateau());}while(x<=1);
			do{
				y=aleatoire.nextInt(getY_plateau());}while(x<=1);
			this.tableauEnv[x][y].setEtat_case(Enum_Etat_Case.obstacle);				
		}
	}
	
	public boolean isBordureEnvDir(int x,int y,Enum_Orientation_Robot orient)
	{
		boolean retour=false;
	    switch(orient)
	    {
		    case N:
		    	retour=this.isBordureEnvY(y);
		    	break;
		    case S:
		    	retour=this.isBordureEnvY(y);
		    	break;
		    case E:
		    	retour=this.isBordureEnvX(x);
		    	break;
		    case W:
		    	retour=this.isBordureEnvX(x);
		    	break;
	    	default:
	    		break;
	    }
	    return retour;
	}
	
	public boolean isBordureEnvX(int x)
	{
		boolean retour=false;
	    if((x==0 || x==this.getX_plateau()-1))
	        retour=true;
	    return retour;
	}
	
	public boolean isBordureEnvY(int y)
	{
		boolean retour=false;
	    if((y==0 || y==this.getX_plateau()-1))
	        retour=true;
	    return retour;
	}
	
	public int CountMask(){
		int nbrMask=0;
		for(int i=0;i<getX_plateau();i++){
			for(int j=0;j<getY_plateau();j++){
				if(this.getTableauEnv()[i][j].isMasquage())
					nbrMask+=1;
			}
		}
		return nbrMask;
	}
	
	public boolean isColonneParcourue(int x){
		boolean retour=false;
		int count=0;
		for(int i=1;i<this.getX_plateau()-1;i++)
		{
			if(this.getTableauEnv()[x][i].isParcouru()|| this.getTableauEnv()[x][i].isRobot())
				count+=1;
		}
		if (count>=this.getX_plateau()-2)
			retour=true;
		return retour;
	}
	
	public boolean isLigneParcourue(int y){
		boolean retour=false;
		int count=0;
		for(int i=1;i<this.getX_plateau()-1;i++)
		{
			if(this.getTableauEnv()[i][y].isParcouru() || this.getTableauEnv()[i][y].isRobot())
				count+=1;
		}
		if (count>=this.getY_plateau()-2)
			retour=true;
		return retour;
	}

	/***
	 * methode permettant d'afficher ma matrice sous forme ASCII  
	 * 			
	 */
	@SuppressWarnings("resource")
	public static String printMatrix(Case[][] matrix,int sizeX,int sizeY) {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.FRENCH);
		String formatS = "%1$5s";
		String[] valueTab = new String[sizeX+1];
		valueTab[0]="";
		for (int index = 0; index < sizeX; index++) {
		formatS = formatS + " %" + (index + 2) + "$5s";
		valueTab[index+1] = String.valueOf(index);
		}
		formatter.format(formatS + "\n", valueTab);
		formatter.format("%1$5s | %2$47s\n", "",
				"_______________________________________________");
		for (int i = 0; i < sizeY; i++) {
		String formatS2 = "%1$5s | ";
		String[] valueTab2 = new String[sizeY+1];
		valueTab2[0]=String.valueOf(i);
		for (int j = 0; j < sizeX; j++) {
		formatS2 = formatS2 + " %" + (j + 2) + "$5s";
		valueTab2[j+1] = matrix[i][j].toString();///VERIFIER AVEC CLASSE CASE
		}
		formatter.format(formatS2 + "\n", valueTab2);
		}
		return formatter.toString() ;
		}
}