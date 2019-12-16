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
import task4.math.Matrix4;
import task4.math.Matrix4Factories;
import task4.math.Vector3;
import task4.math.Vector4;
import task4.third.IModel;
import task4.third.PolyLine3D;

import static task4.math.Matrix4Factories.rotationXYZ;

/**
 * Описывает параллелипипед по двум диагональным точкам
 *
 * @author Alexey
 */
public class Prism implements IModel {

    private int n;
    Vector3 rBegin;
    Vector3 rChange;
    double u;
    float len;
    Function function;
    boolean isTurned = false;
    Field field = new Field(0.03, 0.03);
    Vector3 vX = new Vector3(1, 0, 0);

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
        float dz = 0;
        //  Vector3 rChange;


        List<Vector3> bottomPolygon = new ArrayList<>();
        LinkedList<Vector3> points = new LinkedList<>();
        LinkedList<Vector3> begin = new LinkedList<>();
        LinkedList<Vector3> change2 = new LinkedList<>();
        LinkedList<Vector3> change22 = new LinkedList<>();
        LinkedList<Vector3> pointsLines = new LinkedList<>();


        for (int i = 0; i < n * 2; i++) {
            rChange = function.getStartPoint(field);

            for (int j = 0; j < n; j++) {
                u0 += u;
                isTurned = false;
                Vector3 beginV3 = new Vector3((float)
                        (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
                        (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
                        (float) (rBegin.getZ()));

                Vector3 changedXV3 = rotateV(beginV3);
                change2.add(changedXV3);
                Vector3 changedXV32 = rotateV(beginV3);
                change22.add(changedXV32);

                // points.add(changedXV3);

            }

            //

            lines.add(new PolyLine3D(change2, true));
            lines.add(new PolyLine3D(change22, true));
            change2.clear();
            change22.clear();
            // points.clear();
            //pointsLines.clear();
            //bottomPolygon.clear();

        }
        //lines.add(new PolyLine3D(begin, true));
        // lines.add(new PolyLine3D(begin, true));

        return lines;
    }

    private Vector3 rotateV(Vector3 beginV3) {
        double angX = angel(vX, rChange);
        Matrix4 matrX = rotationXYZ(angX, Matrix4Factories.Axis.X);
        Vector4 beginV4 = new Vector4(beginV3, 1f);
        Vector3 changedXV3 = matrX.mul(beginV4).asVector3();
        return changedXV3;
    }

    public double angel(Vector3 v1, Vector3 v2) {
        double ang = 0;
        ang = (v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ()) / (v1.length()) / v2.length();
        if (!isTurned) ang = 2 * Math.PI - Math.acos(ang);
        else ang = Math.acos(ang);
        isTurned = true;
        return ang;

    }
//
//                Vector3 vX = new Vector3(1, 0, 0);
//                Vector3 b =  new Vector3(rChange.getX(), rChange.getY(), 0);
//                double angX = angel(vX, b);
//                Matrix4 matrX = rotationXYZ(angX, Matrix4Factories.Axis.X);
//                Vector4 beginV4 = new Vector4(beginV3, 1f);
//                Vector3 changedXV3 = matrX.mul(beginV4).asVector3();
//
//
//                Vector4 changedXV4 = new Vector4(changedXV3, 1f);
//                Vector3 vZ = new Vector3(0, 0, 1);
//                double angZ = angel(vZ, rChange);
//                Matrix4 matrZ = rotationXYZ(angZ, Matrix4Factories.Axis.Z);
//                Vector3 changedZV3 = matrZ.mul(changedXV4).asVector3();
//               changedZV3.setX(new float[]{changedZV3.getX()+rChange.getY(),0,0});
//               changedZV3.setY(new float[]{0,changedZV3.getY()+rChange.getX(),0,0});
//               changedZV3.setZ(new float[]{0,0,changedZV3.getZ()+rChange.getZ(),0,0});
//                change2.add(changedZV3);
//

    //rBegin = function.getStartPoint(field);
//            begin.add(rBegin);
//            for (int j = 0; j < n; j++) {
//                //
//                //rChange = function.getStartPoint(field);
//                // rBegin = function.getStartPoint(field);
//
//                Vector3 vector3 = new Vector3((float)
//                        (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                        (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                        (float) (rBegin.getZ()));
//
//
//                double ang2 = angel(rChange, rBegin);
//                Matrix4 m2;
//                m2 = rotationXYZ(ang2, Matrix4Factories.Axis.Y);
//
//                Vector4 v42 = new Vector4(vector3, 1f);
//                points.add(  m2.mul(v42).asVector3());
////
////                Vector4 v5 = new Vector4(vector3, 1.3f);
////                v5.normalized();
////                points.add(v5.asVector3());
//                //rBegin = function.getStartPoint(field);
//                for (int i = 0; i < n; i++) {
//                    //
//                    //поворачиваем по проекции , поворачиваем на улог, фигачим в новое место
//                    Vector3 vector = new Vector3((float)
//                            (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//                            (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//                            (float) (rBegin.getZ()
//                            ));
//
//                    double ang1 = angel(rChange, rBegin);
//                    Matrix4 m1;
//                    m1 = rotationXYZ(ang1, Matrix4Factories.Axis.X);
//                    Vector4 v4 = new Vector4(vector, 1f);
//                    points.add(  m1.mul(v4).asVector3());
////                    points.add(new Vector3((float)
////                            (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
////                            (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
////                            (float) (rBegin.getZ() +dz
////                            )));
//                    dz += 0.1f;
//                    u0 += u;
//
//                }
//                //rBegin = function.getStartPoint(field);
//               // lines.add(new PolyLine3D(points, false));
//                bottomPolygon.add(points.get(n));
//                u0 = 0;
//                dz = 0;
//                points.clear();
//
//            }
////            rBegin = function.getStartPoint(field);
//            lines.add(new PolyLine3D(bottomPolygon, true));
//            lines.add(new PolyLine3D(points, false));
//            lines.add(new PolyLine3D(begin, false));
//            bottomPolygon.clear();
//


//    public static double angel2(Vector3 v1, Vector3 v2) {
//        double ang = 0;
//        ang = (v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ()) / (v1.length()) / v2.length();
//        ang = Math.acos(ang);
//        return ang;
//
//    }
}
//
//    LinkedList<PolyLine3D> lines = new LinkedList<>();
//    double u0 = 0;
//    int m = n * 3;
//    Vector3 rChange;
//
//
//    List<Vector3> bottomPolygon = new ArrayList<>();
//    float dz = 0;
//    LinkedList<Vector3> points = new LinkedList<>();
//    LinkedList<Vector3> begin = new LinkedList<>();
//
//        for (int c = 0; c < n * 3; c++) {
//        //
//        rChange = rBegin;
//        //rBegin = function.getStartPoint(field);
//        begin.add(rBegin);
//        for (int j = 0; j < n; j++) {
//        //
//        //rChange = function.getStartPoint(field);
//        // rBegin = function.getStartPoint(field);
//
//        Vector3 vector3 = new Vector3((float)
//        (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//        (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//        (float) (rBegin.getZ()));
//
//
//        double ang2 = angel(rChange, rBegin);
//        Matrix4 m2;
//        m2 = rotationXYZ(ang2, Matrix4Factories.Axis.Y);
//
//        Vector4 v42 = new Vector4(vector3, 1f);
//        points.add(  m2.mul(v42).asVector3());
////
////                Vector4 v5 = new Vector4(vector3, 1.3f);
////                v5.normalized();
////                points.add(v5.asVector3());
//        //rBegin = function.getStartPoint(field);
//        for (int i = 0; i < c; i++) {
//        //
//        //поворачиваем по проекции , поворачиваем на улог, фигачим в новое место
//        Vector3 vector = new Vector3((float)
//        (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
//        (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
//        (float) (rBegin.getZ()
//        ));
//
//        double ang1 = angel(rChange, rBegin);
//        Matrix4 m1;
//        m1 = rotationXYZ(ang1, Matrix4Factories.Axis.X);
//        Vector4 v4 = new Vector4(vector, 1f);
//        points.add(  m1.mul(v4).asVector3());
////                    points.add(new Vector3((float)
////                            (rBegin.getX() + len * Math.sin(u0 + 2 * Math.PI * j / n)),
////                            (float) (rBegin.getY() + len * Math.cos(u0 + 2 * Math.PI * j / n)),
////                            (float) (rBegin.getZ() +dz
////                            )));
//        dz += 0.1f;
//        u0 += u;
//
//        }
//        //rBegin = function.getStartPoint(field);
//        // lines.add(new PolyLine3D(points, false));
//        bottomPolygon.add(points.get(c));
//        u0 = 0;
//        dz = 0;
//        points.clear();
//
//        }
////            rBegin = function.getStartPoint(field);
//        lines.add(new PolyLine3D(bottomPolygon, true));
//        lines.add(new PolyLine3D(points, false));
//        lines.add(new PolyLine3D(begin, false));
//        bottomPolygon.clear();
//        }
//


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




