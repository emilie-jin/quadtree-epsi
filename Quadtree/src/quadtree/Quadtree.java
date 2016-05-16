/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.util.Scanner;

/**
 *
 * @author Yuzhu & Adrien
 */
public class Quadtree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int numberOfThePointsToDraw;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How many points you want to draw? (numbers)");
        numberOfThePointsToDraw = Integer.parseInt(scanner.nextLine());
        
        System.out.println("___________________________BEGIN__________________________");
        System.out.println("Creating an  100x100 canvas containing " + numberOfThePointsToDraw + " points");
        Point newPointInitial = new Point(0,0);
        Canvas canvas = new Canvas(newPointInitial,100,100);
        
        
        for (int i=0; i<numberOfThePointsToDraw; i++) {
            int limitLow = 0;
            int limitHeigher = 100;

            double randomX = (double)(Math.random() * (limitHeigher-limitLow)) + limitLow;
            double randomY = (double)(Math.random() * (limitHeigher-limitLow)) + limitLow;
            
            Point randomPoint = new Point(randomX,randomY);
            canvas.addPointToCanvas(randomPoint);
            
            System.out.println("Your point " +randomPoint.toString() + "is created.");
        }
        
        canvas.drawTree();
        
        for(Point pointToShow:canvas.getAllPoints()){
            System.out.println("The depth of the point ("+pointToShow.getX()+";"+pointToShow.getY()+") is "+canvas.getDepthOfPoint(pointToShow));
            System.out.println("The neighbor of the point ("+pointToShow.getX()+";"+pointToShow.getY()+") are "+canvas.pointInTheSameCanvas(pointToShow));  
        }
        
        System.out.println("Max depth of your tree is : "+ canvas.getCanvasDepth());
        System.out.println("___________________________END__________________________");
        
    }
    
}
