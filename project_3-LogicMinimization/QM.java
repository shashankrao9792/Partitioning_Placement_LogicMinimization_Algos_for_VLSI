import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.*;

public class QM {
	
	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static void fillMatrixWithVal(int x[][], int val) {
		for (int i=0; i<x.length; i++) {
			for (int j=0; j<x[i].length; j++) {
				x[i][j] = val;
			}
		}
	}
	
	public static void fillRowWithVal(int x[], int val) {
		for (int i = 0; i < x.length; i++)
			x[i] = val;
	}
	
	public static boolean getPIfromMinterms(int min, int a[][], int row, int numOfVariables) {
		
		int b[]=new int[numOfVariables], i=numOfVariables-1, c=0;      
		fillRowWithVal(b, 0);                                    
		while (min>0) {      
			b[i]= min%2;      
			min = min/2;    
			i--;            
		}                                       
		for (i=0; i<numOfVariables; i++) {      
			if (a[row][i] == 9) {  
				continue;         
			}                 
			if (a[row][i] != b[i]) {
				c++;                 
			}        
		}           
		if (c == 0) {
			return true;    
		}                 
		return false;  
	}
	
	public static String decode(int x[][], int row, int numOfVariables, char bitvar[]) {
		String S = "";
		for (int i = 0; i < x[row].length; i++) {
			if (x[row][i] == 9)
				continue;
			else if (x[row][i] == 1)
				S += bitvar[i];
			else
				S += bitvar[i] + "'";
		}
		return S;
	}
	
	public static void main(String args[]) throws IOException {
		
		System.out.print("Enter the number of variables: ");
		int numOfVariables = getInt();
		System.out.print("Enter the number of minterms present in the expression (max "+(int)(Math.pow(2,numOfVariables)-1)+"): ");
		int numOfMinterms = getInt();
		int minTerms[] = new int[numOfMinterms];

		for(int i=0; i<numOfMinterms; i++) {
			System.out.print("Enter minterm in ascending order (Remaining Minterms - "+(numOfMinterms-i)+"): ");
			minTerms[i] = getInt();
		}
		
		int matrixA[][] = new int[numOfMinterms*(numOfMinterms+1)/2][numOfVariables];
		int matrixB[][] = new int[numOfMinterms*(numOfMinterms+1)/2][numOfVariables];
		int matrixPrimeImpl[][] = new int[numOfMinterms*(numOfMinterms+1)/2][numOfVariables];
		int checkerRow[] = new int[numOfMinterms*(numOfMinterms+1)/2];
		
		fillMatrixWithVal(matrixA, -1);
		
		for (int i=0; i<numOfMinterms; i++) {
			for (int j=0; j<numOfVariables; j++) {
				matrixA[i][j] = 0;
			}
		}
		
		int position=0;
		for (int i=0; i<numOfMinterms; i++) {
			int temp = minTerms[i];  
			position = numOfVariables - 1; 
			while(temp>0) {   
				matrixA[i][position] = temp % 2; 
				temp = temp/2; 
				position--; 
			}
		}
		
		System.out.println("\nThe following minterms have been entered: ");
		for (int i = 0; i < numOfMinterms; i++) {
			System.out.print(minTerms[i] + " ");
		}
		System.out.println("\n");
		
		System.out.println("\nThe following shows the grouping happenning at each pass:\n");
		int count=0;
		int flag=0, flag1=0, flag2=0;
		int i, j, k;
		int c, c1, c2, c3;
		int x, y;
		while(true){    
			count = 0;         
			flag = 0;    
			fillMatrixWithVal(matrixB, -1);      
			fillRowWithVal(checkerRow, -1);      
			for (i=0; i<matrixA.length; i++) {    
				if (matrixA[i][0] == -1) {    
					break;    
				}     
				for (j=i+1; j<matrixA.length; j++) {     
					c = 0;     
					if (matrixA[j][0] == -1) {     
						break;   
					}    
					for (k=numOfVariables-1; k>=0; k--) {      
						if (matrixA[i][k] != matrixA[j][k]) {           
							position = k;          
							c++;     
						}      
					}     
					if (c==1) {    
						count++;    
						checkerRow[i]++;   
						checkerRow[j]++;   
						for (k=numOfVariables-1; k>=0; k--) {        
							matrixB[flag][k] = matrixA[i][k];     
						}                                     
						matrixB[flag][position] = 9;    
						flag++;                      
					}        
				}      
			}      
			for (j=0; j<i; j++) {      
				if (checkerRow[j] == -1) {      
					for (k=0; k<numOfVariables; k++) {   
					  	matrixPrimeImpl[flag2][k] = matrixA[j][k];    
				  	}   
					c3 = 0;
					for (x=(flag2-1); x>=0; x--) {    
						c1 = 0;     
						for(y=0; y<numOfVariables; y++) {  
							if(matrixPrimeImpl[x][y] != matrixPrimeImpl[flag2][y]) {    
								c1++;      
							}    
						}    
						if (c1 == 0) {   
							c3++;    
							break;   
						}     
					}   
					if(c3==0) {   
						flag2++;    
					}  
				}   
			}    
			if(count==0) {
				break;
			}
			for (i=0; i<matrixB.length; i++) {     
				if (matrixB[i][0] == -1) {                   
					break;                                 
				}               
				for(j=0; j<numOfVariables; j++) {               
					if (matrixB[i][j] == 9) {     
						System.out.print("_");                      
					}                                   
					else {                                 
						System.out.print(matrixB[i][j]);               
					}                                 
				}                               
				System.out.println();
			}
			System.out.println();
			for (i=0; i<matrixB.length; i++) {             
				for (j = 0; j < matrixB[i].length; j++) {           
					matrixA[i][j] = matrixB[i][j];                
				}         
			}        
			flag1++;             
		}
		
		System.out.println("\nThe binary forms of the Prime Implicant are: ");      
		for (i=0; i<flag2; i++) {               
			for (j=0; j<numOfVariables; j++) {   
				if (matrixPrimeImpl[i][j] == 9)  
					System.out.print("_");   
				else                                           
					System.out.print(matrixPrimeImpl[i][j]);  
			}                     
			System.out.println();     
		}                     
		System.out.println();             
		                                    
		int dash[]=new int[numOfVariables];        
		fillRowWithVal(dash, -1);               
		matrixA=new int[flag2][numOfMinterms];            
		fillMatrixWithVal(matrixA, 0);           
		for (i = 0; i < flag2; i++) {           
			for (j = 0; j < numOfMinterms; j++) {             
				boolean check = getPIfromMinterms(minTerms[j], matrixPrimeImpl, i, numOfVariables);    
				if (check == true)          
					matrixA[i][j] = 1;                  
			}
		}
		
		
		System.out.println("The Prime Implicant table : ");  
		for (i=0; i<numOfMinterms; i++) {
			System.out.print(minTerms[i]+"\t");   
		}                     
		System.out.println();              
		for (i = 0; i < matrixA.length; i++) {          
			for (j = 0; j < numOfMinterms; j++) {  
				if (matrixA[i][j] == 1) {                                   
					System.out.print((char)(matrixA[i][j]+87)+"\t");
				}
				else {                              
					System.out.print(" "+"\t");
				}
			}                     
			System.out.println();                      
		}        
		
		checkerRow = new int[flag2];         
		dash = new int[numOfMinterms];      
		count = 0;                           
		fillRowWithVal(checkerRow, -1);        
		fillRowWithVal(dash, -1);                 
		for (j = 0; j < numOfMinterms; j++) {       
			count = 0;                     
			for (i = 0; i < flag2; i++) {       
				if (matrixA[i][j] == 1) {                 
					position = i;                 
					count++;       
				}             
			}                 
			if (count == 1)          
				checkerRow[position]++;         
		}                                                                               
		
		System.out.println("\nThe binary forms of the ESSENTIAL Prime Implicant are: "); 
		for (i=0; i<flag2; i++) {             
			if (checkerRow[i] != -1) {                        
				for (j=0; j<numOfVariables; j++) {                 
					if (matrixPrimeImpl[i][j] == 9) {                
						System.out.print("_");                    
					}                  
					else {                           
						System.out.print(matrixPrimeImpl[i][j]);                 
					}                          
				}                                         
				System.out.println();           
			}                               
		}                                       
		System.out.println();
		
		for (i = 0; i < flag2; i++) {               
			if (checkerRow[i] != -1) {               
				for (j=0; j<numOfMinterms; j++) {         
					if (matrixA[i][j] == 1)              
						dash[j]++;                   
				}                                  
				for (j=0; j<numOfMinterms; j++)           
					matrixA[i][j] = -1;             
			}                                   
		}                                   
		for (j=0; j<numOfMinterms; j++) {               
			if (dash[j] != -1) {               
				for (i=0; i<flag2; i++) {       
					matrixA[i][j] = -1;               
				}                            
			}                   
		}          
		       
		        
		                                                                     
		System.out.println("The PI table after removing essential PI : ");                 
		for (i=0; i<numOfMinterms; i++) {                        
			System.out.print(minTerms[i]+"\t");                  
		}                                 
		System.out.println();                         
		for (i=0; i<matrixA.length; i++) {                                
			for (j=0; j<numOfMinterms; j++) {                    
				if (matrixA[i][j] == 1) {                                  
					System.out.print((char)(matrixA[i][j]+87)+"\t");                
				}                                   
				else {                                           
					System.out.print(" "+"\t");                                           
				}                                                  
			}             
			System.out.println();
		}     
		
		System.out.println();    
		
		int nonine;                
		while(true) {                         
			count = 0;                         
			for(j=0; j<numOfMinterms; j++) {                
				for(k=j+1; k<numOfMinterms; k++) {                             
					c1 = 0; c2 = 0; c3 = 0;                  
					for(i=0; i<flag2; i++) {              
						if(matrixA[i][j]==1 && matrixA[i][k]==1) {            
							c1++;                                      
						}                                                   
						if(matrixA[i][j]==1 && matrixA[i][k]==0) {             
							c2++;                                    
						}                                               
						if(matrixA[i][j]==0 && matrixA[i][k]==1) {               
							c3++;                                   
						}                                                  
					}                                                    
					if(c2>0 && c3>0) {                                     
						break;                  
					}                                                   
					if(c1>0 && c2>0 && c3==0) {                          
						for(nonine=0; nonine<flag2; nonine++) {           
							matrixA[nonine][j] = -1;                
						}                                           
						count++;                                   
					}                                                 
					if(c1>0 && c3>0 && c2==0) {                  
						for(nonine=0; nonine<flag2; nonine++) {                
							matrixA[nonine][k] = -1;                
						}                                   
						count++;                                     
					}                                                
					if(c1>0 && c2==0 && c3==0) {                    
						for(nonine=0; nonine<flag2; nonine++) {          
							matrixA[nonine][j] = -1;                       
						}                   
						count++;           
					}           
				}                
			}                   
                                           
			for(i=0; i<flag2; i++) {            
				for(j=i+1; j<flag2; j++) {              
					c1 = 0;	c2 = 0;	c3 = 0;                    
					for(k=0; k<numOfMinterms; k++) {                 
						if(matrixA[i][k]==1 && matrixA[j][k]==1) {           
							c1++;                                     
						}                             
						if(matrixA[i][k]==1 && matrixA[j][k]==0) {            
							c2++;                                  
						}                                              
						if(matrixA[i][k]==0 && matrixA[j][k]==1) {                        
							c3++;                                         
						}                                               
					}                                                        
					if(c2>0 && c3>0) {                                    
						break;                                         
					}                                                       
					if(c1>0 && c2>0 && c3==0) {                        
						for(nonine=0; nonine<numOfMinterms; nonine++) {                
							matrixA[j][nonine] = -1;                       
						}                                                    
						count++;                                               
					}                                                     
					if(c1>0 && c3>0 && c2==0) {                             
						for(nonine=0; nonine<numOfMinterms; nonine++) {                
							matrixA[i][nonine] = -1;                       
						}                                                     
						count++;                                              
					}                                                        
					if(c1>0 && c2==0 && c3==0) {                               
						for(nonine=0; nonine<numOfMinterms; nonine++) {            
							matrixA[j][nonine] = -1;                    
						}                                                       
 						count++;                                           
					}                    
				}              
			}                
			if (count==0) {          
				break;              
			}               
		}                         
		
		System.out.println("PI table after applying both Row and Column Dominance : ");        
		for (i=0; i<numOfMinterms; i++) {           
			System.out.print(minTerms[i]+"\t");         
		}                          
		System.out.println();                
		for (i=0; i<matrixA.length; i++) {          
			for (j=0; j<numOfMinterms; j++) {                 
				if (matrixA[i][j] == 1) {                           
					System.out.print((char)(matrixA[i][j]+87)+"\t");         
				}                              
				else {                              
					System.out.print(" "+"\t");                   
				}                                          
			}       
			System.out.println();
		}                  
		System.out.println();  
		
		for (i=0; i<matrixA.length;i++) {                                              
			for (j=0; j<numOfMinterms; j++) {             
				if (matrixA[i][j]==1) {                
					checkerRow[i]++;                     
				}                              
			}                                      
		}                                            
				                                     
		char bitvar[] = new char[numOfVariables];                           
		for (i=0; i<numOfVariables; i++) {                                         
			bitvar[i] = (char)(65+i);                                           
		}                                                                            
                
		System.out.print("\nThe variables used in this expression are: ");
		for (i = 0; i < numOfVariables; i++) {
			System.out.print(bitvar[i] + " ");      
		}                
		System.out.println();
		System.out.println();
		
		System.out.println("The final simplified minterms are :");          
		for(i=0; i<flag2; i++) {                                                     
			if(checkerRow[i] != -1) {                   
				System.out.println(decode(matrixPrimeImpl, i, numOfVariables, bitvar));               
			}                 
		}                
	}              
}
