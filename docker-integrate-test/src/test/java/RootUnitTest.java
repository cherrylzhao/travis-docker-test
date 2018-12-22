import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.DockerPort;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;

public class RootUnitTest {
    
    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
        .file("src/test/resources/docker-compose.mysql.yaml")
        .waitingForService("database", HealthChecks.toHaveAllPortsOpen())
        .build();
    
    @BeforeClass
    public static void beforeClass() throws IOException, InterruptedException {
        docker.containers().container("database").kill();
        docker.containers().container("database").start();
        DockerPort dockerPort = docker.containers().container("database").port(3306);
        System.out.println(dockerPort.getIp());
    }
    
    @Test
    public void case01() {
        System.out.println("succeed");
    }
}
