
public class Matrix {

	private double[][] values;
	private int size;
	
	Matrix(){
		
	}
	
	Matrix(double[][] arg){
		size = arg.length;
		values = new double[size][size];
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				values[i][j]=arg[i][j];
			}
		}
	}
	
//	public double[] sweepMethod(Matrix matrix, double[] vector) {
//		int size=matrix.size;
//		double[] result = new double[size] ;
//		double y=matrix.values[0][0];
//		double[] alpha = new double[size-1];
//		double[] beta = new double[size];
//		alpha[0] = -matrix.values[0][1]/y;
//		beta[0] = vector[0]/y;
//		for(int i=1;i<size-1;i++) {
//			y=matrix.values[i][i]+matrix.values[i][i-1]*alpha[i-1];
//			alpha[i]=-matrix.values[i][i+1]/y;
//			beta[i]=(vector[i]-matrix.values[i][i-1]*beta[i-1])/y;
//		}
//		y=matrix.values[size-1][size-1]+matrix.values[size-1][size-2]*alpha[size-2];
//		
//		
//		for(int i=0;i<size-1;i++) {
//			System.out.print(alpha[i]+" ");
//		}
//		System.out.println();
//		for(int i=0;i<size;i++) {
//			System.out.print(beta[i]+" ");
//		}
//		
//		result[size-1]=beta[size-1];
//		for(int i=size-2;i>-1;i--) {
//			result[i]=alpha[i]*result[i+1]+beta[i];
//		}
//		return result;
//	}
	
	private double[][] transformeMatrixToFourVectorsForSweepMethod(double[] arg){
		double[][] result = new double[4][size];
		
		result[1][0]=values[0][0];
		result[2][0]=values[0][0+1];
		result[3][0]=arg[0];
		for(int i=1;i<size-1;i++) {			
			result[0][i]=values[i][i-1];
			result[1][i]=values[i][i];
			result[2][i]=values[i][i+1];
			result[3][i]=arg[i];
		}
		result[0][size-1]=values[size-1][size-2];
		result[1][size-1]=values[size-1][size-1];
		
		result[3][size-1]=arg[size-1];
		return result;
	}
	
	
	private double[][] straingthCourseForSweepMethod(double[][] arg){
		double[][] result = new double [2][size];
		double y=arg[1][0];
		result[0][0]=-arg[2][0]/y;
		result[1][0]=arg[3][0]/y;
		for(int i=1;i<size;i++) {
			y=arg[1][i]+arg[0][i]*result[0][i-1];
			if (i<size-1) {
				result[0][i]=-arg[2][i]/y;
			}
			result[1][i]=(arg[3][i]-arg[0][i]*result[1][i-1])/y;
		}
		return result;
	}
	
	private double[] reverseForSweepMethod(double[][] arg) {
		double[] result = new double[size];
		result[size-1]=arg[1][size-1];
		for(int i=size-2;i>-1;i--) {
			result[i]=arg[0][i]*result[i+1]+arg[1][i];
		}
		return result;
	}
	
	public double[] sweepMethod(double[] arg) {
		return reverseForSweepMethod(straingthCourseForSweepMethod(transformeMatrixToFourVectorsForSweepMethod(arg)));
	}
	public void showMatrix() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.print(values[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	
}
