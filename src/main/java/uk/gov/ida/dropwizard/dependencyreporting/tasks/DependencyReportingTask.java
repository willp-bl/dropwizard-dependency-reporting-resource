package uk.gov.ida.dropwizard.dependencyreporting.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import uk.gov.ida.dropwizard.dependencyreporting.tools.DependencyReportGenerator;

import java.io.PrintWriter;

public class DependencyReportingTask extends Task {

    public static final String DEPENDENCY_REPORT_TASK_NAME = "dependency-report";

    private DependencyReportGenerator dependencyReportGenerator = new DependencyReportGenerator();

    public DependencyReportingTask() {
        super(DEPENDENCY_REPORT_TASK_NAME);
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
        output.println(dependencyReportGenerator.getDependencyReportingResponseAsJsonString());
    }
}
