package uk.gov.ida.dropwizard.dependencyreporting.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.Collection;

public class DependencyReportingResponse {

    @JsonProperty("datetime")
    private final DateTime dateTime;
    @JsonProperty("system.hostname")
    private final String hostname;
    @JsonProperty("os.name")
    private final String osName;
    @JsonProperty("os.arch")
    private final String osArch;
    @JsonProperty("os.version")
    private final String osVersion;
    @JsonProperty("runtime.vendor")
    private final String runtimeVendor;
    @JsonProperty("runtime.version")
    private final String runtimeVersion;
    @JsonProperty("application.name")
    private final String applicationName;
    @JsonProperty("application.version")
    private final String applicationVersion;
    @JsonProperty("application.libraries")
    private final Collection<String> applicationLibraries;

    @JsonCreator
    public DependencyReportingResponse(@JsonProperty("datetime") DateTime dateTime,
                                       @JsonProperty("system.hostname") String hostname,
                                       @JsonProperty("os.name") String osName,
                                       @JsonProperty("os.arch") String osArch,
                                       @JsonProperty("os.version") String osVersion,
                                       @JsonProperty("runtime.vendor") String runtimeVendor,
                                       @JsonProperty("runtime.version") String runtimeVersion,
                                       @JsonProperty("application.name") String applicationName,
                                       @JsonProperty("application.version") String applicationVersion,
                                       @JsonProperty("application.libraries") Collection<String> applicationLibraries) {
        this.dateTime = dateTime;
        this.hostname = hostname;
        this.osName = osName;
        this.osArch = osArch;
        this.osVersion = osVersion;
        this.runtimeVendor = runtimeVendor;
        this.runtimeVersion = runtimeVersion;
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
        this.applicationLibraries = applicationLibraries;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getHostname() {
        return hostname;
    }

    public String getOsName() {
        return osName;
    }

    public String getOsArch() {
        return osArch;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getRuntimeVendor() {
        return runtimeVendor;
    }

    public String getRuntimeVersion() {
        return runtimeVersion;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public Collection<String> getApplicationLibraries() {
        return applicationLibraries;
    }

}
