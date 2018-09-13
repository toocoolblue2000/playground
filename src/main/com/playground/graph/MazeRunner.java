import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MazeRunner {
    
    public static void main(String[] args) {
        char[][] data = new char[][]{
                {'.','.','.','.','.','#'},
                {'.','.','E','.','.','#'},
                {'#','#','#','#','.','#'},
                {'.','B','.','.','.','.'},
                {'.','.','.','.','.','.'},
        };

        Point point = new Point(-1, -1);
        for (int i = 0; i < data.length && point.x == -1; i++) {
            for (int j = 0; j < data[i].length && point.y == -1; j++) {
                if (data[i][j] == 'E') {
                    point.x = i;
                    point.y = j;
                    break;
                }
            }
        }

        Queue<Point> points = new LinkedList<>();
        points.offer(point);

        while (!points.isEmpty()) {
            Point currentPoint = points.poll();

            if (data[currentPoint.x][currentPoint.y] != 'B') {
                if (isValidPoint(currentPoint.x - 1, currentPoint.y, data)) {
                    points.offer(new Point(currentPoint.x - 1, currentPoint.y));
                }
                if (isValidPoint(currentPoint.x + 1, currentPoint.y, data)) {
                    points.offer(new Point(currentPoint.x + 1, currentPoint.y));
                }
                if (isValidPoint(currentPoint.x, currentPoint.y - 1, data)) {
                    points.offer(new Point(currentPoint.x, currentPoint.y - 1));
                }
                if (isValidPoint(currentPoint.x, currentPoint.y + 1, data)) {
                    points.offer(new Point(currentPoint.x, currentPoint.y + 1));
                }
                data[currentPoint.x][currentPoint.y] = 'X';
            } else {
                System.out.println("Reached");
                break;
            }
            for (int i = 0; i < data.length; i++) {
                System.out.println(Arrays.toString(data[i]));
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    private static boolean isValidPoint(int x, int y, char[][] data) {
        return !(x < 0 || y < 0 || x >= data.length || y >= data[0].length
                || data[x][y] == 'X' || data[x][y] == 'E' || data[x][y] == '#');
    }
}
