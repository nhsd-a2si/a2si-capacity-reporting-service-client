package com.nhsd.a2si.capacity.reporting.service.client;

import com.nhsd.a2si.capacity.reporting.service.dto.log.Detail;
import com.nhsd.a2si.capacity.reporting.service.dto.log.Header;
import com.nhsd.a2si.capacity.reporting.service.dto.waittime.WaitTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class CapacityReportingServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(CapacityReportingServiceClient.class);

    @Value("${reporting.service.api.base.url}")
    private String reportingService;

    @Autowired
    private RestTemplate reportingServiceClientRestTemplate;

    public Header sendLogHeaderToRepotingService(Header header) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        try {
            logger.debug("Attempting to call Reporting Service");
            ResponseEntity<Header> result = this.reportingServiceClientRestTemplate.postForEntity(new URI(reportingService + "/log/"), new HttpEntity<Header>(header, httpHeaders), Header.class);
            if (result.getStatusCode().value() == 201) {
                logger.debug("Reporting Service has created: " + result.getHeaders().get("Location"));
                return result.getBody();
            } else {
                //logger.error("The Header " + header.toString() + " has not been accepted by the Reporting Service(" + reportingService + "/log/" + headerId + "/)");
            }
        } catch (URISyntaxException e) {
            logger.error("Unacceptable 'reporting.service.api.base.url' value", e.getMessage());
        } catch (ResourceAccessException e) {
            logger.error("Reporting Service(" + reportingService + "/log/) is offline. Missing data: " + header.toString());
        }
        return null;
    }

    public void sendLogDetailsToRepotingService(Detail detail, long headerId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        try {
            logger.debug("Attempting to call Reporting Service");
            ResponseEntity<String> result = this.reportingServiceClientRestTemplate.postForEntity(new URI(reportingService + "/log/" + headerId + "/"), new HttpEntity<Detail>(detail, httpHeaders), String.class);
            if (result.getStatusCode().value() == 201) {
                logger.debug("Reporting Service has created: " + result.getHeaders().get("Location"));
            } else {
                logger.error("The Detail " + detail.toString() + " has not been accepted by the Reporting Service(" + reportingService + "/log/" + headerId + "/)");
            }
        } catch (URISyntaxException e) {
            logger.error("Unacceptable 'reporting.service.api.base.url' value", e.getMessage());
        } catch (ResourceAccessException e) {
            logger.error("Reporting Service(" + reportingService + "/log/" + headerId + ") is offline. Missing data: " + detail.toString());
        }
    }

    public void sendWaitTimeToRepotingService(WaitTime waitTime) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        try {
            logger.debug("Attempting to call Capacity History Service");
            ResponseEntity<String> result = this.reportingServiceClientRestTemplate.postForEntity(new URI(reportingService + "/wait-times/"), new HttpEntity<WaitTime>(waitTime, httpHeaders), String.class);
            if (result.getStatusCode().value() == 201) {
                logger.debug("Capacity History Service has created: " + result.getHeaders().get("Location"));
            } else {
                logger.error("The WaitTime object " + waitTime.toString() + " has not been accepted by Reporting Service(" + reportingService + "/wait-times/)");
            }
        } catch (URISyntaxException e) {
            logger.error("Unacceptable 'reporting.service.api.base.url' value", e.getMessage());
        } catch (ResourceAccessException e) {
            logger.error("Reporting Service(" + reportingService + "/wait-times/) is offline. Missing data: " + waitTime.toString());
        }
    }

}
