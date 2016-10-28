
/* Test to see if a maze can be created using ideas from Princeton
 * University's StdDraw to draw the maze, and a multidimensional array to create the
 * actual maze.
 */
public class Maze {
	private int n; //this will be the dimension of the maze
	private boolean[][] north;
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] visited;
	private boolean done = false; //checks if the maze is done
	
	public Maze(int n) {
		 this.n = n;
	        Draw.setXscale(0, n+2);
	        Draw.setYscale(0, n+2);
	        init();
	        generate();
	}
	
	private void init() {
        // initialize border cells as already visited
        visited = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }


        // initialize all walls as present
        north = new boolean[n+2][n+2];
        east  = new boolean[n+2][n+2];
        south = new boolean[n+2][n+2];
        west  = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            for (int y = 0; y < n+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }
    }
	
	 private void generate(int x, int y) {
	        visited[x][y] = true;

	        // while there is an unvisited neighbor
	        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

	            // pick random neighbor (could use Knuth's trick instead)
	            while (true) {
	                double r = RandomGenerator.uniform(4);
	                if (r == 0 && !visited[x][y+1]) {
	                    north[x][y] = false;
	                    south[x][y+1] = false;
	                    generate(x, y + 1);
	                    break;
	                }
	                else if (r == 1 && !visited[x+1][y]) {
	                    east[x][y] = false;
	                    west[x+1][y] = false;
	                    generate(x+1, y);
	                    break;
	                }
	                else if (r == 2 && !visited[x][y-1]) {
	                    south[x][y] = false;
	                    north[x][y-1] = false;
	                    generate(x, y-1);
	                    break;
	                }
	                else if (r == 3 && !visited[x-1][y]) {
	                    west[x][y] = false;
	                    east[x-1][y] = false;
	                    generate(x-1, y);
	                    break;
	                }
	            }
	        }
	    }
	 
	 // generate the maze starting from lower left
	   private void generate() {
	        generate(1, 1);


	        // delete some random walls
	        for (int i = 0; i < n; i++) {
	            int x = 1 + RandomGenerator.uniform(n - 1);
	            int y = 1 + RandomGenerator.uniform(n - 1);
	            north[x][y] = south[x][y+1] = false;
	        }

	        // add some random walls
	        for (int i = 0; i < 10; i++) {
	            int x = n/2 + RandomGenerator.uniform(n / 2);
	            int y = n/2 +RandomGenerator.uniform(n / 2);
	            east[x][y] = west[x+1][y] = true;
	        }
	    
	    } 
	   
	   // draw the maze
	    public void draw() {
	        Draw.setPenColor(Draw.RED);
	        Draw.filledCircle(n/2.0 + 0.5, n/2.0 + 0.5, 0.375);
	        Draw.filledCircle(1.5, 1.5, 0.375);

	        Draw.setPenColor(Draw.BLACK);
	        for (int x = 1; x <= n; x++) {
	            for (int y = 1; y <= n; y++) {
	                if (south[x][y]) Draw.line(x, y, x+1, y);
	                if (north[x][y]) Draw.line(x, y+1, x+1, y+1);
	                if (west[x][y])  Draw.line(x, y, x, y+1);
	                if (east[x][y])  Draw.line(x+1, y, x+1, y+1);
	            }
	        }
	        Draw.show();
	        Draw.pause(1000);
	    } 
	    
	 // a test client
	    public static void main(String[] args) {
	        int n = Integer.parseInt("100");
	        Maze maze = new Maze(n);
	        Draw.enableDoubleBuffering();
	        maze.draw();
	       // maze.solve();
	    }

}
