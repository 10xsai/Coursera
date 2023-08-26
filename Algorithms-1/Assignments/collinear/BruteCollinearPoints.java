/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] points;

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) throw new IllegalArgumentException();
        this.points = points.clone();
        Arrays.sort(this.points);

        for (int i = 1; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            if (this.points[i].compareTo(this.points[i - 1]) == 0)
                throw new IllegalArgumentException();
        }
    }

    public int numberOfSegments()        // the number of line segments
    {
        return segments().length;
    }

    public LineSegment[] segments()                // the line segments
    {
        ArrayList<LineSegment> result = new ArrayList<>();
        int n = points.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p1 = points[i];
                        Point p2 = points[j];
                        Point p3 = points[k];
                        Point p4 = points[l];
                        // We'll see if points are all collinear
                        if (p1.slopeTo(p2) == p1.slopeTo(p3)
                                && p1.slopeTo(p2) == p1.slopeTo(p4)) {
                            result.add(new LineSegment(p1, p4));
                        }
                    }
                }
            }
        }
        return result.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
