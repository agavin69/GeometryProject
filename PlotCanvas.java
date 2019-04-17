/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics2D;

/**
 *
 * @author antho
 */
import java.awt.*;
import java.util.*;
import javax.vecmath.*;
import java.text.DecimalFormat;
import javax.swing.JFrame;

//==============================================================================
// PlotCanvas class definition
//==============================================================================
public class PlotCanvas extends Canvas {

    private double[][] yValues = null;
    private double[] xValues = null;
    private final String name;
    private final String xLabel;
    private final String yLabel;
    private double minY, maxY;
    public boolean writeData = false;
    public static boolean dots = true;
    public static int dotsize = 6;

    //==========================================================================
    public PlotCanvas(double[] xVal, double[][] yVal, String name, String xlbl, String ylbl) {
        super();
        this.name = name;
        xLabel = xlbl;
        yLabel = ylbl;
        setDataPoints(xVal, yVal);
    }

    //==========================================================================
    public final void setDataPoints(double[] xVals, double[][] yVals) {
        xValues = xVals;
        yValues = yVals;
        setBackground(Color.white);
    } // setDataPoints

    //==========================================================================
    // existing function in Canvas : draw 2D graphics
    @Override
    public void paint(Graphics g) {
        plotCurves();
    } // end of if(stPList.size() > 1)

    //==========================================================================
    public void plotCurves() {
        Graphics2D g = (Graphics2D) getGraphics();
        g.setStroke(new BasicStroke(3));
        drawCoordinateAxes();
        double den = xValues[xValues.length - 1] - xValues[0];
        if (yValues != null) {
            for (double[] yValue : yValues) {
                for (int j = 0; j < yValue.length - 1; j++) {
                    double xValue1, xValue2 = 0.0;
                    if (xValues.length == yValue.length) {
                        xValue1 = (xValues[j] - xValues[0]) / den;
                        xValue2 = (xValues[j + 1] - xValues[0]) / den;
                    } else {
                        xValue1 = ((double) j) / ((double) (yValue.length - 1));
                        xValue2 = ((double) j + 1) / ((double) (yValue.length - 1));
                    }
                    Point3d firstPt = new Point3d(xValue1, yValue[j], 0.0);
                    Point3d secondPt = new Point3d(xValue2, yValue[j + 1], 0.0);
                    Point pt1 = convertCoordToPixel(firstPt);
                    Point pt2 = convertCoordToPixel(secondPt);
                    g.setColor(new Color(0.0f, 0.0f, 1.0f)); // set the color for curve
                    g.drawLine(pt1.x, pt1.y, pt2.x, pt2.y);
                    g.setColor(new Color(0.0f, 0.0f, 0.0f));    // set the color for data points
                    if (dots) {
                        int ds2 = dotsize / 2;
                        g.fillOval(pt1.x - ds2, pt1.y - ds2, dotsize, dotsize);
                        if (j == yValue.length - 2) {
                            g.fillOval(pt2.x - ds2, pt2.y - ds2, dotsize, dotsize);
                        }
                    }
                }
            }
        }
    } // end plotCurve

    //==========================================================================
    // function to draw coordinate axes
    private void drawCoordinateAxes() {
        minY = yValues[0][0];
        maxY = yValues[0][0];
        for (int i = 0; i < yValues.length; i++) {
            for (int j = 0; j < yValues[i].length; j++) {
                if (yValues[i][j] > maxY) {
                    maxY = yValues[i][j];
                } else if (yValues[i][j] < minY) {
                    minY = yValues[i][j];
                }
            }
        }
//        checkResetMaxMin();

        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.setStroke(new BasicStroke(2));
        Dimension dim = new Dimension();
        getSize(dim);

        double tmpHeight = dim.getHeight();
        double tmpWidth = dim.getWidth();
        double xCoord1 = 0.1 * tmpWidth;
        double xCoord2 = 0.95 * tmpWidth;
        double yCoord1 = 0.9 * tmpHeight;
        double yCoord2 = 0.1 * tmpHeight;
        double yCoord0 = yCoord1; // value for location of x-axis
        if (minY * maxY < 0) {
            yCoord0 = yCoord1 - minY * (yCoord2 - yCoord1) / (maxY - minY);
        } else if (maxY <= 0) {
            yCoord0 = yCoord2;
        }

        g2d.setColor(Color.black);
        Font txtFont = new Font("Times New Roman", Font.BOLD, 20);
        g2d.setFont(txtFont);

        // Title
        if (name != null) {
            String title = new String();
            title = "Plot of " + name + "-" + yLabel;
            g2d.drawString(title, (int) (0.4 * tmpWidth), (int) (0.05 * tmpHeight));
        }

        // Draw axes
        g2d.setColor(new Color(0.0f, 0.0f, 0.0f)); // set the color for axes
        g2d.drawLine((int) xCoord1, (int) yCoord0, (int) xCoord2, (int) yCoord0);
        g2d.drawLine(
                (int) xCoord1, (int) yCoord1 + (int) (0.05 * tmpHeight),
                (int) xCoord1, (int) yCoord2 - (int) (0.05 * tmpHeight));

        // Draw arrow heads
        int delta = (int) (tmpHeight + tmpWidth) / 100;
        Polygon tri = new Polygon();
        tri.npoints = 3;
        tri.xpoints = new int[]{(int) xCoord2, (int) (xCoord2 + delta), (int) xCoord2};
        tri.ypoints = new int[]{(int) yCoord0 - delta / 3, (int) yCoord0, (int) yCoord0 + delta / 3};
        g2d.fillPolygon(tri);
        tri.xpoints = new int[]{(int) xCoord1 + delta / 3, (int) xCoord1, (int) xCoord1 - delta / 3};
        int yy = (int) yCoord2 - (int) (0.05 * tmpHeight);
        tri.ypoints = new int[]{yy, yy - delta, yy};
        g2d.fillPolygon(tri);
        tri.xpoints = new int[]{(int) xCoord1 + delta / 3, (int) xCoord1, (int) xCoord1 - delta / 3};
        yy = (int) yCoord1 + (int) (0.05 * tmpHeight);
        tri.ypoints = new int[]{yy, yy + delta, yy};
        g2d.fillPolygon(tri);

        int numPts = 10;
        for (int i = 0; i < numPts; i++) {
            double ratio = ((double) i) / ((double) (numPts - 1));
            double xCoord = xCoord1 + ratio * 0.8 * tmpWidth;
            double yCoord = yCoord1 - ratio * 0.8 * tmpHeight;
            Point xPt = new Point((int) xCoord, (int) yCoord0);
            Point yPt = new Point((int) xCoord1, (int) yCoord);
            g2d.setColor(new Color(0.0f, 0.0f, 0.0f));    // set the color for data points
            g2d.fillOval(xPt.x - 2, xPt.y - 2, 5, 5);
            g2d.fillOval(yPt.x - 2, yPt.y - 2, 5, 5);

            double yValue = minY + ratio * (maxY - minY);
            g2d.drawString(format(yValue), yPt.x - (int) (3.5 * delta), yPt.y + delta / 3);
            double xTrans = tmpWidth / (4 * (numPts - 1));
            double yTrans = (tmpHeight / (3 * numPts));
            if (xValues != null) {
                double xValue1 = xValues[0] + ratio * (xValues[xValues.length - 1] - xValues[0]);
                if (i != 0) {
                    g2d.drawString(format(xValue1), xPt.x - (int) xTrans, xPt.y + (int) yTrans);
                }
            }
        } // end of i

        // Labels
        int xi = (int) xCoord1 + delta / 2;
        int yi = (int) yCoord2 - (int) 2.5 * delta;
        g2d.drawString(yLabel, xi, yi);
        xi = (int) xCoord2 - 2 * delta;
        yi = (int) yCoord0 - delta / 2;
        g2d.drawString(xLabel, xi, yi);

    } // drawCoordinateAxes()

    //==========================================================================
    private String format(double value) {
        DecimalFormat df = new DecimalFormat();
        if(Math.abs(value) < 1.0e-2) {
            df.applyPattern("0.##E0");
        } else {
            df.applyPattern("0.###");
        }
        return df.format(value);
    }

    //==========================================================================
    // code to check whether the difference between max and min values are significant
    // enough to justify use of different colors and to reset lower or upper limits
    // to zero when appropriate
    protected void checkResetMaxMin() {
        double tempMax;
        if (Math.abs(maxY) >= Math.abs(minY)) {
            tempMax = maxY;
        } else {
            tempMax = minY;
        }
        double checkRatio = ((maxY - minY) / Math.abs(tempMax));
        if (Math.abs(tempMax) < 1.0e-14) {
            maxY = minY = 0.0;
        } else if (checkRatio < 0.02) {
            if (maxY > 0) {
                minY = maxY - 0.02 * Math.abs(maxY);
            } else {
                maxY = minY + 0.02 * Math.abs(minY);
            }
        } else { // also set limit to zero if close enough
            if ((Math.abs(maxY / minY) > 1.0e4) || Math.abs(minY) < 1.0e-14) {
                minY = 0.0;
            } else if (Math.abs(minY / maxY) > 1.0e4 || Math.abs(maxY) < 1.0e-14) {
                maxY = 0.0;
            }
        }
    } // checkResetMaxMin

    //==========================================================================
    // convert pixel point to point in the coordinate
    private Point convertCoordToPixel(Point3d pt3d) {
        // find the size of the canvas, say (lx,ly)-- lower point, (ux,uy)-- upper point
        // then plot in the region which lies in ( Lower corner -- (lx + 0.1*L, ly+0.1*H), Upper Corner (lx + 0.9*L, ly+0.9*H))
        // project the data points in this region.

        double minX = 0.;
        double maxX = 1.0;

        double xRatio = (pt3d.x - minX) / (maxX - minX);
        double yRatio = (pt3d.y - minY) / (maxY - minY);

        Dimension dim = new Dimension();
        getSize(dim);

        double tmpHeight = dim.getHeight();
        double tmpWidth = dim.getWidth();

        double xPixCoord = 0.1 * tmpWidth + xRatio * 0.8 * tmpWidth;
        double yPixCoord = 0.9 * tmpHeight - yRatio * 0.8 * tmpHeight;
        Point pt = new Point((int) xPixCoord, (int) yPixCoord);
        return pt;
    } // end of convertCoordToPixel(Point3d pt, int[] pixelPos)

    //==========================================================================
    public static void main (String[] args) {
        double delT = 0.36;
        double T = 72;
        ArrayList xlist = new ArrayList();
        ArrayList ylist = new ArrayList();
        String title = "Canvas Plot";
        int ex = 1;
        for (double t = 0; t < T; t += delT) {
            xlist.add(t);
            double[] ys;
            switch (ex) {
                case 1: //* Example 1
                    title = "Ex1 analytical solution";
                    ys = new double[]{
                        0.5818 * Math.sin(0.17 * t) - 0.0608 * Math.sin(1.7 * t) + 0.0035 * Math.sin(t),
                        0.0751 * Math.sin(0.17 * t) - 0.0452 * Math.sin(1.7 * t) + 0.0641 * Math.sin(t)
                    };
                    break;
                case 2: //* Example 2
                    title = "Ex2 analytical solution";
                    ys = new double[]{
                        0.3192 * (5.88 * Math.sin(0.17 * t) - Math.sin(t)),
                        0.2328 * (-0.588 * Math.sin(1.7 * t) + Math.sin(t))
                    };
                    break;
                default:
                    return;
            }
            ylist.add(ys); //*/
        }
        PlotCanvas.dots = true;
        PlotCanvas.dotsize = 6;
        plot(title, xlist, ylist);
    } // plot

    //==========================================================================
    public static void plot(String title, ArrayList xlist, ArrayList ylist) {
        JFrame frame = new JFrame();
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        frame.setTitle(title);
        frame.setSize(1024, 800);
        Object[] xValtmp = xlist.toArray();
        Object[] yValtmp = ylist.toArray();
        double[] xVal = new double[xValtmp.length];
        double[][] yVal = new double[2][yValtmp.length];
        for (int ii = 0; ii < xVal.length; ii++) {
            xVal[ii] = (double) xValtmp[ii];
            double[] uu1 = (double[]) yValtmp[ii];
            yVal[0][ii] = uu1[0];
            yVal[1][ii] = uu1[1];
        }
        Canvas plt = new PlotCanvas(xVal, yVal, "PlotCanvas", "time", "uu");
        frame.add(plt);
        frame.setVisible(true);
    } // plot


} // end PlotCanvas class definition
