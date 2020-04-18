package com.personalstudy.designpattern.principle.depend.ocp;

/**
 * @author congyaozhu
 * @date 2020-02-06 16:17
 * @description 开闭原则demo
 */
public class Ocp {

    public static void main(String[] args) {

        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Circular());
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new OtherGraphic());
    }
}

//这是一个用于绘图的类 [使用方]
class GraphicEditor{

    public void drawShape(Shape shape){
        shape.draw();
    }
}
// 基类
abstract class Shape{
    int m_type;
    abstract void draw();
}

class Rectangle extends Shape{

    public Rectangle() {
        super.m_type = 1;
    }

    @Override
    void draw() {
        System.out.println(" 绘制矩形");
    }
}

class Circular extends Shape{

    public Circular() {
        super.m_type = 2;
    }

    @Override
    void draw() {
        System.out.println(" 绘制圆形");
    }
}

class OtherGraphic extends Shape{

    @Override
    void draw() {
        System.out.println(" 绘制其他图形 ");
    }
}

