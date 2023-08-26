/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] sites;
    private WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF fullUf; // For isFull without backwash issue
    private final int top = 0;
    private final int bottom;
    private final int length;
    private int openSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException(
                    "N cannot be lessthan or equals to " + 0
            );
        }

        bottom = (n * n) + 1;
        length = n;
        sites = new boolean[n][n];
        uf = new WeightedQuickUnionUF((n * n) + 2);
        fullUf = new WeightedQuickUnionUF((n * n) + 1); // No bottom virtual site
    }

    private void validateRange(int row, int col) {
        if (col <= 0 || col > length || row <= 0 || row > length) {
            throw new IllegalArgumentException("Row or Col must be in between "
                                                       + 1 + " and " + length);
        }
    }

    private int getIndex(int row, int col) {
        return ((row - 1) * length) + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRange(row, col);
        if (isOpen(row, col)) {
            return;
        }
        sites[row - 1][col - 1] = true;
        ++openSites;

        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(getIndex(row, col), getIndex(row, col - 1));
            fullUf.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if (col < length && isOpen(row, col + 1)) {
            uf.union(getIndex(row, col), getIndex(row, col + 1));
            fullUf.union(getIndex(row, col), getIndex(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(getIndex(row, col), getIndex(row - 1, col));
            fullUf.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if (row < length && isOpen(row + 1, col)) {
            uf.union(getIndex(row, col), getIndex(row + 1, col));
            fullUf.union(getIndex(row, col), getIndex(row + 1, col));
        }

        if (row == 1) {
            uf.union(getIndex(row, col), top);
            fullUf.union(getIndex(row, col), top);
        }

        if (row == length) {
            uf.union(getIndex(row, col), bottom);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRange(row, col);
        return fullUf.find(top) == fullUf.find(getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
