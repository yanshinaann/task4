/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package task4.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import task4.math.Vector3;
import task4.third.IModel;
import task4.third.PolyLine3D;

/**
 * Описывает параллелипипед по двум диагональным точкам
 *
 * @author Alexey
 */
public class Prisma implements IModel {
    private Vector3 LTF, RBN;
    private int n;
    Vector3 rBegin, rEnd, a;
    double u;
    double aX, aY, aZ;
    float len;//сделать норм

    /**
     * Создаёт экземпляр параллелипипеда
     * <p>
     * //* @param LTF Левая Верхняя Дальняя точка (Left Top Far)
     * // * @param RBN Правая Нижняя Ближняя точка (Right Bottom Near)
     */
//    public Prisma(Vector3 LTF, Vector3 RBN) {
//        this.LTF = LTF;
//        this.RBN = RBN;
//    }
    public Prisma(Vector3 rBegin,  float len, int n, double u) {
        this.rBegin = rBegin;
        this.n = n;
        this.len = len;
        this.u = u;

    }

//this.aX = rBegin.getX()+ len*Math.cos(2*Math.PI*i/n);
//        this.aY = rBegin.getY()+ len*Math.sin(2*Math.PI*i/n);
//        this.aZ = 1;
//        this.a = (aX,aY, aZ);
//a=r*2*sin(360/2n)

    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();
double u0 = 0;
        //делаем верхний полигон
        List<Vector3> topPolygon = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            topPolygon.add(new Vector3((float)
                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * i / n)),
                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * i / n)),
                    rBegin.getZ()));
        }

        lines.add(new PolyLine3D(topPolygon, true));
        //меняем угол поворота(сделать по-нормальному в конструкторе)
        u0 += u;
        //делаем нижний полигон
        List<Vector3> bottomPolygon = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            bottomPolygon.add(new Vector3((float)
                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * i / n)),
                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * i / n)),
                    rBegin.getZ() + 1f));
        }
        lines.add(new PolyLine3D(bottomPolygon, true));

        //соединяем вершины
        for (int i = 0; i < n; i++) {
            lines.add(new PolyLine3D(Arrays.asList((new Vector3(topPolygon.get(i).getX(), topPolygon.get(i).getY(), topPolygon.get(i).getZ())),
                    (new Vector3(bottomPolygon.get(i).getX(), bottomPolygon.get(i).getY(), bottomPolygon.get(i).getZ()))), true));
        }

        return lines;
    }

}
