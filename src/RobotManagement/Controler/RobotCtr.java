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

	public void deplacerRobot(Enum_Direction_Robot dir){
		switch(dir)
		{
			case UP:
				robot.deplacement(robot.getX(),robot.getY()-1,Enum_Orientation_Robot.N);
				break;
			case DOWN:
				robot.deplacement(robot.getX(),robot.getY()+1,Enum_Orientation_Robot.S);
				break;
			case RIGHT:
				robot.deplacement(robot.getX()+1,robot.getY(),Enum_Orientation_Robot.E);
				break;
			case LEFT:
				robot.deplacement(robot.getX()-1,robot.getY(),Enum_Orientation_Robot.W);
				break;
			default:
				System.out.println("Erreur ");
				break;
		} 
	}
}
