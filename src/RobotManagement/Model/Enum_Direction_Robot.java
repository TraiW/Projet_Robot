package RobotManagement.Model;

public enum Enum_Direction_Robot {
	UP,DOWN,RIGHT,LEFT;
	
	 public Enum_Direction_Robot getOpposite(Enum_Direction_Robot dir){
		 Enum_Direction_Robot retour=Enum_Direction_Robot.LEFT;
		 switch(dir)
		 {
		 	case UP:
		 		retour=Enum_Direction_Robot.DOWN;
		 		break;
		 	case DOWN:
		 		retour=Enum_Direction_Robot.UP;
		 		break;
		 	case RIGHT:
		 		retour=Enum_Direction_Robot.LEFT;
		 		break;
		 	case LEFT:
		 		retour=Enum_Direction_Robot.RIGHT;
		 		break;
	 		default:
	 			break;
		 }
		 return retour;
	  }
}
