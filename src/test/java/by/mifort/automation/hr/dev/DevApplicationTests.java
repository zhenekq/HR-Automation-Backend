package by.mifort.automation.hr.dev;

import by.mifort.automation.hr.dev.dto.CandidateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class DevApplicationTests {
    @Test
    void contextLoads() {
        CandidateDto expected = new CandidateDto("1", new Timestamp(2L), "status");
        CandidateDto actual = new CandidateDto("1", new Timestamp(2L), "status");
        assertEquals(expected, actual);
    }
}
