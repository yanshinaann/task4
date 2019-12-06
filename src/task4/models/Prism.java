/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package task4.models;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import task4.Field;
import task4.function.Function;
import task4.math.Vector3;
import task4.third.IModel;
import task4.third.PolyLine3D;

/**
 * Описывает параллелипипед по двум диагональным точкам
 *
 * @author Alexey
 */
public class Prism implements IModel {

    private int n;
    Vector3 rBegin;
    double u;
    float len;
    Function function;
    Field field = new Field(0.03, 0.03);

    /**
     * Создаёт экземпляр призмы
     * <p>
     */

    public Prism(Vector3 rBegin, float len, int n, double u, Function function) {
        this.rBegin = rBegin;
        this.n = n;
        this.len = len;
        this.u = u;
        this.function = function;

    }


    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();
        double u0 = 0;
        int m = n * 3;
        Vector3 rChange;


        List<Vector3> bottomPolygon = new ArrayList<>();
        float dz = 0;
        LinkedList<Vector3> points = new LinkedList<>();
        LinkedList<Vector3> begin = new LinkedList<>();

        for (int c = 0; c < n*(n); c++) {
           //
            //begin.add(rChange);
            rBegin = function.getStartPoint(field);
            begin.add(rBegin);
            for (int j = 0; j < n; j++) {
                   //
                //rChange = function.getStartPoint(field);

                Vector3 vector3 = new Vector3((float)
                        (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
                        (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
                        rBegin.getZ());

                points.add(vector3);
                for (int i = 0; i < n*n; i++) {
                        //
                    //rBegin = function.getStartPoint(field);
                    points.add(new Vector3((float)
                            (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
                            (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
                            rBegin.getZ()));
                    dz += 0.1f;
                    u0 += u;

                }
                //rBegin = function.getStartPoint(field);
                lines.add(new PolyLine3D(points, false));
                bottomPolygon.add(points.get(n));
                u0 = 0;
                dz = 0;
                points.clear();

            }
//            rBegin = function.getStartPoint(field);
            lines.add(new PolyLine3D(bottomPolygon, true));
            lines.add(new PolyLine3D(points, false));
            bottomPolygon.clear();
        }

        return lines;
    }
}
//        LinkedList<PolyLine3D> lines = new LinkedList<>();
//        double u0 = 0;
//        int m = n * 3;
//        Vector3 rChange;
//
//
//        List<Vector3> bottomPolygon = new ArrayList<>();
//        float dz = 0;
//        LinkedList<Vector3> points = new LinkedList<>();
//        LinkedList<Vector3> begin = new LinkedList<>();
//
//
//        for (int j = 0; j < n; j++) {
//            //rChange = function.getStartPoint(field);
//            // rBegin = function.getStartPoint(field);
//            Vector3 vector3 = new Vector3((float)
//                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                    rBegin.getZ());
//
//            points.add(vector3);
//
//
//        }
//        begin.add(rBegin);
//        lines.add(new PolyLine3D(points, true));
//        points.clear();
//
//        for (int j = 0; j < n; j++) {
//            //rChange = function.getStartPoint(field);
//            rBegin = function.getStartPoint(field);
//            Vector3 vector3 = new Vector3((float)
//                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                    rBegin.getZ());
//
//            points.add(vector3);
//
//
//        }
//        begin.add(rBegin);
//        lines.add(new PolyLine3D(points, true));
//        lines.add(new PolyLine3D(begin, true));
//
//        return lines;
//    }
//


//


//        for (int j = 0; j < n; j++) {
//          Vector3 vector3=  new Vector3((float)
//                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                    rBegin.getZ());
//
//
//            points.add(vector3);
//            for (int i = 0; i < m; i++) {
//                points.add(new Vector3((float)
//                        (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                        (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                        rBegin.getZ() + dz));
//                dz += 0.09f;
//                u0 += u;
//
//            }
//            lines.add(new PolyLine3D(points, false));
//            bottomPolygon.add(points.get(m));
//            u0=0;
//            dz=0;
//          points.clear();
//        }
//
//        lines.add(new PolyLine3D(bottomPolygon, true));


//рабочий метода
//        LinkedList<PolyLine3D> lines = new LinkedList<>();
//        double u0 = 0;
//        int m = n*3;
//        Vector3 rChange ;
//
//        //делаем верхний полигон
//        List<Vector3> topPolygon = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            topPolygon.add(new Vector3((float)
//                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * i / n)),
//                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * i / n)),
//                    rBegin.getZ()));
//        }
//
//        lines.add(new PolyLine3D(topPolygon, true));
//
//        List<Vector3> bottomPolygon = new ArrayList<>();
//        float dz = 0;
//        LinkedList<Vector3> points = new LinkedList<>();
//
//        //рисуем спиральки и нижний полигон
//        for (int j = 0; j < n; j++) {
//          Vector3 vector3=  new Vector3((float)
//                    (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                    (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                    rBegin.getZ());
//
//
//            points.add(vector3);
//            for (int i = 0; i < m; i++) {
//                points.add(new Vector3((float)
//                        (rBegin.getX() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                        (float) (rBegin.getY() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                        rBegin.getZ() + dz));
//                dz += 0.09f;
//                u0 += u;
//
//            }
//            lines.add(new PolyLine3D(points, false));
//            bottomPolygon.add(points.get(m));
//            u0=0;
//            dz=0;
//          points.clear();
//        }
//
//        lines.add(new PolyLine3D(bottomPolygon, true));


//не 6 полилиний, а поверхности; окружность, трилистникж интерпретатор не надо
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




