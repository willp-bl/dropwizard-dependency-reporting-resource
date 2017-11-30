package uk.gov.ida.dropwizard.dependencyreporting.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import uk.gov.ida.dropwizard.dependencyreporting.domain.DependencyReportingResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.ida.dropwizard.dependencyreporting.resources.DependencyReportingResource.DEPENDENCY_REPORT_RESOURCE;

public class DependencyReportingResourceTest {

    @ClassRule
    public static final ResourceTestRule rootResource = ResourceTestRule.builder()
            .addResource(new DependencyReportingResource())
            .build();

    @Test
    public void getDependencyReportingResponse() throws Exception {
        final DependencyReportingResponse response = rootResource.client().target(DEPENDENCY_REPORT_RESOURCE).request().get(DependencyReportingResponse.class);
        assertThat(response.getDateTime().isBeforeNow()).isTrue();
        assertThat(response.getHostname()).isNotEmpty();
        assertThat(response.getHostname()).isNotEqualTo("error");
        assertThat(response.getOsName()).isNotEmpty();
        assertThat(response.getOsName()).isNotEqualTo("error");
        assertThat(response.getOsArch()).isNotEmpty();
        assertThat(response.getOsArch()).isNotEqualTo("error");
        assertThat(response.getOsVersion()).isNotEmpty();
        assertThat(response.getOsVersion()).isNotEqualTo("error");
        assertThat(response.getRuntimeVendor()).isNotEmpty();
        assertThat(response.getRuntimeVendor()).isNotEqualTo("error");
        assertThat(response.getRuntimeVersion()).isNotEmpty();
        assertThat(response.getRuntimeVersion()).isNotEqualTo("error");
        assertThat(response.getApplicationName()).isNotEmpty();
        assertThat(response.getApplicationName()).isNotEqualTo("error");
        assertThat(response.getApplicationVersion()).isNotEmpty();
        assertThat(response.getApplicationLibraries().size()).isNotZero();
        assertThat(response.getApplicationLibraries().stream().anyMatch(name -> name.endsWith(".jar"))).isTrue();
    }

}