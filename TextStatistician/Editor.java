package program;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Editor extends JFrame {
    JLabel l1 = new JLabel("请编辑项目说明(编辑结束请点击保存，出现弹窗后退出！)");

    JButton bt7 = new JButton("保存说明");
    JButton bt8 = new JButton("返回主菜单");

    private JTextArea editableTextArea1;

    public Editor() {
        //窗口及按钮摆放
        l1.setBounds(20, -20, 700, 80);
        bt7.setBounds(725, 560, 135, 60);
        bt8.setBounds(725, 660, 135, 60);

        l1.setFont(new Font("黑体", Font.BOLD, 25));
        bt7.setFont(new Font("黑体", Font.BOLD, 15));
        bt8.setFont(new Font("黑体", Font.BOLD, 15));

        this.setTitle("文本统计器V1.0");
        this.setLayout(null);
        this.setSize(900, 800);
        this.add(bt7);
        this.add(bt8);
        this.add(l1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400, 50);
        this.setResizable(false);
        this.setVisible(true);
        background();

        show10();
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("菜单");
        JMenuItem jMenu1 = new JMenuItem("退出");
        jMenu.add(jMenu1);
        jMenuBar.add(jMenu);
        this.setJMenuBar(jMenuBar);
        this.setVisible(true);

        bt8.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Textbt2 t = new Textbt2();
                t.Text();
                Core w1 = new Core(t.text);
                w1.Window1();
                w1.setVisible(true);
                dispose();
            }
        });
        jMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void show10() {

        // 可编辑的文本区
        JPanel p10 = new JPanel();
        p10.setLayout(null);
        p10.setBorder(BorderFactory.createTitledBorder(""));
        p10.setLayout(new GridLayout(1, 3, 20, 5));
        p10.setLocation(100, 100);
        p10.setBounds(20, 40, 650, 680);
        p10.setVisible(true);
        //p1.add(p1, JLayeredPane.PALETTE_LAYER);

        Textbt2 t=new Textbt2();
        t.Text();

        editableTextArea1 = new JTextArea(t.text);
        editableTextArea1.setLineWrap(true);
        editableTextArea1.setVisible(true);
        editableTextArea1.setFont(new Font("黑体", Font.BOLD, 20));
        JScrollPane scrollPane3 = new JScrollPane(editableTextArea1);
        p10.add(scrollPane3);
        add(p10);

        bt7.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        readin();
                    }
                });
            }
        });
    }
    public void readin(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                String text = editableTextArea1.getText();
                try {
                    /* 写入Txt文件 */
                    File writename = new File("Text2.txt");
                    writename.createNewFile();
                    BufferedWriter out = new BufferedWriter(new FileWriter(writename));
                    out.write(text);
                    out.flush();
                    out.close();

                    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.BOLD, 15)));
                    JOptionPane.showMessageDialog(null, "保存成功!", "项目说明", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
        });
    }
    public void background(){
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("pic2.jpg"); //添加图片
        JLabel background = new  JLabel(img);
        this.getLayeredPane().add(background, Integer.valueOf(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

}
