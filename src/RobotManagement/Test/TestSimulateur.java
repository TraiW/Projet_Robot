package RobotManagement.Test;

import org.junit.Test;

import RobotManagement.Controler.RobotCtr;
import RobotManagement.Model.Case;
import RobotManagement.Model.Enum_Direction_Robot;
import RobotManagement.Model.Enum_Orientation_Robot;
import RobotManagement.Model.Env;
import RobotManagement.Model.Robot;
 
public class TestSimulateur {

	@Test
	public void test() {
		int x=0,y=0;
		
		System.out.println("********************************************");
		System.out.println("*TEST CREATION ENV+Obstacle +déplacement robot(avec controleur)*");
		System.out.println("********************************************");
		int i=0,j=0;
		Env env=new Env(4,4,1);
		env.GenerationEnv();
		Case[][] tab=env.getTableauEnv();
		Robot bot=new Robot(x, y, Enum_Orientation_Robot.N, env);
		tab[x][y].setRobot();
		RobotCtr botctrl=new RobotCtr(env,bot);
		
		System.out.println("***********BASE****************");
		for(i=0;i<4;i++)
			for(j=0;j<4;j++)
				System.out.println("case["+i+"]["+j+"]"+tab[i][j].toString());
		System.out.println("DOWN");

		botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);

		System.out.println("DOWN");
		botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);
		System.out.println("RIGHT");
		botctrl.deplacerRobot(Enum_Direction_Robot.RIGHT);
		System.out.println("LEFT");
		botctrl.deplacerRobot(Enum_Direction_Robot.LEFT);	
		System.out.println("UP");
		botctrl.deplacerRobot(Enum_Direction_Robot.UP);	
		System.out.println("**************END******************");
		for(i=0;i<4;i++)
			for(j=0;j<4;j++)
				System.out.println("case["+i+"]["+j+"]"+tab[i][j].toString());
	}

}
