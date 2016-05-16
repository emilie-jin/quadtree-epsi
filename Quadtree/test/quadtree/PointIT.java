/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quadtree;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Yuzhu & Adrien
 */
public class PointIT {
    
    @Test
    public void canGetXOfThePoint(){
        Point point = new Point(2,3);
        
        assertEquals(3, point.getY(),0);
    }
    
    @Test
    public void canGetYOfThePoint(){
        Point point = new Point(4.0,5.0);
        assertEquals(5.0, point.getY(),0);
    }
}
