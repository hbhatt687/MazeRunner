/* This class is the GUI aspect of the maze. It draws using lines and 
 * colors to make the maze more appealing to the eye. Code ideas are borrowed
 * from Princeton University's StdDraw.java.
 */
import java.awt.BasicStroke; //used to describe shape of a mark describes by a pen
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
public final class Draw implements ActionListener, MouseListener {
	
	//The color black
	public static final Color BLACK = Color.BLACK;
	
	//The color red
	public static final Color RED = Color.RED;
	
	// current pen color
    public static Color penColor;
    
    // default color
    private static final Color DEFAULT_PEN_COLOR   = BLACK;
    
    // default pen radius
    private static final double DEFAULT_PEN_RADIUS = 0.002;
    
    // time in milliseconds (from currentTimeMillis()) when we can draw again
    // used to control the frame rate
    private static long nextDraw = -1;  
    
    // current pen radius
    private static double penRadius;
    
    // default canvas size is DEFAULT_SIZE-by-DEFAULT_SIZE
    private static final int DEFAULT_SIZE = 512;
    private static int width  = DEFAULT_SIZE;
    private static int height = DEFAULT_SIZE;
	
	//boundaries for drawing 
	 private static final double BORDER = 0.00;
	//for x-scale and y-scale
	 private static final double DEFAULT_XMIN = 0.0;
	 private static final double DEFAULT_XMAX = 1.0;
	 private static final double DEFAULT_YMIN = 0.0;
	 private static final double DEFAULT_YMAX = 1.0;
	private static double xmin, ymin, xmax, ymax;
	
	// double buffered graphics
	private static BufferedImage offscreenImage, onscreenImage;
	 private static Graphics2D offscreen, onscreen;
	 
	// should we draw immediately or wait until next show?
	 private static boolean defer = false;
	 
	 // the frame for drawing to the screen
	  private static JFrame frame;
	
	 // for synchronization
    private static Object mouseLock = new Object();
    private static Object keyLock = new Object();
    
 // helper functions that scale from user coordinates to screen coordinates and back
    private static double  scaleX(double x) { return width  * (x - xmin) / (xmax - xmin); }
    private static double  scaleY(double y) { return height * (ymax - y) / (ymax - ymin); }
    private static double factorX(double w) { return w * width  / Math.abs(xmax - xmin);  }
    private static double factorY(double h) { return h * height / Math.abs(ymax - ymin);  }
    private static double   userX(double x) { return xmin + x * (xmax - xmin) / width;    }
    private static double   userY(double y) { return ymax - y * (ymax - ymin) / height;   }
	
	public static void setXscale() {
        setXscale(DEFAULT_XMIN, DEFAULT_XMAX);
    }

	 public static void setYscale() {
	        setYscale(DEFAULT_YMIN, DEFAULT_YMAX);
	 }
	 
	 public static void setXscale(double min, double max) {
	        double size = max - min;
	        if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
	        synchronized (mouseLock) {
	            xmin = min - BORDER * size;
	            xmax = max + BORDER * size;
	        }
	    }
	 
	 public static void setYscale(double min, double max) {
	        double size = max - min;
	        if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
	        synchronized (mouseLock) {
	            ymin = min - BORDER * size;
	            ymax = max + BORDER * size;
	        }
	    }
	 
	 // returns the pen colors used to draw
	 public static Color getPenColor() {
	        return penColor;
	    }

	// sets pen color to default color (black)
	  public static void setPenColor() {
	        setPenColor(DEFAULT_PEN_COLOR);
	  }
	  
	  // sets pen color to custom color
	  public static void setPenColor(Color color) {
	        color = RED;
	    }
	  
	  // draws one pixel at x and y
	  private static void pixel(double x, double y) {
	        offscreen.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
	    }
	  
	  
	  // draws a filled circle of the specified radius
	  public static void filledCircle(double x, double y, double radius) {
	        if (!(radius >= 0)) throw new IllegalArgumentException("radius must be nonnegative");
	        double xs = scaleX(x);
	        double ys = scaleY(y);
	        double ws = factorX(2*radius);
	        double hs = factorY(2*radius);
	        if (ws <= 1 && hs <= 1) pixel(x, y);
	       // else offscreen.fill(new Ellipse2D.Double(xs - ws/2, ys - hs/2, ws, hs));
	        draw();
	    }
	  
	   // Copies offscreen buffer to onscreen buffer. There is no reason to call
	   // this method unless double buffering is enabled.
	    public static void show() {
	        onscreen.drawImage(offscreenImage, 0, 0, null);
	        frame.repaint();
	    }
	  
	  // draw onscreen if defer is false
	    private static void draw() {
	        if (!defer) show();
	    }
	    
	    // draws a line segment
	    public static void line(double x0, double y0, double x1, double y1) {
	        offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
	        draw();
	    }
	    
	    // pause for t milliseconds, intended to help computer animations
	    public static void pause(int t) {
	        // sleep until the next time we're allowed to draw
	        long millis = System.currentTimeMillis();
	        if (millis < nextDraw) {
	            try {
	                Thread.sleep(nextDraw - millis);
	            }
	            catch (InterruptedException e) {
	                System.out.println("Error sleeping");
	            }
	            millis = nextDraw;
	        }

	        // when are we allowed to draw again
	        nextDraw = millis + t;
	    }
	    
	    // useful for animations
	    public static void enableDoubleBuffering() {
	        defer = true;
	    }
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
}
