import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.ArrayList;

public class NodeRowInfo {
	
	public class Node{
		String nodeName;	
		int width;
		int height;
		int area;
		int terminal;
		int xCordinate;
		int yCordinate;
		int xCenter;
		int yCenter;
		String orientation;
		int cellRowId;
		ArrayList<Integer> netList;
		
		public Node() {
			this.nodeName = "";	
			this.width = 0;
			this.height = 0;
			this.area = 0;
			this.terminal = 0;
			this.xCordinate = 0;
			this.yCordinate = 0;
			this.xCenter = 0;
			this.yCenter = 0;
			this.orientation = "";
			this.cellRowId = 0;
			this.netList = new ArrayList<Integer>();
		}
		void copyNode(Node copyToNode)
		{
			copyToNode.nodeName=this.nodeName;
			copyToNode.width=this.width;
			copyToNode.height=this.height;
			copyToNode.area=this.area;
			copyToNode.terminal=this.terminal;
			copyToNode.xCordinate=this.xCordinate;
			copyToNode.yCordinate=this.yCordinate;
			copyToNode.xCenter=this.xCenter;
			copyToNode.yCenter=this.yCenter;
			copyToNode.orientation=this.orientation;
			copyToNode.cellRowId=this.cellRowId;
			for (Integer net : this.netList)
				copyToNode.netList.add(net);	
			
		}
		
		void setParametersFromNodes(String N, int W, int H, int T) {
			this.nodeName = N;
			this.width = W;
			this.height = H;
			this.terminal = T;
		}
		
		void setParametersFromWts(int A) {
			this.area = A;
		}
		
		void setParametersFromPl(int X, int Y, String S) {
			this.xCordinate = X;
			this.yCordinate = Y;
			this.orientation = S;
		}
		
		Node setRowId(int R_id) {
			this.cellRowId = R_id;
			return this;
		}
		
		void setNetList(int N_id) {
			this.netList.add(N_id); 
		}
		
		Node setCenter(int X, int Y) {
			this.xCenter = X;
			this.yCenter = Y;
			return this;
		}
		
		void printNodeParameters() {
			System.out.println("-------------------------------------------------------------");
			System.out.println("NodeName:   "+this.nodeName);
			System.out.println("Width:   "+this.width);
			System.out.println("Height:   "+this.height);
			System.out.println("Area:   "+this.area);
			System.out.println("Terminal:   "+this.terminal);
			System.out.println("xCo-ordinate:   "+this.xCordinate);
			System.out.println("yCo-rdinate:   "+this.yCordinate);
			System.out.println("x/2:   "+this.xCenter);
			System.out.println("y/2:   "+this.yCenter);
			System.out.println("Orientation:   "+this.orientation);
			System.out.println("CellRowId:   "+this.cellRowId);
			
			Iterator<Integer> it = this.netList.iterator();
			System.out.print("Netlist: ");
			while(it.hasNext()) {
				System.out.print(it.next()+" ");
			}
			System.out.println("");
		}
	}
		
	public class Row {
		int Id;
		int cordinate;
		int height;
		int siteWidth;
		int siteSpacing;
		String siteOrient;
		String siteSymmetry;
		int siteRowOrigin;
		int numSites;
		int overlap;
		ArrayList<String> cellList;
		
		public Row() {
			this.Id = 0;
			this.cordinate = 0;
			this.height = 0;
			this.siteWidth = 0;
			this.siteSpacing = 0;
			this.siteOrient = "";
			this.siteSymmetry = "";
			this.siteRowOrigin = 0;
			this.numSites = 0;
			this.overlap = 0;
			this.cellList = new ArrayList<String>();
		}
			
		void copyRow(Row copyToRow)
		{
			copyToRow.Id=this.Id;
			copyToRow.cordinate=this.cordinate;
			copyToRow.height=this.height;
			copyToRow.siteWidth=this.siteWidth;
			copyToRow.siteSpacing=this.siteSpacing;
			copyToRow.siteOrient=this.siteOrient;
			copyToRow.siteSymmetry=this.siteSymmetry;
			copyToRow.siteRowOrigin=this.siteRowOrigin;
			copyToRow.numSites=this.numSites;
			copyToRow.overlap=this.overlap;
			for(String e:this.cellList)	
			 copyToRow.cellList.add(e);
		}
		void setId(int I) {
			this.Id = I;
		}
		
		void setRowParameter(int C, int H, int SW, int SS, String SO, String SSym, int SRO, int NS) {
			this.cordinate = C;
			this.height = H;
			this.siteWidth = SW;
			this.siteSpacing = SS;
			this.siteOrient = SO;
			this.siteSymmetry = SSym;
			this.siteRowOrigin = SRO;
			this.numSites = NS;
		}
		
		void setCellList(ArrayList<String> cellId) {
			this.cellList = cellId;
		}
		
		void setCellListElement (String cellId) {
			this.cellList.add(cellId);
		}
		
		String removeCellListElement(String cellId) {
			this.cellList.remove(cellId);
			return cellId;
		}
		
		ArrayList<String> sortByX() {
			int i=0;
			int x=0;
			String t;
			
			Map<Integer, String> sortX = new HashMap<Integer, String>();
			for(i=0; i<(this.cellList.size()); i++) {
				t = this.cellList.get(i);
				x = nodeId.get(t).xCordinate;
				sortX.put(x, this.cellList.get(i));
			}
			
			ArrayList<String> list = new ArrayList<String>();
	        Map<Integer, String> map = new TreeMap<Integer, String>(sortX); 
	        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
	        while(it.hasNext()) {
	        	Entry<Integer, String> next = it.next();
	        	list.add(next.getValue());
	        }
			
			this.cellList = list;
			
			return this.cellList;
		}
		
//		void calculateRowOverlap() {
//			int xLast = 0, widthLast = 0;
//			String t = this.cellList.get(this.cellList.size()-1);
//			xLast = nodeId.get(t).xCordinate;
//			widthLast = nodeId.get(t).width;
//			int overlap = xLast + widthLast - (rowWidth+xLimit);
//			
//			this.overlap = overlap;
//		}
		
		void printRowParameters() {
			System.out.println("-------------------------------------------------------------");
			System.out.println("RowId:    "+this.Id);
			System.out.println("RowCordinate:    "+this.cordinate);
			System.out.println("Height:    "+this.height);
			System.out.println("SiteWidth:    "+this.siteWidth);
			System.out.println("SiteSpacing:    "+this.siteSpacing);
			System.out.println("SiteOrient:    "+this.siteOrient);
			System.out.println("SiteSymmetry:    "+this.siteSymmetry);
			System.out.println("SiteRowOrigin:    "+this.siteRowOrigin);
			System.out.println("NumSites:    "+this.numSites);
			System.out.println("Overlap:    "+this.overlap);
			System.out.print("Cells in Row:  ");
		
			Iterator<String> it = this.cellList.iterator();
			while(it.hasNext()) {
				System.out.print(it.next()+" ");
			}
			System.out.println("");
		}
	}
	
	public class Boundaries {
		int minXBound, maxXBound, minYBound, maxYBound;
		
		public Boundaries() {
			this.minXBound = 0;
			this.maxXBound = 0;
			this.minYBound = 0;
			this.maxYBound = 0;
		}
		
		void copyBoundaries(Boundaries copyToBoundary)
		{
			copyToBoundary.minXBound=this.minXBound;
			copyToBoundary.maxXBound=this.maxXBound;
			copyToBoundary.minYBound=this.minYBound; 
			copyToBoundary.maxYBound=this.maxYBound;
		}
		void printBoundaries() {
			System.out.println("-------------------------------------------------------------");
			System.out.println("Boundaries Of Region: ");
			System.out.println("Bottom-Left Boundary: ( "+this.minXBound+", "+this.minYBound+" )");
			System.out.println("Bottom-Right Boundary: ( "+this.maxXBound+", "+this.minYBound+" )");
			System.out.println("Top-Right Boundary: ( "+this.maxXBound+", "+this.maxYBound+" )");
			System.out.println("Top-Left Boundary: ( "+this.minXBound+", "+this.maxYBound+" )");
		}
	}
	
	public NodeRowInfo() {
		this.node = new Node();
		this.row = new Row();
		this.boundaries = new Boundaries();
//		this.maxNumberOfCellsPerRow = 0;
		this.numberOfCells = 0;
		this.totalWidthOfCells = 0;
		this.nodeId = new HashMap<String, Node>();
//		this.rowWidth = 0;
		this.xLimit = 0;
//		this.xLimit2 = 0;
//		this.yLimit = 0;
		this.rowId = new HashMap<Integer, Row>();
		this.netToCell = new HashMap<Integer, ArrayList<String>>();
		this.numNodes = 0;
		this.numTerminals = 0;
		this.xCordinateAfterLastPlacedCell = 0;
		this.yCordinateAfterLastPlacedCell = 0;
	}
	
	public void printNodeRowInfo() {
		printNodeIdHashMap();
		printRowIdHashMap();		
		printNetToCellHashMap();
		this.boundaries.printBoundaries();
	}
	
	public void printNodeIdHashMap() {
		System.out.println("************************************************************************");
		System.out.println("For HashMap nodeId:");
		Iterator<Map.Entry<String, Node>> it1 = this.nodeId.entrySet().iterator();
		while(it1.hasNext()) {
			Entry<String, Node> next = it1.next();
			System.out.println("-------------------------------------------------------------");
			System.out.println("NodeId HashMap Key Value: "+next.getKey());
			next.getValue().printNodeParameters();
		}
	}
	
	public void printRowIdHashMap() {
		System.out.println("************************************************************************");
		System.out.println("For HashMap rowId:");
		Iterator<Map.Entry<Integer, Row>> it2 = this.rowId.entrySet().iterator();
		while(it2.hasNext()) {
			Entry<Integer, Row> next = it2.next();
			System.out.println("-------------------------------------------------------------");
			System.out.println("RowId HashMap Key Value: "+next.getKey());
			next.getValue().printRowParameters();
		}
	}
	
	public void printNetToCellHashMap() {
		System.out.println("************************************************************************");
		System.out.println("For HashMap netToCell:");
		Iterator<Map.Entry<Integer, ArrayList<String>>> it3 = this.netToCell.entrySet().iterator();
		while(it3.hasNext()) {
			Entry<Integer, ArrayList<String>> next = it3.next();
			System.out.println("NetToCell HashMap Key Value: "+next.getKey());
			System.out.println("-------------------------------------------------------------");
			Iterator<String> it_3 = next.getValue().iterator();
			while(it_3.hasNext()) {
				String next_3 = it_3.next(); 
				System.out.print(" "+next_3);
			}
			System.out.println("");
			System.out.println("-----------------------------------------------------");
		}
	}
	
	void updateCenterOfEachCell() {
		Iterator<Map.Entry<String, Node>> it = this.nodeId.entrySet().iterator();
		int xCenter = 0, yCenter = 0;
		while(it.hasNext()) {
			Entry<String, Node> next = it.next();
			xCenter = (next.getValue().xCordinate) + ((next.getValue().width)/2);
			yCenter = (next.getValue().yCordinate) + ((next.getValue().height)/2);
			Node newNode = new Node();
			newNode = next.getValue();
			next.setValue((newNode.setCenter(xCenter, yCenter)));
		}
	}
	
	void createRowToCellMap() {
		Iterator<Map.Entry<String, Node>> NodeIter = this.nodeId.entrySet().iterator();
		Iterator<Map.Entry<Integer, Row>> RowIter = this.rowId.entrySet().iterator();
		
		while(RowIter.hasNext()) {
			Entry<Integer, Row> RowNext = RowIter.next();
			while(NodeIter.hasNext()) {
				Entry<String, Node> NodeNext = NodeIter.next();
				if(NodeNext.getValue().height == 16) {
//					if( ((RowNext.getValue().cordinate) <= (NodeNext.getValue().yCordinate)) && 
//							(((RowNext.getValue().cordinate)+(RowNext.getValue().height)) >= (NodeNext.getValue().yCordinate)) ) {
					if((RowNext.getValue().cordinate) == (NodeNext.getValue().yCordinate)) {
						
						Node newNode = new Node();
						newNode = NodeNext.getValue();
						newNode.setRowId(RowNext.getKey().intValue());
						NodeNext.setValue(newNode);
						
						Row newRow = new Row();
						newRow = RowNext.getValue();
						ArrayList<String> newCellList = new ArrayList<String>();
						newCellList = newRow.cellList;
						newCellList.add(NodeNext.getKey().toString());
						newRow.setCellList(newCellList);
//						Iterator<String> it = newRow.cellList.iterator();
//						while(it.hasNext()) {
//							System.out.println("id = "+newRow.Id+" "+it.next()+" ");
//						}
						RowNext.setValue(newRow);
					}
				}
			}
			NodeIter = this.nodeId.entrySet().iterator();
		}
	}
	
	void calculateBoundariesOfEntireRegion() {
		int xValue = 0, yValue = 0;
		Iterator<Map.Entry<String, Node>> it = this.nodeId.entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Node> next = it.next();
			if(next.getValue().terminal == 1) {
				xValue = next.getValue().xCordinate;
				yValue = next.getValue().yCordinate;
				if(xValue < this.boundaries.minXBound) {
					this.boundaries.minXBound = xValue;
				}
				if(xValue > this.boundaries.maxXBound) {
					this.boundaries.maxXBound = xValue;
				}
				if(yValue < this.boundaries.minYBound) {
					this.boundaries.minYBound = yValue;
				}
				if(yValue > this.boundaries.maxYBound) {
					this.boundaries.maxYBound = yValue;
				}
			}
		}
	}
	
	int placeMacroBlocks() {
		int xVal = this.boundaries.minXBound, yVal = 0;
		this.xLimit = this.boundaries.minXBound;
		
		int rowHeight = 16; // This value is taken as constant since all 18 ibm files do not have any row height greater than this value
		
		Iterator<Map.Entry<String, Node>> iter = this.nodeId.entrySet().iterator();
		while(iter.hasNext()) {
			Entry<String, Node> next = iter.next();
			if((next.getValue().terminal == 0) && (next.getValue().height > rowHeight)) {
				Node newNode = new Node();
				newNode = next.getValue();
				
				if( (xVal + next.getValue().width) > this.xLimit) {
					this.xLimit = xVal + next.getValue().width +1;
				}
				
				if((yVal + next.getValue().height) < this.boundaries.maxYBound) {
					newNode.xCordinate = xVal;
					newNode.yCordinate = yVal;
					next.setValue(newNode);
				}
				else {
					xVal = this.xLimit;
					yVal = 0;
					newNode.xCordinate = xVal;
					newNode.yCordinate = yVal;
					next.setValue(newNode);
				}
				yVal = yVal + next.getValue().height;
			}
		}
//		this.xLimit2 = xVal;
//		this.yLimit = yVal;
		return this.xLimit;
	}
	
	void initialPlacement() {
		Iterator<Map.Entry<String, Node>> iterNode1 = this.nodeId.entrySet().iterator();
		Iterator<Map.Entry<Integer, Row>> iterRow = this.rowId.entrySet().iterator();
		
//		int totalWidthOfCells = 0, rowWidth1 = 0, rowWidth2 = 0, count = 0, xCord = this.xLimit, yCord = 0;
		int xCord = this.xLimit;
		while(iterNode1.hasNext()) {
			Entry<String, Node> next = iterNode1.next();
			
			if((next.getValue().terminal == 0) && (next.getValue().height == 16)) {
				this.totalWidthOfCells += next.getValue().width + 1;
				this.numberOfCells++;
			}
		}
		
//		int numOfRows = this.rowId.size();
		int rowWidth = (this.boundaries.maxXBound-1) - (this.xLimit);
//		this.maxNumberOfCellsPerRow = ((this.numberOfCells)/(numOfRows)) ;
		int occupiedRowWidth = 0;
		
		Iterator<Map.Entry<String, Node>> iterNode2 = this.nodeId.entrySet().iterator();
		Entry<Integer, Row> nextRow = iterRow.next();
		while(iterNode2.hasNext()) {
			Entry<String, Node> nextNode = iterNode2.next();
			if( iterRow.hasNext() && (nextNode.getValue().terminal == 0) 
					&& (nextNode.getValue().height <= nextRow.getValue().height)) {
				
				occupiedRowWidth += nextNode.getValue().width;
				
				if(occupiedRowWidth >= rowWidth) {
					occupiedRowWidth = 0;
					xCord = xLimit;
					nextRow = iterRow.next();
				}
				
				Node newNode = new Node();
				newNode = nextNode.getValue();
				newNode.yCordinate = nextRow.getValue().cordinate;
				newNode.xCordinate = xCord;
//				System.out.println(nextRow.getValue().Id);
				newNode.setRowId(nextRow.getValue().Id);
				nextNode.setValue(newNode);
				xCord += (newNode.width)+1;
//				count++;
			}
		}
		this.xCordinateAfterLastPlacedCell = xCord;
		this.yCordinateAfterLastPlacedCell = nextRow.getValue().cordinate;
	}
	
	public void forGraphicalRepresentation(String filename) throws IOException {
		
		BufferedWriter bw = null;
		FileWriter fw = null;
		fw = new FileWriter(filename);
		bw = new BufferedWriter(fw);
		Iterator<Map.Entry<String, Node>> iterNode2 = this.nodeId.entrySet().iterator();
		String content1 = this.boundaries.minXBound+" "+this.boundaries.maxXBound+" "+this.boundaries.minYBound+" "+this.boundaries.maxYBound+"\n";
		bw.write(content1);
		while(iterNode2.hasNext()) {
			Entry<String, Node> nextNode = iterNode2.next();
			String content = nextNode.getValue().nodeName+" "+nextNode.getValue().xCordinate+" "+nextNode.getValue().yCordinate+
					" "+nextNode.getValue().width+" "+nextNode.getValue().height+"\n";
			bw.write(content);
		}
		
		bw.close();
		fw.close();
	}
	
	public boolean checkIfPadOrMacroblock(String cell1, String cell2) {
		Node n1 = nodeId.get(cell1);
		Node n2 = nodeId.get(cell2);
		if(n1.terminal == 0 && n2.terminal == 0 && n1.height == 16 && n2.height == 16) {
			return true;
		}
		return false;
	}
	
	public boolean checkIfPadOrMacroblock(String cell) {
		Node n = nodeId.get(cell);
		if(n.terminal == 0 && n.height == 16) {
			return true;
		}
		return false;
	}
	
	public void swapCells(String cell1, String cell2) {
		Node n1 = new Node();
		Node n2 = new Node();
		n1 = this.nodeId.get(cell1);
		n2 = this.nodeId.get(cell2);
		Row r1 = new Row();
		Row r2 = new Row();
		r1 = this.rowId.get(n1.cellRowId);
		r2 = this.rowId.get(n2.cellRowId);
		int r1key = r1.Id;
		int r2key = r2.Id;
		
		System.out.println("Swapping cell "+cell1+" of row "+n1.cellRowId+" with cell "+cell2+" of row "+n2.cellRowId);
			
		Node nTemp = new Node();
		
		nTemp.xCordinate = n1.xCordinate;
		nTemp.yCordinate = n1.yCordinate;
		nTemp.xCenter = n1.xCenter;
		nTemp.yCenter = n1.yCenter;
		nTemp.cellRowId = n1.cellRowId;
		
		n1.xCordinate = n2.xCordinate;
		n1.yCordinate = n2.yCordinate;
		n1.xCenter = n2.xCenter;
		n1.yCenter = n2.yCenter;
		n1.cellRowId = n2.cellRowId;
		
		n2.xCordinate = nTemp.xCordinate;
		n2.yCordinate = nTemp.yCordinate;
		n2.xCenter = nTemp.xCenter;
		n2.yCenter = nTemp.yCenter;
		n2.cellRowId = nTemp.cellRowId;
		
		this.nodeId.put(cell1, n1);
		this.nodeId.put(cell2, n2);
		
		String s1 = r1.removeCellListElement(cell1);
		String s2 = r2.removeCellListElement(cell2);
		r1.setCellListElement(s2);
		r2.setCellListElement(s1);
		
		this.rowId.put(r1key, r1);
		this.rowId.put(r2key, r2);
		
		sortCellListAccordingToX();
	}
	
	public void moveCell(String cell) {
		
		int min = 1, max = rowId.size();
		int randomRowSelected = (int) (Math.random() * (max-min) + min);
		min = this.xLimit;
		max = this.boundaries.maxXBound - 1;
		int randomXcordinateSelected = (int)(Math.random() * (max - min) + min);
		
		Node moveNode = new Node();
		moveNode = this.nodeId.get(cell);
		int prevRow = moveNode.cellRowId;
		int prevX = moveNode.xCordinate;
//		int cellWidth = moveNode.width;
		
		while(randomXcordinateSelected+moveNode.width > this.boundaries.maxXBound-1) {
			randomXcordinateSelected = (int)(Math.random() * (max - min) + min);
		}
				
		Row destinationRow = new Row();
		destinationRow = rowId.get(randomRowSelected);
		
		ArrayList<String> cellsInRow = destinationRow.cellList;
		Iterator<String> cellIt = cellsInRow.iterator();
		
		int i = randomXcordinateSelected;
		int d = moveNode.width;
		
		Map<String, Integer> forUpdatingCellListAfterLoop = new HashMap<String, Integer>();
		while(cellIt.hasNext()) {
			String c = cellIt.next();
			if(c.equals(cell)) {
				continue;
			}
			Node n = new Node();
			n = this.nodeId.get(c);
			int x = 0;
			
			if((n.xCordinate < i) && (n.xCordinate+n.width < i)) {
				continue;
			}
			else if((n.xCordinate < i) && (n.xCordinate+n.width > i)) {
				x = i + d + 1;
				i = x;
				d = n.width;
				System.out.println("Displacing cell "+c+ " of row "+n.cellRowId+" and x-cordinate "+n.xCordinate+" to x-cordinate "+x);
			}
			else if((n.xCordinate > i) && (n.xCordinate < i+d)) {
				int diff = (i+d)-n.xCordinate;
				x = n.xCordinate + diff + 1;
				i = x;
				d = n.width;
				System.out.println("Displacing cell "+c+ " of row "+n.cellRowId+" and x-cordinate "+n.xCordinate+" to x-cordinate "+x);
			}
			else if((n.xCordinate >= i) && (n.xCordinate+n.width < i+d)){
				x = i + d + 1;
				i = x;
				d = n.width;
				System.out.println("Displacing cell "+c+ " of row "+n.cellRowId+" and x-cordinate "+n.xCordinate+" to x-cordinate "+x);
			}
			else if((n.xCordinate > i) && (n.xCordinate >= i+d)) {
				break;
			}
			
			if((x+d > this.boundaries.maxXBound-1) || (x > this.boundaries.maxXBound-1)) {
				x = this.xCordinateAfterLastPlacedCell;
				int y = this.yCordinateAfterLastPlacedCell;
				
				if(this.xCordinateAfterLastPlacedCell+d <= this.boundaries.maxXBound-1) {
					this.xCordinateAfterLastPlacedCell += d;
					n.xCordinate = x;
					n.yCordinate = y;
					int newRowId = getRowIdFromY(n.yCordinate);
					String updNode = n.nodeName;
					forUpdatingCellListAfterLoop.put(updNode, newRowId);
//					updateCellListAndRowCellIdEntry(updNode, newRowId);
					this.nodeId.put(c, n);
				}
				else {
					this.xCordinateAfterLastPlacedCell = this.xLimit;
					if(this.yCordinateAfterLastPlacedCell < this.boundaries.maxYBound-1) {
						this.yCordinateAfterLastPlacedCell += 16;
						n.xCordinate = this.xCordinateAfterLastPlacedCell;
						n.yCordinate = this.yCordinateAfterLastPlacedCell;
						int newRowId = getRowIdFromY(n.yCordinate);
						String updNode = n.nodeName;
						forUpdatingCellListAfterLoop.put(updNode, newRowId);
//						updateCellListAndRowCellIdEntry(updNode, newRowId);
						this.nodeId.put(c, n);
					}
					else {
						searchForEmptySpace(c, d);
					}
				}
			}
			else if((x+d <= this.boundaries.maxXBound-1)) {
				n.xCordinate = x;
				this.nodeId.put(c, n);
			}
		}
		
		for(Map.Entry<String, Integer> it: forUpdatingCellListAfterLoop.entrySet()) {
			updateCellListAndRowCellIdEntry(it.getKey().toString(), it.getValue().intValue());
		}
		
//		int destRowId = destinationRow.Id;
		
		moveNode.yCordinate = destinationRow.cordinate;
		moveNode.xCordinate = randomXcordinateSelected;
//		moveNode.cellRowId = destinationRow.Id;
		this.nodeId.put(cell, moveNode);
		
		updateCellListAndRowCellIdEntry(cell, destinationRow.Id);
		
//		ArrayList<String> c1 = destinationRow.cellList;
//		
//		Row r = this.rowId.get(prevRow);
//		r.removeCellListElement(cell);
//		this.rowId.put(prevRow, r);
//		
//		destinationRow.setCellListElement(cell);
//		this.rowId.put(destinationRow.Id, destinationRow);
		
		System.out.println("Moving "+cell+" from row "+prevRow+" at position "+prevX+" to row "+destinationRow.Id+" at position "+randomXcordinateSelected);
		sortCellListAccordingToX();
//		this.printNodeRowInfo();		
	}
	
	void moveCell2(String cell) {
		int min = 1, max = rowId.size();
		int randomRowSelected = (int) (Math.random() * (max-min) + min);
		min = this.xLimit;
		max = this.boundaries.maxXBound - 1;
		int randomXcordinateSelected = (int)(Math.random() * (max - min) + min);
		
		Node moveNode = new Node();
		moveNode = this.nodeId.get(cell);
		int prevRow = moveNode.cellRowId;
		int prevX = moveNode.xCordinate;
//		int cellWidth = moveNode.width;
		
		while(randomXcordinateSelected+moveNode.width > this.boundaries.maxXBound-1) {
			randomXcordinateSelected = (int)(Math.random() * (max - min) + min);
		}
				
		Row destinationRow = new Row();
		destinationRow = rowId.get(randomRowSelected);
		
		moveNode.yCordinate = destinationRow.cordinate;
		moveNode.xCordinate = randomXcordinateSelected;
//		moveNode.cellRowId = destinationRow.Id;
		this.nodeId.put(cell, moveNode);
		
		updateCellListAndRowCellIdEntry(cell, destinationRow.Id);
		
//		ArrayList<String> c1 = destinationRow.cellList;
//		
//		Row r = this.rowId.get(prevRow);
//		r.removeCellListElement(cell);
//		this.rowId.put(prevRow, r);
//		
//		destinationRow.setCellListElement(cell);
//		this.rowId.put(destinationRow.Id, destinationRow);
		
		System.out.println("Moving "+cell+" from row "+prevRow+" at x-coordinate "+prevX+" to row "+destinationRow.Id+" at x-coordinate "+randomXcordinateSelected);
		sortCellListAccordingToX();
	}
	
	void sortCellListAccordingToX() {		
		for(Map.Entry<Integer, Row> it : this.rowId.entrySet()) {
			ArrayList<String> sortedList = it.getValue().sortByX();
			int rowid = it.getValue().Id;
			Row r = it.getValue();
			r.setCellList(sortedList);
			this.rowId.put(rowid, r);
		}
	}
	
	void searchForEmptySpace(String cell, int width) {
		Iterator<Map.Entry<Integer, Row>> rowIter = this.rowId.entrySet().iterator();
		String c1 = null, c2 = null;
		sortCellListAccordingToX();
		Map<String, Integer> forUpdatingCellListAfterLoop = new HashMap<String, Integer>();
		while(rowIter.hasNext()) {
			int flag = 0;
			Entry<Integer, Row> nextRow = rowIter.next();
			ArrayList<String> cL =nextRow.getValue().cellList;
			Iterator<String> it = cL.iterator();
			while(it.hasNext()) {
				c1 = it.next();
				if(flag == 0) {
					c2 = it.next();
					flag = 1;
				}
				Node n1 = this.nodeId.get(c1);
				Node n2 = this.nodeId.get(c2);
				int lower = n1.xCordinate + n1.width;
				int upper = n2.xCordinate;
				if(upper-lower >= width) {
					Node n = this.nodeId.get(cell);
					n.xCordinate = lower+1;
					n.yCordinate = nextRow.getValue().cordinate;
					forUpdatingCellListAfterLoop.put(cell, n.yCordinate);
					this.nodeId.put(cell, n);
				}
				c2 = c1;
			}
		}
		for(Map.Entry<String, Integer> it: forUpdatingCellListAfterLoop.entrySet()) {
			updateCellListAndRowCellIdEntry(it.getKey().toString(), it.getValue().intValue());
		}
	}
	
	int getRowIdFromY(int y) {
		int row = 0;
		for(Map.Entry<Integer, Row> it: this.rowId.entrySet()) {
			if(it.getValue().cordinate == y) {
				row = it.getKey().intValue();
			}
		}
		return row;
	}
	
	void updateCellListAndRowCellIdEntry(String addRemElem, int addRowTo) {
		Node n = this.nodeId.get(addRemElem);
		int oldRow = n.cellRowId;
		n.cellRowId = addRowTo;
		this.nodeId.put(addRemElem, n);
		
		Row rRem = this.rowId.get(oldRow);
		rRem.removeCellListElement(addRemElem);
		this.rowId.put(oldRow, rRem);
		
		Row rAdd = this.rowId.get(addRowTo);
		rAdd.setCellListElement(addRemElem);
		this.rowId.put(addRowTo, rAdd);
		
//		sortCellListAccordingToX();
	}
	
//	void test() {
//		Row r1 = this.rowId.get(1);
//		r1.removeCellListElement("a2");
//		r1.removeCellListElement("a5");
//		r1.setCellListElement("a5");
//		r1.setCellListElement("a2");
//		this.rowId.put(1, r1);
//	}
	
	int wireLengthCalc() {
		int wireLength = 0;
		int xCord = 0, yCord = 0;
		
		for(Map.Entry<Integer, ArrayList<String>> netIter : this.netToCell.entrySet()) {
			int minXBound = 500000, minYBound = 500000, maxXBound = -500000, maxYBound = -500000;
			ArrayList<String> cellList = netIter.getValue();
			Iterator<String> cellListIter = cellList.iterator();
			while(cellListIter.hasNext()) {
				String cell = cellListIter.next();
				xCord = this.nodeId.get(cell).xCenter;
				yCord = this.nodeId.get(cell).yCenter;
				
				if(xCord < minXBound) {
					minXBound = xCord;
				}
				if(xCord > maxXBound) {
					maxXBound = xCord;
				}
				if(yCord < minYBound) {
					minYBound = yCord;
				}
				if(yCord > maxYBound) {
					maxYBound = yCord;
				}
			}
			
			wireLength += (Math.abs(maxXBound - minXBound)) + (Math.abs(maxYBound - minYBound));
		}
		
		return wireLength;
	}
	
	int overlapAreaCalc() {
		int totalOverlapArea = 0;
		sortCellListAccordingToX();
		String c1 = null, c2 = null;
		int xCord1 = 0, xCord2 = 0, xWidth1 = 0, xWidth2 = 0;
		for(Map.Entry<Integer, Row> rowIter : this.rowId.entrySet()) {
			int rowOverlapArea = 0, flag = 0;
			ArrayList<String> rowCellList = rowIter.getValue().cellList;
			Iterator<String> rowCellListIter = rowCellList.iterator();
			while(rowCellListIter.hasNext()) {
				if(flag == 0) {
					c1 = rowCellListIter.next();
					if(rowCellListIter.hasNext()) {
						c2 = rowCellListIter.next();
					}
					else {
						break;
					}
					flag = 1;
				}
				else if (flag == 1 || flag == 2) {
					c1 = c2;
					if(rowCellListIter.hasNext()) {
						c2 = rowCellListIter.next();
					}
					else {
						break;
					}
				}
				else if (flag == 3) {
					if(rowCellListIter.hasNext()) {
						c2 = rowCellListIter.next();
					}
					else {
						break;
					}
					flag = 1;
				}
				xCord1 = this.nodeId.get(c1).xCordinate;
				xWidth1 = this.nodeId.get(c1).width;
				xCord2 = this.nodeId.get(c2).xCordinate;
				xWidth2 = this.nodeId.get(c2).width;
				
				if((xCord1+xWidth1 >= xCord2) && ( xCord2+xWidth2 >= xCord1+xWidth1)) {
					rowOverlapArea += (((xCord1+xWidth1) - xCord2)*16);
					flag = 2;
				}
				else if((xCord2 >= xCord1) && (xCord2+xWidth2 < xCord1+xWidth1)) {
					rowOverlapArea += ((xWidth2)*16);
					flag = 3;
				}
			}
			totalOverlapArea += rowOverlapArea;
		}
		return totalOverlapArea;
	}
	
	float unevenRowsCalc() {		
		float desiredLengthOfEachRow = (this.totalWidthOfCells)/(this.rowId.size());
		float totalDeviationFromDesiredLength = 0;
		for(Map.Entry<Integer, Row> rowIter : this.rowId.entrySet()) {
			float perRowDeviationFromDesiredLength = 0, perRowUsedLength = 0;
			ArrayList<String> cellList = rowIter.getValue().cellList;
			Iterator<String> cellListIter = cellList.iterator();
			while(cellListIter.hasNext()) {
				String cell = cellListIter.next();
				perRowUsedLength += this.nodeId.get(cell).width + 1;
			}
			perRowDeviationFromDesiredLength = Math.abs(desiredLengthOfEachRow - perRowUsedLength);
			totalDeviationFromDesiredLength += perRowDeviationFromDesiredLength;
		}
		return totalDeviationFromDesiredLength;
	}
	
	void copyMaps(NodeRowInfo newN) {
		Map<Integer, Row> newRowId = new HashMap<Integer, Row>();
		Map<Integer, ArrayList<String>> newNetToCell = new HashMap<Integer, ArrayList<String>>();
		Map<String, Node> netNodeId = new HashMap<String, Node>();
		
		for(Map.Entry<Integer, Row> it : this.rowId.entrySet()) {
			newRowId.put(it.getKey().intValue(), it.getValue());
		}
		for(Map.Entry<Integer, Row> it : this.rowId.entrySet()) {
			newRowId.put(it.getKey().intValue(), it.getValue());
		}
		
		newNetToCell.putAll(this.netToCell);
		netNodeId.putAll(this.nodeId);
	}
	
	Node node;
	Row row;
	Boundaries boundaries;
	int numberOfCells;
	int totalWidthOfCells;	
	Map<String, Node> nodeId;
	int xLimit;
	Map<Integer, Row> rowId;
	Map<Integer, ArrayList<String>> netToCell;
	int numNodes;
	int numTerminals;
	int xCordinateAfterLastPlacedCell;
	int yCordinateAfterLastPlacedCell;
}
