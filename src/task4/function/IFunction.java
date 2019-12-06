package task4.function;

import task4.Field;
import task4.math.Vector3;
import task4.screen.ScreenPoint;

import java.awt.*;
import java.util.ArrayList;

public interface IFunction {
    void update(double dt);

  //  void drawPath(Graphics2D g, Field f, ScreenPoint tl, ArrayList<ScreenPoint> points);

   Vector3 getStartPoint(Field f);
}
