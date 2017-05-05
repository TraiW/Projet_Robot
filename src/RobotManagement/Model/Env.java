package RobotManagement.Model;

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
	}
	
	/***
	 * methode de génération d'environnement 
	 * 			creation de la matrice
	 * 			ajout d'obstacle de manière aléatoire en fonction du taux de chance et du nombre de case
	 */
	public void GenerationEnv(){
		int i=0,j=0,x=0,y=0;
		int nbreObstacle=0;
		
		Random aleatoire=new Random();
		nbreObstacle=aleatoire.nextInt((int) (getNbreCase_plateau()*getTaux_chance()));
		
		for(i=0;i<getX_plateau();i++){
			for(j=0;j<getY_plateau();j++){
				Case case=new Case(); // checker constructeur de l'objet Case
				this.tableauEnv[i][j]=case.             //appeler la focntion de l'objet Case permettant de ne rien mettre dans la cellule 
			}
		}
		
		for(i=0;i<nbreObstacle;i++){
			x=aleatoire.nextInt(getX_plateau());
			y=aleatoire.nextInt(getY_plateau());
			this.tableauEnv[x][y]=case.				//appeler la focntion de l'objet Case permettant de mettre un obstacle dans la cellule	
		}
	}

	/***
	 * getter sur le tableau environnement 
	 * 			
	 */
	public Case[][] getTableauEnv() {
		return tableauEnv;
	}
	
	/***
	 * setter sur le tableau environnement 
	 * 			
	 */
	public void setTableauEnv(Case[][] tableauEnv) {
		this.tableauEnv = tableauEnv;
	}
	
	/***
	 * methode permettant d'afficher ma matrice sous forme ASCII  
	 * 			
	 */
//voir annexe du cahier des charges => faire un tablaeu de case en String au lieu de Case
	
}