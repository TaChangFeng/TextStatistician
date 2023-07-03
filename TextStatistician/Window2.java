package program;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Window2 extends JFrame{
    JLabel l1 = new JLabel("文本编辑区");
    JLabel l2 = new JLabel("出现单词");
    JLabel l3 = new JLabel("单词总数");
    JLabel l4 = new JLabel("出现数字");
    JLabel l5 = new JLabel("数字总数");

    JButton bt2 = new JButton("退出程序");
    JButton bt3 = new JButton("返回主菜单");
    private JTextArea editableTextArea;
    private JTextArea wordTextArea;
    private JTextArea wordCountTextArea;
    private JTextArea numberTextArea;
    private JTextArea numberCountTextArea;
    public Window2(){
        //窗口及按钮摆放
        l1.setBounds(190, -20, 600, 80);
        l2.setBounds(660, -20, 600, 80);
        l3.setBounds(660, 140, 600, 80);
        l4.setBounds(660, 300, 600, 80);
        l5.setBounds(660, 460, 600, 80);
        bt2.setBounds(560, 660, 135, 60);
        bt3.setBounds(725, 660, 135, 60);

        l1.setFont(new Font("黑体",Font.BOLD,25));
        l2.setFont(new Font("黑体",Font.BOLD,25));
        l3.setFont(new Font("黑体",Font.BOLD,25));
        l4.setFont(new Font("黑体",Font.BOLD,25));
        l5.setFont(new Font("黑体",Font.BOLD,25));
        bt2.setFont(new Font("黑体",Font.BOLD,15));
        bt3.setFont(new Font("黑体",Font.BOLD,15));


        this.setTitle("文本统计器V1.0");
        this.setLayout(null);
        this.setSize(900, 800);
        this.add(bt2);
        this.add(bt3);
        //this.add(bt4);
        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        this.add(l5);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400,50);
        this.setResizable(false);
        this.setVisible(true);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("文件");
        JMenu jMenu3 = new JMenu("说明");
        JMenuItem jMenu1 = new JMenuItem("打开");
        JMenuItem jMenu2 = new JMenuItem("退出");
        JMenuItem jMenu4 = new JMenuItem("项目说明");
        jMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                        String line;
                        StringBuilder sb = new StringBuilder();
                        while ((line = br.readLine()) != null) {
                            sb.append(line).append("\n");
                        }
                        editableTextArea.setText(sb.toString());
                        br.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        jMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        jMenu.add(jMenu1);
        jMenu.add(jMenu2);
        jMenu3.add(jMenu4);
        jMenuBar.add(jMenu);
        jMenuBar.add(jMenu3);

        this.setJMenuBar(jMenuBar);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = Color.RED;
                Color color2 = Color.BLUE;
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        this.add(panel);
        this.setVisible(true);
        bt2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出软件?", "退出程序",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        bt3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Textbt2 t=new Textbt2();
                t.Text();
                Core w1 = new Core(t.text);
                w1.Window1();
                w1.setVisible(true);
                dispose();
            }
        });
        background();
        show2();
    }
    //窗口2背景图
    public void background(){
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("pic2.jpg"); //添加图片
        JLabel background = new  JLabel(img);
        this.getLayeredPane().add(background, Integer.valueOf(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
    public void show2() {
        // 可编辑的文本区
        JPanel p1=new JPanel();// 创建面板p_1
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createTitledBorder(""));// 面板p1添加边框
        p1.setLayout(new GridLayout(1,3,20,5));// p_1建立布局,网格布局；行，列，水平间距，竖直间距
        p1.setLocation(100,100);
        p1.setBounds(20,40,500,680);
        p1.setVisible(true);
        //p1.add(p1, JLayeredPane.PALETTE_LAYER);

        editableTextArea = new JTextArea();
        editableTextArea.setLineWrap(true);
        editableTextArea.setFont(new Font("Consolas",Font.BOLD,20));
        JScrollPane scrollPane3 = new JScrollPane(editableTextArea);
        p1.add(scrollPane3);
        add(p1);

        // 单词出现次数
        JPanel p2=new JPanel();
        p2.setLayout(null);
        p2.setBorder(BorderFactory.createTitledBorder(""));
        p2.setLayout(new GridLayout(1,3,20,5));
        p2.setLocation(100,100);
        p2.setBounds(560,40,300,110);
        p2.setVisible(true);
        wordTextArea = new JTextArea(4,2);
        wordTextArea.setEditable(false);
        wordTextArea.setFont(new Font("Consolas",Font.BOLD,20));
        JScrollPane scrollPane = new JScrollPane(wordTextArea);
        p2.add(scrollPane);
        add(p2);

        // 单词总数
        JPanel p3=new JPanel();
        p3.setLayout(null);
        p3.setBorder(BorderFactory.createTitledBorder(""));
        p3.setLayout(new GridLayout(1,3,20,5));
        p3.setLocation(100,100);
        p3.setBounds(560,200,300,110);
        p3.setVisible(true);
        wordCountTextArea = new JTextArea();
        wordCountTextArea.setEditable(false);
        wordCountTextArea.setFont(new Font("Consolas",Font.BOLD,50));
        p3.add(wordCountTextArea);
        add(p3);

        // 数字出现次数
        JPanel p4=new JPanel();
        p4.setLayout(null);
        p4.setBorder(BorderFactory.createTitledBorder(""));
        p4.setLayout(new GridLayout(1,3,20,5));
        p4.setLocation(100,100);
        p4.setBounds(560,360,300,110);
        p4.setVisible(true);
        numberTextArea = new JTextArea();
        numberTextArea.setEditable(false);
        numberTextArea.setFont(new Font("Consolas",Font.BOLD,20));
        JScrollPane scrollPane2 = new JScrollPane(numberTextArea);
        p4.add(scrollPane2);
        add(p4);

        // 数字总数
        JPanel p5=new JPanel();
        p5.setLayout(null);
        p5.setBorder(BorderFactory.createTitledBorder(""));
        p5.setLayout(new GridLayout(1,3,20,5));
        p5.setLocation(100,100);
        p5.setBounds(560,520,300,110);
        p5.setVisible(true);
        numberCountTextArea = new JTextArea();
        numberCountTextArea.setEditable(false);
        numberCountTextArea.setFont(new Font("Consolas",Font.BOLD,50));
        p5.add(numberCountTextArea);
        add(p5);
        // 监听编辑区域的文本变化
        editableTextArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateTextArea();
            }

            public void removeUpdate(DocumentEvent e) {
                updateTextArea();
            }

            public void changedUpdate(DocumentEvent e) {
                updateTextArea();
            }
        });
    }

    // 更新四个不可编辑的文本区
    private void updateTextArea() {
        String text = editableTextArea.getText();
        // 单词出现
        String words = countWords(text);
        wordTextArea.setText(words);
        // 单词总数
        String wordCount = countWords(text.split("\\s+"));
        wordCountTextArea.setText(wordCount);
        // 数字出现
        String numbers = countNumbers(text);
        numberTextArea.setText(numbers);
        // 数字总数
        String numberCount = countNumbers(text.split("\\D+"));
        numberCountTextArea.setText(numberCount);
    }
    // 统计文本中的单词出现
    private String countWords(String text) {
        String result = "";
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b"); // 正则表达式匹配单词
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result += matcher.group() + "\n";
        }

        return result;
    }

    // 统计文本中的单词总数和数字总数
    private String countWords(String[] words) {
        int count = 0;
        for (String word : words) {
            if (word.matches("\\b[a-zA-Z]+\\b")) {
                count++;
            }
        }
        return String.valueOf(count);
    }

    // 统计文本中的数字出现
    private String countNumbers(String text) {
        String result = "";
        Pattern pattern = Pattern.compile("\\b\\d+\\b"); // 正则表达式匹配数字
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            result += matcher.group() + "\n";
        }

        return result;
    }

    // 统计文本中的数字总数
    private String countNumbers(String[] numbers) {
        int count = 0;
        for (String number : numbers) {
            if (number.matches("\\b\\d+\\b")) {
                count++;
            }
        }
        return String.valueOf(count);
    }

}
