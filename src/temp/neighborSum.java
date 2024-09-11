package temp;

import java.util.HashMap;
import java.util.Map;

public class neighborSum {
    int[][] grid;
    int row;
    Map<Integer, Integer> map;
    Map<Integer, Integer> neighbourMap;
    Map<Integer, Integer> diagonalMap;
    Map<Integer, int[]> indexMap;

    public neighborSum(int[][] grid) {
        this.indexMap = new HashMap<>();
        this.diagonalMap = new HashMap<>();
        this.neighbourMap = new HashMap<>();
        this.grid = grid;
        this.row = grid.length;
        map = new HashMap<>();
        var count = 1;
        for (var arr : grid) {
            for (var num : arr) {
                map.put(num, count);
                count++;
            }
        }
    }

    public int adjacentSum(int value) {
        if (neighbourMap.containsKey(value)) {
            return neighbourMap.get(value);
        }
        int[] arr;
        if (indexMap.containsKey(value)) {
            arr = indexMap.get(value);
        } else {
            arr = getIndexes(value);
            indexMap.put(value, arr);
        }
        var res = getAdjacentSum(arr[0], arr[1]);
        neighbourMap.put(value, res);
        return res;
    }

    private int[] getIndexes(int value) {
        var index = map.get(value);
        var cnt = index / row;
        var rem = index % row;
        var rowIndex = 0;
        var colIndex = 0;
        if (rem == 0) {
            if (cnt == 0 || cnt == 1) {
                rowIndex = 0;
            } else {
                rowIndex = cnt - 1;
            }
            colIndex = row - 1;
        } else {
            rowIndex = cnt;
            colIndex = rem - 1;
        }
        return new int[]{rowIndex, colIndex};
    }

    private int getAdjacentSum(int rowIndex, int col) {
        var res = 0;
        if (rowIndex + 1 < row) {
            res = res + grid[rowIndex + 1][col]; //down
        }
        if (col + 1 < row) {
            res = res + grid[rowIndex][col + 1]; //right
        }
        if (col - 1 >= 0) {
            res = res + grid[rowIndex][col - 1];//left
        }
        if (rowIndex - 1 >= 0) {
            res = res + grid[rowIndex - 1][col]; //up
        }
        return res;
    }

    public int diagonalSum(int value) {
        if (diagonalMap.containsKey(value)) {
            return diagonalMap.get(value);
        }
        int[] arr;
        if (indexMap.containsKey(value)) {
            arr = indexMap.get(value);
        } else {
            arr = getIndexes(value);
            indexMap.put(value, arr);
        }
        var res = getDiagonalSum(arr[0], arr[1]);
        diagonalMap.put(value, res);
        return res;
    }

    private int getDiagonalSum(int rowIndex, int colIndex) {
        var res = 0;
        var rowStart = rowIndex + 1;
        var col = colIndex + 1;
        if (rowStart < row && col < row) { // right bottom
            res = res + grid[rowStart][col];
        }
        rowStart = rowIndex - 1;
        col = colIndex - 1;
        if (rowStart >= 0 && col >= 0) { // left top
            res = res + grid[rowStart][col];
        }

        rowStart = rowIndex - 1;
        col = colIndex + 1;
        if (rowStart >= 0 && col < row) { // right top
            res = res + grid[rowStart][col];
        }
        rowStart = rowIndex + 1;
        col = colIndex - 1;
        if (rowStart < row && col >= 0) { // left bottom
            res = res + grid[rowStart][col];
        }
        return res;
    }



}
