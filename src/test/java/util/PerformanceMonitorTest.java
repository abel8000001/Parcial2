package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PerformanceMonitorTest {
    @Test
    void testLifecycle() {
        PerformanceMonitor monitor = new PerformanceMonitor("TestProceso");
        assertDoesNotThrow(monitor::inicio);
        // Pequeño workload
        long sum = 0;
        for (int i = 0; i < 1000; i++) sum += i;
        assertDoesNotThrow(monitor::finalizado);
        assertTrue(sum > 0);
    }
}
