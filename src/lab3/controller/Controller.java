package lab3.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import lab3.model.Figure;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private static final double DELTA = 5;
    @FXML Pane mainPanel;
    @FXML Canvas canvas;

    Figure f = new Figure();

    public void drawFigure(ActionEvent actionEvent) {
        draw();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.widthProperty().bind(mainPanel.widthProperty());
        canvas.heightProperty().bind(mainPanel.heightProperty());
        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());
        mainPanel.requestFocus();
    }

    private void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);

        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        f.draw(gc, Color.BLUE);
    }

    public void processKey(Event event) {
        KeyEvent keyEvent = (KeyEvent) event;
        KeyCode keyCode = keyEvent.getCode();

        switch (keyCode) {
            case UP:
                f.move(0,-DELTA);
                break;
            case DOWN:
                f.move(0,DELTA);
                break;
            case LEFT:
                f.move(-DELTA,0);
                break;
            case RIGHT:
                f.move(DELTA,0);
                break;
            case PAGE_UP:
                f.scale(1.1);
                break;
            case PAGE_DOWN:
                f.scale(1/1.1);
                break;
            case A:
//                rotatePlus(null);
                f.rotate(10);
                break;
            case Z:
//                rotateMinus(null);
                f.rotate(-10);
                break;
        }
        draw();
    }
}
