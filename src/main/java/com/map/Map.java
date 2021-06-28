package com.map;

public class Map {
    private int nRow;                    //行数
    private int nCol;                    //列数
    private int nowGeneration;           // 当前代数
    private int[][] grid;                //网格按钮表示细胞状态

    //构造方法
    public Map(int nRow, int nCol) {
        this.nRow = nRow;
        this.nCol = nCol;
    }

    //设置地图行数
    public void setNRow(int nRow)
    {
        this.nRow=nRow;
    }

    //获得地图行数
    public int getNRow()
    {
        return nRow;
    }

    //设置地图列数
    public void setNCol(int nCol)
    {
        this.nCol=nCol;
    }

    //获得地图列数
    public int getNCol()
    {
        return nCol;
    }

    //设置网格按钮对应的状态
    public void setGridStatus(int[][] grid)
    {
        this.grid = grid;
    }

    //获取网格按钮对应的状态
    public int[][] getGridStatus()
    {
        return grid;
    }

    // 设置当前繁衍代数
    public void setNowGeneration(int nowGeneration)
    {
        this.nowGeneration = nowGeneration;
    }

    // 获得当前繁衍代数
    public int getNowGeneration()
    {
        return nowGeneration;
    }

    // 获取细胞的邻居数量
    public int getNeighborCount(int row, int col) {
        int countNeighbor = 0;
        // 以自己为中心，判断周围八个细胞状态
        for (int i = row - 1; i <= row + 1; i++)
        {
            for (int j = col - 1; j <= col + 1; j++)
            {
                // 判断到自己时，直接跳过
                if(i == row && j == col)
                {
                    continue;
                }
                countNeighbor += grid[i][j]; //如果邻居还活着，邻居的状态为1，邻居数便会+1
            }
        }

        return countNeighbor;
    }
}