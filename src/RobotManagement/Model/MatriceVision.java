package RobotManagement.Model;

public class MatriceVision {
	private int[][] mat=new int[3][3];
	private int nbLignes=mat.length;
	private int nbColonnes=mat[0].length;
	
	
	public MatriceVision(){
		for (int i=0;i<mat.length;i++)
			for(int j=0;j<mat[i].length;j++)
				mat[i][j]=0;
		mat[0][1]=2;
		mat[1][0]=1;
		mat[1][1]=1;
		mat[1][2]=1;
	}
	
	public int[][] getMat(){return this.mat;}
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
	
	public MatriceVision rotation(Enum_Orientation_Robot orient){
		MatriceVision matrice=new MatriceVision();
		matrice.mat=this.mat;
		matrice=this.rot90(matrice);
		if (orient==Enum_Orientation_Robot.N ||orient==Enum_Orientation_Robot.E){
			matrice=this.rot90(matrice);
			if (orient==Enum_Orientation_Robot.E)
				matrice=this.rot90(matrice);
		}
		return matrice;
	}
	
	private MatriceVision rot90(MatriceVision matrice){
		MatriceVision retour=new MatriceVision();
		for (int j=0;j<matrice.nbLignes;j++){
			for (int i=0;i<matrice.nbColonnes;i++){
				retour.mat[i][matrice.nbColonnes-1-j]=matrice.mat[j][i];
			}
		}
		return retour;
	}
	
	public static void main(String[] args){
		MatriceVision mat=new MatriceVision();
		mat.mat[0][1]=2;
		mat.mat[1][0]=1;
		mat.mat[1][1]=1;
		mat.mat[1][2]=1;
		mat.printMatrix();
		System.out.println("");
		//mat.rotation(mat).printMatrix();
		mat=mat.rotation(Enum_Orientation_Robot.E);
		mat.printMatrix();
	}
}
