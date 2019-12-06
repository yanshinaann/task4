/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package task4.screen;

/**
 * Класс, описывающий координаты экранной точки.
 * @author Alexey
 */
public class ScreenPoint {
    int i, j;

    public ScreenPoint(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
