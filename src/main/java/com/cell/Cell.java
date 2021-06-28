
package com.cell;

public class Cell {

    // 界面中细胞是否死完
    private boolean isDead;
    // 是否正在繁衍
    private boolean isRunning;
    // 细胞是否被选中
    private final boolean[][] isSelected;
    // 构造方法
    public Cell(int row, int col) {

        isSelected = new boolean[row][col];
        // 默认初始化所有细胞未选中
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < col; j++)
            {
                isSelected[i][j] = false;
            }
        }
    }

    // 获取所有细胞是否死亡
    public boolean getIsDead()
    {
        return isDead;
    }

    // 设置所有细胞是否死亡
    public void setIsDead(boolean isDead)
    {
        this.isDead = isDead;
    }

    // 获取是否正在繁衍状态
    public boolean getIsRunning()
    {
        return isRunning;
    }

    // 设置是否正在繁衍状态
    public void setIsRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    // 获取细胞是否选中
    public boolean getIsSelected(int row, int col)
    {
        return isSelected[row][col];
    }

    // 设置细胞是否选中
    public void setIsSelected(int row, int col)
    {
        isSelected[row][col] = !isSelected[row][col];
    }


}