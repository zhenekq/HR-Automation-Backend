package by.mifort.automation.hr.dev;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class DevApplicationTests {
    @Test
    void contextLoads() {
        assertEquals(1, 1);
    }
}
