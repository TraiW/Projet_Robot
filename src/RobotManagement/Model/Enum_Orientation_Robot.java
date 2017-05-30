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
}
