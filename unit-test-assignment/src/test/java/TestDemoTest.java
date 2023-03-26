import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class TestDemoTest {
		
	TestDemo testDemo = new TestDemo();
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, 
			boolean expectException) {
		if(!expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
			} else {
				assertThatThrownBy(() -> 
			    testDemo.addPositive(a, b))
			        .isInstanceOf(IllegalArgumentException.class);

			}
	}
	
	public static Stream<Arguments> argumentsForAddPositive() {
        return Stream.of(
            arguments(2, 3, 5, false),
            arguments(1, 3, 4, false),
            arguments(-2, 3, 0, true),
            arguments(2, 0, 0, true)
        );
    }
	
	@Test
    public void testRandomNumberSquared() {
        TestDemo mockDemo = spy(TestDemo.class);
        doReturn(5).when(mockDemo).getRandomInt();

        int fiveSquared = mockDemo.randomNumberSquared();

        assertThat(fiveSquared).isEqualTo(25);
    }

}
