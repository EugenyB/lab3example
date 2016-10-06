package lab3.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Eugeny Berkunsky on 08.10.2015.
 * Modified 05.10.2016
 */

public class Figure {
    private double[] x = {150, 100, 200, 200, 100};
    private double[] y = {50,  100, 100, 200, 200};

    private int[][] polygons = {
            {0,1,2},
            {1,4,3,2}
    };

    private double[][] matrix = {
            {1,0,0}, {0,1,0}, {0,0,1}
    };

    private double fx(double x, double y) {
        return matrix[0][0] * x + matrix[0][1] * y + matrix[0][2];
    }

    private double fy(double x, double y) {
        return matrix[1][0] * x + matrix[1][1] * y + matrix[1][2];
    }

    public void draw(GraphicsContext gc, Color color) {
        gc.setStroke(color);
        for (int[] polygon : polygons) {
            double[] xe = new double[polygon.length];
            double[] ye = new double[polygon.length];
            for (int j = 0; j < xe.length; j++) {
                xe[j] = fx(x[polygon[j]], y[polygon[j]]);
                ye[j] = fy(x[polygon[j]], y[polygon[j]]);
            }
            gc.strokePolygon(xe, ye, xe.length);
        }
    }

    public void rotate(double alpha) {
        double cos = Math.cos(Math.toRadians(alpha));
        double sin = Math.sin(Math.toRadians(alpha));

        double[][] r = {
                {cos, -sin, 0},{sin, cos, 0},{0,0,1}
        };

        matrix = multMatrix(r,matrix);
    }

    public void move(double dx, double dy) {
        double[][] t = {
                {1,0,dx},{0,1,dy},{0,0,1}
        };
        matrix = multMatrix(t,matrix);
    }

    private double[][] multMatrix(double[][] r, double[][] matrix) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[i][j] += r[i][k] * matrix[k][j];
                }
            }
        }
        return result;
    }

    public void scale(double v) {
        double[][] t = {
                {v,0,0},{0,v,0},{0,0,1}
        };
        matrix = multMatrix(t,matrix);
    }
}
