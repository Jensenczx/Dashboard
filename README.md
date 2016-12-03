#README

###JAVA Dashboard

####Support
**Type：** 

- 线性仪表
- 扇形仪表
- 圆形仪表
- 中间刻度仪表盘

**Function：**

- 表盘颜色设置
- 表盘尺寸大小
- 表盘单位显示
- 表盘单位刻度设置

####Usage

```
	Dashboard dashboard = new Dashboard();
	//设置类型，arc+角度
    dashboard.setType("arc240");
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
```
 
### Update
 
 - 解决了控件较小问题
 - 添加了颜色自定义
 - 对内部代码进行了部分更新
 
 
### Implement note
- [基于Java Swing的仪表盘实现](https://segmentfault.com/a/1190000007683729)