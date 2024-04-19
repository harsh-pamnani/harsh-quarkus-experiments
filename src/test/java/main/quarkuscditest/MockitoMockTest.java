package main.quarkuscditest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

/**
 * IMPORTANT
 * 1. As we are using @Mock from Mockito, try to run the test without MockitoExtension, it will fail because bean1 will still be null in @BeforeEach.
 * 2. Run the test with @ExtendWith(MockitoExtension.class)
 *      - We must have "quarkus-junit5-mockito" dep in pom.xml to import MockitoExtension
 *      - This dependency is already added in pom.xml. Just uncomment it and run the test. Now it will work
 * 3. bean1 is mocked bean since it uses @Mock, whereas bean2 is actual instance as we are initializing it in @BeforeEach.
 * 4. For test `testBean1AlwaysMockedDueToBeforeAll`,
 *      - bean1 will return mocked message.
 *      - bean2 actual instance, so it will return actual message.
 */

//@ExtendWith(MockitoExtension.class)
public class MockitoMockTest {

    @Mock
    Bean1 bean1;

    Bean2 bean2;

    @BeforeEach
    public void setup() {
        when(bean1.greet("Stuart")).thenReturn("Mocked greeting message 1");
        bean2 = new Bean2();
    }

    @Test
    public void testBean1AlwaysMockedDueToBeforeAll() {
        Assertions.assertEquals("Mocked greeting message 1", bean1.greet("Stuart"));
        Assertions.assertEquals("Actual greeting message for Stuart", bean2.greet("Stuart"));
    }
}
