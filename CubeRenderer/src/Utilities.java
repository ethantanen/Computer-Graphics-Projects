
public class Utilities {
	
	public Utilities(){
		
	}
 
	/*
	 * multiply two matrices
	 */
	public double[][]matMult(double[][] mat, double[][] mat2){
		
		double[][] product; 
	
		//check if matrices have proper dimension (3x1 1x3) and (4x3 3x5) are acceptable (5x2 3x5) is not acceptable 
		if(mat[0].length != mat2.length){
			System.out.println("Error: MatMult incorrect dimension");
			System.out.println("Mat1[0]: " + mat[0].length + " Mat2: " + mat2.length);
			return null;
		}
		
		//new array is the first dimension of the first mat and the second dimension of the second 1x3 3x1 will be a 1x1 mat
		product = new double[mat.length][mat2[0].length]; 
		
		//transpose matrix so columns can be accessed easily using an index 
		mat2 = transpose(mat2);
		
		//cycle through entries of the product array multiplying rows by columns (columns transposed so they are now rows!)
		for(int i=0; i<product.length; i++){
			for(int j=0; j<product[0].length;j++){
				System.out.println("i: " + i + " j: " + j);
				product[i][j] = dotProd(mat[i],mat2[j]);	
			}
		}
		
		return product;
	}
	

	/*
	 * multiply a matrix by a vector 
	 * 	-a vector is a nx1 matrix
	 */
	public double[]matMult(double[][] mat1, double[] vec){		
		double[] product = new double[mat1.length]; 		
		for(int i=0; i<product.length; i++){
				product[i] = dotProd(mat1[i],vec);	
		}
		return product;
	}
	
	/*
	 * determine dot product of two vectors
	 * 	
	 */
	public double dotProd(double[] vec1, double[] vec2){
		double sum = 0;//variable to hold dot product 	
		//cycle through vector multiplying like indexed pts and adding them to sum 
		for(int i=0; i<vec1.length; i++){
			sum += (vec1[i] * vec2[i]);
		}
		return sum; 
	}
	
	/*
	 * return the dot product of two 3 dimensional vectors 
	 */
	public double[] crossProd(double []vec1, double[] vec2){
		double[] product;//array to hold new vector 
		//cross product only works for 3 dimension vectors so the calculation is hard coded in
		product = new double[3];
		product[0] = (vec1[1] * vec2[2]) - (vec1[2] * vec2[1]);
		product[1] = -1 * ((vec1[0] * vec2[2]) - (vec1[2] * vec2[0]));
		product[2] = (vec1[0] * vec2[1]) - (vec1[1] * vec2[0]);
		return product; 
	}
	
	/*
	 * return the magnitude of a vector 
	 */
	public double magnitude(double[] vec){
		double sum = 0;
		//cycle through vector adding square of each element to sum 
		for(int i=0; i<vec.length; i++){
			sum += vec[i] * vec[i];
		}
		//return the sqrt of the sum of elements squared 
		return Math.sqrt(sum);
	}
	
	/*
	 * return the transpose of a matrix 
	 */
	public double[][] transpose(double[][] mat){
		double[][] trans; //array to hold new array 
		trans = new double[mat[0].length][mat.length];
		//cycle through trans array adding oppositely indexed elements 
		for(int i=0; i<trans.length; i++){
			for(int j=0; j<trans[0].length; j++){
				trans[i][j] = mat[j][i];
			}
		}
		return trans;
	}
	
	/*
	 * turn a vector into a 3x1 matrix for use in the matrix multiplication method 
	 */
	public double[][] matrixify(double[] vec){
		double[][] trans = new double[vec.length][1];
		for(int i=0; i<vec.length; i++){
			trans[i][0] = vec[i];
		}
		return trans;
	}
	
	/*
	 * return projection of vec1 onto vec2
	 * 	--> ((V·W)/|W|) * W-normal
	 */
	public double[] projection(double[] vec1, double[] vec2){
		double dot = dotProd(vec1,vec2);
		double[] vec2N = normalize(vec2);
		double vec2M = magnitude(vec2);
		double[] projection = new double[vec1.length];
		for(int i=0; i<projection.length; i++){
			projection[i] = (dot/vec2M)  * vec2N[i];
		}
		return projection;
	}
	
	/*
	 * return normalized vector
	 */
	public double[] normalize(double[] vec){
		double mag;
		double[] normal;
		if(vec == null){
			System.out.println("Error: Normalize null vector");
		}
		mag = magnitude(vec);
		normal = new double[vec.length];
		for(int i=0; i<vec.length; i++){
			normal[i] = vec[i] / mag; 
		}
		return normal;
	}
	
	
	//Prints a vector 
	public static void printV(double[] a){
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	//Prints a matrix 
	public static void printM(double[][] p){
		System.out.println("******************************");
		for(int i=0; i<p.length; i++){
			for(int j=0; j<p[0].length; j++){	
				System.out.print(p[i][j] + " ");	
			}
			System.out.println();
		}
		System.out.println("******************************");
		System.out.println();
	}
}

