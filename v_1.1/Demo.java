import com.jensen.Dashboard;

import javax.swing.*;
import java.awt.*;

/**
 * Created by chenjensen on 16/12/2.
 */
public class Demo {

    public static void main(String[] args) {
        //semiCircleTest();
        //circleTest();
//        arcTest();
        lineTest();
    }


    private static void lineTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard.Builder()
                .from(0)
                .to(50)
                .major(5)
                .minor(1)
                .value("10")
                .unit("M/S")
                .type("line")
                .textColor(Color.black)
                .valueColor(Color.black)
                .pointerColor(Color.black)
                .majorScaleColor(Color.BLACK)
                .minorScaleColor(Color.DARK_GRAY)
                .foregroundColor(Color.BLUE)
                .backgroundColor(Color.WHITE)
                .size(new Dimension(400, 100))
                .build();

        p.add(dashboard);
        f.setSize(900, 200);
        f.setVisible(true);
    }

    private static void arcTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard.Builder()
                .from(0)
                .to(50)
                .major(5)
                .minor(1)
                .value("10")
                .unit("M/S")
                .type("arc180")
                .textColor(Color.black)
                .valueColor(Color.black)
                .pointerColor(Color.black)
                .majorScaleColor(Color.BLACK)
                .minorScaleColor(Color.DARK_GRAY)
                .foregroundColor(Color.BLUE)
                .backgroundColor(Color.WHITE)
                .size(new Dimension(400, 100))
                .build();

        p.add(dashboard);
        f.setSize(640, 480);
        f.setVisible(true);
    }

    private static void circleTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard.Builder()
                .from(0)
                .to(50)
                .major(5)
                .minor(1)
                .value("10")
                .unit("M/S")
                .type("circle")
                .textColor(Color.black)
                .valueColor(Color.black)
                .pointerColor(Color.black)
                .majorScaleColor(Color.BLACK)
                .minorScaleColor(Color.DARK_GRAY)
                .foregroundColor(Color.BLUE)
                .backgroundColor(Color.WHITE)
                .size(new Dimension(400, 100))
                .build();

        p.add(dashboard);
        f.setSize(640, 480);
        f.setVisible(true);
    }

    private static void semiCircleTest() {
        JFrame f = new JFrame("速度展示");
        Container p = f.getContentPane();
        Dashboard dashboard = new Dashboard.Builder()
                .from(0)
                .to(50)
                .major(5)
                .minor(1)
                .value("right10")
                .unit("M/S")
                .type("semi_circle180")
                .textColor(Color.black)
                .valueColor(Color.black)
                .pointerColor(Color.black)
                .majorScaleColor(Color.BLACK)
                .minorScaleColor(Color.DARK_GRAY)
                .foregroundColor(Color.BLUE)
                .backgroundColor(Color.WHITE)
                .size(new Dimension(400, 100))
                .build();

        p.add(dashboard);
        f.setSize(640, 480);
        f.setVisible(true);
    }
}
