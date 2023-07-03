package program;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

public class Core extends JFrame {
    String T=null;
    JFrame frm = new JFrame();
    JLabel l1 = new JLabel("欢迎使用文本统计器！");
    JButton bt2 = new JButton("项目说明");
    JButton bt3 = new JButton("退出程序");
    JButton bt4 = new JButton("开始使用");
    JButton bt5 = new JButton("修改说明");
    JTextField tf = new JTextField();

    Core(String T){
        this.T=T;
    }
    public int Window1(){
        //窗口及按钮摆放
        l1.setBounds(150, 150, 600, 80);
        bt4.setBounds(550, 390, 150, 50);
        bt2.setBounds(550, 470, 150, 50);
        bt3.setBounds(550, 550, 150, 50);
        bt5.setBounds(50, 550, 150, 50);
        l1.setFont(new Font("黑体",Font.BOLD,50));
        bt2.setFont(new Font("黑体",Font.BOLD,20));
        bt3.setFont(new Font("黑体",Font.BOLD,20));
        bt4.setFont(new Font("黑体",Font.BOLD,20));
        bt5.setFont(new Font("黑体",Font.BOLD,20));
        background();

        this.setTitle("文本统计器V1.0");
        this.setLayout(null);
        this.setSize(800, 700);
        this.add(bt2);
        this.add(bt3);
        this.add(bt4);
        this.add(bt5);
        this.add(l1);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400,50);
        this.setResizable(false);
        this.setVisible(true);
        //退出弹窗
        bt3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "确认退出软件?", "退出程序",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        //项目说明按钮事件
        bt2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, T, "项目说明", JOptionPane.PLAIN_MESSAGE);
            }
        });
        //主程序窗口
        bt4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Window2 w2 = new Window2();
                w2.setVisible(true);
                dispose();
            }
        });
        bt5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String info = JOptionPane.showInputDialog(null,"请输入操作密码：（密码为123456）","请输入密码",JOptionPane.WARNING_MESSAGE);
                if(info.equals("123456")){
                    JOptionPane.showMessageDialog(null,"密码正确，您可以对项目说明的内容进行更改！（点击“确定”继续）",
                            "密码正确",JOptionPane.WARNING_MESSAGE);
                    Editor ee = new Editor();
                    ee.setVisible(true);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"密码错误，请重试！！","密码错误",JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        return 0;
    }
    //窗口1背景图
    public void background(){
        ((JPanel)this.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon("pic1.jpg"); //添加图片
        JLabel background = new  JLabel(img);
        this.getLayeredPane().add(background, Integer.valueOf(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }

    public static void main(String args[]) {
        Textbt2 t=new Textbt2();
        t.Text();
        Core c = new Core(t.text);
        c.Window1();
    }
}