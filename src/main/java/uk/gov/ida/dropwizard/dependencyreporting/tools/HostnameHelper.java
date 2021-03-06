package uk.gov.ida.dropwizard.dependencyreporting.tools;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostnameHelper {
    private HostnameHelper() {}

    public static String getHostname() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
}
