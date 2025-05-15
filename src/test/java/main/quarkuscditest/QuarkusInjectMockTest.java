package main.quarkuscditest;

import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * IMPORTANT - Remember to remove @Disabled annotation to run the test
 * 1. As we are using @InjectMock, try to run the test without @QuarkusTest, it will fail because bean1 will still be null in @BeforeEach.
 * 2. Run the test with @QuarkusTest, and it will still fail with error - "Failed to create test instance"
 *      - Because when we want to use @InjectMock, we must have "quarkus-junit5-mockito" dep in pom.xml as we are using Mockito features
 *      - This dependency is already added in pom.xml. Just uncomment it and run the test. Now it will work
 * 3. bean1 and bean2 are mocked beans since they use @InjectMock
 *      - @InjectMock results in a Mockito mock being created (similar to @Mock in Mockito).
 *      - Mockito @Mock will not work without `MockitoExtension`. But, @InjectMock will work without any extension as we are using @QuarkusTest.
 *      - Check MockitoMockTest file for the usage of @Mock
 * 4. For test `testBean1ReturnCorrectMessage`,
 *      - bean1 will return mocked message.
 *      - bean2 is mocked, but no mock behavior is defined for `greet` method, so it will return null.
 * 5. For test `bothBeansReturnMockedMessage`,
 *      - bean1 and bean2 both has `greet` method mocked. So, both will return mocked message.
 */
// @QuarkusTest
@Disabled
public class QuarkusInjectMockTest {

    @InjectMock
    Bean1 bean1;

    @InjectMock
    Bean2 bean2;

    @BeforeEach
    public void setup() {
        when(bean1.greet("Stuart")).thenReturn("Mocked greeting message 1");
    }

    @Test
    public void testBean1ReturnCorrectMessage() {
        Assertions.assertEquals("Mocked greeting message 1", bean1.greet("Stuart"));
        Assertions.assertEquals(null, bean2.greet("Stuart"));
    }

    @Test
    public void bothBeansReturnMockedMessage() {
        when(bean2.greet("Stuart")).thenReturn("Mocked greeting message 2");
        Assertions.assertEquals("Mocked greeting message 1", bean1.greet("Stuart"));
        Assertions.assertEquals("Mocked greeting message 2", bean2.greet("Stuart"));
    }
}
