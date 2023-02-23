package yellowcar;

import java.text.SimpleDateFormat;
import java.util.*;

public class Demo {

    // A��B��C��������վ
    private static Queue<Car> stationACars = new LinkedList<>();
    private static Queue<Car> stationBCars = new LinkedList<>();
    private static Queue<Car> stationCCars = new LinkedList<>();
    private static Queue<Car> onlineCars = new LinkedList<>();
    private static Queue<Car> allCars = new LinkedList<>();
    // ��ͼ
    private static int[][] map = new int[8][8];
    // ·�߶�Ӧ;������
    private static HashMap<String, List<Point>> linesPoint = new HashMap<>();

    public static void main(String[] args) {

        // ��ʼ��С�Ƴ�����
        initCars();

        // ��ʼ����ͼ
        initMap();
        showMap();

        // ��ʼ��·�ߺ�;���ĵ�
        initLines();
        int count = 200;
        while (count > 0) {
            // ���ѡ��һ��·��
            // С�Ƴ�������·��
            String[] Lines = {"A-B", "A-C", "B-C", "B-A", "C-A", "C-B"};
            Random random = new Random();
            int lineNo = random.nextInt(6);
            String line = Lines[lineNo];
            System.out.println("�˿�ѡ�����·�ߣ�" + line);
            Queue<Car> startStation = getStartStationByLine(line);
            assert startStation != null;
            Car newCar = startStation.remove();
            // ���³�������λ��
            newCar.setSpeed(1);
            newCar.setLine(line);
            onlineCars.add(newCar);
            // �����Ѿ����н�·�ϵĳ�����״̬
            Iterator<Car> iterator = onlineCars.iterator();
            while (iterator.hasNext()){
                Car car = iterator.next();
                String line1 = car.getLine();
                Point point = car.getPoint();
                Point nextPoint = getNextPoint(line1, point, linesPoint);
                car.setPoint(nextPoint);
                // �ж��Ƿ񵽴�Ŀ�ĵ�
                if (nextPoint.equals(point)) {
                    iterator.remove();
                    Queue<Car> endStation = getEndStationByLine(car.getLine());
                    endStation.add(car);
                }
            }
            // ��ӡ����״̬
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            System.out.println(simpleDateFormat.format(date) + " Aվ�� " + stationACars.size() + ",Bվ�� " + stationBCars.size() + ",Cվ�� " + stationCCars.size() + ",·�ϳ� " + onlineCars.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count--;
        }

    }

    private static void initCars() {
        // ��ʼ��Aվ��С�Ƴ�����30
        for (int i = 0; i < 30; i++) {
            Car yellowCar = new Car();
            yellowCar.setPoint(new Point(0, 0));
            stationACars.add(yellowCar);
        }
        // ��ʼ��Bվ��С�Ƴ�����
        for (int i = 0; i < 40; i++) {
            Car yellowCar = new Car();
            yellowCar.setPoint(new Point(7, 7));
            stationBCars.add(yellowCar);
        }
        // ��ʼ��Cվ��С�Ƴ�����
        for (int i = 0; i < 30; i++) {
            Car yellowCar = new Car();
            yellowCar.setPoint(new Point(2, 3));
            stationCCars.add(yellowCar);
        }
    }

    private static void initMap() {
        // 0��ʾ��ǰλ�ò���ͨ�У�1��ʾ����ͨ��
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
        System.out.println("��ͼ���£�1��ʾ����ͨ�У�0��ʾ������ͨ��");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }
    }

    private static void initLines() {
        // С�Ƴ�������·��
        String[] Lines = {"A-B", "A-C", "B-C", "B-A", "C-A", "C-B"};
        // �����Ӧλ��
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
        // �ݹ���ֹ�����������д�λ����ͬ
        if (start.getPointX().equals(end.getPointX()) && start.getPointY().equals(end.getPointY()))
            return points;
        Integer pointX = start.getPointX();
        Integer pointY = start.getPointY();
        // �Ѿ��߹���λ�ñ��Ϊ-1�������ظ���
        map[pointX][pointY] = -1;
        // ���赱ǰλ�ÿ��ߣ��ݹ������һ��λ��ֱ���ҵ��յ�
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
