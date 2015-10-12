package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {

    @FXML Canvas canvas;

    Figure f = new Figure();

    public void drawFigure(ActionEvent actionEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        f.draw(gc, Color.WHITE);
    }

    public void rotatePlus(ActionEvent actionEvent) {
        f.move(-150,-150);
        f.rotate(10);
        f.move(150,150);
        drawFigure(actionEvent);
    }

    public void rotateMinus(ActionEvent actionEvent) {
        f.move(-150,-150);
        f.rotate(-10);
        f.move(150,150);
        drawFigure(actionEvent);
    }

}
