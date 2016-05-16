package quadtree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yuzhu & Adrien
 */
public class CanvasIT {

    private static final int MAX_POINT = 4;

    @Test
    public void canGetTheInitialPoint() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        assertEquals(canvas.getInitialPoint(), point);

    }

    @Test
    public void canGetNewPointInitial() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        canvas.dividedInto4Part();
        Point pointInitialNortheast = new Point(50, 50);
        assertEquals(pointInitialNortheast.getX(), canvas.getNortheastCanvas().getInitialPoint().getX(), 0);
        assertEquals(pointInitialNortheast.getY(), canvas.getNortheastCanvas().getInitialPoint().getY(), 0);
    }

    @Test
    public void findRightPointPositionAtNortheast() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        Point pointToFindPosition = new Point(61, 78);

        String zone = canvas.findPointPosition(pointToFindPosition);

        assertEquals(zone, "NE");
    }

    @Test
    public void findRightPointPositionAtNorthwest() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        Point pointToFindPosition = new Point(31, 78);

        String zone = canvas.findPointPosition(pointToFindPosition);

        assertEquals(zone, "NW");
    }

    @Test
    public void findRightPointPositionAtSoutheast() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        Point pointToFindPosition = new Point(61, 48);

        String zone = canvas.findPointPosition(pointToFindPosition);

        assertEquals(zone, "SE");
    }

    @Test
    public void findRightPointPositionAtSouthwest() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        Point pointToFindPosition = new Point(31, 48);

        String zone = canvas.findPointPosition(pointToFindPosition);

        assertEquals(zone, "SW");
    }

    @Test
    public void shouldAddIntoListPoints() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        Point pointToAddToList = new Point(31, 48);
        canvas.addPointToCanvas(pointToAddToList);
        assertTrue(canvas.getAllPoints().contains(pointToAddToList));
    }

    @Test
    public void shouldPutIntoSouthwestCanvas() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        Point pointToAddToList = new Point(31, 48);
        canvas.dividedInto4Part();
        canvas.putPointInChildrenCanvas(pointToAddToList);
        Canvas canvasSouthwest = canvas.getSouthwestCanvas();
        assertTrue(canvasSouthwest.getAllPoints().contains(pointToAddToList));
    }

    @Test
    public void shouldInTheSameCanvas() {
        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        Point point1 = new Point(31, 48);
        Point point2 = new Point(42, 45);
        Point point3 = new Point(61, 68);
        Point point4 = new Point(72, 45);
        Point point5 = new Point(72, 85);
        canvas.addPointToCanvas(point1);
        canvas.addPointToCanvas(point2);
        canvas.addPointToCanvas(point3);
        canvas.addPointToCanvas(point4);
        canvas.addPointToCanvas(point5);
        canvas.drawTree();
        assertTrue(canvas.pointInTheSameCanvas(point3).contains(point5));
        assertTrue(!canvas.pointInTheSameCanvas(point3).contains(point1));

    }

    @Test
    public void CanvasShouldDepthAt1() {

        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);

        for (int i = 0; i < 7; i++) {
            int limitLow = 0;
            int limitHeigher = 100;

            double randomX = (double) (Math.random() * (limitHeigher - limitLow)) + limitLow;
            double randomY = (double) (Math.random() * (limitHeigher - limitLow)) + limitLow;

            Point randomPoint = new Point(randomX, randomY);
            canvas.addPointToCanvas(randomPoint);
        }
        canvas.drawTree();
        assertEquals(canvas.getCanvasDepth(), 1);
    }
    
    @Test
    public void shouldGetDepthOfPoint() {

        Point point = new Point(0, 0);
        Canvas canvas = new Canvas(point, 100, 100);
        canvas.addPointToCanvas(new Point(56,32));
        canvas.addPointToCanvas(new Point(11,72));
        canvas.addPointToCanvas(new Point(14,66));
        canvas.addPointToCanvas(new Point(61,73));
        canvas.addPointToCanvas(new Point(64,95));
        canvas.addPointToCanvas(new Point(62,10));
        canvas.addPointToCanvas(new Point(7,93));
        canvas.addPointToCanvas(new Point(15,26));
        
        canvas.drawTree();
        
        assertEquals(canvas.getDepthOfPoint(new Point(6,7)),1);
    }

}
