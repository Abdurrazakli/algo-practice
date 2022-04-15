import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MaxCostTest {

    @Test
    void calculateMaxCostTest1() {
        int matrix[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int expectedCost = 42;
        Assertions.assertEquals(expectedCost, MaxCostCalculator.calculateMaxCost(matrix));
    }

    @Test
    void calculateMaxCostTest2() {
        int matrix[][] = {{1, 9, 8, 1}, {3, 1, 3, 4}, {2, 5, -2, 6}};
        int expectedCost = 24;
        Assertions.assertEquals(expectedCost, MaxCostCalculator.calculateMaxCost(matrix));
    }

    @Test
    void calculateMaxCostTest3() {
        int matrix[][] = {
                {1, 9, 8, 1},
                {1, 2, 3, 4},
                {3, 1, 3, 4},
                {2, 5, -2, 6},
                {1, 2, 3, 4}
        };
        int expectedCost = 22;
        Assertions.assertEquals(expectedCost, MaxCostCalculator.calculateMaxCost(matrix));
    }
}