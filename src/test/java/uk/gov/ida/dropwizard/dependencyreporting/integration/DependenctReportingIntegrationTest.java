package uk.gov.ida.dropwizard.dependencyreporting.integration;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;
import uk.gov.ida.dropwizard.dependencyreporting.domain.DependencyReportingResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.ida.dropwizard.dependencyreporting.resources.DependencyReportingResource.DEPENDENCY_REPORT_RESOURCE;
import static uk.gov.ida.dropwizard.dependencyreporting.tasks.DependencyReportingTask.DEPENDENCY_REPORT_TASK_NAME;

public class DependenctReportingIntegrationTest {

    @ClassRule
    public static final DropwizardAppRule<MicroserviceConfiguration> microserviceAppRule =
            new DropwizardAppRule<>(MicroserviceApplication.class,
                    null,
                    ConfigOverride.config("server.applicationConnectors[0].port", "0"),
                    ConfigOverride.config("server.applicationConnectors[0].type", "http"),
                    ConfigOverride.config("server.adminConnectors[0].port", "0"),
                    ConfigOverride.config("server.adminConnectors[0].type", "http"),
                    ConfigOverride.config("logging.appenders[0].type", "console"),
                    ConfigOverride.config("logging.appenders[0].target", "stdout")
            );

    @Test
    public void testGetDependencyReportResource() throws UnknownHostException {
        Client client = new JerseyClientBuilder().build();
        final String resource = MessageFormat.format("http://localhost:{0}"+DEPENDENCY_REPORT_RESOURCE, Integer.toString(microserviceAppRule.getLocalPort()));

        final Response response = client.target(resource).request().get();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        DependencyReportingResponse dependencyReportingResponse = response.readEntity(DependencyReportingResponse.class);
        assertThat(dependencyReportingResponse).isNotNull();
        assertThat(dependencyReportingResponse.getApplicationLibraries().size()).isNotZero();
        assertThat(dependencyReportingResponse.getApplicationLibraries().contains("rt.jar")).isTrue();
    }

    @Test
    public void testDependencyReportTask() throws IOException {
        Client client = new JerseyClientBuilder().build();
        final String resource = MessageFormat.format("http://localhost:{0}/tasks/"+ DEPENDENCY_REPORT_TASK_NAME, Integer.toString(microserviceAppRule.getAdminPort()));

        final Response response = client.target(resource).request().post(null);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);
        final String jsonString = response.readEntity(String.class);
        DependencyReportingResponse dependencyReportingResponse = Jackson.newObjectMapper().readValue(jsonString, DependencyReportingResponse.class);
        assertThat(dependencyReportingResponse).isNotNull();
        assertThat(dependencyReportingResponse.getApplicationLibraries().size()).isNotZero();
        assertThat(dependencyReportingResponse.getApplicationLibraries().contains("rt.jar")).isTrue();
    }
}
