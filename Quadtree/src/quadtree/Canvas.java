/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yuzhu & Adrien
 */
class Canvas {

    private Point initialPoint;
    private double width;
    private double height;
    private static final int MAX_POINT = 4;
    private ArrayList<Point> allPoints = new ArrayList<>();

    private Canvas northwestCanvas = null;
    private Canvas northeastCanvas = null;
    private Canvas southwestCanvas = null;
    private Canvas southeastCanvas = null;

    public Canvas() {
    }

    public Canvas(Point InitialPoint, double width, double hight) {
        this.initialPoint = InitialPoint;
        this.width = width;
        this.height = hight;
        this.allPoints = new ArrayList<Point>();

    }

    public Point getInitialPoint() {
        return initialPoint;
    }

    public void setInitialPoint(Point InitialPoint) {
        this.initialPoint = InitialPoint;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHight() {
        return height;
    }

    public List<Point> getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(ArrayList<Point>  allPoints) {
        this.allPoints = allPoints;
    }

    public void removeAllPoints() {
        this.allPoints.clear();
    }

    public Canvas getNorthwestCanvas() {
        return northwestCanvas;
    }

    public void setNorthwestCanvas(Canvas northwestCanvas) {
        this.northwestCanvas = northwestCanvas;
    }

    public Canvas getNortheastCanvas() {
        return northeastCanvas;
    }

    public void setNortheastCanvas(Canvas northeastCanvas) {
        this.northeastCanvas = northeastCanvas;
    }

    public Canvas getSouthwestCanvas() {
        return southwestCanvas;
    }

    public void setSouthwestCanvas(Canvas southwestCanvas) {
        this.southwestCanvas = southwestCanvas;
    }

    public Canvas getSoutheastCanvas() {
        return southeastCanvas;
    }

    public void setSoutheastCanvas(Canvas southeastCanvas) {
        this.southeastCanvas = southeastCanvas;
    }

    public void dividedInto4Part() {
        if (northwestCanvas == null) {
            Point northwestInitialPoint = new Point(initialPoint.getX(), (initialPoint.getY() + this.height) / 2);
            northwestCanvas = new Canvas(northwestInitialPoint, this.width / 2, this.height / 2);
        }
        if (northeastCanvas == null) {
            Point northeastInitialPoint = new Point((initialPoint.getX() + this.width) / 2, (initialPoint.getY() + this.height) / 2);
            northeastCanvas = new Canvas(northeastInitialPoint, this.width / 2, this.height / 2);
        }
        if (southwestCanvas == null) {
            Point southwestInitialPoint = initialPoint;
            southwestCanvas = new Canvas(southwestInitialPoint, this.width / 2, this.height / 2);
        }
        if (southeastCanvas == null) {
            Point southeastInitialPoint = new Point((initialPoint.getX() + this.width) / 2, initialPoint.getY());
            southeastCanvas = new Canvas(southeastInitialPoint, this.width / 2, this.height / 2);
        }

    }

    public void addPointToCanvas(Point newPoint) {

        if (!allPoints.contains(newPoint)) {
            allPoints.add(newPoint);
        }

    }

    public String findPointPosition(Point point) {

        String position;
        if (point.getX() < ((initialPoint.getX() + this.width) / 2)) {
            if (point.getY() < (initialPoint.getY() + this.height) / 2) {
                position = "SW";
            } else {
                position = "NW";
            }
        } else {
            if (point.getY() < (initialPoint.getY() + this.height) / 2) {
                position = "SE";
            } else {
                position = "NE";
            }
        }
        return position;


    }

    public void putPointInChildrenCanvas(Point point) {
        String zone = findPointPosition(point);
        switch (zone) {
            case "NW":
                northwestCanvas.addPointToCanvas(point);
                break;
            case "NE":
                northeastCanvas.addPointToCanvas(point);
                break;
            case "SW":
                southwestCanvas.addPointToCanvas(point);
                break;
            case "SE":
                southeastCanvas.addPointToCanvas(point);
                break;
            default:
                break;
        }

    }

    public void drawTree() {
        if (allPoints.size() > MAX_POINT) {
            dividedInto4Part();
            
            for (Point pointToAdd : allPoints) {
                putPointInChildrenCanvas(pointToAdd);
            }

            //removeAllPoints();
            drawChildrenTree();
        }

    }

    public void drawChildrenTree() {
        if (northeastCanvas != null) {
            northeastCanvas.drawTree();
        }
        if (northwestCanvas != null) {
            northwestCanvas.drawTree();
        }
        if (southeastCanvas != null) {
            southeastCanvas.drawTree();
        }
        if (southwestCanvas != null) {
            southwestCanvas.drawTree();
        }

    }

    public int getCanvasDepth() {
        int depth;
        if (this.northeastCanvas != null) {
            depth = this.northwestCanvas.getCanvasDepth();
            depth = Math.max(depth, this.northeastCanvas.getCanvasDepth());
            depth = Math.max(depth, this.southeastCanvas.getCanvasDepth());
            depth = Math.max(depth, this.southwestCanvas.getCanvasDepth());

            depth++;
        } else {
            depth = 0;
        }
        return depth;
    }

    public int getDepthOfPoint(Point pointWanted) {

        if (northeastCanvas != null && northwestCanvas != null && southeastCanvas != null && southwestCanvas != null) {

            int depth = -1;

            switch (findPointPosition(pointWanted)) {
                case "NW":
                    depth = northwestCanvas.getDepthOfPoint(pointWanted);
                    break;
                case "NE":
                    depth = northeastCanvas.getDepthOfPoint(pointWanted);
                    break;
                case "SW":
                    depth = southwestCanvas.getDepthOfPoint(pointWanted);
                    break;
                case "SE":
                    depth = southeastCanvas.getDepthOfPoint(pointWanted);
                    break;
                default:
                    break;
            }

            return depth++;

        } else {

            return 1;
        }
    }

    public List<Point> pointInTheSameCanvas(Point point) {

        String pointPosition = findPointPosition(point);
        if (northeastCanvas != null && northwestCanvas != null && southeastCanvas != null && southwestCanvas != null) {
            switch (pointPosition) {
                case "NW":
                    return this.northwestCanvas.pointInTheSameCanvas(point);
                case "NE":
                    return this.northeastCanvas.pointInTheSameCanvas(point);
                case "SW":
                    return this.southwestCanvas.pointInTheSameCanvas(point);
                case "SE":
                    return this.southeastCanvas.pointInTheSameCanvas(point);
                default:
                    return null;
            }
        } else {
            return allPoints;
        }

    }

}
