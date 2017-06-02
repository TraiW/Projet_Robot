package RobotManagement.Controler;

import RobotManagement.Model.*;


public class RobotCtr {

	private Env environnement;
	private Robot robot;
	private static int autoCall=0;
	private static Enum_Orientation_Robot prochDirAutoMap=Enum_Orientation_Robot .E;
	
	public RobotCtr(Env environnement, Robot robot) {
		this.environnement = environnement;
		this.robot = robot;
	}

	public Env getEnvironnement() {return environnement;}
	public void setEnvironnement(Env environnement) {this.environnement = environnement;}
	public Robot getRobot() {return robot;}
	public void setRobot(Robot robot) {this.robot = robot;}
	public void incAutoCall(){RobotCtr.autoCall+=1;}
	public void decAutoCall(){RobotCtr.autoCall-=1;}
	public int getAutocall(){return RobotCtr.autoCall;}
	public void RAZAutoCall(){RobotCtr.autoCall=0;}
	
	public boolean deplacerRobot(Enum_Direction_Robot dir){
		boolean retour=false;
		switch(dir)
		{
			case UP:
				retour=robot.deplacement(robot.getX(),robot.getY()-1,Enum_Orientation_Robot.N);
				if(retour!=true)
					this.robot.setOrientation(Enum_Orientation_Robot.N);
				break;
			case DOWN:
				retour=robot.deplacement(robot.getX(),robot.getY()+1,Enum_Orientation_Robot.S);
				if(retour!=true)
					this.robot.setOrientation(Enum_Orientation_Robot.S);
				break;
			case RIGHT:
				retour=robot.deplacement(robot.getX()+1,robot.getY(),Enum_Orientation_Robot.E);
				if(retour!=true)
					this.robot.setOrientation(Enum_Orientation_Robot.E);
				break;
			case LEFT:
				retour=robot.deplacement(robot.getX()-1,robot.getY(),Enum_Orientation_Robot.W);
				if(retour!=true)
					this.robot.setOrientation(Enum_Orientation_Robot.W);
				break;
			default:
				System.out.println("Erreur RobotCtr.deplacerRobot unexpected direction");
				break;
		} 
		robot.RemoveMask();
		return retour;
	}
	
	public Robot razRobot(){
			for (int i=0;i<environnement.getX_plateau();i++){
				for (int j=0;j<environnement.getY_plateau();j++){
					this.environnement.getTableauEnv()[j][i].setMasquage(true);
					if(this.environnement.getTableauEnv()[j][i].getEtat_case()==Enum_Etat_Case.parcouru)
						{this.environnement.getTableauEnv()[j][i].setRAZParcouru();}
				}
			}
			robot.getMeasures().razMesures();
			robot.setX(0);
			robot.setY(0);
			return robot;
	}
	

			
			//				
			//
			//
			//
			
			

	public int autoMappingSimple(){
			//SI n'est pas en bordure ou ne le sera pas si deplacement dans la direction initiale et si contournement==0
			//		SI deplacement possible
			//			do it (sens de base here)
			//		SINON
			//			contournement+=1
			//			SI possible turn90right
			//			SINON si possible turn90left
			//			SINON getopposite
			//				contournement+=1;
			//				autre traitement de merde à faire
			//
			//SINON SI n'est pas en bordure dans le sens de deplacement de base && deplacement possible && contournement !=0
			//		deplacement dans le sens de base
			//SINON
			//		continuer dans direction de contour 
		
		Enum_Orientation_Robot [] Tab = Enum_Orientation_Robot.values();
		int cpt=0;
		int nbBlock=8;
		int autoDeplList=0;
		boolean detour=false;
		this.incAutoCall();
		if(this.getAutocall()==1){
			int i = (int) (Math.random() * 4);		
			robot.setOrientation(Tab[i]);
			prochDirAutoMap=robot.getOrientation();
		}
		int x=robot.getX(),y=robot.getY();
		do{
			if(cpt>=nbBlock)
			{
				prochDirAutoMap=Enum_Orientation_Robot.getAleat();
				switch (prochDirAutoMap){
					case N :
						if(robot.DeplacementEtreValide(x, y-1))
							autoDeplList=1;
						break;
					case S :
						if(robot.DeplacementEtreValide(x, y+1))
							autoDeplList=2;
						break;
					case E :
						if(robot.DeplacementEtreValide(x+1, y))
							autoDeplList=3;
						break;
					case W :
						if(robot.DeplacementEtreValide(x-1, y))
							autoDeplList=4;
						break;
					default:
						break;
				}
			}
			else{
				autoDeplList=0;
				if(robot.getEnv_decouvert().CountMask()==0)
				{
					autoDeplList=5;
				}
				else{
						switch (prochDirAutoMap){
							case N :
									if(robot.DeplacementEtreValide(x, y-1)){// && !detour){
											if(robot.getEnv_decouvert().isBordureEnvDir(x, y-1,prochDirAutoMap) 
													|| robot.getEnv_decouvert().isColonneParcourue(y)
													|| robot.isFrontParcouru(prochDirAutoMap)
													)
											{
												prochDirAutoMap=Enum_Orientation_Robot.getAleatHorizontal();
												detour=true;
											}else{
												autoDeplList=1;
											}
									}else if(detour){
										prochDirAutoMap=Enum_Orientation_Robot.getOpposite(prochDirAutoMap);
										detour=false;
									}else {
										prochDirAutoMap=Enum_Orientation_Robot.getAleatHorizontal();
										detour=true;
									}
								break;
							case S :
									if(robot.DeplacementEtreValide(x, y+1)){//&& !detour){
											if(robot.getEnv_decouvert().isBordureEnvDir(x, y+1,prochDirAutoMap) 
													|| robot.getEnv_decouvert().isColonneParcourue(y)
													|| robot.isFrontParcouru(prochDirAutoMap)
													)
											{
												prochDirAutoMap=Enum_Orientation_Robot.getAleatHorizontal();
												detour=true;
											}else{
												autoDeplList=2;
											}
									}else if(detour){
										prochDirAutoMap=Enum_Orientation_Robot.getOpposite(prochDirAutoMap);
										detour=false;
									}else {
										prochDirAutoMap=Enum_Orientation_Robot.getAleatHorizontal();
										detour=true;
									}
								break;
							case E :
									if(robot.DeplacementEtreValide(x+1, y)){//&& !detour){
											if(robot.getEnv_decouvert().isBordureEnvDir(x+1, y,prochDirAutoMap) 
													|| robot.getEnv_decouvert().isLigneParcourue(y)
													|| robot.isFrontParcouru(prochDirAutoMap)
													)
											{
													prochDirAutoMap=Enum_Orientation_Robot.getAleatVertical();
													detour=true;
											}else{
												autoDeplList=3;
											}
									}else if(detour){
										prochDirAutoMap=Enum_Orientation_Robot.getOpposite(prochDirAutoMap);
										detour=false;
									}else {				
										prochDirAutoMap=Enum_Orientation_Robot.getAleatVertical();
										detour=true;
									}
								break;
							case W :
									if(robot.DeplacementEtreValide(x-1, y)){//&& !detour){
											if(robot.getEnv_decouvert().isBordureEnvDir(x-1, y,prochDirAutoMap) 
													|| robot.getEnv_decouvert().isLigneParcourue(y)
													|| robot.isFrontParcouru(prochDirAutoMap)
													)
											{
												prochDirAutoMap=Enum_Orientation_Robot.getAleatVertical();
												detour=true;
											}else{
												autoDeplList=4;
											}
									}else if(detour){
										prochDirAutoMap=Enum_Orientation_Robot.getOpposite(prochDirAutoMap);
										detour=false;
									}else {
										prochDirAutoMap=Enum_Orientation_Robot.getAleatVertical();
										detour=true;
									}
								break;
							 default :
								break;
						}//fin switch
				}//fin else countmask
			}//fin else cpt
			cpt++;
		}while(autoDeplList==0);
		cpt=0;
		detour=false;
		return autoDeplList;
	}
		
}
