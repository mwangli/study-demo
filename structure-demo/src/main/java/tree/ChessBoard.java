package tree;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author mwangli
 * @date 2022/8/16 14:53
 */
public class ChessBoard {

    private static int count = 1;
    private static int step = 1;
    private static int[][] chess;
    private static int[][] visited;
    private static boolean finish;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int length = 6;
        chess = new int[length][length];
        visited = new int[length][length];
        Point s = new Point(1, 1);
        travelChess(chess, s, step);
        Floyd.show(chess, "chess");
        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start) + "ms");
        System.out.println("回溯次数：" + count + "次");
    }

    public static boolean travelChess(int[][] chess, Point point, int step) {
        // 假设当前点可以走
        chess[point.x][point.y] = step;
        visited[point.x][point.y] = 1;
        ArrayList<Point> points = getNext(chess, point);
        // 尽可能选择下次可选位置少的点来减少回溯次数
        points.sort(Comparator.comparingInt(p -> getNext(chess, p).size()));
        // 递归选择下一个点
        while (!points.isEmpty()) {
            Point p = points.remove(0);
            if (chess[p.x][p.y] == 0) {
                if (travelChess(chess, p, step + 1)) {
                    return true;
                }
            }
        }
        // 递归终止条件
        if (step == chess.length * chess.length) {
            return true;
        }
        // 如果没有完成任务，则取消当前位置，进行回溯
        chess[point.x][point.y] = 0;
        visited[point.x][point.y] = 0;
        count++;
        return false;
    }

    public static ArrayList<Point> getNext(int[][] chess, Point point) {
        ArrayList<Point> nextPoints = new ArrayList<>();
        Point p = new Point();
        if ((p.x = point.x - 2) >= 0 && (p.y = point.y - 1) >= 0) nextPoints.add(new Point(p));
        if ((p.x = point.x - 2) >= 0 && (p.y = point.y + 1) < chess.length) nextPoints.add(new Point(p));
        if ((p.x = point.x + 2) < chess.length && (p.y = point.y - 1) >= 0) nextPoints.add(new Point(p));
        if ((p.x = point.x + 2) < chess.length && (p.y = point.y + 1) < chess.length) nextPoints.add(new Point(p));
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y - 2) >= 0) nextPoints.add(new Point(p));
        if ((p.x = point.x - 1) >= 0 && (p.y = point.y + 2) < chess.length) nextPoints.add(new Point(p));
        if ((p.x = point.x + 1) < chess.length && (p.y = point.y - 2) >= 0) nextPoints.add(new Point(p));
        if ((p.x = point.x + 1) < chess.length && (p.y = point.y + 2) < chess.length) nextPoints.add(new Point(p));
        return nextPoints;
    }
}
