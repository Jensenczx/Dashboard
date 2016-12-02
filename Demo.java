import javax.swing.*;
import java.awt.*;

/**
 * Created by chenjensen on 16/12/2.
 */
public class Demo {

    public static void main(String[] args) {
        //semiCircleTest();
        //circleTest();
        //arcTest();
        lineTest();
    }


    private static void lineTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard();
        //set the type
        dashboard.setType("line");
        dashboard.setUnit("M/S");
        dashboard.setValue("10");
        dashboard.setFrom(0);
        dashboard.setTo(50);
        dashboard.setMajor(5);
        dashboard.setMinor(1);

        dashboard.setForeground(Color.BLUE);
        dashboard.setBackground(Color.WHITE);
        //刻度盘上文字颜色
        dashboard.setTextColor(Color.black);
        //刻度盘上当前数值的颜色
        dashboard.setValueColor(Color.GREEN);
        //刻度盘上指针的颜色
        dashboard.setPointerColor(Color.BLUE);
        //刻度盘上长刻度的颜色
        dashboard.setMajorScaleColor(Color.BLACK);
        //刻度盘上短刻度的颜色
        dashboard.setMinorScaleColor(Color.DARK_GRAY);

        //解决控件较小问题
        dashboard.setPreferredSize(new Dimension(200, 100));

        JPanel panel = new JPanel();
        panel.add(dashboard);

        p.add(panel);
        f.setSize(900, 200);
        f.setVisible(true);
    }

    private static void arcTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard();
        //set the type
        dashboard.setType("arc180");
        dashboard.setUnit("M/S");
        dashboard.setValue("10");
        dashboard.setFrom(0);
        dashboard.setTo(50);
        dashboard.setMajor(5);
        dashboard.setMinor(1);

        dashboard.setForeground(Color.BLUE);
        dashboard.setBackground(Color.WHITE);
        //刻度盘上文字颜色
        dashboard.setTextColor(Color.black);
        //刻度盘上当前数值的颜色
        dashboard.setValueColor(Color.GREEN);
        //刻度盘上指针的颜色
        dashboard.setPointerColor(Color.BLUE);
        //刻度盘上长刻度的颜色
        dashboard.setMajorScaleColor(Color.BLACK);
        //刻度盘上短刻度的颜色
        dashboard.setMinorScaleColor(Color.DARK_GRAY);

        p.add(dashboard);
        f.setSize(640, 480);
        f.setVisible(true);
    }

    private static void circleTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard();
        //set the type
        dashboard.setType("circle");
        dashboard.setUnit("M/S");
        dashboard.setValue("10");
        dashboard.setFrom(0);
        dashboard.setTo(50);
        dashboard.setMajor(5);
        dashboard.setMinor(1);

        dashboard.setForeground(Color.BLUE);
        dashboard.setBackground(Color.WHITE);
        //刻度盘上文字颜色
        dashboard.setTextColor(Color.black);
        //刻度盘上当前数值的颜色
        dashboard.setValueColor(Color.GREEN);
        //刻度盘上指针的颜色
        dashboard.setPointerColor(Color.BLUE);
        //刻度盘上长刻度的颜色
        dashboard.setMajorScaleColor(Color.BLACK);
        //刻度盘上短刻度的颜色
        dashboard.setMinorScaleColor(Color.DARK_GRAY);

        p.add(dashboard);
        f.setSize(640, 480);
        f.setVisible(true);
    }

    private static void semiCircleTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard();
        //set the type
        dashboard.setType("semi_circle180");
        dashboard.setUnit("M/S");
        //值的设置必须为left或者right
        dashboard.setValue("right10");
        dashboard.setFrom(0);
        dashboard.setTo(50);
        dashboard.setMajor(5);
        dashboard.setMinor(1);

        dashboard.setForeground(Color.BLUE);
        dashboard.setBackground(Color.WHITE);
        //刻度盘上文字颜色
        dashboard.setTextColor(Color.black);
        //刻度盘上当前数值的颜色
        dashboard.setValueColor(Color.GREEN);
        //刻度盘上指针的颜色
        dashboard.setPointerColor(Color.BLUE);
        //刻度盘上长刻度的颜色
        dashboard.setMajorScaleColor(Color.BLACK);
        //刻度盘上短刻度的颜色
        dashboard.setMinorScaleColor(Color.DARK_GRAY);

        p.add(dashboard);
        f.setSize(640, 480);
        f.setVisible(true);
    }
}
