package sample;

import java.util.ArrayList;
import java.util.List;

public class Coordinate {
    public Point A;
    public Point B;
    public Point C;
    public Point D;

    public Coordinate(Point a, Point b, Point c, Point d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        points.add(A);
        points.add(B);
        points.add(C);
        points.add(D);
        return points;
    }
}
