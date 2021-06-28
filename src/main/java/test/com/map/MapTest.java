package test.com.map; 

import com.map.Map;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* Map Tester. 
* 
* @author <Authors name> 
* @since <pre>6月 27, 2021</pre> 
* @version 1.0 
*/ 
public class MapTest {
    Map map = new Map(5,5);
    int[][] GridTest = new int[7][7];

@Before
public void before() {
    GridTest = new int[][]{
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };
    map.setGridStatus(GridTest);
} 

@After
public void after() {
} 


/**
*
* Method: getNeighborCount(int row, int col)
*
*/
@Test
public void testGetNeighborCount() {
    //Case 1: 顶点测试
    assertEquals(3, map.getNeighborCount(1, 1));
    //Case 2: 边界测试
    assertEquals(2, map.getNeighborCount(1, 3));
    //Case 3: 中间值测试
    assertEquals(2, map.getNeighborCount(3, 3));
}
} 


