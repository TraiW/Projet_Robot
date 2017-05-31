package RobotManagement.Model;

public enum Enum_Orientation_Robot {
	N,S,E,W;
	
	 public static Enum_Direction_Robot getCorrespondance(Enum_Orientation_Robot orient){
		 Enum_Direction_Robot retour=Enum_Direction_Robot.LEFT;
		 switch(orient)
		 {
		 	case N:
		 		retour=Enum_Direction_Robot.UP;
		 		break;
		 	case S:
		 		retour=Enum_Direction_Robot.DOWN;
		 		break;
		 	case E:
		 		retour=Enum_Direction_Robot.RIGHT;
		 		break;
		 	case W:
		 		retour=Enum_Direction_Robot.LEFT;
		 		break;
	 		default:
	 			break;
		 }
		 return retour;
	  }
	 
	 public static Enum_Orientation_Robot getOpposite(Enum_Orientation_Robot dir){
		 Enum_Orientation_Robot retour=Enum_Orientation_Robot.W;
		 switch(dir)
		 {
		 	case N:
		 		retour=Enum_Orientation_Robot.S;
		 		break;
		 	case S:
		 		retour=Enum_Orientation_Robot.N;
		 		break;
		 	case E:
		 		retour=Enum_Orientation_Robot.W;
		 		break;
		 	case W:
		 		retour=Enum_Orientation_Robot.E;
		 		break;
	 		default:
	 			break;
		 }
		 return retour;
	  }
}
