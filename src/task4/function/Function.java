package task4.function;

import task4.Field;
import task4.math.Vector3;
import task4.math.Vector4;
import task4.screen.ScreenPoint;

public class Function implements IFunction {
    Vector3 beginPoint;
    double angle;
    double m = 0;
    double dt = 0.1f;
    boolean isTurned;
    double len = 0.6;

    @Override
    public void update(double dt) {

        if (angle <= 2 * Math.PI) {
            angle += len;
            //isTurned = false;
        } else {
            angle = 0;
            isTurned = true;
        }


    }

    @Override
    public Vector3 getStartPoint(Field f) {

        update(dt);

        Vector3 v3 = new Vector3(0, 0, 0);
        // float[] a = {(float)(angle + ( f.getWeight() * Math.sin(angle))), 0, 0};

//        v3.setX(new float[]{(float) ((len * Math.cos(angle))), 0, 0});
//        v3.setY(new float[]{0, (float) ((len * Math.sin(angle))), 0});
//
//        v3.setZ(new float[]{0, 0, 0});
        //  if (isTurned) {
        v3.setX(new float[]{(float) ((len * Math.cos(angle))), 0, 0});
        v3.setY(new float[]{0, (float) ((len * Math.sin(angle))), 0});
        v3.setZ(new float[]{0, 0, 0});
        // }


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

