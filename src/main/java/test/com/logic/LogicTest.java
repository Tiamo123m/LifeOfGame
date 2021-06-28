package test.com.logic; 

import com.logic.Logic;
import com.map.Map;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** 
* Logic Tester. 
* 
* @author <Authors name> 
* @since <pre>6ÔÂ 27, 2021</pre> 
* @version 1.0 
*/ 
public class LogicTest {
    Logic logic = new Logic();
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
* Method: InitMap(Map map) 
* 
*/ 
@Test
public void testInitMap() {
    logic.InitMap(map);
    assertEquals(1,map.getNowGeneration());
    for (int i = 1; i <= 5; i++) {
        for (int j = 1; j <= 5; j++) {
            assertEquals(0, map.getGridStatus()[i][j]);
        }
    }
} 

/** 
* 
* Method: ClearMap(Map map) 
* 
*/ 
@Test
public void testClearMap() {
    logic.ClearMap(map);

    for (int i = 1; i <= 5; i++) {
        for (int j = 1; j <= 5; j++) {
            assertEquals(0, map.getGridStatus()[i][j]);
        }
    }

} 

/** 
* 
* Method: RandomMap(Map map) 
* 
*/ 
@Test
public void testRandomMap() {

    int[][] blankGrid =
            {
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0}
            };
    map.setGridStatus(blankGrid);
    logic.RandomMap(map);
    int number = 0;
    for (int i = 1; i < 5; i++) {
        for (int j = 1; j < 5; j++) {
            number = map.getGridStatus()[i][j];
        }
    }
    Assertions.assertTrue(number > 0 && number < 25);

} 

/** 
* 
* Method: NextMap(Map map) 
* 
*/ 
@Test
public void testNextMap() {
    //map.setGridStatus(GridTest);
    int[][] nextGrid = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };
    logic.NextMap(map);
    for (int i = 1; i <= 5; i++) {
        for (int j = 1; j <= 5; j++) {
            assertEquals(nextGrid[i][j], map.getGridStatus()[i][j]);
        }
    }

    }
} 



