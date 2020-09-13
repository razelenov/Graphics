package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane parent;

    @FXML
    private TextField mashField;

    @FXML
    private Button mash;

    @FXML
    private Button choose1;

    @FXML
    private Button choose2;

    @FXML
    private Button sdvig1;

    @FXML
    private Button sdvig2;

    @FXML
    private TextField sdvigField;

    @FXML
    private Button povorotButton;

    @FXML
    private TextField ygolField;

    public List<Line> r;
    public Coordinate square = new Coordinate(
            new Point(0, 0),
            new Point(1, 0),
            new Point(1, 1),
            new Point(0, 1)
    );

    @FXML
    void initialize() {

        Label label_1 = new Label();
        label_1.setText("-3");

        Line lineX = new Line();
        lineX.setStartX(0);
        lineX.setStartY(300);
        lineX.setEndX(600);
        lineX.setEndY(300);

        Line lineY = new Line();
        lineY.setStartX(300);
        lineY.setStartY(0);
        lineY.setEndX(300);
        lineY.setEndY(600);

        List<Line> lines_1 = new ArrayList<>();
        List<Line> lines_2 = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Line line = new Line();
            line.setStartX(0 + (100 * i));
            line.setEndX(0 + (100 * i));
            line.setStartY(290);
            line.setEndY(310);
            if (i != 3) {
                lines_1.add(line);
            }
        }

        for (int i = 0; i < 7; i++) {
            Line line = new Line();
            line.setStartX(290);
            line.setEndX(310);
            line.setStartY(0 + (100 * i));
            line.setEndY(0 + (100 * i));
            if (i != 3) {
                lines_1.add(line);
            }
        }

        showSquare(square);

        parent.getChildren().addAll(lineX, lineY);
        parent.getChildren().addAll(lines_1);
        parent.getChildren().addAll(lines_2);

        mash.setOnAction(event -> {
            try {
                double k = Double.valueOf(mashField.getText());
                showSquare(mash(k));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введите корректное значение:");
                alert.setContentText("Коэффициент масштабирования вводится в виде числа!");
                alert.showAndWait();
            }
        });

        choose1.setOnAction(event -> {
            List<Point> p = square.getPoints();
            for (int i = 0; i < p.size(); i++) {
                p.get(i).x = p.get(i).x * (-1);
            }
            showSquare(square);
        });

        choose2.setOnAction(event -> {
            List<Point> p = square.getPoints();
            for (int i = 0; i < p.size(); i++) {
                p.get(i).y = p.get(i).y * (-1);
            }
            showSquare(square);
        });

        sdvig1.setOnAction(event -> {
            try {
                double x = Double.valueOf(sdvigField.getText());
                List<Point> p = square.getPoints();
                for (int i = 0; i < p.size(); i++) {
                    p.get(i).x = p.get(i).x + x;
                }
                showSquare(square);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введите корректное значение:");
                alert.setContentText("Величина сдвига вводится в виде числа!");
                alert.showAndWait();
            }
        });

        sdvig2.setOnAction(event -> {
            try {
                double y = Double.valueOf(sdvigField.getText());
                List<Point> p = square.getPoints();
                for (int i = 0; i < p.size(); i++) {
                    p.get(i).y = p.get(i).y + y;
                }
                showSquare(square);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введите корректное значение:");
                alert.setContentText("Величина сдвига вводится в виде числа!");
                alert.showAndWait();
            }
        });

        povorotButton.setOnAction(event -> {
            try {
                Double ygol = Math.toRadians(Double.valueOf(ygolField.getText()));
                List<Point> p = square.getPoints();
                for (int i = 0; i < p.size(); i++) {
                    Double temp_x = 0.0;
                    Double temp_y = 0.0;
                    temp_x = (p.get(i).x * Math.cos(ygol) - p.get(i).y * Math.sin(ygol));
                    temp_y = (p.get(i).x * Math.sin(ygol) + p.get(i).y * Math.cos(ygol));
                    p.get(i).y = temp_y;
                    p.get(i).x = temp_x;
                }
                showSquare(square);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Введите корректное значение:");
                alert.setContentText("Угол поворота вводится в виде числа!");
                alert.showAndWait();
            }
        });
    }

    public Coordinate mash(double k) {
        List<Point> p = square.getPoints();
        for (int i = 0; i < p.size(); i++) {
            p.get(i).x = (p.get(i).x * k);
            p.get(i).y = (p.get(i).y * k);
        }
        return new Coordinate(p.get(0), p.get(1), p.get(2), p.get(3));
    }

    public void showSquare(Coordinate square) {

        List<Point> points = square.getPoints();

        Line line1 = new Line();
        Line line2 = new Line();
        Line line3 = new Line();
        Line line4 = new Line();

        List<Line> lines = new ArrayList<>();

        try {
            for (int i = 0; i < r.size(); i++) {
                parent.getChildren().remove(r.get(i));
            }
        } catch (Exception e) {}

        lines.add(line1);
        lines.add(line2);
        lines.add(line3);
        lines.add(line4);

        for (int i = 0; i < points.size() - 1; i++) {
            lines.get(i).setStartX(points.get(i).x * 100 + 300);
            lines.get(i).setEndX(points.get(i + 1).x * 100 + 300);
            lines.get(i).setStartY(300 - points.get(i).y * 100);
            lines.get(i).setEndY(300 - points.get(i + 1).y * 100);
            lines.get(i).setStrokeWidth(3);
            lines.get(i).setStroke(Color.ORANGE);
            System.out.println(i);
        }
        lines.get(3).setStartX(points.get(3).x * 100 + 300);
        lines.get(3).setEndX(points.get(0).x * 100 + 300);
        lines.get(3).setStartY(300 - points.get(3).y * 100);
        lines.get(3).setEndY(300 - points.get(0).y * 100);
        lines.get(3).setStrokeWidth(3);
        lines.get(3).setStroke(Color.ORANGE);
        r = lines;

        parent.getChildren().addAll(lines);
    }
}
