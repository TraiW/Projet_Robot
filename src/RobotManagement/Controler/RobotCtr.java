package RobotManagement.Controler;

import projet_majeure.Robot.Orientation;
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
	
	public void autoMapping(){
		Env env_percu=robot.getEnv_decouvert();
		int xRobot=robot.getX();
		int yRobot=robot.getY();
		Enum_Direction_Robot dir=Enum_Direction_Robot.DOWN;
		Enum_Direction_Robot prochaineDir=Enum_Direction_Robot.DOWN;
		
		if(xRobot<=(env_percu.getX_plateau()/2)){
			
		}
		
		
	}
	/*	public void AutoMapping(){
		robot.updateEnvironnement();		
		Orientation [] Tab = Orientation.values();
		int i = (int) (Math.random() * 4);		
		robot.ChangerOrientation(Tab[i]);
		
		String orientation=robot.getCapteurRobot().getOrientation();
				
		switch (orientation){
		case "N" :
			if(robot.DeplacementEtreValide(robot.getX(), robot.getY()-1)){
				robot.Deplacement(robot.getX(), robot.getY()-1);
			}
			else {
				if((int)(Math.random()+0.5)==1)
					robot.ChangerOrientation(Orientation.E);
				else
					robot.ChangerOrientation(Orientation.W);
			}
			break;
		case "S" :
			if(robot.DeplacementEtreValide(robot.getX(), robot.getY()+1)){
				robot.Deplacement(robot.getX(), robot.getY()+1);
			}
			else {
				if((int)(Math.random()+0.5)==1)
					robot.ChangerOrientation(Orientation.W);
				else
					robot.ChangerOrientation(Orientation.E);
			}
			break;
		case "E" :
			if(robot.DeplacementEtreValide(robot.getX()+1, robot.getY())){
				robot.Deplacement(robot.getX()+1, robot.getY());
			}
			else {
				if((int)(Math.random()+0.5)==1)
					robot.ChangerOrientation(Orientation.S);
				else
					robot.ChangerOrientation(Orientation.N);
			}
			break;
		case "W" :
			if(robot.DeplacementEtreValide(robot.getX()-1, robot.getY())){
				robot.Deplacement(robot.getX()-1, robot.getY());
			}
			else {
				if((int)(Math.random()+0.5)==1)
					robot.ChangerOrientation(Orientation.N);
				else
					robot.ChangerOrientation(Orientation.S);
			}
			break;
		 default :
			break;
		}
	}*/
}
