package mwang.online.hot100;

/**
 * 96. 不同的二叉搜索树
 * 思路：动态规划
 * 状态转移方程：  G(n) = F(i,n) + F(2,n) .... F(i,n)
 * F(i,n) = G(i-1) * G(n-i)
 * F(i,n) 代表根节点为i,长度为n的二叉搜索树数量
 * G(i-1) 代表左子数的种类数量，由于二叉排序数，所以左子树节点范围是[1,i-1]
 * G(n-i) 代表右子树种类数量，由于二叉排序数，所以右子树节点范围是[i+1,n],等价于[i,n-i]
 * 举例：根节点为3，长度为7的种类数量 F(3,7)，等于他的左子树的种类数量 G{1,2},乘右子树种类数量G{4,5,6,7}等价于G{1,2,3,4}
 * 所有种类等于左右子树的任意情况两两组合，所以乘算
 */
public class No97_TreeNums {

    public static void main(String[] args) {
        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        int[] G = new int[20];
        G[0] = 1;
        G[1] = 1;
        // 计算G(n)
        for (int j = 2; j <= n; j++) {
            // 累加计算F(i,n)
            for (int i = 1; i <= j; i++) {
                G[j] += G[i - 1] * G[j - i];
            }
        }
        return G[n];
    }

}
