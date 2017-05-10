package RobotManagement.Test;

import org.junit.Test;

import RobotManagement.Model.Case;
import RobotManagement.Model.Enum_Orientation_Robot;
import RobotManagement.Model.Env;
import RobotManagement.Model.Robot;
 
public class TestSimulateur {

	@Test
	public void test() {
		System.out.println("********************************************");
		System.out.println("*TEST CREATION ENV AVEC OBSTACLE / MASQUAGE*");
		System.out.println("********************************************");
		int i=0,j=0;
		Env env=new Env(4,4,1);
		env.GenerationEnv();
		Case[][] tab=env.getTableauEnv();
		Robot bot=new Robot(0, 0, Enum_Orientation_Robot.N, env);
		for(i=0;i<4;i++)
			for(j=0;j<4;j++)
				System.out.println("case["+i+"]["+j+"]"+tab[i][j].toString());
		System.out.println("********************************************");

		
	}

}
