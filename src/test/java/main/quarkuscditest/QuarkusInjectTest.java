package main.quarkuscditest;

import static org.mockito.Mockito.when;

import io.quarkus.test.junit.QuarkusMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * IMPORTANT - Remember to remove @Disabled annotation to run the test
 * 1. As we are using @Inject, try to run the test without @QuarkusTest, it will fail and complain about CDI being not available.
 * 2. Run the test with @QuarkusTest, and it will work now with injected object.
 * 3. bean1 and bean2 are normal scoped beans since they use @Inject.
 * 4. Test `testBean1AlwaysMockedDueToBeforeAll`
 *      - In this case, we have used mocked bean1 behaviour in @BeforeAll method, so the bean1 mock will take effect for all tests.
 *      - bean2 will return actual message.
 * 5. Test `testBean2MockedOnlyForThisTest`
 *      - bean2 was actual scoped instance, but we have mocked it temporarily for this test using QuarkusMock.installMockForInstance
 */

// @QuarkusTest
@Disabled
public class QuarkusInjectTest {

    @Inject
    Bean1 bean1;

    @Inject
    Bean2 bean2;

    @BeforeAll
    public static void setup() {
        Bean1 mock = Mockito.mock(Bean1.class);
        when(mock.greet("Stuart")).thenReturn("Mocked greeting message 1");
        QuarkusMock.installMockForType(mock, Bean1.class);
    }

    @Test
    public void testBean1AlwaysMockedDueToBeforeAll() {
        Assertions.assertEquals("Mocked greeting message 1", bean1.greet("Stuart"));
        Assertions.assertEquals("Actual greeting message for Stuart", bean2.greet("Stuart"));
    }

    @Test
    public void testBean2MockedOnlyForThisTest() {
        Bean2 mock = Mockito.mock(Bean2.class);
        QuarkusMock.installMockForInstance(mock, bean2);
        when(mock.greet("Stuart")).thenReturn("Mocked greeting message 2");

        Assertions.assertEquals("Mocked greeting message 1", bean1.greet("Stuart"));
        Assertions.assertEquals("Mocked greeting message 2", bean2.greet("Stuart"));
    }
}
