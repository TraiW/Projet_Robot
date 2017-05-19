package RobotManagement.Model;

public class Case {
//	structure avec : état de la case
//					 masqué ou non

	private Enum_Etat_Case etat_case; // enum
	private boolean masquage;//true => masqué ; false => visible
	
	public Case(Enum_Etat_Case etat_case, boolean masquage){
		this.etat_case=etat_case;
		this.masquage=masquage;
	}
	
	public Enum_Etat_Case getEtat_case(){return etat_case;}
	public void setEtat_case(Enum_Etat_Case etat_case){this.etat_case = etat_case;}
	public boolean isMasquage(){return masquage;}
	public void setMasquage(boolean masquage) {this.masquage = masquage;}
	
	public void setParcouru()
	{
		this.setEtat_case(Enum_Etat_Case.parcouru);
		//en théorie déjà unmasked
	}
	public void setRobot()
	{
		this.setEtat_case(Enum_Etat_Case.robot);
		this.masquage=false;
	}
	
	public boolean isVide(){
		boolean retour=false;
		if(this.getEtat_case()==Enum_Etat_Case.vide){retour=true;}
		return retour;}
	public boolean isParcouru(){
		boolean retour=false;
		if(this.getEtat_case()==Enum_Etat_Case.parcouru){retour=true;}
		return retour;}
	public boolean isRobot(){
		boolean retour=false;
		if(this.getEtat_case()==Enum_Etat_Case.robot){retour=true;}
		return retour;}
	public boolean isObstacle(){
		boolean retour=false;
		if(this.getEtat_case()==Enum_Etat_Case.obstacle){retour=true;}
		return retour;}
	
	@Override
	public String toString() {
		return "Case [etat_case=" + etat_case + ", masquage=" + masquage + "]";
	}
	
}
