package com.map;

public class Map {
    private int nRow;                    //����
    private int nCol;                    //����
    private int nowGeneration;           // ��ǰ����
    private int[][] grid;                //����ť��ʾϸ��״̬

    //���췽��
    public Map(int nRow, int nCol) {
        this.nRow = nRow;
        this.nCol = nCol;
    }

    //���õ�ͼ����
    public void setNRow(int nRow)
    {
        this.nRow=nRow;
    }

    //��õ�ͼ����
    public int getNRow()
    {
        return nRow;
    }

    //���õ�ͼ����
    public void setNCol(int nCol)
    {
        this.nCol=nCol;
    }

    //��õ�ͼ����
    public int getNCol()
    {
        return nCol;
    }

    //��������ť��Ӧ��״̬
    public void setGridStatus(int[][] grid)
    {
        this.grid = grid;
    }

    //��ȡ����ť��Ӧ��״̬
    public int[][] getGridStatus()
    {
        return grid;
    }

    // ���õ�ǰ���ܴ���
    public void setNowGeneration(int nowGeneration)
    {
        this.nowGeneration = nowGeneration;
    }

    // ��õ�ǰ���ܴ���
    public int getNowGeneration()
    {
        return nowGeneration;
    }

    // ��ȡϸ�����ھ�����
    public int getNeighborCount(int row, int col) {
        int countNeighbor = 0;
        // ���Լ�Ϊ���ģ��ж���Χ�˸�ϸ��״̬
        for (int i = row - 1; i <= row + 1; i++)
        {
            for (int j = col - 1; j <= col + 1; j++)
            {
                // �жϵ��Լ�ʱ��ֱ������
                if(i == row && j == col)
                {
                    continue;
                }
                countNeighbor += grid[i][j]; //����ھӻ����ţ��ھӵ�״̬Ϊ1���ھ������+1
            }
        }

        return countNeighbor;
    }
}