package RobotManagement.Controler;

import RobotManagement.Model.*;


public class RobotCtr {

	private Env environnement;
	private Robot robot;
	private static int autoCall=0;
	private static int xInit=-1;
	private static int yInit=-1;
	private static Enum_Orientation_Robot[] dir={null,null};
	private static int indiceDir=0;
	private static int contournement=0;
	
	
	public RobotCtr(Env environnement, Robot robot) {
		this.environnement = environnement;
		this.robot = robot;
	}

	public Env getEnvironnement() {return environnement;}
	public void setEnvironnement(Env environnement) {this.environnement = environnement;}
	public Robot getRobot() {return robot;}
	public void setRobot(Robot robot) {this.robot = robot;}
	public void incAutoCall(){this.autoCall+=1;}
	public int getAutocall(){return this.autoCall;}
	public void RAZAutoCall(){this.autoCall=0;}
	
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
	
	public void autoMappingSimple(){
		//TODO penser au RAZAutoCall(); lorsqu'on désactive l'automapping
		this.incAutoCall();
		int move=0;
		if(this.getAutocall()==1){
			indiceDir=0;
			dir[indiceDir]=Enum_Orientation_Robot.E;
			//RobotCtr.xy=false; // false = x true = y
			xInit=this.robot.getX();
			yInit=this.robot.getY();
//			if(yInit<=(this.robot.getEnv_decouvert().getY_plateau()-1)/2)
//				RobotCtr.dir[1]=Enum_Orientation_Robot.S;
//			else
//				RobotCtr.dir[1]=Enum_Orientation_Robot.N;
		}
			
		do{
			if(!robot.getEnv_decouvert().isBordureEnvDir(this.robot.getX(), this.robot.getY(), dir[indiceDir]) 
					&& robot.getEnv_decouvert().isBordureEnvDirMoinsUn(this.robot.getX(), this.robot.getY(), dir[indiceDir]))
			{
				if(deplacerRobot(Enum_Orientation_Robot.getCorrespondance(dir[0]))){
					move+=1;
				}else{
					if(indiceDir==0)
						indiceDir=1;
					else
						indiceDir=0;
					
					if(deplacerRobot(Enum_Orientation_Robot.getCorrespondance(dir[1]))){
						move+=1;
					}
					
				}
			}
			if(move==1)
			{
				if(robot.getEnv_decouvert().isBordureEnvY(this.robot.getY()))
					RobotCtr.dir[0]=RobotCtr.dir[1];
				
			}
						
		}while(move!=1);
		
		do{
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
			//				
			//
			//
			//
			
			
			
			
		}while(move!=1);
		
//		Enum_Orientation_Robot [] Tab = Enum_Orientation_Robot.values();
//		int i = (int) (Math.random() * 4);		
//		robot.setOrientation(Tab[i]);
		//while(this.robot.getEnv_decouvert().CountMask()!=0)
		
//			Enum_Orientation_Robot orientation=robot.getOrientation();		
			//do{
//				switch (orientation){
//					case N :
//						System.out.println("Switch N");
//						if(!robot.getEnv_decouvert().isBordureEnvY(robot.getY()+1))
//						{
//							if(!robot.deplacement(robot.getX(),robot.getY()-1,orientation)){
//								if((int)(Math.random()+0.5)==1)
//									robot.setOrientation(Enum_Orientation_Robot.E);
//								else
//									robot.setOrientation(Enum_Orientation_Robot.W);
//							}
//						}
//						break;
//					case S :
//						System.out.println("Switch S");
//						if(!robot.getEnv_decouvert().isBordureEnvY(robot.getY()-1))
//						{
//							if(!robot.deplacement(robot.getX(),robot.getY()+1,orientation)){
//								if((int)(Math.random()+0.5)==1)
//									robot.setOrientation(Enum_Orientation_Robot.W);
//								else
//									robot.setOrientation(Enum_Orientation_Robot.E);
//							}
//						}
//						break;
//					case E :
//						System.out.println("Switch E");
//						if(!robot.getEnv_decouvert().isBordureEnvX(robot.getX()+1))
//						{
//							if(!robot.deplacement(robot.getX()+1,robot.getY(),orientation)){
//								if((int)(Math.random()+0.5)==1)
//									robot.setOrientation(Enum_Orientation_Robot.S);
//								else
//									robot.setOrientation(Enum_Orientation_Robot.N);
//							}
//						}
//						break;
//					case W :
//						System.out.println("Switch W");
//						if(!robot.getEnv_decouvert().isBordureEnvX(robot.getX()-1))
//						{
//							if(!robot.deplacement(robot.getX()-1,robot.getY(),orientation)){
//								if((int)(Math.random()+0.5)==1)
//									robot.setOrientation(Enum_Orientation_Robot.N);
//								else
//									robot.setOrientation(Enum_Orientation_Robot.S);
//							}
//						}
//						break;
//					 default :
//						break;
//				}
			//}while(this.robot.getEnv_decouvert().);
		
	}
	
	
	public void autoMappingHard(){//DOESN'T WORK
		//TODO
		Env env_percu=this.robot.getEnv_decouvert();
		int[] tabCoord={this.robot.getX(),this.robot.getY()};
		int indiceCoord=0;//indiceCoord=0 -> tabCoord de X, si 1 -> tabCoord de Y
		boolean retour=false;
		Enum_Direction_Robot dir=Enum_Direction_Robot.DOWN;
		Enum_Direction_Robot prochaineDir=Enum_Direction_Robot.DOWN;
		
		while(this.robot.getEnv_decouvert().CountMask()!=0)
		{
			indiceCoord=0;
			if(env_percu.isBordureEnvX(tabCoord[indiceCoord])){
				if(tabCoord[indiceCoord]==0)
					dir=Enum_Direction_Robot.RIGHT;
				else
					dir=Enum_Direction_Robot.LEFT;
			}else{
				if(tabCoord[indiceCoord]<=(env_percu.getX_plateau()/2))
					dir=Enum_Direction_Robot.LEFT;
				else
					dir=Enum_Direction_Robot.RIGHT;
			}
			indiceCoord=1;
			if(env_percu.isBordureEnvY(tabCoord[indiceCoord])){
				if(tabCoord[indiceCoord]==0)
					prochaineDir=Enum_Direction_Robot.DOWN;
				else
					prochaineDir=Enum_Direction_Robot.UP;
			}else{
				if(tabCoord[indiceCoord]<=(env_percu.getY_plateau()/2))
					prochaineDir=Enum_Direction_Robot.UP;
				else
					prochaineDir=Enum_Direction_Robot.DOWN; 
			}
			
			do{
				if(!this.deplacerRobot(dir))
				{
					if(indiceCoord==0)
						retour=this.robot.getEnv_decouvert().isLigneParcourue(tabCoord[indiceCoord]);
					else
						retour=this.robot.getEnv_decouvert().isColonneParcourue(tabCoord[indiceCoord]);
					
					if(retour)
					{
						
					}
					this.deplacerRobot(dir);
				}
				
			}while(!env_percu.isBordureEnvX(tabCoord[indiceCoord]));//pb here
		}
	}
	
}
