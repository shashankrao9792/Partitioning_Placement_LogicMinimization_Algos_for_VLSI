import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

public class TimberWolf {

//	NodeRowInfo N;
//	
//	public TimberWolf() {
//		N = new NodeRowInfo();
//		
//	}
	
	public void readNodesFile(String filename, NodeRowInfo N) {
		
		String line = null;
		int i=0, val=2;
		try {
				filename = filename + ".nodes";
				InputStream fis = new FileInputStream(filename);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			    while ((line = br.readLine()) != null) {
			    	i++;
			    	if(i==6) {
			    		String[] words = line.split("\\s+");
			    		N.numNodes = Integer.parseInt(words[2]);
			    	}
			    	else if(i==7) {
			    		String[] words = line.split("\\s+");
			    		N.numTerminals = Integer.parseInt(words[2]);
			    	}
			    	else if(i>7) {
			    		String[] words = line.split("\\s+");
			    		int len = words.length;
			    		if(len>4) {
			    			if(words[4].equals("terminal")) {
			    				val = 1;
			    			}
			    		}
			    		else {
			    			val = 0;
			    		}
			    		
			    		N.node = N.new Node();
			    		N.node.setParametersFromNodes(words[1], Integer.parseInt(words[2]), Integer.parseInt(words[3]), val);
			    		N.nodeId.put(words[1], N.node);
			    	}
			    }
			    
			    fis.close();
			    isr.close();
			    br.close();
			    
		}catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file "+filename);
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file "+filename);                  
            }
	}
	
	public void readWtsFile(String filename, NodeRowInfo N) {
		String line = null;
		int i=0;
		try {
				filename = filename + ".wts";
				InputStream fis = new FileInputStream(filename);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			    while ((line = br.readLine()) != null) {
			    	i++;
			    	if(i>5) {
			    		String[] words = line.split("\\s+");
//			    		System.out.println(words[1]+"hi"+words[2]);
			    		(N.nodeId.get(words[1])).setParametersFromWts(Integer.parseInt(words[2]));
			    	}
			    }
			    
			    fis.close();
			    isr.close();
			    br.close();
			    
		}catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file "+filename);
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file "+filename);                  
            }		
	}
	
	public void readPlFile(String filename, NodeRowInfo N) {
		String line = null;
		int i=0;
		try {
				filename = filename + ".pl";
				InputStream fis = new FileInputStream(filename);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			    while ((line = br.readLine()) != null) {
			    	i++;
			    	if(i>6) {
			    		String[] words = line.split("\\s+");
			    		(N.nodeId.get(words[0])).setParametersFromPl(Integer.parseInt(words[1]), Integer.parseInt(words[2]), words[4]);
			    	}
			    }
			    
			    fis.close();
			    isr.close();
			    br.close();
			    
		}catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file "+filename);
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file "+filename);                  
            }
	}
	
	public void readNetsFile(String filename, NodeRowInfo N) {
		String line = null;
		int i=0, val=0;
		int NetId = 1;
		try {
				filename = filename + ".nets";
				InputStream fis = new FileInputStream(filename);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			    while ((line = br.readLine()) != null) {
			    	i++;
			    	if(i>7) {
			    		String[] words = line.split("\\s+");
			    		if(words[0].equals("NetDegree")) {
			    			val = Integer.parseInt(words[2]);
			    			ArrayList<String> wordsTemp = new ArrayList<String>();
			    			for(int j=0; j<val; j++) {
			    				line = br.readLine();
			    				words = line.split("\\s+");
			    				wordsTemp.add(words[1]);
			    				(N.nodeId.get(words[1])).setNetList(NetId);
			    			}
			    			N.netToCell.put(NetId, wordsTemp);
			    		}
			    		NetId++;
			    	}
			    }
			    
			    fis.close();
			    isr.close();
			    br.close();
			    
		}catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file "+filename);
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file "+filename);                  
            }
	}
	
	public void readSclFile(String filename, NodeRowInfo N) {
		String line = null;
		try {
				int i=0, j=0;
				int Id = 1;
				int cordinate = 0;
				int height = 0;
				int siteWidth = 0;
				int siteSpacing = 0;
				String siteOrient = "";
				String siteSymmetry = "";
				int siteRowOrigin = 0;
				int numSites = 0;
				filename = filename + ".scl";
				InputStream fis = new FileInputStream(filename);
			    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			    BufferedReader br = new BufferedReader(isr);
			    while ((line = br.readLine()) != null) {
			    	i++;
			    	if(i>8) {
			    		String[] words = line.split("\\s+");
			    		j = i%9;
			    		if(j == 1)
						{
			    			cordinate = Integer.parseInt(words[3]);			
						}
						else if(j == 2)
						{
							height = Integer.parseInt(words[3]);
						}
						else if(j == 3)
						{
							siteWidth = Integer.parseInt(words[3]);			
						}
						else if(j == 4)
						{
							siteSpacing = Integer.parseInt(words[3]);			
						}
						else if(j == 5)
						{
							siteOrient = words[3];
						}
						else if(j == 6)
						{
							siteSymmetry = words[3];
						}
						else if(j == 7)
						{
							siteRowOrigin = Integer.parseInt(words[3]);
							numSites = Integer.parseInt(words[6]);
						}
						else if(j == 8)
						{
							N.row = N.new Row();
							N.row.setId(Id);
							N.rowId.put(Id, N.row);
							(N.rowId.get(Id)).setRowParameter(cordinate, height, siteWidth, siteSpacing, siteOrient, siteSymmetry, siteRowOrigin, numSites);
							Id++;
						}
			    	}
			    }
			    
			    fis.close();
			    isr.close();
			    br.close();
			    
		}catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file "+filename);
            }
            catch(IOException ex) {
                System.out.println(
                    "Error reading file "+filename);                  
            }
	}
	
	
	
	public NodeRowInfo runTimberWolfAlgo(NodeRowInfo T_N) {
		float temperature = 4000000;
		float time = 0;
		float alpha = (float) 0.8;
		int M = 120;
		
		System.out.println("Starting TimberWolf Algorithm...");
		while(temperature > 0.1) {
			
			System.out.println("Current Temperature = "+temperature+" and alpha = "+alpha);
			T_N = Metropolis(temperature, M, T_N);
			temperature = alpha*temperature;
			
			if(temperature < 4000000 && (temperature >= 2666667)) {
				alpha = (float) 0.8;
			}
			else if((temperature < 2666667) && (temperature >= 1333333)) {
				alpha = (float) 0.95;
			}
			else if((temperature < 1333333) && (temperature > 0.1)) {
				alpha = (float) 0.8;
			}
			
//			if(temperature < 4000000 && (temperature >= ((temperature/3)*2))) {
//				alpha = 0.8;
//			}
//			else if((temperature < ((temperature/3)*2)) && (temperature > (temperature/2))) {
//				alpha = 0.95;
//			}
//			else if((temperature < (temperature/2)) && (temperature > 0.1)) {
//				alpha = 0.8;
//			}
			
//			if(alpha <= 0.95) {
//				alpha = alpha + 0.01;
//			}
//			else if(alpha >= 0.80) {
//				alpha = alpha - 0.01;
//			}
		}
		return T_N;
	}
	
	public NodeRowInfo Metropolis(double T, int M, NodeRowInfo T_N) {
		
		System.out.println("Entering Metropolis Function...");
		while(M != 0) {
			NodeRowInfo newN = new NodeRowInfo();
			copyObjects(T_N, newN);
//			newN = T_N;
			newN = perturb(newN);
			int oldCost = CostFunction(T_N);
			int newCost = CostFunction(newN);
			System.out.println("Cost of Placement before perturbation = "+oldCost);
			System.out.println("Cost of Placement after perturbation = "+newCost);
			int deltaH = newCost - oldCost;
			
			double randomNum = (double) (java.lang.Math.random());
			double x = Math.exp(-deltaH/T);
			
			if((deltaH < 0) || (randomNum < x)) {
//				T_N = newN;
				copyObjects(newN, T_N);
				System.out.println("New Placement accepted due to lower cost");
			}
			else {
//				perturbCount++;
				System.out.println("New Placement rejected due to higher cost");
			}
			M--;
		}
		return T_N;
	}
	
	public void copyObjects(NodeRowInfo oldN, NodeRowInfo newN) {

		oldN.boundaries.copyBoundaries(newN.boundaries);

		newN.numberOfCells = oldN.numberOfCells;
		newN.totalWidthOfCells = oldN.totalWidthOfCells;
//		newN.nodeId = oldN.nodeId;
		for (Map.Entry<String, NodeRowInfo.Node> entry : oldN.nodeId.entrySet()) {
		    NodeRowInfo.Node tmpNode=oldN.new Node();
		    entry.getValue().copyNode(tmpNode);
		    newN.nodeId.put(entry.getKey(),tmpNode);
		}
		newN.xLimit = oldN.xLimit;
//		newN.rowId = oldN.rowId;
		for (Map.Entry<Integer, NodeRowInfo.Row> entry : oldN.rowId.entrySet()) {
		    NodeRowInfo.Row tmpRow=oldN.new Row();
		    entry.getValue().copyRow(tmpRow);
		    newN.rowId.put(entry.getKey(),tmpRow);
		}
//		newN.netToCell = oldN.netToCell;
		for (Map.Entry<Integer, ArrayList<String>> entry : oldN.netToCell.entrySet()) {
			ArrayList<String> tmpNetToCell=new ArrayList<String>();
			for(String e : entry.getValue())
				tmpNetToCell.add(e);
		    newN.netToCell.put(entry.getKey(),tmpNetToCell);
		}
		newN.numNodes = oldN.numNodes;
		newN.numTerminals = oldN.numTerminals;
		newN.xCordinateAfterLastPlacedCell = oldN.xCordinateAfterLastPlacedCell;
		newN.yCordinateAfterLastPlacedCell = oldN.yCordinateAfterLastPlacedCell;
	}
	
	int perturbCount = 1;
	public NodeRowInfo perturb(NodeRowInfo newN) {
		
//		NodeRowInfo newN = new NodeRowInfo();
		System.out.println("Entering perturb function...");
		int min = 1;
		int max = 3;
		int x = (int)(Math.random() * (max - min) + min);
		if(x == 1) {
			System.out.println("Cell swap randomly selected!");
			newN = cellSwap(newN);
		}
		else if(x == 2) {
			System.out.println("Cell move randomly selected!");
			newN = cellMove(newN);
		}
		
//		if(perturbCount == 3) {
//			newN = cellMirror(N);
//			perturbCount = 1;
//		}
		return newN;
	}
	
	public NodeRowInfo cellSwap(NodeRowInfo newN) {
//		NodeRowInfo newN = N;
		String randomCell1 = null, randomCell2 = null;
		int randomNo1 = 0, randomNo2 = 0;
		int randomMin = 1;
		int randomMax = newN.numNodes - newN.numTerminals;
		
		while((randomNo1 == 0 && randomNo2 == 0)) {
			randomNo1 = (int)(Math.random() * (randomMax - randomMin) + randomMin);
			randomNo2 = (int)(Math.random() * (randomMax - randomMin) + randomMin);
			if(randomNo1 == randomNo2) {
				randomNo1 = randomNo2 = 0;
				continue;
			}
			randomCell1 = "a" + randomNo1;
			randomCell2 = "a" + randomNo2;
			if(!newN.checkIfPadOrMacroblock(randomCell1, randomCell2)) {
				randomNo1 = randomNo2 = 0;
				continue;
			}
		}
		
		if( (!(randomCell1.equals(null))) && (!(randomCell2.equals(null))) ){
			newN.swapCells(randomCell1, randomCell2);
		}
		newN.updateCenterOfEachCell();
		return newN;
	}
	
	public NodeRowInfo cellMove(NodeRowInfo newN) {
//		NodeRowInfo newN = N;
		String randomCell = null;
		int randomNo = 0;
		int randomMin = 1;
		int randomMax = newN.numNodes - newN.numTerminals;
		
		while(randomNo == 0) {
			randomNo = (int)(Math.random() * (randomMax - randomMin) + randomMin);
			randomCell = "a" + randomNo;
			if(!newN.checkIfPadOrMacroblock(randomCell)) {
				randomNo = 0;
				continue;
			}
		}
		
		if(!(randomCell.equals(null))) {
			newN.moveCell2(randomCell);
		}
		newN.updateCenterOfEachCell();
//		newN.printNodeRowInfo();
		return newN;
	}
//	
//	public NodeRowInfo cellMirror(NodeRowInfo N) {
//		
//	}
	
	public int CostFunction(NodeRowInfo S) {
		int C = 0;
		
		float c1 = calculateWireLength(S);
		float c2 = calculateOverlapArea(S);
		float c3 = calculateUnevenRowsPenalty(S);
		
		double alpha1 = 1.5;
		double alpha2 = 3;
		double alpha3 = 1;
		
		C = (int)((alpha1*c1) + (alpha2*c2) + (alpha3*c3));
		
		return C;
	}

	public int calculateWireLength(NodeRowInfo S) {
		int wireLength = S.wireLengthCalc();
		return wireLength;
	}
	
	public int calculateOverlapArea(NodeRowInfo S) {
		int overlapArea = S.overlapAreaCalc();
		return overlapArea;
	}

	public float calculateUnevenRowsPenalty(NodeRowInfo S) {
		float unevenRowsLength = S.unevenRowsCalc();
		return unevenRowsLength;
	}
	
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		PrintStream o = new PrintStream(new File("executionTrace.txt"));
		System.setOut(o);
		String filename = "ibm18\\ibm18";
		
		TimberWolf T = new TimberWolf();
		
		NodeRowInfo T_N = new NodeRowInfo();
//		NodeRowInfo T_Temp = new NodeRowInfo();
		
		T.readNodesFile(filename, T_N);
		T.readWtsFile(filename, T_N);
		T.readPlFile(filename, T_N);
		T.readNetsFile(filename, T_N);
		T.readSclFile(filename, T_N);
		
		T_N.calculateBoundariesOfEntireRegion();
		T_N.placeMacroBlocks();
		T_N.initialPlacement();
		T_N.updateCenterOfEachCell();
		T_N.createRowToCellMap();
		T_N.forGraphicalRepresentation("InitialPlacement.txt");
		
		System.out.println("INITIAL PLACEMENT");
		T_N.printNodeRowInfo();
		
		T_N = T.runTimberWolfAlgo(T_N);
		T_N.forGraphicalRepresentation("FinalPlacement.txt");
		System.out.println("FINAL PLACEMENT");
		T_N.printNodeRowInfo();
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Execution Time is: "+totalTime);
	}
}
