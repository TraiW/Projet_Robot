package RobotManagement.Model;

import java.util.Formatter;
import java.util.Locale;
import java.util.Random;

public class Env extends Config {
	
	private Case[][]tableauEnv;
	
	public Env(int X_plateau,int Y_plateau,double taux_chance){
		int nbreCase= X_plateau*Y_plateau;
		this.tableauEnv = new Case[X_plateau][Y_plateau];
		
		setTaux_chance(taux_chance);
		setX_plateau(X_plateau);
		setY_plateau(Y_plateau);
		setNbreCase_plateau(nbreCase);		
//		GenerationEnv();
	}
	/***
	 * getter et setter sur le tableau environnement 
	 * 			
	 */
	public Case[][] getTableauEnv(){return tableauEnv;}
	public void setTableauEnv(Case[][] tableauEnv){this.tableauEnv = tableauEnv;}
	
	
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
		nbreObstacle=aleatoire.nextInt((int) (getNbreCase_plateau()*getTaux_chance()));
		
		for(i=0;i<getX_plateau();i++){
			for(j=0;j<getY_plateau();j++){
				c = new Case(Enum_Etat_Case.vide,true);
				this.tableauEnv[i][j]=c;            
			}
		}
		
		for(i=0;i<nbreObstacle;i++){
			x=aleatoire.nextInt(getX_plateau());
			y=aleatoire.nextInt(getY_plateau());
			this.tableauEnv[x][y].setEtat_case(Enum_Etat_Case.obstacle);				
		}
	}

	/***
	 * methode permettant d'afficher ma matrice sous forme ASCII  
	 * 			
	 */
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