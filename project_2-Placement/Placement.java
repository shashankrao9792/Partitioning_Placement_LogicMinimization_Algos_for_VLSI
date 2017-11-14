import java.io.*;
import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.paint.Color;
import javafx.stage.Stage; 
import javafx.scene.shape.Rectangle;

public class Placement extends Application {
		static String filename="";
	   @Override 
	   public void start(Stage stage) { 
		   try {
			   	Group root = new Group();
		        Scene scene = new Scene(root, 1360, 720, Color.WHITE);
		       
		        FileReader fr=new FileReader(filename);    
		        BufferedReader br=new BufferedReader(fr);    
		        String line;
		        int i=0,xMin=0,yMin=0;
		        int xMax,yMax,scaleX=1, scaleY=1;
		        while((line = br.readLine())!=null){
		        	i++;
		        	String [] words=line.split("\\s+");
		        	if(i==1) {
		        	
		        	xMax=Integer.parseInt(words[1]);
		        	yMax=Integer.parseInt(words[3]);
		        	while(xMax >1360 || yMax>720) {
		        		xMax=xMax/scaleX;
		        		yMax=yMax/scaleY;
		        		scaleX++;
		        		scaleY++;
		        	}
		        			
		        	}
		        	else if(i>1) {
		        	System.out.println(line);
		        	int x=  (Integer.parseInt(words[1])/scaleX)*2+50;
		        	int y=  (Integer.parseInt(words[2])/scaleY)+50;
		        	double w= (Integer.parseInt(words[3])/scaleX)*2;
		        	double h= (Integer.parseInt(words[4])/scaleX);
		        	Rectangle rectangle = new Rectangle(x,y,w,h); 
		        	rectangle.setFill(Color.WHITE);
		        	rectangle.setStroke(Color.CADETBLUE);
		        
		        	 root.getChildren().add(rectangle); 
		        	
		        	}
		        } 
		       
		        br.close();    
		        fr.close();  
			     
		        stage.setScene(scene);
		        stage.show();
		   }catch (Exception e) {
			e.printStackTrace();
		}

   	}      
		   	   
	public static void main(String[] args) {
		filename=args[0];
	    launch(args);
 
		}
		
	}
