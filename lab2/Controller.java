package sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;

public class Controller {

    @FXML
    private Line l2;

    @FXML
    private Line l1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane pane;

    static List<Double> ks = new ArrayList<>();

    @FXML
    void initialize() {

        WritableImage writableImage = new WritableImage(600, 600);
        ImageView imageView = new ImageView(writableImage);
        imageView.setFitHeight(600);
        imageView.setFitWidth(600);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        pane.getChildren().addAll(imageView);

        Random rand = new Random();
        List<Circle> circles = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        int z = 3 + (int) (Math.random() * 10);
        for (int i = 0; i < z; i++) {
            points.add(new Point(rand.nextInt(591) - 295, rand.nextInt(591) - 295));
        }

        List<Integer> indexes = new ArrayList<>();
        List<Point> part_left = new ArrayList<>();
        List<Point> part_right = new ArrayList<>();
        List<Double> ks_left = new ArrayList<>();
        List<Double> ks_right = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            Circle c = new Circle(points.get(i).x, points.get(i).y, 1);
            circles.add(c);
            indexes.add(i);
            if (((points.get(i).x <= 0) && (points.get(i).y >= 0)) || ((points.get(i).x < 0) && (points.get(i).y < 0))) {
                part_left.add(points.get(i));
            } else {
                part_right.add(points.get(i));
            }
        }

        //System.out.println("=================================");
        //System.out.println(part_left.size());
        for (int i = 0; i < part_left.size(); i++) {
            Double r = (Double.valueOf(part_left.get(i).y) / Double.valueOf(part_left.get(i).x));
            ks_left.add(r);
        }

        //System.out.println("=================================");
        //System.out.println(part_right.size());
        for (int i = 0; i < part_right.size(); i++) {
            Double r = (Double.valueOf(part_right.get(i).y) / Double.valueOf(part_right.get(i).x));
            ks_right.add(r);
        }


        //show(circles);

        part_left = sort(ks_left, part_left);
        part_right = sort(ks_right, part_right);

        List<Point> points1 = new ArrayList<>();
        points1.addAll(part_left);
        points1.addAll(part_right);

        for (int i = 0; i < points1.size() - 1; i++) {
            Line line = new Line();
            line.setStartX(points1.get(i).x + 300);
            line.setStartY(300 - points1.get(i).y);
            line.setEndX(points1.get(i + 1).x + 300);
            line.setEndY(300 - points1.get(i + 1).y);
            line.setStrokeWidth(3);
            //line.setStrokeWidth(4 * (i + 1));
            pane.getChildren().addAll(line);
        }

        Line line = new Line();
        line.setStartX(points1.get(0).x + 300);
        line.setStartY(300 - points1.get(0).y);
        line.setEndX(points1.get(points1.size() - 1).x + 300);
        line.setEndY(300 - points1.get(points1.size() - 1).y);
        line.setStrokeWidth(3);
        pane.getChildren().addAll(line);

        //l1.setVisible(false);
        //l2.setVisible(false);
        l1.setStroke(Color.WHITE);
        l2.setStroke(Color.WHITE);
        WritableImage image = pane.snapshot(new SnapshotParameters(), null);
        //l1.setVisible(true);
        //l2.setVisible(true);
        l1.setStroke(Color.BLACK);
        l2.setStroke(Color.BLACK);
        File file = new File("image.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }catch (IOException e) {}

        // ТУТА Я ЗАКОММЕНТИЛ ВСЕ

        //Point start = points.get(0);
        //indexes.remove((Object) 0);

        /*List<Line> lines = new ArrayList<>();
        while (indexes.size() > 0) {
            Double min = getCos(start, points.get(indexes.get(0)));
            int index = indexes.get(0);
            for (int i = 0; i < indexes.size(); i++) {
                Double result = getCos(start, points.get(indexes.get(i)));
                if ((result > 0) && (min > result)) {
                    min = result;
                    index = indexes.get(i);
                }
            }
            Line l = new Line();
            l.setStartX(start.x);
            l.setStartY(start.y);
            l.setEndX(points.get(index).x);
            l.setEndY(points.get(index).y);
            lines.add(l);
            indexes.remove((Object) index);
            start = points.get(index);
        }

        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).setStartX(lines.get(i).getStartX() + 300);
            lines.get(i).setStartY(300 - lines.get(i).getStartY());
            lines.get(i).setEndX(lines.get(i).getEndX() + 300);
            lines.get(i).setEndY(300 - lines.get(i).getEndY());
            pane.getChildren().addAll(lines.get(i));
        }

        for (int i = 0; i < ks.size(); i++) {
            System.out.println(ks.get(i));
        }*/

        /*Double min = getCos(points.get(1), points.get(0));
        int index = 1;

        for (int i = 2; i < points.size(); i++) {
            Double result = getCos(points.get(i), points.get(0));
            System.out.println(result);
            if ((result > 0) && (min > Math.abs(result))) {
                min = Math.abs(result);
                index = i;
            }
        }

        Line l = new Line();
        l.setStartX(points.get(0).x + 300);
        l.setStartY(300 - points.get(0).y);
        l.setEndX(points.get(index).x + 300);
        l.setEndY(300 - points.get(index).y);
        pane.getChildren().addAll(l);*/
        pane.setOnMouseClicked(event -> {

            File file1 = new File("image.png");
            List<java.awt.Color> pixels = new ArrayList<>();
            try {
                BufferedImage image1 = ImageIO.read(file1);
                java.awt.Color[][] array = new java.awt.Color[600][600];
                for (int i = 0; i < image1.getHeight() - 1; i++) {
                    for (int j = 0; j < image1.getWidth() - 1; j++) {
                        array[i][j] = (new java.awt.Color(image1.getRGB(i, j)));
                        //System.out.println(new java.awt.Color(image1.getRGB(i, j)));
                    }
                }

            /*for (int i = 0; i < image1.getHeight() - 1; i++) {
                for (int j = 0; j < image1.getWidth() - 1; j++) {
                    if ((array[i][j].getBlue() == 255) && (array[i][j].getRed() == 255) && (array[i][j].getGreen() == 255)) {
                        System.out.print("W");
                    } else {
                        System.out.print("B");
                    }
                }
                System.out.println();
            }*/


                List<Point> stack = new ArrayList<>();
                //Point start = new Point(300, 300);
                Point start = new Point((int)event.getX(), (int)event.getY());
                stack.add(start);
                int y = 0;
                while (stack.size() != 0) {
                    //while (y < 100000) {
                    y = y + 1;
                    Point p = stack.get(0);
                    stack.remove(p);
                    array[p.x][p.y] = new java.awt.Color(0, 0, 0);
                    /*Rectangle r = new Rectangle();
                    r.setX(p.x);
                    r.setY(p.y);
                    r.setWidth(1);
                    r.setHeight(1);
                    r.setStroke(Color.BLACK);
                    pane.getChildren().add(r);*/
                    pixelWriter.setColor(p.x, p.y, Color.BLACK);


                /*if (y == 500000) {
                    l1.setStroke(Color.WHITE);
                    l2.setStroke(Color.WHITE);
                    image = pane.snapshot(new SnapshotParameters(), null);

                    l1.setStroke(Color.BLACK);
                    l2.setStroke(Color.BLACK);
                    file = new File("image.png");
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                    } catch (IOException e) {
                    }
                    Scanner scanner = new Scanner(System.in);
                    scanner.next();
                }*/
                    //System.out.println(stack.size() + " ~ " + y);
                    try {
                        if ((array[p.x + 1][p.y].getRed() != 0) && (array[p.x + 1][p.y].getGreen() != 0) && (array[p.x + 1][p.y].getBlue() != 0)) {
                            stack.add(new Point(p.x + 1, p.y));
                            //System.out.println("Справа");
                            array[p.x + 1][p.y] = new java.awt.Color(0, 0, 0);
                        }

                        if ((array[p.x - 1][p.y].getRed() != 0) && (array[p.x - 1][p.y].getGreen() != 0) && (array[p.x - 1][p.y].getBlue() != 0)) {
                            stack.add(new Point(p.x - 1, p.y));
                            //System.out.println("Слева");
                            array[p.x - 1][p.y] = new java.awt.Color(0, 0, 0);
                        }

                        if ((array[p.x][p.y + 1].getRed() != 0) && (array[p.x][p.y + 1].getGreen() != 0) && (array[p.x][p.y + 1].getBlue() != 0)) {
                            stack.add(new Point(p.x, p.y + 1));
                            //System.out.println("Сверху");
                            array[p.x][p.y + 1] = new java.awt.Color(0, 0, 0);
                        }

                        if ((array[p.x][p.y - 1].getRed() != 0) && (array[p.x][p.y - 1].getGreen() != 0) && (array[p.x][p.y - 1].getBlue() != 0)) {
                            stack.add(new Point(p.x, p.y - 1));
                            //System.out.println("Снизу");
                            array[p.x][p.y - 1] = new java.awt.Color(0, 0, 0);
                        }
                    } catch (Exception e) {}
                    //Scanner scanner = new Scanner(System.in);
                    //scanner.next();
                }
            } catch (IOException e) {}

            l1.setStroke(Color.WHITE);
            l2.setStroke(Color.WHITE);
            WritableImage image2 = pane.snapshot(new SnapshotParameters(), null);

            l1.setStroke(Color.BLACK);
            l2.setStroke(Color.BLACK);
            file1 = new File("image.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image2, null), "png", file);
            } catch (IOException e) {
            }

        });

    }

    public List<Point> sort(List<Double> x, List<Point> p) {
        boolean flag = true;
        while (flag == true) {
            flag = false;
            for (int i = 0; i < x.size() - 1; i++) {
                //System.out.println(x.get(i));
                if (x.get(i) < x.get(i + 1)) {
                    Double t = x.get(i);
                    Point p_temp = p.get(i);
                    x.set(i, x.get(i + 1));
                    p.set(i, p.get(i + 1));
                    x.set(i + 1, t);
                    p.set(i + 1, p_temp);
                    flag = true;
                }
            }
        }
        //System.out.println(x.size());;
        //for (int i = 0; i < x.size(); i++) {
            //System.out.println(x.get(i));
        //}
        return p;
    }

    public void show(List<Circle> c) {
        for (int i = 0; i < c.size(); i++) {
            //System.out.println((c.get(i).getCenterX()) + " : " + (c.get(i).getCenterY()));
            //ks.add(c.get(i).getCenterX() * (-1) / c.get(i).getCenterY());
            Circle t = new Circle(c.get(i).getCenterX() + 300, 300 - c.get(i).getCenterY(), 3);
            pane.getChildren().add(t);
        }
    }

    public Double getCos(Point a, Point b) {
        //return ((a.x * b.x + a.y * b.y) / (Math.sqrt(a.x * a.x + a.y * a.y) + Math.sqrt(b.x * b.x + b.y * b.y)));
        double ma = Math.sqrt(a.x*a.x + a.y*a.y);
        double mb = Math.sqrt(b.x*b.x + b.y*b.y);
        double sc = a.x * b.x + a.y * b.y;
        double res = Math.acos(sc / ma / mb);
        //System.out.println(res);
        return res;
    }
}
