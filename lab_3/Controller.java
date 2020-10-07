package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button lolButton;

    public int s = 0;

    public static List<Point> points = new ArrayList<>();

    public static List<Line> lines = new ArrayList<>();

    public static int id = 4;

    @FXML
    void initialize() {

        points.add(new Point(0, 0, 1));
        //points.add(new Point(150, 0, 2));
        //points.add(new Point(150, 150, 3));
        //points.add(new Point(0, 150, 4));

        lolButton.setOnAction(event -> {

            showPoint(points, s);

            for (int i = 0; i < points.size(); i++) {
                points.get(i).id = getId();
            }

            List<Point> temp_mas = new ArrayList<>();
            List<Line> temp_mas_1 = new ArrayList<>();

            for (int i = 0; i < points.size(); i++) {
                if (s == 0) {
                    Point p = new Point(points.get(i).x - 75, points.get(i).y - 75, points.get(i).id);
                    temp_mas.add(p);
                } else if (s == 1) {
                    Point p = new Point(points.get(i).x, points.get(i).y + 150, points.get(i).id);
                    temp_mas.add(p);
                } else if (s == 2) {
                    Point p = new Point(points.get(i).x + 150, points.get(i).y, points.get(i).id);
                    temp_mas.add(p);
                } else if (s == 3) {
                    Point p = new Point(points.get(i).x - 100, points.get(i).y - 200, points.get(i).id);
                    temp_mas.add(p);
                } else if (s == 4) {
                    Point p = new Point(points.get(i).x + 200, points.get(i).y - 50, points.get(i).id);
                    temp_mas.add(p);
                } else if (s == 5) {
                    Point p = new Point(points.get(i).x - 200, points.get(i).y + 270, points.get(i).id);
                    temp_mas.add(p);
                }
            }

            for (int i = 0; i < lines.size(); i++) {
                System.out.println(lines.size());
                if (s == 0) {
                    Line line = new Line();
                    line.setStartX(lines.get(i).getStartX() - 75);
                    line.setEndX(lines.get(i).getEndX() - 75);
                    line.setStartY(lines.get(i).getStartY() - 75);
                    line.setEndY(lines.get(i).getEndY() - 75);
                    temp_mas_1.add(line);
                } else if (s == 1) {
                    Line line = new Line();
                    line.setStroke(lines.get(i).getStroke());
                    line.setStartX(lines.get(i).getStartX());
                    line.setEndX(lines.get(i).getEndX());
                    line.setStartY(lines.get(i).getStartY() + 150);
                    line.setEndY(lines.get(i).getEndY() + 150);
                    temp_mas_1.add(line);
                    System.out.println((line));
                } else if (s == 2) {
                    Line line = new Line();
                    line.setStroke(lines.get(i).getStroke());
                    line.setStartX(lines.get(i).getStartX() + 150);
                    line.setEndX(lines.get(i).getEndX() + 150);
                    line.setStartY(lines.get(i).getStartY());
                    line.setEndY(lines.get(i).getEndY());
                    temp_mas_1.add(line);
                } else if (s == 3) {
                    Line line = new Line();
                    line.setStroke(lines.get(i).getStroke());
                    line.setStartX(lines.get(i).getStartX() - 100);
                    line.setEndX(lines.get(i).getEndX() - 100);
                    line.setStartY(lines.get(i).getStartY() - 200);
                    line.setEndY(lines.get(i).getEndY() - 200);
                    temp_mas_1.add(line);
                } else if (s == 4) {
                    Line line = new Line();
                    line.setStroke(lines.get(i).getStroke());
                    line.setStartX(lines.get(i).getStartX() + 200);
                    line.setEndX(lines.get(i).getEndX() + 200);
                    line.setStartY(lines.get(i).getStartY() - 50);
                    line.setEndY(lines.get(i).getEndY() - 50);
                    temp_mas_1.add(line);
                } else if (s == 5) {
                    Line line = new Line();
                    line.setStroke(lines.get(i).getStroke());
                    line.setStartX(lines.get(i).getStartX() - 200);
                    line.setEndX(lines.get(i).getEndX() - 200);
                    line.setStartY(lines.get(i).getStartY() + 270);
                    line.setEndY(lines.get(i).getEndY() + 270);
                    temp_mas_1.add(line);
                }
            }

            for (int i = 0; i < temp_mas.size(); i++) {
                points.add(temp_mas.get(i));
            }

            for (int i = 0; i < temp_mas_1.size(); i++) {
                lines.add(temp_mas_1.get(i));
            }

            s = s + 1;
        });

    }

    public int getId() {
        id = id + 1;
        return id;
    }

    public void showPoint(List<Point> points, int s) {
        for (int i = 0; i < points.size(); i++) {
            Circle circle = new Circle();
            circle.setCenterX(points.get(i).x + 400);
            circle.setCenterY(400 - points.get(i).y);
            circle.setRadius(3);
            anchorPane.getChildren().add(circle);
        }
        for (int i = 0; i < lines.size(); i++) {
            Line line1 = new Line();
            line1.setStroke(lines.get(i).getStroke());
            line1.setStartX(lines.get(i).getStartX() + 400);
            line1.setStartY(400 - lines.get(i).getStartY());
            line1.setEndX(lines.get(i).getEndX() + 400);
            line1.setEndY(400 - lines.get(i).getEndY());
            line1.setStrokeWidth(3.0);
            anchorPane.getChildren().add(line1);
        }

        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < points.size(); j++) {
                if (points.get(i).id == points.get(j).id) {
                    Line line = new Line();
                    Line temp_line = new Line();
                    line.setStartX(points.get(i).x + 400);
                    temp_line.setStartX(points.get(i).x);
                    line.setStartY(400 - points.get(i).y);
                    temp_line.setStartY(points.get(i).y);
                    line.setEndX(points.get(j).x + 400);
                    temp_line.setEndX(points.get(j).x);
                    line.setEndY(400 - points.get(j).y);
                    temp_line.setEndY(points.get(j).y);

                    if (s == 1) {
                        line.setStroke(Color.GREEN);
                        temp_line.setStroke(Color.GREEN);
                    } else if (s == 2) {
                        line.setStroke(Color.BLUE);
                        temp_line.setStroke(Color.BLUE);
                    } else if (s == 3) {
                        line.setStroke(Color.RED);
                        temp_line.setStroke(Color.RED);
                    } else if (s == 4) {
                        line.setStroke(Color.ORANGE);
                        temp_line.setStroke(Color.ORANGE);
                    } else if (s == 5) {
                        line.setStroke(Color.DEEPPINK);
                        temp_line.setStroke(Color.DEEPPINK);
                    } else if (s == 6) {
                        line.setStroke(Color.OLIVE);
                        temp_line.setStroke(Color.OLIVE);
                    }
                    line.setStrokeWidth(3.0);
                    temp_line.setStrokeWidth(3.0);
                    lines.add(temp_line);
                    anchorPane.getChildren().add(line);
                }
            }
        }
    }
}
