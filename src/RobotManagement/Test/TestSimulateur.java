package RobotManagement.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import RobotManagement.Model.Case;
import RobotManagement.Model.Env;

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
		for(i=0;i<4;i++)
			for(j=0;j<4;j++)
				System.out.println(tab[i][j].toString());
		System.out.println("********************************************");
		
	}

}
