package triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Path2D;
import java.util.Vector;

public class RecursiveSquares {
    //Size of the Frame
    public static int SIZE = 1000;

    JFrame frame;
    JPanel panel;
    private static Color color = new Color(0,0,0);
    public static int colorStep;
    public static int recursiveDepth = 10;
    public void display() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                DrawSquareRecursive(g,recursiveDepth,new Point(0,SIZE),500, color );
            }
        };
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.repaint();
            }
        });
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(SIZE, SIZE);
        frame.setVisible(true);
    }

    // Initialisation of Frame ( environment )
    public static void main(String[] args) {
        RecursiveSquares squares = new RecursiveSquares();
        squares.display();

    }


    static void DrawSquareRecursive (Graphics g, int level, Point Start, int Size, Color col){
        if (level == recursiveDepth)   colorStep = (255-color.getRed())/level;

        Point p1 = Start;
        Point p2 = new Point(p1.x+Size,p1.y);
        Point p3 = new Point(p1.x+Size,p1.y-Size);
        Point p4 = new Point(p1.x,p1.y-Size);

        col = new Color(Math.min(col.getRed()+colorStep/2,255),Math.min(col.getGreen()+colorStep,255),Math.min(col.getBlue()+colorStep,255));
        g.setColor(col);

        DrawSquare(g,p1,p2,p3,p4,col);

        if (level >0){
            int newSize = Size/2;
            Point newStart = p4;
            DrawSquareRecursive(g, level -1, newStart, newSize, col);
            newStart = p2;
            DrawSquareRecursive(g, level -1, newStart, newSize, col);
        }
    }
    static void DrawSquare(Graphics g, Point p1, Point p2, Point p3, Point p4, Color col) {
        Graphics2D g2 =  (Graphics2D) g;
        Path2D myPath = new Path2D.Double();
        Polygon p = new Polygon();
        p.addPoint(p1.x,p1.y);
        p.addPoint(p2.x,p2.y);
        p.addPoint(p3.x,p3.y);
        p.addPoint(p4.x,p4.y);


        g2.fillPolygon(p);


    }

}
