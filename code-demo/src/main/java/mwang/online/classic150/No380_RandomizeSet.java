package mwang.online.classic150;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * @version 1.0.0
 * @author: mwangli
 * @date: 2024/3/15 10:27
 * @description: No380_RandomizeSet
 */
public class No380_RandomizeSet {

    @Test
    public void test() {
        final No380_RandomizeSet randomizedSet = new No380_RandomizeSet();
        Assert.assertTrue(randomizedSet.insert(1));
        Assert.assertFalse(randomizedSet.remove(2));
        Assert.assertTrue(randomizedSet.insert(2));
        final int randomValue1 = randomizedSet.getRandom();
        Assert.assertTrue(randomValue1 == 1 || randomValue1 == 2);
        Assert.assertTrue(randomizedSet.remove(1));
        Assert.assertFalse(randomizedSet.insert(2));
        Assert.assertEquals(randomizedSet.getRandom(), 2);

    }

    private int[] vales;
    private boolean[] flag;
    private int capacity;
    private int size;

    public No380_RandomizeSet() {
        vales = new int[1024];
        flag = new boolean[1024];
        capacity = 1024;
        size = 0;
    }


    private boolean isPresent(int val) {
        return getIndex(val) != -1;
    }

    private int getIndex(int val) {
        for (int i = 0; i < capacity; i++) {
            if (flag[i] && vales[i] == val) {
                return i;
            }
        }
        return -1;
    }

    private int findFistEmptyIndex() {
        int i = 0;
        while (i < capacity) {
            if (!flag[i]) return i;
            i++;
        }
        return -1;
    }


    public boolean insert(int val) {
        // increase capacity to double size
        if (size + 1 > capacity) {
            capacity <<= 1;
            final int[] destArray = new int[capacity];
            final boolean[] flagArray = new boolean[capacity];
            System.arraycopy(vales, 0, destArray, 0, vales.length);
            System.arraycopy(flag, 0, flagArray, 0, flag.length);
            vales = destArray;
            flag = flagArray;
        }
        // if value have been present
        if (isPresent(val)) {
            return false;
        } else {
            // find a empty place to insert
            final int insetIndex = findFistEmptyIndex();
            vales[insetIndex] = val;
            flag[insetIndex] = true;
            size++;
            return true;
        }
    }

    public boolean remove(int val) {
        final int index = getIndex(val);
        if (index == -1) {
            return false;
        } else {
            vales[index] = 0;
            flag[index] = false;
            size--;
            return true;
        }

    }

    public int getRandom() {
        final int randomIndex = new Random().nextInt(size);
        int count = 0;
        int i = 0;
        while (i < capacity && count <= randomIndex) {
            if (flag[i]) count++;
            i++;
        }
        return vales[i - 1];
    }
}
