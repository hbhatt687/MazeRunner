import java.util.Random;	
public final class RandomGenerator {
	 private static Random random;    // pseudo-random number generator
	 private static long seed;        // pseudo-random number generator seed

	    // static initializer
	    static {
	        // this is how the seed was set in Java 1.4
	        seed = System.currentTimeMillis();
	        random = new Random(seed);
	    }

	    // don't instantiate
	    private RandomGenerator() { }

	    /**
	     * Sets the seed of the pseudorandom number generator.
	     * This method enables you to produce the same sequence of "random"
	     * number for each execution of the program.
	     * Ordinarily, you should call this method at most once per program.
	     *
	     * @param s the seed
	     */
	    public static void setSeed(long s) {
	        seed   = s;
	        random = new Random(seed);
	    }
	 public static int uniform(int n) {
	        if (n <= 0) throw new IllegalArgumentException("Parameter N must be positive");
	        return random.nextInt(n);
	    }
}
