package tree;

import java.util.Arrays;

/**
 * @author mwangli
 * @date 2022/8/16 11:34
 */
public class Floyd {

    private static final int N = Byte.MAX_VALUE;

    private static final char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    public static void main(String[] args) {
        //创建邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{0, 12, N, N, N, 16, 14};
        matrix[1] = new int[]{12, 0, 10, N, N, 7, N};
        matrix[2] = new int[]{N, 10, 0, 3, 5, 6, N};
        matrix[3] = new int[]{N, N, 3, 0, 4, N, N};
        matrix[4] = new int[]{N, N, 5, 4, 0, 2, 8};
        matrix[5] = new int[]{16, 7, 6, N, 2, 0, 9};
        matrix[6] = new int[]{14, N, N, N, 8, 9, 0};
        show(matrix, "matrix");
        floyd(matrix, vertex);
    }

    public static void floyd(int[][] matrix, char[] vertex) {
        int size = vertex.length;
        // 初始化最短路径表前驱表
        int[][] dis = new int[size][size];
        int[][] pre = new int[size][size];
        for (int i = 0; i < size; i++) {
            dis[i] = Arrays.copyOf(matrix[i], size);
            Arrays.fill(pre[i], i);
        }
        show(dis, "dis_start");
        show(pre, "pre_start");
        // 遍历中间点为k的所有情况，来更新最短路径表和前驱表
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    // 如果 i->k->j 距离小于 i->j 则更新
                    if (dis[i][k] + dis[k][j] < dis[i][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                        // k->j 可能经过其他顶点，所以不能直接修改成k
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
        show(dis, "dis_end");
        show(pre, "pre_end");
    }

    private static void show(int[][] arr, String key) {
        System.out.println("-------------" + key + "-------------");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == N) {
                    System.out.printf("%4s", 'N');
                } else {
                    if (key.startsWith("pre")) {
                        System.out.printf("%4s", vertex[arr[i][j]]);
                    } else {
                        System.out.printf("%4d", arr[i][j]);
                    }
                }
            }
            System.out.println();
        }
    }
}
