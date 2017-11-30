package uk.gov.ida.dropwizard.dependencyreporting.resources;

import com.codahale.metrics.annotation.Timed;
import uk.gov.ida.dropwizard.dependencyreporting.domain.DependencyReportingResponse;
import uk.gov.ida.dropwizard.dependencyreporting.tools.DependencyReportGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.UnknownHostException;

import static uk.gov.ida.dropwizard.dependencyreporting.resources.DependencyReportingResource.DEPENDENCY_REPORT_RESOURCE;

@Path(DEPENDENCY_REPORT_RESOURCE)
@Produces(MediaType.APPLICATION_JSON)
public class DependencyReportingResource {

    public final static String DEPENDENCY_REPORT_RESOURCE = "/dependency-report";

    private DependencyReportGenerator dependencyReportGenerator = new DependencyReportGenerator();

    public DependencyReportingResource() {
    }

    @GET
    @Timed
    public DependencyReportingResponse getDependencyReportingResponse() throws UnknownHostException {
        return dependencyReportGenerator.getDependencyReportingResponse();
    }

}
