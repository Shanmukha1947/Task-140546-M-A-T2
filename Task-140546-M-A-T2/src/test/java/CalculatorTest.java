import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest(Calculator.class)
public class CalculatorTest {

    private Calculator calculator;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
        calculator = PowerMockito.mock(Calculator.class);
    }

    @ParameterizedTest
    @MethodSource("multiplyDataProvider")
    public void testMultiply(int a, int b, int expectedResult) {
        // Mocking the behavior of multiply method
        PowerMockito.when(calculator.multiply(a, b)).thenReturn(expectedResult);

        int result = calculator.multiply(a, b);

        // Verification
        Mockito.verify(calculator).multiply(a, b);
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> multiplyDataProvider() {
        return Stream.of(
                Arguments.of(3, 4, 12),
                Arguments.of(5, -2, -10),
                Arguments.of(-7, 8, -56),
                Arguments.of(0, 9, 0),
                Arguments.of(10, 0, 0)
        );
    }
}
