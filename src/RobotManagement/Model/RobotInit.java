package RobotManagement.Model;

import RobotManagement.Model.Config;
import RobotManagement.Model.Enum_Orientation_Robot;
import RobotManagement.Model.Env;
import RobotManagement.Model.Robot;

public class RobotInit extends Config{

	private static volatile RobotInit instance = null;
	
	private int xInit=0, yInit=0;
	private Enum_Orientation_Robot orientationInit=Enum_Orientation_Robot.E;
	private  Env environnement = new Env();
	private MatriceVision matriceVision=new MatriceVision();
	
	public RobotInit(){
		super();
	}
	
	
	public final static RobotInit getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
		// d'éviter un appel coûteux à synchronized,
		// une fois que l'instanciation est faite.
		if (RobotInit.instance == null) {
			// Le mot-clé synchronized sur ce bloc empêche toute instanciation
			// multiple même par différents "threads".
			synchronized (RobotInit.class) {
				if (RobotInit.instance == null) {
					RobotInit.instance = new RobotInit();
				}
			}
		}
		return RobotInit.instance;
	}
	
	public Robot createRobot() {
		environnement.GenerationEnv();
		Robot robot = new Robot(xInit, yInit, orientationInit, environnement, matriceVision);
		return robot;
	}
	
}




