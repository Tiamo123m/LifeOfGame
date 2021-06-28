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

    // �ı�����ϸ��������ϸ����
    private JTextField textRowNum, textColNum;
    // ��ť��һ������ť��ʾһ��ϸ��
    private JButton[][] btnGridCell;
    // ��ť��ȷ�����������㡢ϸ�����㡢�����ʼ������ǰ����
    private JButton btnOK, btnClearGeneration, btnClearCell, btnRandomInit, btnNowGeneration;
    // ��ť����ʼ���ܡ���һ������ͣ���˳�
    private JButton btnAutoProduce, btnNextGeneration, btnStop, btnExit;

    // ����Logic��Ķ���
    Logic logic = new Logic();
    //������ͼ����
    Map map = new Map(20, 35);
    // ����Cell��Ķ���
    Cell cell = new Cell(20,35);
    // �߳�
    private Thread thread;

    // ���������
    public static void main(String[] arg) {
        new GUI();
    }

    // ���췽��
    public GUI() {
        // ���ø���JFrame�Ĺ��췽�����ô������
        super("������Ϸ");
        // ��ʼ������
        InitGUI();
        //��ʼ����Ϸ��ͼ
        InitGameMap();
    }

    //��ʼ����Ϸ��ͼ
    public void InitGameMap() {

        logic.InitMap(map);
        cell = new Cell(map.getNRow(), map.getNCol());

    }

    // ��ʼ��ui����
    public void InitGUI() {

        // ������壺�߿򲼾�
        JPanel backPanel = new JPanel(new BorderLayout());
        this.setContentPane(backPanel);

        // �в���壺���񲼾�
        JPanel centerPanel = new JPanel(new GridLayout(map.getNRow(), map.getNCol()));
        backPanel.add(centerPanel, "Center");

        // �ײ���壺���ɸ��ְ�ť
        JPanel bottomPanel = new JPanel();
        backPanel.add(bottomPanel, "South");

        // ��������ϸ����ť����,��ť����
        btnGridCell = new JButton[map.getNRow()][map.getNCol()];

        // ��ʼ������ť�����Ա�ʾϸ��
        for (int i = 0; i < map.getNRow(); i++) {
            for (int j = 0; j < map.getNCol(); j++) {
                // ϸ����������Ϊ�ձ�ʾϸ��
                btnGridCell[i][j] = new JButton("");
                // ϸ������ɫΪ��ɫ
                btnGridCell[i][j].setBackground(Color.WHITE);
                // ���ϸ������ť�����
                centerPanel.add(btnGridCell[i][j]);
            }
        }
        // ��ǩ������
        // ��ǩ����ϸ��������ϸ������ǰ����
        JLabel labelRowNum = new JLabel("��    ��:");
        bottomPanel.add(labelRowNum);

        // �ı�������
        textRowNum = new JTextField(2);
        textRowNum.setText("" + map.getNRow());
        bottomPanel.add(textRowNum);

        // ��ǩ������
        JLabel labelColNum = new JLabel("��    ��:");
        bottomPanel.add(labelColNum);

        // �ı�������
        textColNum = new JTextField(2);
        textColNum.setText("" + map.getNCol());
        bottomPanel.add(textColNum);

        // ��ť��ȷ��
        btnOK = new JButton("ȷ��");
        bottomPanel.add(btnOK);

        // ��ǩ����ǰ����
        JLabel labelNowGeneration = new JLabel("��ǰ����:");
        bottomPanel.add(labelNowGeneration);

        // ��ť����ʾ��ǰ����
        btnNowGeneration = new JButton("" + map.getNowGeneration());
        // ���ð�ť���ɵ��
        btnNowGeneration.setEnabled(false);
        bottomPanel.add(btnNowGeneration);

        // �������㰴ť
        btnClearGeneration = new JButton("��������");
        bottomPanel.add(btnClearGeneration);

        // �����ʼ����ť
        btnRandomInit = new JButton("�����ʼ��");
        bottomPanel.add(btnRandomInit);

        // ϸ�����㰴ť
        btnClearCell = new JButton("ϸ������");
        bottomPanel.add(btnClearCell);

        // �Զ����ܰ�ť
        btnAutoProduce = new JButton("�Զ�����");
        bottomPanel.add(btnAutoProduce);

        // ��һ����ť
        btnNextGeneration = new JButton("��һ��");
        bottomPanel.add(btnNextGeneration);

        // ��ͣ��ť
        btnStop = new JButton("��ͣ");
        bottomPanel.add(btnStop);

        // �˳���ť
        btnExit = new JButton("�˳�");
        bottomPanel.add(btnExit);

        //���ð�ť״̬
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
        // ע�������
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

        // ���ô����С
        this.setSize(1000, 650);
        // �����С�ɱ�
        this.setResizable(true);
        // ���������ʾ
        this.setLocationRelativeTo(null);
        // ����ɼ�
        this.setVisible(true);
    }

    //�����࣬����ȷ����ť
    public class OKListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // ��Map���л�ȡ���µ�ui�����С���С���ֵ
            map.setNRow(Integer.parseInt(textRowNum.getText()));
            map.setNCol(Integer.parseInt(textColNum.getText()));
            // ���´���
            InitGUI();
            //������Ϸ��ͼ
            InitGameMap();
        }
    }

    //�����࣬����������㰴ť
    public class ClearListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // ���ô���ֵΪ1
            map.setNowGeneration(1);
            // ˢ�µ�ǰ������ʾ
            btnNowGeneration.setText("" + map.getNowGeneration());
            // δ���Զ�����
            cell.setIsRunning(false);
        }
    }

    //�����࣬���������ʼ����ť
    public class RandomListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // �����ʼ��һ����ϸ��״̬Ϊ1
            logic.RandomMap(map);
            // ��ʾϸ��
            showCell();
            // δ���Զ�����
            cell.setIsRunning(false);
        }
    }

    //�����࣬����ϸ�����㰴ť
    public class ClearCellListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // ��������ϸ��״̬Ϊ0
            logic.ClearMap(map);
            // ��ʾϸ��
            showCell();
            // δ���Զ�����
            cell.setIsRunning(false);
        }
    }

    //�����࣬�����Զ����ܰ�ť
    public class AutoProduceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // �����Զ�����
            cell.setIsRunning(true);
            // �Զ�����
            autoProduce();

            //���ð�ť״̬
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

    //�����࣬������һ����ť
    public class NextGenerationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // ������һ��
            produce();
            // δ���Զ�����
            cell.setIsRunning(false);

        }
    }

    //�����࣬������ͣ��ť
    public class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // δ���Զ�����
            cell.setIsRunning(false);

            //���ð�ť״̬
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

    //�����࣬�����˳���ť
    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dispose();
            System.exit(0);
        }
    }

    //�����࣬����ϸ����ť
    public class GridListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            {
                // ��Map���л��ÿ������ϸ����ť��Ӧ��ֵ��1��0
                int[][] grid = map.getGridStatus();

                for (int i = 0; i < map.getNRow(); i++) {
                    for (int j = 0; j < map.getNCol(); j++) {
                        // �����ĳһ��ϸ����ť
                        if (e.getSource() == btnGridCell[i][j]) {
                            // �޸���ѡ��״̬Ϊ�෴״̬
                            cell.setIsSelected(i, j);

                            // ���ѡ���˸�ϸ����ť
                            if (cell.getIsSelected(i, j)) {
                                // ����ɫΪ��ɫ
                                btnGridCell[i][j].setBackground(Color.BLACK);
                                // ѡ�������ø�ϸ��Ϊ��ϸ����״̬Ϊ1
                                grid[i + 1][j + 1] = 1;
                            } else {
                                // ��ϸ������ɫΪ��ɫ��״̬Ϊ0
                                btnGridCell[i][j].setBackground(Color.WHITE);
                                grid[i + 1][j + 1] = 0;
                            }
                            break;
                        }
                    }
                }
                // �޸�Map����������ť��Ӧ��ά������Ԫ�ص�ֵ
                map.setGridStatus(grid);
            }
        }
    }

    // ������һ��
    protected void produce() {
        // ����
        logic.NextMap(map);
        // ��ʾϸ��
        showCell();
        // ˢ�´�����ʾ��ť
        btnNowGeneration.setText("" + map.getNowGeneration());

    }

    // �Զ�����
    private void autoProduce() {
        thread = new Thread(() -> {
            while (cell.getIsRunning())
            {
                // ������һ��
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
                    JOptionPane.showMessageDialog(null, "����ϸ��������");
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

    // ����ϸ��״̬��ʾϸ����ɫ
    private void showCell() {
        int[][] grid = map.getGridStatus();

        for (int i = 0; i < map.getNRow(); i++) {
            for (int j = 0; j < map.getNCol(); j++) {
                if (grid[i + 1][j + 1] == 1) {
                    // ��ϸ���ú�ɫ��ʾ
                    btnGridCell[i][j].setBackground(Color.BLACK);
                } else {
                    // ��ϸ���ð�ɫ��ʾ
                    btnGridCell[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

}
