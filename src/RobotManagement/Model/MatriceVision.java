package RobotManagement.Model;

public class MatriceVision {
	private int[][] mat=new int[3][3];
	private int nbLignes=mat.length;
	private int nbColonnes=mat[0].length;
	
	
	public MatriceVision(){
		for (int i=0;i<mat.length;i++)
			for(int j=0;j<mat[i].length;j++)
				mat[i][j]=0;
		
		mat[0][0]=1;
		
	}
	public int getNbLignes() {return nbLignes;}
	public int getNbColonnes() {return nbColonnes;}
	
	public void printMatrix(){
		for (int i=0;i<mat.length;i++)
		{
			for(int j=0;j<mat[i].length;j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println("");
		}
	}
	public MatriceVision rotation(MatriceVision matrice){
		MatriceVision retour=new MatriceVision();
		for (int i=0;i<matrice.nbColonnes;i++)
		{
			
		}
		return retour;
	}
	
	public static void main(String[] args){
		MatriceVision mat=new MatriceVision();
		mat.printMatrix();
		System.out.println("");

		
		mat.printMatrix();

		
		System.out.println(mat.getNbLignes()+" lign "+mat.getNbColonnes()+" col");
	}
}
