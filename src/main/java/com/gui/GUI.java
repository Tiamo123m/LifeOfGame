package com.gui;

import com.cell.Cell;
import com.logic.Logic;
import com.map.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame {

    // 文本框：行细胞数、列细胞数
    private JTextField textRowNum, textColNum;
    // 按钮：一个网格按钮表示一个细胞
    private JButton[][] btnGridCell;
    // 按钮：确定、代数清零、细胞清零、随机初始化、当前代数
    private JButton btnOK, btnClearGeneration, btnClearCell, btnRandomInit, btnNowGeneration;
    // 按钮：开始繁衍、下一代、暂停、退出
    private JButton btnAutoProduce, btnNextGeneration, btnStop, btnExit;

    // 创建Logic类的对象
    Logic logic = new Logic();
    //创建地图对象
    Map map = new Map(20, 35);
    // 创建Cell类的对象
    Cell cell = new Cell(20,35);
    // 线程
    private Thread thread;

    // 主程序入口
    public static void main(String[] arg) {
        new GUI();
    }

    // 构造方法
    public GUI() {
        // 调用父类JFrame的构造方法设置窗体标题
        super("生命游戏");
        // 初始化窗体
        InitGUI();
        //初始化游戏地图
        InitGameMap();
    }

    //初始化游戏地图
    public void InitGameMap() {

        logic.InitMap(map);
        cell = new Cell(map.getNRow(), map.getNCol());

    }

    // 初始化ui界面
    public void InitGUI() {

        // 背景面板：边框布局
        JPanel backPanel = new JPanel(new BorderLayout());
        this.setContentPane(backPanel);

        // 中部面板：网格布局
        JPanel centerPanel = new JPanel(new GridLayout(map.getNRow(), map.getNCol()));
        backPanel.add(centerPanel, "Center");

        // 底部面板：容纳各种按钮
        JPanel bottomPanel = new JPanel();
        backPanel.add(bottomPanel, "South");

        // 创建网格细胞按钮对象,按钮数组
        btnGridCell = new JButton[map.getNRow()][map.getNCol()];

        // 初始化网格按钮数组以表示细胞
        for (int i = 0; i < map.getNRow(); i++) {
            for (int j = 0; j < map.getNCol(); j++) {
                // 细胞网格，内容为空表示细胞
                btnGridCell[i][j] = new JButton("");
                // 细胞背景色为白色
                btnGridCell[i][j].setBackground(Color.WHITE);
                // 添加细胞网格按钮到面板
                centerPanel.add(btnGridCell[i][j]);
            }
        }
        // 标签：行数
        // 标签：行细胞数、列细胞数当前代数
        JLabel labelRowNum = new JLabel("行    数:");
        bottomPanel.add(labelRowNum);

        // 文本框：行数
        textRowNum = new JTextField(2);
        textRowNum.setText("" + map.getNRow());
        bottomPanel.add(textRowNum);

        // 标签：列数
        JLabel labelColNum = new JLabel("列    数:");
        bottomPanel.add(labelColNum);

        // 文本框：列数
        textColNum = new JTextField(2);
        textColNum.setText("" + map.getNCol());
        bottomPanel.add(textColNum);

        // 按钮：确定
        btnOK = new JButton("确定");
        bottomPanel.add(btnOK);

        // 标签：当前代数
        JLabel labelNowGeneration = new JLabel("当前代数:");
        bottomPanel.add(labelNowGeneration);

        // 按钮：显示当前代数
        btnNowGeneration = new JButton("" + map.getNowGeneration());
        // 设置按钮不可点击
        btnNowGeneration.setEnabled(false);
        bottomPanel.add(btnNowGeneration);

        // 代数清零按钮
        btnClearGeneration = new JButton("代数清零");
        bottomPanel.add(btnClearGeneration);

        // 随机初始化按钮
        btnRandomInit = new JButton("随机初始化");
        bottomPanel.add(btnRandomInit);

        // 细胞清零按钮
        btnClearCell = new JButton("细胞清零");
        bottomPanel.add(btnClearCell);

        // 自动繁衍按钮
        btnAutoProduce = new JButton("自动繁衍");
        bottomPanel.add(btnAutoProduce);

        // 下一代按钮
        btnNextGeneration = new JButton("下一代");
        bottomPanel.add(btnNextGeneration);

        // 暂停按钮
        btnStop = new JButton("暂停");
        bottomPanel.add(btnStop);

        // 退出按钮
        btnExit = new JButton("退出");
        bottomPanel.add(btnExit);

        //设置按钮状态
        btnOK.setEnabled(true);
        btnClearGeneration.setEnabled(true);
        btnRandomInit.setEnabled(true);
        btnClearCell.setEnabled(true);
        btnNextGeneration.setEnabled(true);
        btnAutoProduce.setEnabled(true);
        btnStop.setEnabled(false);
        btnExit.setEnabled(true);
        for (int i = 0; i < map.getNRow(); i++) {
            for (int j = 0; j < map.getNCol(); j++) {
                btnGridCell[i][j].setEnabled(true);
            }
        }
        // 注册监听器
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosed(WindowEvent e)
            {
                System.exit(0);
            }
        });
        btnOK.addActionListener(new OKListener());
        btnClearGeneration.addActionListener(new ClearListener());
        btnRandomInit.addActionListener(new RandomListener());
        btnClearCell.addActionListener(new ClearCellListener());
        btnNextGeneration.addActionListener(new NextGenerationListener());
        btnAutoProduce.addActionListener(new AutoProduceListener());
        btnStop.addActionListener(new StopListener());
        btnExit.addActionListener(new ExitListener());
        for (int i = 0; i < map.getNRow(); i++) {
            for (int j = 0; j < map.getNCol(); j++) {
                btnGridCell[i][j].addActionListener(new GridListener());
            }
        }

        // 设置窗体大小
        this.setSize(1000, 650);
        // 窗体大小可变
        this.setResizable(true);
        // 窗体居中显示
        this.setLocationRelativeTo(null);
        // 窗体可见
        this.setVisible(true);
    }

    //监听类，负责确定按钮
    public class OKListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 从Map类中获取最新的ui界面大小：行、列值
            map.setNRow(Integer.parseInt(textRowNum.getText()));
            map.setNCol(Integer.parseInt(textColNum.getText()));
            // 更新窗体
            InitGUI();
            //更新游戏地图
            InitGameMap();
        }
    }

    //监听类，负责代数清零按钮
    public class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 设置代数值为1
            map.setNowGeneration(1);
            // 刷新当前代数显示
            btnNowGeneration.setText("" + map.getNowGeneration());
            // 未在自动繁衍
            cell.setIsRunning(false);
        }
    }

    //监听类，负责随机初始化按钮
    public class RandomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 随机初始化一部分细胞状态为1
            logic.RandomMap(map);
            // 显示细胞
            showCell();
            // 未在自动繁衍
            cell.setIsRunning(false);
        }
    }

    //监听类，负责细胞清零按钮
    public class ClearCellListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 设置所有细胞状态为0
            logic.ClearMap(map);
            // 显示细胞
            showCell();
            // 未在自动繁衍
            cell.setIsRunning(false);
        }
    }

    //监听类，负责自动繁衍按钮
    public class AutoProduceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 正在自动繁衍
            cell.setIsRunning(true);
            // 自动繁衍
            autoProduce();

            //设置按钮状态
            btnOK.setEnabled(false);
            btnClearGeneration.setEnabled(false);
            btnRandomInit.setEnabled(false);
            btnClearCell.setEnabled(false);
            btnNextGeneration.setEnabled(false);
            btnAutoProduce.setEnabled(false);
            btnStop.setEnabled(true);
            btnExit.setEnabled(false);
            for (int i = 0; i < map.getNRow(); i++) {
                for (int j = 0; j < map.getNCol(); j++) {
                    btnGridCell[i][j].setEnabled(false);
                }
            }
        }
    }

    //监听类，负责下一代按钮
    public class NextGenerationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 产生下一代
            produce();
            // 未在自动繁衍
            cell.setIsRunning(false);

        }
    }

    //监听类，负责暂停按钮
    public class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 未在自动繁衍
            cell.setIsRunning(false);

            //设置按钮状态
            btnOK.setEnabled(true);
            btnClearGeneration.setEnabled(true);
            btnRandomInit.setEnabled(true);
            btnClearCell.setEnabled(true);
            btnNextGeneration.setEnabled(true);
            btnAutoProduce.setEnabled(true);
            btnStop.setEnabled(false);
            btnExit.setEnabled(true);
            for (int i = 0; i < map.getNRow(); i++) {
                for (int j = 0; j < map.getNCol(); j++) {
                    btnGridCell[i][j].setEnabled(true);
                }
            }
        }
    }

    //监听类，负责退出按钮
    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            System.exit(0);
        }
    }

    //监听类，负责细胞按钮
    public class GridListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            {
                // 从Map类中获得每个网格细胞按钮对应的值：1或0
                int[][] grid = map.getGridStatus();

                for (int i = 0; i < map.getNRow(); i++) {
                    for (int j = 0; j < map.getNCol(); j++) {
                        // 点击了某一个细胞按钮
                        if (e.getSource() == btnGridCell[i][j]) {
                            // 修改其选择状态为相反状态
                            cell.setIsSelected(i, j);

                            // 如果选中了该细胞按钮
                            if (cell.getIsSelected(i, j)) {
                                // 背景色为黑色
                                btnGridCell[i][j].setBackground(Color.BLACK);
                                // 选中则设置该细胞为活细胞，状态为1
                                grid[i + 1][j + 1] = 1;
                            } else {
                                // 死细胞背景色为白色，状态为0
                                btnGridCell[i][j].setBackground(Color.WHITE);
                                grid[i + 1][j + 1] = 0;
                            }
                            break;
                        }
                    }
                }
                // 修改Map类中与网格按钮对应多维数组中元素的值
                map.setGridStatus(grid);
            }
        }
    }

    // 生成下一代
    protected void produce() {
        // 繁衍
        logic.NextMap(map);
        // 显示细胞
        showCell();
        // 刷新代数显示按钮
        btnNowGeneration.setText("" + map.getNowGeneration());

    }

    // 自动繁衍
    private void autoProduce() {
        thread = new Thread(() -> {
            while (cell.getIsRunning())
            {
                // 产生下一代
                produce();

                try
                {
                    Thread.sleep(500);
                } catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }

                cell.setIsDead(true);

                for(int row = 1; row <= map.getNRow(); row++)
                {
                    for (int col = 1; col <= map.getNCol(); col++)
                    {
                        if (map.getGridStatus()[row][col] != 0)
                        {
                            cell.setIsDead(false);
                            break;
                        }
                    }
                    if (!cell.getIsDead())
                    {
                        break;
                    }
                }

                if (cell.getIsDead())
                {
                    JOptionPane.showMessageDialog(null, "所有细胞已死亡");
                    btnOK.setEnabled(true);
                    btnClearGeneration.setEnabled(true);
                    btnRandomInit.setEnabled(true);
                    btnClearCell.setEnabled(true);
                    btnNextGeneration.setEnabled(true);
                    btnAutoProduce.setEnabled(true);
                    btnStop.setEnabled(false);
                    btnExit.setEnabled(true);
                    for (int i = 0; i < map.getNRow(); i++) {
                        for (int j = 0; j < map.getNCol(); j++) {
                            btnGridCell[i][j].setEnabled(true);
                        }
                    }
                    cell.setIsRunning(false);
                    thread = null;
                }
            }
        });
        thread.start();
    }

    // 根据细胞状态显示细胞颜色
    private void showCell() {
        int[][] grid = map.getGridStatus();

        for (int i = 0; i < map.getNRow(); i++) {
            for (int j = 0; j < map.getNCol(); j++) {
                if (grid[i + 1][j + 1] == 1) {
                    // 活细胞用黑色表示
                    btnGridCell[i][j].setBackground(Color.BLACK);
                } else {
                    // 死细胞用白色表示
                    btnGridCell[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

}
