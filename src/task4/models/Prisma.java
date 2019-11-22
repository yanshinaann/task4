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
    public Prisma(Vector3 rBegin, float len, int n, double u) {
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
        int m = n*3;
        //делаем верхний полигон
        List<Vector3> topPolygon = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            topPolygon.add(new Vector3((float)
                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * i / n)),
                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * i / n)),
                    rBegin.getZ()));
        }

        lines.add(new PolyLine3D(topPolygon, true));

        List<Vector3> bottomPolygon = new ArrayList<>();
        float dz = 0;
        LinkedList<Vector3> points = new LinkedList<>();

        //рисуем спиральки
        for (int j = 0; j < n; j++) {
          Vector3 vector3=  new Vector3((float)
                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
                    rBegin.getZ());
            points.add(vector3);
            for (int i = 0; i < m; i++) {
                points.add(new Vector3((float)
                        (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
                        (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
                        rBegin.getZ() + dz));
                dz += 0.09f;
                u0 += u;
            }
            lines.add(new PolyLine3D(points, true));
            bottomPolygon.add(points.get(m));
            u0=0;
            dz=0;
          points.clear();
        }
        lines.add(new PolyLine3D(bottomPolygon, true));

        //делаем нижний полигон
//        List<Vector3> bottomPolygon = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            bottomPolygon.add(new Vector3((float)
//                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * i / n) + rBegin.getX() / 2 * Math.cos(u0)),
//                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * i / n) + rBegin.getY() / 2 * Math.sin(u0)),
//                    rBegin.getZ() + 1f));
//        }
//        lines.add(new PolyLine3D(bottomPolygon, true));

//        float dz = 0;
//        LinkedList<Vector3> points = new LinkedList<>();
//        for (int j = 0; j < n; j++) {
//            for (int i = 0; i < 20; i++) {//points.add(topPolygon.get(0));
//                {
//                    points.add(new Vector3((float) (len + rBegin.getX() + rBegin.getX() / 2 * Math.cos(u0)),
//                            (float) (len + rBegin.getY() + rBegin.getY() / 2 * Math.sin(u0)), rBegin.getZ() + dz));
//                }
//                dz += 0.09f;
//                u0 += u;
//            }
//        }
//        lines.add(new PolyLine3D(points, true));

        //соединяем вершины
//        for (int i = 0; i < n; i++) {
//            lines.add(new PolyLine3D(Arrays.asList((new Vector3(topPolygon.get(i).getX(), topPolygon.get(i).getY(), topPolygon.get(i).getZ())),
//                    (new Vector3(bottomPolygon.get(i).getX(), bottomPolygon.get(i).getY(), bottomPolygon.get(i).getZ()))), true));
//        }

        return lines;
    }

}
