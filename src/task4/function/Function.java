package task4.function;

import task4.Field;
import task4.math.Vector3;
import task4.screen.ScreenPoint;

public class Function implements IFunction {
    Vector3 beginPoint;
    double angle;
    double m = 0;
    double dt = 0.0000001f;

    @Override
    public void update(double dt) {
        if (angle < 6.28319) {
           // m+=0.001;
            angle += 0.19;
        } else angle = 0;

    }

    @Override
    public Vector3 getStartPoint(Field f) {

        update(dt);
        Vector3 v3 = new Vector3(0, 0, 0);
        // float[] a = {(float)(angle + ( f.getWeight() * Math.sin(angle))), 0, 0};
        v3.setX(new float[]{ (float) ((0.3 * Math.sin(angle))), 0, 0});
        v3.setY( new float[]{0, (float) ((0.3 * Math.sin(angle))), 0});
      //   v3.setZ(new float[]{0, 0, (float)m});
//        if (isPoint(v2, f)) {
//            angle = 0;
//            // v2.setX(f.getRectangle().getRight()-f.getRectangle().getWidth()/2 * Math.sin(angel));
//            // cnt = 0;
//        }
        return v3;

    }
//
//    public boolean isPoint(ScreenPoint v2, Field f) {
//    // if ((v2.getI() > f.getRight()) || (v2.getI() < f.getLeft())) {
//            return true;
//        }
//        return false;
//    }
}

