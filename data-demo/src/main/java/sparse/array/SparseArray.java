package sparse.array;

/**
 * @author mwangli
 * @date 2022/1/24 14:35
 */
public class SparseArray {

    public static void main(String[] args) {
        int[][] ints = new int[9][9];
        ints[1][2] = 3;
        ints[2][3] = 1;
        showArray(ints);
        int[][] spareArray = toSpareArray(ints);
        System.out.println();
        showArray(spareArray);
        System.out.println();
        int[][] normalArray = toNormalArray(spareArray);
        showArray(normalArray);
    }

    public static int[][] toSpareArray(int[][] arrays) {
        // 获取原始数组的行和列计算总数据量
        int columnLength = arrays.length;
        int[] row = arrays[0];
        int rowLength = row.length;
        // 遍历获取有效数值
        int valueCount = 0;
        for (int[] array : arrays) {
            for (int i : array) {
                if (i != 0) valueCount++;
            }
        }
        // 定义稀疏数组的行列及初始化第一行的值
        int[][] spareArray = new int[valueCount + 1][3];
        spareArray[0][0] = columnLength;
        spareArray[0][1] = rowLength;
        spareArray[0][2] = valueCount;
        // 将非默认值存入稀疏数组
        int rowCount = 1;
        for (int i = 0; i < arrays.length; i++) {
            int[] array = arrays[i];
            for (int j = 0; j < array.length; j++) {
                if (array[j] != 0) {
                    spareArray[rowCount][0] = i;
                    spareArray[rowCount][1] = j;
                    spareArray[rowCount][2] = array[j];
                    rowCount++;
                }
            }
        }
        return spareArray;
    }

    public static int[][] toNormalArray(int[][] arrays) {
        // 初始化原始数组的行列
        int row = arrays[0][0];
        int column = arrays[0][1];
        int valueCount = arrays[0][2];
        int[][] normalArray = new int[row][column];
        // 还原成原始数组
        for (int i = 1; i < arrays.length; i++) {
            int[] array = arrays[i];
            int rowIndex = array[0];
            int columnIndex = array[1];
            int value = array[2];
            normalArray[rowIndex][columnIndex] = value;
        }
        return normalArray;
    }

    public static void showArray(int[][] arrays) {
        for (int[] array : arrays) {
            for (int i : array) {
                System.out.printf("%d\t", i);
            }
            System.out.println();
        }
    }
}
