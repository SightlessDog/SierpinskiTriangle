package triangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Path2D;

    import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.xpath.XPath;

    public class SierpinskiTriangle {

        //Size of the Frame
        public static int SIZE = 1000;

        static int[] XPoints = new int[3];
        static int[] YPoints = new int[3];
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Point p3 = new Point(0,0);
        static int[] YPoints2 = new int[3];

        JFrame frame;
        JPanel panel;

        //Creates the Frame
        @SuppressWarnings("serial")
        public void display() {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new JPanel() {
                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    paintSierpinskiTriangle(g, getSize());
                    EquilateralTriangles(g,p1,p2,p3, 11);
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
            SierpinskiTriangle triangle = new SierpinskiTriangle();
            triangle.display();

        }

        // Frame constructor clean
        public void paintSierpinskiTriangle(Graphics g, Dimension size) {
            //XPoints[0] = 20 ;
            //YPoints[0] = 20 ;
            p1 = new Point(20,20);
            //XPoints [1] = size.width/2 ;
            //YPoints[1] = size.height-20;
            p2 = new Point(size.width/2, size.height-20);
            //XPoints[2]=size.width-20;
            //YPoints[2]=20;
            p3 = new Point(size.width-20, 20);
            Graphics2D g2 = (Graphics2D) g;
            g2.setBackground(Color.white);
            g2.clearRect(0, 0, size.width, size.height);
            g2.draw3DRect(20, 20, size.width - 40, size.height - 40, true);

        }


        //Triangle
        public  void EquilateralTriangles (Graphics g, Point p1, Point p2,Point p3 ,int level) {
            if (level == 1) {
                DrawTriangle(g,p1,p2,p3);
            }
            if(level != 1) {
                Point p4 = midPoint(p1,p2);
                Point p5 = midPoint(p2,p3);
                Point p6 = midPoint(p1,p3);
                EquilateralTriangles(g,p1,p4,p6,level-1);
                EquilateralTriangles(g,p4,p2,p5,level-1);
                EquilateralTriangles(g,p6,p5,p3,level-1);

            }
        }

        //Following drawing actions and calulations.

        //Method that makes the first triangle based by canvas size
        //C ( Width/2 | Height )   *
        // 	                  -   -
        //				    -       -
        //	              -           -
        // 	A ( 0 | 0 ) *--------------*B ( Width | 0 )
        //	Midpoint ( x  |  y ) = ( (x1+x2)/2  |  (y1+y2)/2 )
        // i=0 x1 ; i=1 y1 ; i=0 x2 ; i=1 y2 ; i=0 x3 ; i=1 y3

	/* Data Structure

		//A
		double ax1 =0;
		double ay1 =0;
		//B
		double bx2 =0;
		double by2 =0;
		//C
		double cx3 =0;
		double cy3 =0;

		// Array that stores all 6 coordinates
		double[] triangle = {ax1,ay1,bx2,by2,cx3,cy3};

	 */

        static void DrawTriangle (Graphics g, Point p1, Point p2, Point p3) {
            Graphics2D g2 =  (Graphics2D) g;
            Path2D myPath = new Path2D.Double();
            Polygon p = new Polygon();
            p.addPoint(p1.x,p1.y);
            p.addPoint(p2.x,p2.y);
            p.addPoint(p3.x,p3.y);
            g2.fillPolygon(p);

            // g2.fillPolygon(p);

            //g2.drawLine(XPoints[0],YPoints[0], XPoints[1], YPoints[1]);
            //g2.drawLine(XPoints[1], YPoints[1],XPoints[2],YPoints[2]);
            //g2.drawLine(XPoints[2], YPoints[2],XPoints[0],YPoints[0]);
        }

        public static Point midPoint (Point x1,Point x2) {
            return new Point((x1.x + x2.x)/2 , (x1.y + x2.y)/2);
        }

        static int [] getMid(int [] points) {
            int x1 = points[0];
            int x2 = points[1];
            int x3 = points[2];

            int [] midPoints = {(x1+x2)/2, (x1+x3)/2, (x2+x3)/2}  ;

            return midPoints ;
        }

        public double[] calculatingMidpoints(double[] triangleCoordinates){

            //A
            double ax1 = triangleCoordinates[0];
            double ay1 = triangleCoordinates[1];
            //B
            double bx2 = triangleCoordinates[2];
            double by2 = triangleCoordinates[3];
            //C
            double cx3 = triangleCoordinates[4];
            double cy3 = triangleCoordinates[5];

            //AB Midpoint
            double abMidpointX1 = (ax1+bx2)/2;
            double abMidpointY1 = (ay1+by2)/2;
            //BC Midpoint
            double bcMidpointX2 = (bx2+cx3)/2;
            double bcMidpointY2 = (by2+cy3)/2;
            //CA Midpoint
            double caMidpointX3 = (ax1+cx3)/2;
            double caMidpointY3 = (ay1+cy3)/2;

            double[] triangleMidpointsCoordinates = {abMidpointX1,abMidpointY1,bcMidpointX2,bcMidpointY2,caMidpointX3,caMidpointY3};

		/*	Skizze der Punkte

		    C ( 0 | Width/2 )   *
		 	                  -   -
						    -       -
			              -           -
		 	A ( 0 | 0 ) *--------------*B ( Width | 0 )

		                      ||
						   --------
				   		    -    -
		                      --

		                      -
		 	                -   -
		  CAMid ( 0 | 0 ) ------- BCMid ( Width | 0 )
			            -   -   -   -
		              --------*--------
		               ABMid ( 0 | 0 )
		*/

            return triangleMidpointsCoordinates;

        }







    }


