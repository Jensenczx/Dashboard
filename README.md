#README

###JAVA仪表盘

####Support
**Type：** 线性仪表，扇形仪表，圆形仪表

**Function：**支持对于各类表盘的颜色，大小，单位，刻度的设置


####Usage

```
	Dashboard dashboard = new Dashboard();
	//设置类型，ring+角度
    dashboard.setType("ring240");
    //设置前景颜色，表盘颜色
    dashboard.setForeground(Color.BLUE);
    //设置表盘背景色
    dashboard.setBackground(Color.WHITE);
    //设置单位
    dashboard.setUnit("M/S");
    //设置仪表盘的当前值
    dashboard.setValue("20");
    //设置仪表盘的刻度，从小到大
    dashboard.setFrom(0);
    dashboard.setTo(50);
    //设置大的刻度差距
    dashboard.setMajor(5);
    //设置最小的刻度
    dashboard.setMinor(1);
```
 