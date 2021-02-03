import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LogicalConnectorMatrixTests {
    LogicalConnectorMatrix lcm;

    @BeforeEach
    public void setUp() throws Exception {
        lcm = new LogicalConnectorMatrix("./inputs/lcm1.txt");
    }
    
    @Test
    @DisplayName("Calculate valid")
    void calculateValidTests() {
        assertTrue(lcm.calculate(0, 0, true, true));
        assertFalse(lcm.calculate(1, 1, true, false));
        assertFalse(lcm.calculate(2, 2, false, true));
        assertTrue(lcm.calculate(2, 0, true, true));
        assertTrue(lcm.calculate(2, 0, false, true));
        assertTrue(lcm.calculate(4, 3, false, true));
    }
}
