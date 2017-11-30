package uk.gov.ida.dropwizard.dependencyreporting.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.dropwizard.jackson.Jackson;
import org.joda.time.DateTime;
import uk.gov.ida.dropwizard.dependencyreporting.domain.DependencyReportingResponse;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class DependencyReportGenerator {

    public DependencyReportGenerator() {
    }

    public DependencyReportingResponse getDependencyReportingResponse() {
        final DateTime dateTime = DateTime.now();
        String hostname;
        try {
            hostname = HostnameHelper.getHostname();
        } catch (UnknownHostException e) {
            hostname = "error";
        }
        final String osName = Optional.ofNullable(System.getProperty("os.name")).orElse("error");
        final String osArch = Optional.ofNullable(System.getProperty("os.arch")).orElse("error");
        final String osVersion = Optional.ofNullable(System.getProperty("os.version")).orElse("error");
        final String runtimeVendor = Optional.ofNullable(System.getProperty("java.vendor")).orElse("error");
        final String runtimeVersion = Optional.ofNullable(System.getProperty("java.version")).orElse("error");
        final String applicationName = Optional.ofNullable(System.getProperty("sun.java.command")).orElse("error");
        final String applicationVersion = Optional.ofNullable(DependencyReportGenerator.class.getPackage().getImplementationVersion()).orElse("not set");
        final Collection<String> applicationLibraries = getLibraries();
        return new DependencyReportingResponse(dateTime, hostname, osName, osArch, osVersion, runtimeVendor, runtimeVersion, applicationName, applicationVersion, applicationLibraries);
    }

    public String getDependencyReportingResponseAsJsonString() {
        try {
            return Jackson.newObjectMapper().writeValueAsString(getDependencyReportingResponse());
        } catch (JsonProcessingException e) {
            return "error writing json";
        }
    }

    private Set<String> getLibraries() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        Set<String> jars = new HashSet<>();
        if(classLoader instanceof URLClassLoader) {
            for(URL jar:((URLClassLoader)classLoader).getURLs()) {
                jars.add(new File(jar.getFile()).getName());
            }
        } else {
            jars.add("could not list libraries");
        }
        return jars;
    }

}
