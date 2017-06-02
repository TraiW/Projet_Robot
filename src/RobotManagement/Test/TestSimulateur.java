package RobotManagement.Test;

import org.junit.Test;

import RobotManagement.Controler.RobotCtr;
import RobotManagement.Model.Enum_Direction_Robot;
import RobotManagement.Model.Env;
import RobotManagement.Model.Robot;
import RobotManagement.Model.RobotInit;
 
public class TestSimulateur {

	@Test
	public void test() throws CloneNotSupportedException {
		int i=0,j=0;
		int xEnv=20,yEnv=20;
		int[] val = new int[3000];
		Env enviro=new Env();
		enviro.GenerationEnv();
		Robot bot=RobotInit.getInstance().createRobot(); 
		bot.setEnv_decouvert(enviro);
		RobotCtr botctrl = new RobotCtr(bot.getEnv_decouvert(), bot);
		botctrl.RAZAutoCall();
		int count=0;
		do{
			val[count]=botctrl.autoMappingSimple();
			switch(val[count]){
					case 0:
						System.out.println("//////////////////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
						break;
					case 1:
						botctrl.deplacerRobot(Enum_Direction_Robot.UP);
						break;
					case 2:
						botctrl.deplacerRobot(Enum_Direction_Robot.DOWN);
						break;
					case 3:
						botctrl.deplacerRobot(Enum_Direction_Robot.RIGHT);
						break;
					case 4:
						botctrl.deplacerRobot(Enum_Direction_Robot.LEFT);
						break;
					default:
						break;
			}
			count+=1;
		}while(val[count-1]!=5 && count<3000);//botctrl.getRobot().getEnv_decouvert().CountMask()!=0);
		
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
		
//		for(i=0;i<xEnv;i++)
//		{
//			for(j=0;j<yEnv;j++)
//			{
//				System.out.print(bot.getEnv_decouvert().getTableauEnv()[j][i].isMasquage()+" ");
//			}
//			System.out.println("");
//		}
		
//		for(i=0;i<count;i++)
//		{
//			System.out.println("valeur renvoyée d'automappin : "+val[i]);
//		}
		//System.out.println(enviro.getX_plateau()+" par "+enviro.getY_plateau());
	}

}
