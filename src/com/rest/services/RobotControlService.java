package com.rest.services;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import RobotManagement.Controler.RobotCtr;
import RobotManagement.Model.Enum_Direction_Robot;
import RobotManagement.Model.Robot;
import RobotManagement.Model.RobotInit;
import RobotManagement.Model.Measures;


@Path("/cmd")
public class RobotControlService {
	private final static String ROBOT_SIMULATOR_LABEL="robot_simulator";
	private Robot robot=RobotInit.getInstance().createRobot(); 
	private ArrayList<Measures>mesureList=new ArrayList<Measures>();
	RobotCtr robotCtr = new RobotCtr(robot.getEnv_decouvert(), robot);
	//Inject servlet context (needed to get general context, application memory space, session memory space ...)
	@Context
	ServletContext context;
	
	//After RestService construction launches init method
		@PostConstruct
		public void init(){
			checkRobot();
		
		}
		

		private void checkRobot() {
			Object obj=context.getAttribute(ROBOT_SIMULATOR_LABEL);
			if(obj==null){
				
				//TODO
			}else{
				//TODO
			}
		}
		
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Path("UP")
		public String goUp()
				{
			boolean bool =false;
			bool = robotCtr.deplacerRobot(Enum_Direction_Robot.UP);
			System.out.println(bool);
			//create Json container Object
			JSONObject objContainer = new JSONObject();
			
			//create set of json objects
			JSONObject objVal1 = new JSONObject();
			objVal1.put("UP",true);
			
			//create a json list
			JSONArray list = new JSONArray();
			//add json objects to jsonlist
			list.add(objVal1);
			
			//add jsonlist to json container
			objContainer.put("data", list);
			//return json string of the json container
			return objContainer.toJSONString();
		}
		
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("DOWN")
		public String goDown()
				{
			boolean bool =false;
			bool = robotCtr.deplacerRobot(Enum_Direction_Robot.DOWN);
////			mesureList.add(robotCtr.getRobot().getMeasures());
////			System.out.println(bool);
////			return bool;
//			//create Json container Object
//			JSONObject objContainer = new JSONObject();
//			
//			//create set of json objects
//			JSONObject objVal1 = new JSONObject();
//			objVal1.put("UP",true);
//			
//			//create a json list
//			JSONArray list = new JSONArray();
//			//add json objects to jsonlist
//			list.add(objVal1);
//			
//			//add jsonlist to json container
//			objContainer.put("data", list);
//			//return json string of the json container
//			return objContainer.toJSONString();
			System.out.println(bool);
			if(bool==true)return "DOWN OK";
			else return "DOWN KO";
		}
		
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("RIGHT")
		public boolean goRight()
				{
			boolean bool =false;
			bool = robotCtr.deplacerRobot(Enum_Direction_Robot.RIGHT);
			mesureList.add(robotCtr.getRobot().getMeasures());
			for(int i=0;i<mesureList.size();i++){
				System.out.println(mesureList.get(i));
			}
			System.out.println(bool);
			return bool;
		}
		
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("LEFT")
		public boolean goLeft()
				{
			boolean bool =false;
			bool = robotCtr.deplacerRobot(Enum_Direction_Robot.LEFT);
			mesureList.add(robotCtr.getRobot().getMeasures());
			System.out.println(bool);
			return bool;
		}
		
		
		
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("STOP")
		public String goStop()
				{
			return "STOP Done";
		}
		
		
		@GET		
		@Produces(MediaType.TEXT_HTML)
		@Path("test")
		public String test(){
			return "index-1.html";
		}
		
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("env")
		public String getEnv()
				{
			//create Json container Object
			JSONObject objContainer = new JSONObject();
			
			//create set of json objects
			JSONObject objVal1 = new JSONObject();
			objVal1.put("x",new Integer(0));
			objVal1.put("y",new Integer(0));
			objVal1.put("val","FREE");
			JSONObject objVal2 = new JSONObject();
			objVal2.put("x",new Integer(0));
			objVal2.put("y",new Integer(1));
			objVal2.put("val","WALL");
			JSONObject objVal3 = new JSONObject();
			objVal3.put("x",new Integer(1));
			objVal3.put("y",new Integer(1));
			objVal3.put("val","ROBOT");
			
			
			//create a json list
			JSONArray list = new JSONArray();
			//add json objects to jsonlist
			list.add(objVal1);
			list.add(objVal2);
			list.add(objVal3);
			
			//add jsonlist to json container
			objContainer.put("data", list);
			System.out.println(objContainer.toString());
			//return json string of the json container
			return objContainer.toJSONString();
			
			
			//ALTERNATIVE send direct a json String
			//return "{\"data\":[{\"x\":0,\"y\":0,\"val\":\"FREE\"},{\"x\":0,\"y\":1,\"val\":\"WALL\"},{\"x\":1,\"y\":1,\"val\":\"ROBOT\"}]}";
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("measure")
		public String getMesure()
				{
			//create Json container Object
			JSONObject objContainerData = new JSONObject();
			
			//create set of json objects
			JSONObject objVal_NbreCommande = new JSONObject();
			objVal_NbreCommande.put("name", new String("Nombre de Commandes"));
			objVal_NbreCommande.put("value", new Integer(this.robotCtr.getRobot().getMeasures().getNbrCommandes()));

			JSONObject objVal_ObstaclesRencontres = new JSONObject();
			objVal_ObstaclesRencontres.put("name", new String("Nombre d obstacles rencontres"));
			objVal_ObstaclesRencontres.put("value", new Integer(this.robotCtr.getRobot().getMeasures().getObstaclesRencontres()));

			JSONObject objVal_ObstaclesVisibles = new JSONObject();
			objVal_ObstaclesVisibles.put("name", new String("Nombre d obstacles visibles"));
			objVal_ObstaclesVisibles.put("value", new Integer(this.robotCtr.getRobot().getMeasures().getObstaclesVisibles()));

			JSONObject objVal_DistancesParcourue = new JSONObject();
			objVal_DistancesParcourue.put("name", new String("Distance parcourue"));
			objVal_DistancesParcourue.put("value",new Integer(this.robotCtr.getRobot().getMeasures().getDistanceParcourue()));
			
			//create a json list
			JSONArray listData = new JSONArray();
			//add json objects to jsonlist
			
			listData.add(objVal_NbreCommande);
			listData.add(objVal_ObstaclesRencontres);
			listData.add(objVal_ObstaclesVisibles);			
			listData.add(objVal_DistancesParcourue);
			
			//add jsonlist to json container
			objContainerData.put("mesuresGraphes", listData);

			//return json string of the json container
			return objContainerData.toJSONString();
		}
		
		

}
