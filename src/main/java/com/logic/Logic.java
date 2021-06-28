package com.logic;
import com.map.Map;

public class Logic {
    //初始化地图
    public void InitMap(Map map){
        // 代数置1
        map.setNowGeneration(1);
        // 创建多维数组
        int[][] grid = new int[map.getNRow() + 2][map.getNCol() + 2];
        // 所有细胞开局全死亡
        for (int i = 0; i <= map.getNRow() + 1; i++)
        {
            for (int j = 0; j <= map.getNCol() + 1; j++)
            {
                grid[i][j] = 0;
            }
        }
        map.setGridStatus(grid);
    }

    //清空地图
    public void ClearMap(Map map) {
        int[][] grid = map.getGridStatus();
        // 所有细胞状态为0：死细胞
        for (int row = 1; row <= map.getNRow(); row++)
            for (int col = 1; col <= map.getNCol(); col++)
                grid[row][col] = 0;
           map.setGridStatus(grid);
    }

    //随机初始化地图
    public void RandomMap(Map map){
        int[][] grid = map.getGridStatus();
        for (int row = 1; row <= map.getNRow(); row++)
        {
            for (int col = 1; col <= map.getNCol(); col++)
            {
                grid[row][col] = Math.random() > 0.5? 1 : 0;
            }
        }
        map.setGridStatus(grid);
    }

    //繁衍后的地图
    public void NextMap(Map map){
        int generation = map.getNowGeneration();
        int[][] grid = map.getGridStatus();
        int[][] NewGrid = new int[map.getNRow() + 2][map.getNCol() + 2];
        // 遍历每一个元素，每一个元素对应一个网格细胞按钮
        for (int row = 1; row <= map.getNRow(); row++){

            for (int col = 1; col <= map.getNCol(); col++)
            {
                // 根据邻居数量，判断细胞存亡
                switch (map.getNeighborCount(row, col)) {
                    case 2 ->
                            // 邻居数量为2，细胞状态保持不变
                            NewGrid[row][col] = grid[row][col];
                    case 3 ->
                            // 邻居数量为3，死细胞变为活细胞
                            NewGrid[row][col] = 1;
                    default ->
                            // 其它情况一律为死细胞
                            NewGrid[row][col] = 0;
                }
            }
        }
        // 给grid[][]多维数组赋值为新的细胞状态
        for (int i = 1; i <= map.getNRow(); i++)
        {
            if (map.getNCol() >= 0) {
                System.arraycopy(NewGrid[i], 1, grid[i], 1, map.getNCol());
            }
        }
        // 代数自增1
        generation ++;
        map.setNowGeneration(generation);

        map.setGridStatus(grid);
    }
}
