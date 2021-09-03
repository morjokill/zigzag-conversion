import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Input: s = "PAYPALISHIRING", numRows = 3
        //Output: "PAHNAPLSIIGYIR"
        //  P   A   H   N
        //  A P L S I I G
        //  Y   I   R

        //Input: s = "PAYPALISHIRING", numRows = 4
        //Output: "PINALSIGYAHRPI"
        //  P     I    N
        //  A   L S  I G
        //  Y A   H R
        //  P     I

        //Input: s = "A", numRows = 1
        //Output: "A"

        List<AbstractMap.SimpleEntry<String, Integer>> tests = Arrays.asList(
                new AbstractMap.SimpleEntry<String, Integer>("PAYPALISHIRING", 3),
                new AbstractMap.SimpleEntry<String, Integer>("A", 2),
                new AbstractMap.SimpleEntry<String, Integer>("A", 1),
                new AbstractMap.SimpleEntry<String, Integer>("AAA", 3),
                new AbstractMap.SimpleEntry<String, Integer>("AAA", 1),
                new AbstractMap.SimpleEntry<String, Integer>("AAABB", 1),
                new AbstractMap.SimpleEntry<String, Integer>("ABC", 2),
                new AbstractMap.SimpleEntry<String, Integer>("AB", 1),
                new AbstractMap.SimpleEntry<String, Integer>("PAYPALISHIRING", 4));

        for (AbstractMap.SimpleEntry<String, Integer> test : tests) {
            System.out.println(test.toString() + ". out:" + convert(test.getKey(), test.getValue()));
        }
    }

    public static String convert(String s, int numRows) {
        //3 + (3 - 1) * 2. numRows = 4. full row = 3. 3 - 1 = 2. 4 - 2 = 1. 3 + (3 - 1) * 2 = 7
        //4 + (4 - 1) * 1. numRows = 3. full row = 4. 4 - 1 = 3. 3 - 2 = 1. 4 + (4 - 1) * 1 = 7


        //ROWS 1 2 3 5 10
        //COLS

        //BRIDGES

        int length = s.length();
        int fullRows = length / numRows + 1;

        int bridgeLength = 0;
        int bridges = 0;
        if (numRows > 2) {
            bridgeLength = numRows - 2;
            bridges = fullRows * bridgeLength;
        }
        int cols = fullRows + bridges;
        char[][] matrix = new char[numRows][cols];

        int charIndex = 0;
        //Zig-zags
        for (int colIndex = 0; colIndex < cols; colIndex++) {
            //row
            for (int row = 0; row < numRows; row++) {
                matrix[row][colIndex] = s.charAt(charIndex);
                charIndex++;
                if (charIndex >= s.length()) {
                    return buildResultString(matrix);
                }
            }

            if (bridgeLength > 0) {
                int col = colIndex + 1;
                for (int row = numRows - 2; row > 0; row--) {
                    matrix[row][col] = s.charAt(charIndex);
                    charIndex++;
                    if (charIndex >= s.length()) {
                        return buildResultString(matrix);
                    }
                    col++;
                }
                colIndex = col - 1;
            }

        }

        return buildResultString(matrix);
    }

    private static String buildResultString(char[][] matrix) {

        System.out.println(Arrays.deepToString(matrix).replaceAll(";|],", "\n"));

        StringBuilder result = new StringBuilder();
        for (char[] chars : matrix) {
            for (char symbol : chars) {
                if (symbol != '\u0000') {
                    result.append(symbol);
                }
            }
        }
        return result.toString();
    }
}
