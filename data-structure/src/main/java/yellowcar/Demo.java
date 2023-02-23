package yellowcar;

import java.text.SimpleDateFormat;
import java.util.*;

public class Demo {

    // A、B、C三个地铁站
    private static Queue<Car> stationACars = new LinkedList<>();
    private static Queue<Car> stationBCars = new LinkedList<>();
    private static Queue<Car> stationCCars = new LinkedList<>();
    private static Queue<Car> onlineCars = new LinkedList<>();
    private static Queue<Car> allCars = new LinkedList<>();
    // 地图
    private static int[][] map = new int[8][8];
    // 路线对应途径坐标
    private static HashMap<String, List<Point>> linesPoint = new HashMap<>();

    public static void main(String[] args) {

        // 初始化小黄车数量
        initCars();

        // 初始化地图
        initMap();
        showMap();

        // 初始化路线和途径的点
        initLines();
        int count = 200;
        while (count > 0) {
            // 随机选择一条路线
            // 小黄车的所有路线
            String[] Lines = {"A-B", "A-C", "B-C", "B-A", "C-A", "C-B"};
            Random random = new Random();
            int lineNo = random.nextInt(6);
            String line = Lines[lineNo];
            System.out.println("乘客选择随机路线：" + line);
            Queue<Car> startStation = getStartStationByLine(line);
            assert startStation != null;
            Car newCar = startStation.remove();
            // 更新车辆坐标位置
            newCar.setSpeed(1);
            newCar.setLine(line);
            onlineCars.add(newCar);
            // 更新已经在行进路上的车辆的状态
            Iterator<Car> iterator = onlineCars.iterator();
            while (iterator.hasNext()){
                Car car = iterator.next();
                String line1 = car.getLine();
                Point point = car.getPoint();
                Point nextPoint = getNextPoint(line1, point, linesPoint);
                car.setPoint(nextPoint);
                // 判断是否到达目的地
                if (nextPoint.equals(point)) {
                    iterator.remove();
                    Queue<Car> endStation = getEndStationByLine(car.getLine());
                    endStation.add(car);
                }
            }
            // 打印车辆状态
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            System.out.println(simpleDateFormat.format(date) + " A站车 " + stationACars.size() + ",B站车 " + stationBCars.size() + ",C站车 " + stationCCars.size() + ",路上车 " + onlineCars.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }

    }

    private static void initCars() {
        // 初始化A站点小黄车数量30
        for (int i = 0; i < 30; i++) {
            Car yellowCar = new Car();
            yellowCar.setPoint(new Point(0, 0));
            stationACars.add(yellowCar);
        }
        // 初始化B站点小黄车数量
        for (int i = 0; i < 40; i++) {
            Car yellowCar = new Car();
            yellowCar.setPoint(new Point(7, 7));
            stationBCars.add(yellowCar);
        }
        // 初始化C站点小黄车数量
        for (int i = 0; i < 30; i++) {
            Car yellowCar = new Car();
            yellowCar.setPoint(new Point(2, 3));
            stationCCars.add(yellowCar);
        }
    }

    private static void initMap() {
        // 0表示当前位置不能通行，1表示可以通行
        map = new int[8][8];
        map[0][0] = 1;
        map[0][1] = 1;
        map[0][2] = 1;
        map[0][3] = 1;
        map[1][0] = 1;
        map[1][3] = 1;
        map[2][0] = 1;
        map[2][3] = 1;
        map[2][4] = 1;
        map[2][5] = 1;
        map[2][6] = 1;
        map[2][7] = 1;
        map[3][0] = 1;
        map[3][7] = 1;
        map[4][0] = 1;
        map[4][1] = 1;
        map[4][2] = 1;
        map[4][3] = 1;
        map[4][4] = 1;
        map[4][5] = 1;
        map[4][7] = 1;
        map[5][5] = 1;
        map[5][7] = 1;
        map[6][5] = 1;
        map[6][6] = 1;
        map[6][7] = 1;
        map[7][7] = 1;
    }

    private static void showMap() {
        System.out.println("地图如下，1表示可以通行，0表示不可以通行");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }
    }

    private static void initLines() {
        // 小黄车的所有路线
        String[] Lines = {"A-B", "A-C", "B-C", "B-A", "C-A", "C-B"};
        // 坐标对应位置
        Point pointA = new Point(0, 0);
        Point pointB = new Point(7, 7);
        Point pointC = new Point(2, 3);
        linesPoint.put("A-B", getLines(pointA, pointB, map, new ArrayList<>()));
        initMap();
        linesPoint.put("A-C", getLines(pointA, pointC, map, new ArrayList<>()));
        initMap();
        linesPoint.put("B-C", getLines(pointB, pointC, map, new ArrayList<>()));
        initMap();
        linesPoint.put("B-A", getLines(pointB, pointA, map, new ArrayList<>()));
        initMap();
        linesPoint.put("C-A", getLines(pointC, pointA, map, new ArrayList<>()));
        initMap();
        linesPoint.put("C-B", getLines(pointC, pointB, map, new ArrayList<>()));
        initMap();
        System.out.println(linesPoint);
    }

    public static ArrayList<Point> getLines(Point start, Point end, int[][] map, ArrayList<Point> points) {
        points.add(start);
        // 递归终止条件，起点和中袋位置相同
        if (start.getPointX().equals(end.getPointX()) && start.getPointY().equals(end.getPointY()))
            return points;
        Integer pointX = start.getPointX();
        Integer pointY = start.getPointY();
        // 已经走过的位置标记为-1，不能重复走
        map[pointX][pointY] = -1;
        // 假设当前位置可走，递归查找下一个位置直至找到终点
        if (pointX + 1 < 8 && map[pointX + 1][pointY] == 1) {
            getLines(new Point(pointX + 1, pointY), end, map, points);
        } else if (pointX - 1 >= 0 && map[pointX - 1][pointY] == 1) {
            getLines(new Point(pointX - 1, pointY), end, map, points);
        } else if (pointY + 1 < 8 && map[pointX][pointY + 1] == 1) {
            getLines(new Point(pointX, pointY + 1), end, map, points);
        } else if (pointY - 1 >= 0 && map[pointX][pointY - 1] == 1) {
            getLines(new Point(pointX, pointY - 1), end, map, points);
        }
        return points;
    }


    private static Queue<Car> getStartStationByLine(String line) {
        if (line.startsWith("A")) return stationACars;
        if (line.startsWith("B")) return stationBCars;
        if (line.startsWith("C")) return stationCCars;
        else return null;
    }

    private static Queue<Car> getEndStationByLine(String line) {
        if (line.endsWith("A")) return stationACars;
        if (line.endsWith("B")) return stationBCars;
        if (line.endsWith("C")) return stationCCars;
        else return null;
    }

    private static Point getNextPoint(String line, Point point, HashMap<String, List<Point>> linesPoint) {
        List<Point> points = linesPoint.get(line);
        int index = points.indexOf(point);
        if (index == points.size() - 1) {
            return point;
        } else {
            return points.get(index + 1);
        }
    }

}
