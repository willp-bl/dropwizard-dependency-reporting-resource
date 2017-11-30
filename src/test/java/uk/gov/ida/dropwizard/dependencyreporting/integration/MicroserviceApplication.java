package uk.gov.ida.dropwizard.dependencyreporting.integration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import uk.gov.ida.dropwizard.dependencyreporting.resources.DependencyReportingResource;
import uk.gov.ida.dropwizard.dependencyreporting.tasks.DependencyReportingTask;

public class MicroserviceApplication extends Application<MicroserviceConfiguration> {

    public static void main(String[] args) throws Exception {
        new MicroserviceApplication().run(args);
    }

    @Override
    public String getName() {
        return "micro-servicey";
    }

    @Override
    public void initialize(Bootstrap<MicroserviceConfiguration> bootstrap) {
    }

    @Override
    public void run(MicroserviceConfiguration microserviceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(DependencyReportingResource.class);
        environment.admin().addTask(new DependencyReportingTask());
    }

}
