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
		mat[1][0]=1;
		mat[1][1]=1;
		mat[1][2]=1;
		
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
		//TODO
		MatriceVision retour=new MatriceVision();
		for (int j=0;j<matrice.nbLignes;j++)
		{
			for (int i=0;i<matrice.nbColonnes;i++)
			{
				retour.mat[i][matrice.nbColonnes-j]=matrice.mat[j][i];
			}
		}
		return retour;
	}
	
	public static void main(String[] args){
		MatriceVision mat=new MatriceVision();
		mat.printMatrix();
		System.out.println("");
		//mat.rotation(mat).printMatrix();
		mat=mat.rotation(mat);
		
		mat.printMatrix();

		
		System.out.println(mat.getNbLignes()+" lign "+mat.getNbColonnes()+" col");
	}
}
