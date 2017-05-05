package RobotManagement.Model;

public class Case {
//	structure avec : état de la case
//					 masqué ou non

	Enum_Etat_Case etat_case; // enum
	boolean masquage;//true => masqué ; false => visible
	
	public Case(Enum_Etat_Case etat_case, boolean masquage){
		this.etat_case=etat_case;
		this.masquage=masquage;
	}
	
	public Enum_Etat_Case getEtat_case(){return etat_case;}
	public void setEtat_case(Enum_Etat_Case etat_case){this.etat_case = etat_case;}
	public boolean isMasquage(){return masquage;}
	public void setMasquage(boolean masquage) {this.masquage = masquage;}

	@Override
	public String toString() {
		return "Case [etat_case=" + etat_case + ", masquage=" + masquage + "]";
	}
	
}
