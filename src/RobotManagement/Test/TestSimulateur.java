package RobotManagement.Test;

import org.junit.Test;

import RobotManagement.Controler.RobotCtr;
import RobotManagement.Model.Enum_Direction_Robot;
import RobotManagement.Model.Enum_Orientation_Robot;
import RobotManagement.Model.Env;
import RobotManagement.Model.Robot;
import RobotManagement.Model.RobotInit;
 
public class TestSimulateur {

	@Test
	public void test() {
		int i=0,j=0;
		int xEnv=10,yEnv=10;
		Env enviro=new Env(xEnv,yEnv,1);
		enviro.GenerationEnv();
		
		Robot bot=RobotInit.getInstance().createRobot(); 
		bot.setEnv_decouvert(enviro);
		bot.RemoveMask(Enum_Orientation_Robot.E);
		RobotCtr botctrl = new RobotCtr(bot.getEnv_decouvert(), bot);
		System.out.println("***********BASE****************");
		for(i=0;i<xEnv;i++)
		{
			for(j=0;j<yEnv;j++)
			{
				System.out.print(bot.getEnv_decouvert().getTableauEnv()[j][i].isMasquage()+" ");
			}
			System.out.println("");
		}
		System.out.println("DOWN");
		botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);
		
		System.out.println("DOWN");
		botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);
		
		System.out.println("DOWN");
		botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);
		
		
		System.out.println("DOWN");
		botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);

//		System.out.println("DOWN");
//		botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);
		System.out.println("RIGHT");
		botctrl.deplacerRobot(Enum_Direction_Robot.RIGHT);
//		System.out.println("LEFT");
//		botctrl.deplacerRobot(Enum_Direction_Robot.LEFT);	
//		System.out.println("UP");
//		botctrl.deplacerRobot(Enum_Direction_Robot.UP);	
		//System.out.println("**************END******************");
		for(i=0;i<xEnv;i++)
		{
			for(j=0;j<yEnv;j++)
				System.out.print(bot.getEnv_decouvert().getTableauEnv()[j][i].isMasquage()+" ");
			System.out.println("");
		}
	}

}
