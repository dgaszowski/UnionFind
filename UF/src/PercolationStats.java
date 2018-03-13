
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	// Site vacancy propabilities.
	private double[] p;	
	
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		
		p = new double[trials];	
		
		for (int i = 0; i < trials; i++) {

			Percolation percolation = new Percolation(n);			
			
			while (!percolation.percolates()) {
				int row = StdRandom.uniform(1, n + 1);
				int col = StdRandom.uniform(1, n + 1);				
				
				if (! percolation.isOpen(row, col)) {				
					percolation.open(row, col);
					
				}
			}
			
			p[i] = (double) percolation.numberOfOpenSites() / (n * n);	
			
		}		
	}
	
	public double mean() {
		return StdStats.mean(p);
	}
	
	public double stddev() {
		return StdStats.stddev(p);
	}
	
	public double confidenceLo() {

		return mean() - (1.96 * stddev()/Math.sqrt(p.length));
	}
	
	public double confidenceHi() {
		return mean() + (1.96 * stddev()/Math.sqrt(p.length));
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);				
		
		PercolationStats ps = new PercolationStats(n, trials);
		
		System.out.format("mean                    = %.5f\n", ps.mean());
		System.out.format("stddev                  = %.5f\n", ps.stddev());
		System.out.format(
				"95%% confidence interval = [%f, %f]\n", 
				ps.confidenceLo(), 
				ps.confidenceHi()
		);
	}

}
