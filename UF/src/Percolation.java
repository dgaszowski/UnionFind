
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF uf;
	private int n;
	private int openSitesCount = 0;
	// Keeps information if sites are open or closed.	
	private boolean[] sites;
	
	public Percolation (int n) {
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.n = n;		
		
		uf = new WeightedQuickUnionUF(n * n + 2);		
		for (int i = 1; i <= n; i++) {
			uf.union(0, i);
			uf.union(n * n + 1, n * n - i + 1);
		}				
		
		// At the beginning all sites are closed.
		sites = new boolean[n * n];
		for (int i = 0; i < n * n; i++) {
			sites[i] = false;			
		}		
		
	}
		
	/**
	 * 
	 * @param row: number of row in a matrix, starting from 1,
	 * @param col: number of column in a matrix, starting from 1,
	 * @return index of corresponding element in a flat array starting form 0,
	 * 		   but containing two additional elements.
	 */
	private int matrixToFlat(int row, int col) {		
		return n * (row - 1) + col;
	}
	
	private void connectNeighbors(int row, int col) {
		
			// Linear addresses of cells to connect.
			int p = matrixToFlat(row, col);
			int[] q = new int[4];
			q[0] = (col > 1) && isOpen(row, col - 1) ? matrixToFlat(row, col - 1) : -1;
			q[1] = (col < n) && isOpen(row, col + 1) ? matrixToFlat(row, col + 1) : -1;
			q[2] = (row > 1) && isOpen(row - 1, col) ? matrixToFlat(row - 1, col) : -1;
			q[3] = (row < n) && isOpen(row + 1, col) ? matrixToFlat(row + 1, col) : -1;
			
			for (int i = 0; i < 4; i++) {
				if (q[i] != -1 && !uf.connected(p, q[i])) {					
					uf.union(p, q[i]);
					//System.out.println( "(" + p + ", " + q + ") have been connected.");
				} 
			}
		//}
	}
	
	public void open(int row, int col) {
		if (col < 1 || row < 1 || col > this.n || row > this.n) {
			throw new java.lang.IllegalArgumentException();
		}
		
		if (isOpen(row, col)) return;
		
		sites[matrixToFlat(row, col) - 1] = true;
		openSitesCount++;
		
		connectNeighbors(row, col);						
	}
	
	public boolean isOpen(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new java.lang.IllegalArgumentException();
		}
		
		return sites[matrixToFlat(row, col) - 1];
	}
	
	public boolean isFull(int row, int col) {
		if (row < 1 || row > n || col < 1 || col > n) {
			throw new java.lang.IllegalArgumentException();
		}
		
		return isOpen(row, col) && uf.connected(0, matrixToFlat(row, col));
	}
	
	public int numberOfOpenSites() {
		return openSitesCount;
	}
	
	public boolean percolates() {
		if (openSitesCount == 0) return false;
		return uf.connected(0, n * n + 1);
	}
}
