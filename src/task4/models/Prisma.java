/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package task4.models;

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
    private int rad, n;
    Vector3 rBegin, rEnd, a;
    double aX, aY, aZ;
    int len;//сделать норм

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
    public Prisma(Vector3 rBegin, Vector3 rEnd, int len, int n) {
        this.rBegin = rBegin;
        this.rEnd = rEnd;
        this.n = n;
        this.len = len;

    }

//this.aX = rBegin.getX()+ len*Math.cos(2*Math.PI*i/n);
//        this.aY = rBegin.getY()+ len*Math.sin(2*Math.PI*i/n);
//        this.aZ = 1;
//        this.a = (aX,aY, aZ);
//a=r*2*sin(360/2n)

    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();
        for (int i = 1; i < n; i++) {
            lines.add(new PolyLine3D(Arrays.asList(new Vector3((float)
                    (rBegin.getX() + len * Math.cos(2 * Math.PI * i / n)),
                    (float) (rBegin.getY() + len * Math.sin(2 * Math.PI * i / n)),
                    rBegin.getZ())), true));
        }
        /*Дальняя сторона (Z фиксирован и вязт у LTF)*/
//        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
//                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
//                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
//                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()),
//                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ())
//        }), true));
//        /*Ближняя сторона  (Z фиксирован и вязт у RBN)*/
//        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
//                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
//                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ())
//        }), true));
//
//        /*Верхняя сторона (Y фиксирован и вязт у LTF)*/
//        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
//                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
//                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ())
//        }), true));
//        /*Нижняя сторона (Y фиксирован и вязт у RBN)*/
//        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
//                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
//                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())
//        }), true));
//
//        /*Левая сторона (X фиксирован и вязт у LTF)*/
//        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
//                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
//                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
//                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
//                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ())
//        }), true));
//        /*Правая сторона (X фиксирован и вязт у RBN)*/
//        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
//                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()),
//                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
//                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())
//        }), true));

        return lines;
    }

}
