import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        validate(points);
        segments = analyze(points.clone()); // Clone for immutability and to avoid side effects
    }

    private void validate(Point[] points) {
        if (points == null) throw new IllegalArgumentException("points can't be null");
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("point can't be null");
            }
        }
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("repeated points");
                }
            }
        }
    }

    private LineSegment[] analyze(Point[] points) {
        List<LineSegment> segmentList = new ArrayList<>();
        MergeX.sort(points);

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            Point[] slopes = Arrays.copyOfRange(points, i + 1, points.length);
            MergeX.sort(slopes, p.slopeOrder());

            int count = 1;
            for (int j = 1; j < slopes.length; j++) {
                if (Double.compare(p.slopeTo(slopes[j - 1]), p.slopeTo(slopes[j])) == 0) {
                    count++;
                    if (j == slopes.length - 1 && count >= 3) {
                        segmentList.add(new LineSegment(p, slopes[j]));
                    }
                }
                else {
                    if (count >= 3) {
                        segmentList.add(new LineSegment(p, slopes[j - 1]));
                    }
                    count = 1;
                }
            }
        }

        return segmentList.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        drawPoints(points);

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        printAndDrawSegments(collinear.segments());
    }

    private static void drawPoints(Point[] points) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    }

    private static void printAndDrawSegments(LineSegment[] segments) {
        for (LineSegment segment : segments) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
