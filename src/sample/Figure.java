package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by eugeny on 08.10.2015.
 */
public class Figure {
    double[] x = {150, 100, 200, 200, 100};
    double[] y = {50,  100, 100, 200, 200};

    int[][] p = {
            {0,1,2},
            {1,4,3,2}
    };

    double[][] matrix = {
            {1,0,0},{0,1,0},{0,0,1}
    };

    double fx(double x, double y) {
        return matrix[0][0] * x + matrix[0][1] * y + matrix[0][2];
    }

    double fy(double x, double y) {
        return matrix[1][0] * x + matrix[1][1] * y + matrix[1][2];
    }

    public void draw(GraphicsContext gc, Color color) {
        gc.setStroke(color);
        for (int i = 0; i < p.length; i++) {
            double[] xe = new double[p[i].length];
            double[] ye = new double[p[i].length];
            for (int j = 0; j < xe.length; j++) {
                xe[j] = fx(x[p[i][j]],y[p[i][j]]);
                ye[j] = fy(x[p[i][j]], y[p[i][j]]);
            }
            gc.strokePolygon(xe,ye,xe.length);
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
}
