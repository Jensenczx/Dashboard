import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by chenjensen on 16/11/26.
 */
public class Dashboard extends JComponent {

    //字体大小
    private static final int VALUE_FONT_SIZE = 18;

    //直线，扇形，圆形等类型
    public static final String LINE = "line";
    public static final String RING = "ring";
    public static final String CIRCLE = "circle";

    public static final double CIRCLE_ANGLE = 360;
    public static final int ANGLE_INDEX = 4;
    public static final double CIRCLE_ANGLE_START = Math.PI / 2;

    private double from = 0;
    private double to = 10;

    private String type = "line";

    //最大刻度，最小刻度
    private double major = 1;
    private double minor = 0.1;
    //当前指针指向的位置
    private String value = "";
    //单位
    private String unit = "";

    private double width;
    private double height;
    private Graphics2D g2;
    private int fontSize;

    public Dashboard() {
        super();
        this.setPreferredSize(new Dimension(60, 60));
        this.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g) {
        //获取控件当前大小
        width = this.getWidth();
        height = this.getHeight();
        fontSize = 14;
        //画布颜色等相关配置
        g2 = graphicsConfig(g);

        if (major <= 0) {
            major = to - from;
        }
        //类型判断，根据类型设置，进行相应绘制操作
        if (type.startsWith(RING) || type.equals(CIRCLE)) {
                drawArcAndCircle();
                drawArcAndCircleText();
        } else {
                drawLine();
                drawLineText();
        }
    }

    private Graphics2D graphicsConfig(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(this.getBackground());
        g2.fillRect(0, 0, (int) width, (int) height);
        g2.setColor(this.getForeground());
        g2.setStroke(new BasicStroke(1));
        g2.setFont(new Font(Font.SERIF, Font.PLAIN, fontSize));
        return  g2;
    }

    private void drawArcAndCircle() {
        //整体角度和起始弧度
        double angle = type.equals(CIRCLE) ? CIRCLE_ANGLE : toDouble(type.substring(ANGLE_INDEX));
        double angleStart = type.equals(CIRCLE) ? CIRCLE_ANGLE_START : (180 + (angle - 180) / 2) / 180 * Math.PI;
        //根据控件的大小来确定指针的长度
        double r = angle <= 180 ? Math.min(width / 2, height) : Math.min(width / 2, height / 2);
        double voff = angle <= 180 ? 0 : r;
        //确定每一个刻度所表示的弧度
        double dunit = (angle / 180 * Math.PI) / (to - from);
        //绘制出刻度盘
        drawArcScale(angleStart, dunit, r, voff);
        //绘制当前指针的指向
       drawArcValue(angleStart, dunit, r, voff, angle);
    }

    private void drawArcScale(double angleStart, double dunit,  double r, double voff) {
        for (int i = 0; i <= (to - from) / major; i++) {
            g2.draw(new Line2D.Double(Math.cos(angleStart - i * major * dunit) * r + width / 2, height - voff - Math.sin(angleStart - i * major * dunit) * r,
                    Math.cos(angleStart - i * major * dunit) * r * 0.75 + width / 2, height - voff - Math.sin(angleStart - i * major * dunit) * r * 0.75));
            if (minor > 0 && i < (to - from) / major) {
                for (int j = 1; j < major / minor; j++) {
                    if (i * major + j * minor < to - from) {
                        g2.draw(new Line2D.Double(Math.cos(angleStart - (i * major + j * minor) * dunit) * r + width / 2, height - voff - Math.sin(angleStart - (i * major + j * minor) * dunit) * r,
                                Math.cos(angleStart - (i * major + j * minor) * dunit) * r * 0.875 + width / 2, height - voff - Math.sin(angleStart - (i * major + j * minor) * dunit) * r * 0.875));
                    }
                }
            }
        }
    }

    private void drawArcValue(double angleStart, double dunit, double r, double voff, double angle) {
        if (value.length() > 0) {
            double val = toDouble(value);
            GeneralPath p = new GeneralPath();
            p.moveTo(Math.cos(angleStart - (val - from) * dunit) * r * 0.875 + width / 2, height - voff - Math.sin(angleStart - (val - from) * dunit) * r * 0.875);
            p.lineTo(Math.cos(angleStart - (val - from) * dunit + Math.PI * 0.5) * 2 + width / 2, height - voff - Math.sin(angleStart - (val - from) * dunit + Math.PI * 0.5) * 2);
            p.lineTo(Math.cos(angleStart - (val - from) * dunit - Math.PI * 0.5) * 2 + width / 2, height - voff - Math.sin(angleStart - (val - from) * dunit - Math.PI * 0.5) * 2);
            p.closePath();
            g2.fill(p);
            g2.setFont(new Font("", Font.BOLD, VALUE_FONT_SIZE));
            voff = angle <= 180 ? 10 : r - fontSize / 2;
            drawValue(g2, value + unit, (int) (width / 2 - getStrBounds(g2, value).getWidth() / 2), (int) (height - voff));
        }
    }

    private void drawArcAndCircleText() {
        double angle;
        double angleStart;
        if (type.equals(CIRCLE)) {
            angle = 360;
            angleStart = Math.PI / 2;
        } else {
            angle = toDouble(type.substring(4));
            angleStart = (180 + (angle - 180) / 2) / 180 * Math.PI;
        }
        double r = angle <= 180 ? Math.min(width / 2, height) : Math.min(width / 2, height / 2);
        double voff = angle <= 180 ? 0 : r;
        double dunit = (angle / 180 * Math.PI) / (to - from);
        int xoff = 0;
        int yoff = 0;
        double strAngle;
        for (int i = type.equals(CIRCLE) ? 1 : 0; i <= (to - from) / major; i++) {
            String str;
            str = format(from + i * major);
            strAngle = (angleStart - i * major * dunit + Math.PI * 2) % (Math.PI * 2);
            xoff = 0;
            yoff = 0;
            if (strAngle >= 0 && strAngle < Math.PI * 0.25) {
                xoff = (int) -getStrBounds(g2, str).getWidth();
                yoff = fontSize / 2;
                if (strAngle == 0 && angle == 180) {
                    yoff = 0;
                }
            } else if (near(strAngle, Math.PI * 0.5)) {
                xoff = (int) -getStrBounds(g2, str).getWidth() / 2;
                yoff = fontSize;
            } else if (strAngle >= Math.PI * 0.25 && strAngle < Math.PI * 0.5) {
                xoff = (int) -getStrBounds(g2, str).getWidth();
                yoff = fontSize;
            } else if (strAngle >= Math.PI * 0.5 && strAngle < Math.PI * 0.75) {
                yoff = fontSize;
            } else if (strAngle >= Math.PI * 0.75 && strAngle < Math.PI) {
                yoff = fontSize / 2;
            } else if (near(strAngle, Math.PI)) {
                xoff = 1;
                yoff = fontSize / 2;
                if (angle == 180) {
                    yoff = 0;
                }
            } else if (strAngle >= Math.PI && strAngle < Math.PI * 1.25) {
                yoff = fontSize / 4;
            } else if (near(strAngle, Math.PI * 1.5)) {
                xoff = (int) -getStrBounds(g2, str).getWidth() / 2;
            } else if (strAngle >= Math.PI * 1.5 && strAngle < Math.PI * 2) {
                xoff = (int) -getStrBounds(g2, str).getWidth();
            }
            g2.drawString(str, (int) (Math.cos(strAngle) * r * 0.75 + width / 2) + xoff, (int) (height - voff - Math.sin(strAngle) * r * 0.75) + yoff);
        }

    }

    private void drawLine() {
        if (width > height) {
            //计算单位，绘制每一个刻度
            double dunit = width / (to - from);
            drawHorizontalLineScale(dunit);
            drawHorizontalLineValue(dunit);
        } else {
            int max = (int) Math.max(getStrBounds(g2, format(from)).getWidth(), getStrBounds(g2, format(to)).getWidth());
            double dunit = height / (to - from);
            drawVerticalLineScale(max, dunit);
            drawVerticalLineValue(max, dunit);
        }
    }

    private void drawVerticalLineScale(double max, double dunit) {
        for (int i = 0; i <= (to - from) / major; i++) {
            g2.draw(new Line2D.Double(0, height - i * major * dunit, width - max, height - i * major * dunit));
            if (i < (to - from) / major && minor > 0) {
                for (int j = 1; j < major / minor; j++) {
                    g2.draw(new Line2D.Double(0, height - (i * major + j * minor) * dunit, (width - max) / 2, height - (i * major + j * minor) * dunit));
                }
            }
        }
    }

    private void drawHorizontalLineScale(double dunit) {
        for (int i = 0; i <= (to - from) / major; i++) {
            g2.draw(new Line2D.Double(i * major * dunit, 0, i * major * dunit, height - fontSize));
            if (i < (to - from) / major && minor > 0) {
                for (int j = 1; j < major / minor; j++) {
                    g2.draw(new Line2D.Double((i * major + j * minor) * dunit, 0, (i * major + j * minor) * dunit, (height - fontSize) / 2));
                }
            }
        }
    }

    private void drawVerticalLineValue(double max, double dunit) {
        if (value.length() > 0) {
            double val = toDouble(value);
            GeneralPath p = new GeneralPath();
            p.moveTo((width - max) / 2, height - (val - from) * dunit);
            p.lineTo(width - max, height - (val - from) * dunit - 4);
            p.lineTo(width - max, height - (val - from) * dunit + 4);
            p.closePath();
            g2.fill(p);
        }
    }

    private void drawHorizontalLineValue(double dunit) {
        //绘制指针,通过GeneralPath绘制，先将点移动到顶部，然后画出两条线
        if (value.length() > 0) {
            double val = toDouble(value);
            GeneralPath p = new GeneralPath();
            p.moveTo((val - from) * dunit, (height - fontSize) / 2);
            p.lineTo((val - from) * dunit - 4, height - fontSize);
            p.lineTo((val - from) * dunit + 4, height - fontSize);
            p.closePath();
            g2.fill(p);
        }
    }

    private void drawLineText() {
        if (width > height) {
            double dunit = width / (to - from);
            int off = 0;
            String str;
            for (int i = 0; i <= (to - from) / major; i++) {
                str = format(from + i * major);
                if (i == 0) {
                    str += unit;
                    off = 0;
                } else if (i == (to - from) / major) {
                    off = (int) -getStrBounds(g2, str).getWidth();
                } else {
                    off = (int) (-getStrBounds(g2, str).getWidth() / 2);
                }
                g2.drawString(str, (int) (i * major * dunit) + off, (int) (height - 2));
            }
            if (value.length() > 0) {
                double val = toDouble(value);
                value = format(val);
                if (val == from) {
                    off = 0;
                } else if (val == to) {
                    off = (int) -getStrBounds(g2, value + unit).getWidth();
                } else {
                    off = (int) (-getStrBounds(g2, value + unit).getWidth() / 2);
                }
                if ((height - fontSize) / 2 >= fontSize) {
                    drawValue(g2, value + unit, (int) ((val - from) * dunit) + off, (int) ((height - fontSize) / 2));
                } else {
                    drawValue(g2, value + unit, (int) ((val - from) * dunit) + off, fontSize);
                }
            }
        } else {
            double dunit = height / (to - from);
            int max = (int) Math.max(getStrBounds(g2, format(from)).getWidth(), getStrBounds(g2, format(to)).getWidth());
            int off = 0;
            for (int i = 0; i <= (to - from) / major; i++) {
                if (i == 0) {
                    off = 0;
                } else if (i == (to - from) / major) {
                    off = fontSize;
                } else {
                    off = fontSize / 2;
                }
                g2.drawString(format(from + i * major), (int) (width - max + 1), (int) (height - i * major * dunit) + off);
            }
            if (unit.length() > 0) {
                g2.drawString(unit, (int) ((width - max) / 2 + 1), (int) (height - fontSize));
            }
            if (value.length() > 0) {
                double val = toDouble(value);
                value = format(val);
                if (val == 0) {
                    off = 0;
                } else if (val == to) {
                    off = VALUE_FONT_SIZE;
                } else {
                    off = VALUE_FONT_SIZE / 2;
                }
                drawValue(g2, value + unit, (int) ((width - getStrBounds(g2, value + unit).getWidth()) / 2), (int) (height - (val - from) * dunit + off));
            }
        }
    }

    public void updateValue(String value) {
        invalidate();
    }

    private void drawValue(Graphics2D g2, String value, int x, int y) {
        g2.setFont(new Font(Font.SERIF, Font.BOLD, VALUE_FONT_SIZE));
        g2.drawString(value, x, y);
    }

    private String format(double d) {
        if ((int) d == d) {
            return String.valueOf((int) d);
        } else {
            return String.valueOf(d);
        }
    }

    private double toDouble(String string) {
        try {
            return Double.valueOf(string);
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean near(double d1, double d2) {
        return Math.round(d1 * 1000000) == Math.round(d2 * 1000000);
    }

    private static Rectangle2D getStrBounds(Graphics2D g, String str) {
        Font f = g.getFont();
        Rectangle2D rect = f.getStringBounds(str, g.getFontRenderContext());
        if (rect.getHeight() < f.getSize()) {
            rect.setFrame(rect.getX(), rect.getY(), rect.getWidth(), f.getSize() + 1);
        }
        return rect;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getFrom() {
        return from;
    }

    public double getMajor() {
        return major;
    }

    public void setMajor(double major) {
        this.major = major;
    }

    public double getMinor() {
        return minor;
    }

    public void setMinor(double minor) {
        this.minor = minor;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        try {
            JFrame f = new JFrame("速度展示");
            Container p = f.getContentPane();
            Dashboard dashboard = new Dashboard();
            dashboard.setType("ring240");
            dashboard.setForeground(Color.BLUE);
            dashboard.setBackground(Color.WHITE);
            dashboard.setUnit("M/S");
            dashboard.setValue("20");
            dashboard.setFrom(0);
            dashboard.setTo(50);
            dashboard.setMajor(5);
            dashboard.setMinor(1);
            p.add(dashboard);
            f.setSize(640, 480);
            f.setVisible(true);
            //模拟数据变化
            Thread.sleep(3000);
            dashboard.setValue("40");
            dashboard.repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}