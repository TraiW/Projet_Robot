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
import RobotManagement.Model.Case;
import RobotManagement.Model.Enum_Direction_Robot;
import RobotManagement.Model.Env;
import RobotManagement.Model.Robot;
import RobotManagement.Model.RobotInit;
import RobotManagement.Model.Measures;

@Path("/cmd")
public class RobotControlService {
	private final static String ROBOT_SIMULATOR_LABEL="robot_simulator";
	private static Robot robot=RobotInit.getInstance().createRobot(); 
	RobotCtr robotCtr = new RobotCtr(robot.getEnv_decouvert(), robot);
	private Case[][]tabEnv = null;
	private String[]tabAutoMapping=null;
	private static boolean unlock=false;
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
		@Produces(MediaType.TEXT_PLAIN)
		@Path("UP")
		public boolean goUp()
		{
			boolean bool =false;
			if(unlock ==false)
			{
				bool = false;
			}
			else
			{
				bool = robotCtr.deplacerRobot(Enum_Direction_Robot.UP);
				robot = robotCtr.getRobot();
				System.out.println(bool);
				System.out.println("Xu = "+ robot.getX() + " Y = "+robot.getY());
			}
				return bool;
		}
		
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("DOWN")
		public boolean goDown()
		{
			boolean bool =false;
			if(unlock==false)
			{
				bool = false;
			}
			else{
				bool = robotCtr.deplacerRobot(Enum_Direction_Robot.DOWN);
				robot = robotCtr.getRobot();
				System.out.println(bool);
				System.out.println("Xd = "+ robot.getX() + " Y = "+robot.getY());
			}
			return bool;

		}
		
		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("RIGHT")
		public boolean goRight()
		{
			boolean bool =false;
			if(unlock==false)
			{
				bool = false;
			}
			else{
				bool = robotCtr.deplacerRobot(Enum_Direction_Robot.RIGHT);
				robot = robotCtr.getRobot();
				System.out.println(bool);
				System.out.println("Xr = "+ robot.getX() + " Y = "+robot.getY());
			}
			return bool;
		}

		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("LEFT")
		public boolean goLeft()
		{
			boolean bool =false;
		if(unlock==false)
		{
			bool = false;
		}
		else{
			bool = robotCtr.deplacerRobot(Enum_Direction_Robot.LEFT);
			robot = robotCtr.getRobot();
			System.out.println(bool);
			System.out.println("Xl = "+ robot.getX() + " Y = "+robot.getY());
			}
			return bool;
		}


		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("START")
		public String goStart()
		{
			this.unlock = true;
			System.out.println("goStart : " + unlock);
			return "login.xhtml";
		}

		@POST
		@Produces(MediaType.TEXT_PLAIN)
		@Path("STOP")
		public String goStop()
		{
			this.unlock=false;
			System.out.println("goStop : " + unlock);
			return "login.xhtml";
		}
		
		
		@GET		
		@Produces(MediaType.TEXT_HTML)
		@Path("test")
		public String test(){
			return "index-1.html";
		}
		
		@SuppressWarnings("unchecked")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("obstacle")
		public String RecupObstacle()
				{
			
			JSONObject objContainer = new JSONObject();
			JSONObject objVal1 = new JSONObject();
			
			ArrayList<Integer>CX=new ArrayList<Integer>();
			ArrayList<Integer>CY=new ArrayList<Integer>();

			tabEnv=RobotInit.getInstance().getEnvironnement().getTableauEnv();
			int i=0,j=0;
			int cpt=0;
			int nbreObstacle=0;
			
			for(j=0;j<RobotInit.getInstance().getX_plateau();j++){
				for(i=0;i<RobotInit.getInstance().getY_plateau();i++){
					if(tabEnv[j][i].isObstacle()==true){
						CX.add(cpt, i);
						CY.add(cpt, j);
						cpt+=1;
					}
				}
			}
			nbreObstacle=cpt;
			objVal1.put("x", CX);
			JSONObject objVal2 = new JSONObject();
			objVal2.put("y", CY);
			
			JSONObject objVal3 = new JSONObject();
			objVal3.put("nbreObstacle", nbreObstacle);
			
			JSONArray list = new JSONArray();
			//add json objects to jsonlist
			list.add(objVal1);
			list.add(objVal2);
			list.add(objVal3);
			
			//add jsonlist to json container
			objContainer.put("terrain", list);

			return objContainer.toJSONString();
		}
		
		@SuppressWarnings("unchecked")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("masquage")
		public String RecupMasquage()
				{
			
			JSONObject objContainer = new JSONObject();
			JSONObject objVal1 = new JSONObject();
			
			ArrayList<Integer>CX=new ArrayList<Integer>();
			ArrayList<Integer>CY=new ArrayList<Integer>();

			tabEnv=RobotInit.getInstance().getEnvironnement().getTableauEnv();
			int i=0,j=0;
			int cpt=0;
			int nbreMasquage=0; 
			
			for(j=0;j<RobotInit.getInstance().getX_plateau();j++){
				for(i=0;i<RobotInit.getInstance().getY_plateau();i++){
					if(tabEnv[j][i].isMasquage()==false){
						CX.add(cpt, i);
						CY.add(cpt, j);
						cpt+=1;
					}
				}
			}
			nbreMasquage=cpt;

			objVal1.put("x", CX);
			JSONObject objVal2 = new JSONObject();
			objVal2.put("y", CY);
			JSONObject objVal3 = new JSONObject();
			objVal3.put("nbreMasquage", nbreMasquage);
			
			JSONArray list = new JSONArray();
			//add json objects to jsonlist
			list.add(objVal1);
			list.add(objVal2);
			list.add(objVal3);

			//add jsonlist to json container
			objContainer.put("masquage", list);

			return objContainer.toJSONString();
		}		
		
		@SuppressWarnings("unchecked")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("parcouru")
		public String RecupCheminParcouru()
				{
			
			JSONObject objContainer = new JSONObject();
			JSONObject objVal1 = new JSONObject();
			
			ArrayList<Integer>CX=new ArrayList<Integer>();
			ArrayList<Integer>CY=new ArrayList<Integer>();

			tabEnv=RobotInit.getInstance().getEnvironnement().getTableauEnv();
			int i=0,j=0;
			int cpt=0;
			int nbreParcouru=0; 

			for(j=0;j<RobotInit.getInstance().getX_plateau();j++){
				for(i=0;i<RobotInit.getInstance().getY_plateau();i++){
					if(tabEnv[j][i].isParcouru()==true){
						CX.add(cpt, i);
						CY.add(cpt, j);
						cpt+=1;
					}
				}
			}
			nbreParcouru=cpt;
			
			objVal1.put("x", CX);
			JSONObject objVal2 = new JSONObject();
			objVal2.put("y", CY);
			
			JSONObject objVal3 = new JSONObject();
			objVal3.put("nbreParcouru", nbreParcouru);
			
			JSONArray list = new JSONArray();
			//add json objects to jsonlist
			list.add(objVal1);
			list.add(objVal2);
			list.add(objVal3);

			//add jsonlist to json container
			objContainer.put("parcouru", list);

			return objContainer.toJSONString();
		}		
			
/*		// METHODE : Envoie objet JSON avec les infos sur l'herbe et sur les obstacles	
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("env")
		public String getEnv()
				{
			tabEnv=RobotInit.getInstance().getEnvironnement().getTableauEnv();
			int i=0,j=0;
			String[] Env_converti=new String[RobotInit.getInstance().getX_plateau()*RobotInit.getInstance().getY_plateau()+40];
			String debut ="[";
			String fin ="]";
			String obstacle="1";
			String herbe="2";
			int cpt=0;
			
			for(j=0;j<RobotInit.getInstance().getX_plateau();j++){
				Env_converti[cpt]=debut;
				cpt+=1;
				for(i=0;i<RobotInit.getInstance().getY_plateau();i++){
					if(tabEnv[i][j].isVide()==true){
						if(Env_converti[cpt]==debut){
							Env_converti[cpt]=debut+herbe;
						}
						else if(i==RobotInit.getInstance().getX_plateau()-1){
							Env_converti[cpt]=herbe+fin;
						}
						else{
							Env_converti[cpt]=herbe;
						}	
					}
					else if(tabEnv[i][j].isObstacle()==true){
						if(i==RobotInit.getInstance().getX_plateau()-1){
							Env_converti[cpt]=obstacle+fin;
						}
						else{
							Env_converti[cpt]=obstacle;
						}
					}
					cpt+=1;	
					//System.out.println(Env_converti[cpt]);
				}
			}
			
			JSONObject objContainer = new JSONObject();
			//create set of json objects
			JSONObject objVal1 = new JSONObject();
			
			//create a json list
			JSONArray list = new JSONArray();
			JSONArray list1 = new JSONArray();
			
			//add json objects to jsonlist
			list.add("chemin.png");
			for(i=0;i<Env_converti.length;i++){
				//System.out.println(Env_converti[i]);

					list1.add(Env_converti[i]);
			}
			
			//add jsonlist to json container
			objContainer.put("terrain", list1);
			objContainer.put("tileset", list);
			
			System.out.println(objContainer.toString());
			//return json string of the json container
			return objContainer.toJSONString();
			
			
			//ALTERNATIVE send direct a json String
			//return "{\"data\":[{\"x\":0,\"y\":0,\"val\":\"FREE\"},{\"x\":0,\"y\":1,\"val\":\"WALL\"},{\"x\":1,\"y\":1,\"val\":\"ROBOT\"}]}";
		}
*/
		@SuppressWarnings("unchecked")
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
		
		@SuppressWarnings("unchecked")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("automapping")
		public String RecupAutoMapping()
				{
			
			JSONObject objContainer = new JSONObject();
			JSONObject objVal1 = new JSONObject();
			JSONObject objVal2 = new JSONObject();

			ArrayList<String>AutoMappingString=new ArrayList<String>();

			tabAutoMapping=//RobotInit.getInstance().getEnvironnement().getTableauEnv();
			int i=0;
			int cpt=0;
			int TailleAutoMapping=0;
			
			for(i=0;i<RobotInit.getInstance().getX_plateau();i++){
				AutoMappingString.add(cpt,tabAutoMapping[i]);
				cpt+=1;		
			}
			TailleAutoMapping=cpt;
			
			objVal1.put("AutoMappingString", AutoMappingString);
			objVal2.put("TailleAutoMapping", TailleAutoMapping);
			
			JSONArray list = new JSONArray();
			//add json objects to jsonlist
			list.add(objVal1);
			list.add(objVal2);

			//add jsonlist to json container
			objContainer.put("Automapping", list);

			return objContainer.toJSONString();
		}	
		
		

}
