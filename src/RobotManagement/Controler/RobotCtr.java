package RobotManagement.Controler;

import RobotManagement.Model.*;


public class RobotCtr {

	private Env environnement;
	private Robot robot;
	
	public RobotCtr(Env environnement, Robot robot) {
		this.environnement = environnement;
		this.robot = robot;
	}

	public Env getEnvironnement() {return environnement;}
	public void setEnvironnement(Env environnement) {this.environnement = environnement;}
	public Robot getRobot() {return robot;}
	public void setRobot(Robot robot) {this.robot = robot;}

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
		//robot.updateEnvironnement();	
		System.out.println("going in");

		Enum_Orientation_Robot [] Tab = Enum_Orientation_Robot.values();
		int i = (int) (Math.random() * 4);		
		robot.setOrientation(Tab[i]);
		while(this.robot.getEnv_decouvert().CountMask()!=0)
		{
			System.out.println("while");
			Enum_Orientation_Robot orientation=robot.getOrientation();		
			switch (orientation){
				case N :
					System.out.println("Switch N");
					if(!robot.deplacement(robot.getX(),robot.getY()-1,orientation)){
						System.out.println("Switch Nbis");

						if((int)(Math.random()+0.5)==1)
							robot.setOrientation(Enum_Orientation_Robot.E);
						else
							robot.setOrientation(Enum_Orientation_Robot.W);
					}
					break;
				case S :
					System.out.println("Switch S");
					if(!robot.deplacement(robot.getX(),robot.getY()+1,orientation)){
						System.out.println("Switch Sbis");

						if((int)(Math.random()+0.5)==1)
							robot.setOrientation(Enum_Orientation_Robot.W);
						else
							robot.setOrientation(Enum_Orientation_Robot.E);
					}
					break;
				case E :
					System.out.println("Switch E");
					if(!robot.deplacement(robot.getX()+1,robot.getY(),orientation)){
						System.out.println("Switch Ebis");

						if((int)(Math.random()+0.5)==1)
							robot.setOrientation(Enum_Orientation_Robot.S);
						else
							robot.setOrientation(Enum_Orientation_Robot.N);
					}
					break;
				case W :
					System.out.println("Switch W");
					if(!robot.deplacement(robot.getX()-1,robot.getY(),orientation)){
						System.out.println("Switch Wbis");

						if((int)(Math.random()+0.5)==1)
							robot.setOrientation(Enum_Orientation_Robot.N);
						else
							robot.setOrientation(Enum_Orientation_Robot.S);
					}
					break;
				 default :
					 System.out.println("Switch DEF");
					break;
			}
			System.out.println("while -> fin switch");
		}
	}
	
	
	public void autoMappingHard(){
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
