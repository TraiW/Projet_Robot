package RobotManagement.Test;

import org.junit.Test;

import RobotManagement.Controler.RobotCtr;
import RobotManagement.Model.Enum_Direction_Robot;
import RobotManagement.Model.Env;
import RobotManagement.Model.Robot;
import RobotManagement.Model.RobotInit;
 
public class TestSimulateur {

	@Test
	public void test() {
		int i=0,j=0;
		int xEnv=20,yEnv=20;
		Env enviro=new Env();
		enviro.GenerationEnv();
		//enviro.getTableauEnv()[0][1].setEtat_case(Enum_Etat_Case.obstacle);
		Robot bot=RobotInit.getInstance().createRobot(); 
		bot.setEnv_decouvert(enviro);
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
		int count=0;
		do
		{
			botctrl.autoMappingSimple();
			count+=1;
		}while(botctrl.getRobot().getEnv_decouvert().CountMask()!=0);
		
		botctrl.setAutoDeplList(botctrl.getAutocall(),0);//place en première case le nombre de déplacement total
		
		for(i=0;i<xEnv;i++)
		{
			for(j=0;j<yEnv;j++)
			{
				if(bot.getEnv_decouvert().getTableauEnv()[j][i].isObstacle())
					System.out.print("Obs ");
				else if(bot.getEnv_decouvert().getTableauEnv()[j][i].isVide())
					System.out.print("vid ");
				else if(bot.getEnv_decouvert().getTableauEnv()[j][i].isRobot())
					System.out.print("BOT ");
				else if(bot.getEnv_decouvert().getTableauEnv()[j][i].isParcouru())
					System.out.print(" 0  ");
			}	
			System.out.println("");
		}
		System.out.println("BOT en x:"+bot.getX()+" y:"+bot.getY());
		System.out.println("parcouru en "+count+" itération");
		System.out.println("parcouru en "+botctrl.getAutocall()+" déplacements réels");
		System.out.println("");
		
		for(i=0;i<xEnv;i++)
		{
			for(j=0;j<yEnv;j++)
			{
				System.out.print(bot.getEnv_decouvert().getTableauEnv()[j][i].isMasquage()+" ");
			}
			System.out.println("");
		}
		for(i=0;i<botctrl.getAutocall();i++)
		{
			System.out.println(botctrl.getAutoDeplList()[i]);
		}
		//System.out.println(enviro.getX_plateau()+" par "+enviro.getY_plateau());

	}

}
