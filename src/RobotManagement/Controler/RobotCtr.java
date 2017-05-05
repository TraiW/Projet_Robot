package RobotManagement.Controler;

import RobotManagement.Model.*;

public class RobotCtr {

	private Env environnement;
	private Robot robot;
	
	public RobotCtr(Env environnement, Robot robot) {
		this.environnement = environnement;
		this.robot = robot;
	}

	public Env getEnvironnement() {
		return environnement;
	}

	public void setEnvironnement(Env environnement) {
		this.environnement = environnement;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public boolean deplacerRobot(Enum_Direction_Robot dir){
		boolean retour=false;
		switch(dir)
		{
			case UP:
				robot.deplacement(robot.getX(),robot.getY()+1);
				robot.setOrientation(Enum_Orientation_Robot.N);
				retour=true;
				break;
			case DOWN:
				robot.deplacement(robot.getX(),robot.getY()-1);
				robot.setOrientation(Enum_Orientation_Robot.N);
				retour=true;
				break;
			case RIGHT:
				robot.deplacement(robot.getX()+1,robot.getY());
				robot.setOrientation(Enum_Orientation_Robot.N);
				retour=true;
				break;
			case LEFT:
				robot.deplacement(robot.getX()-1,robot.getY());
				robot.setOrientation(Enum_Orientation_Robot.N);
				retour=true;
				break;
			default:
				retour=false;
				break;
		}
		return retour;
	}
}
