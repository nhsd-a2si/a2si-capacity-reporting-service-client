package com.nhsd.a2si.capacity.reporting.service.client;

import com.nhsd.a2si.capacityinformation.domain.CapacityInformation;
import org.json.JSONObject;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class CapacityReportingServiceClient {

    private static final Logger logger = LoggerFactory.getLogger(CapacityReportingServiceClient.class);

    @Value("${reporting.service.api.base.url}")
    private String reportingService;

    @Autowired
    private RestTemplate reportingServiceClientRestTemplate;

    /*
    to do
    public void saveNewLogHeader(long headerId) {

        sendLogDetailsToRepotingService(new JSONObject()
                .put("header-id", headerId)
                .put("service-id", capacityInformation.getServiceId())
                .put("timestamp", new Date())
                .put("waitTimeInMinutes", capacityInformation.getWaitingTimeMins())
                .put("age-in-minutes", ((int) java.time.temporal.ChronoUnit.MINUTES.between(lastUpdated(capacityInformation), LocalDateTime.now()))
                ), headerId);




    }*/

    public void saveNewLogDetail(long headerId, CapacityInformation capacityInformation) {
        logger.info("Sending Capacity Information for Service Id: {} with value of {} to Capacity History Service", capacityInformation.getServiceId(), capacityInformation);
        sendLogDetailsToRepotingService(new JSONObject()
                .put("header-id", headerId)
                .put("service-id", capacityInformation.getServiceId())
                .put("timestamp", new Date())
                .put("waitTimeInMinutes", capacityInformation.getWaitingTimeMins())
                .put("age-in-minutes", ((int) java.time.temporal.ChronoUnit.MINUTES.between(lastUpdated(capacityInformation), LocalDateTime.now()))
                ), headerId);
    }

    public void saveNewLogDetail(long headerId, String serviceId) {
        logger.info("Sending Service Id: {}", serviceId);
        sendLogDetailsToRepotingService(new JSONObject()
                .put("header-id", headerId)
                .put("service-id", serviceId)
                .put("timestamp", new Date()
                ), headerId);
    }

    private LocalDateTime lastUpdated(CapacityInformation capacityInformation) {
        return LocalDateTime.parse(capacityInformation.getLastUpdated(), DateTimeFormatter.ofPattern(CapacityInformation.STRING_DATE_FORMAT));
    }

    private void sendLogDetailsToRepotingService(JSONObject object, long headerId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        try {
            logger.debug("Attempting to call Reporting Service");
            ResponseEntity<String> result = this.reportingServiceClientRestTemplate.postForEntity(new URI(reportingService + "/log/" + headerId + "/"), new HttpEntity<String>(object.toString(), httpHeaders), String.class);
            if (result.getStatusCode().value() == 201) {
                logger.debug("Reporting Service has created: " + result.getHeaders().get("Location"));
            } else {
                logger.error("The JSON object " + object.toString() + " has not been accepted by the Reporting Service(" + reportingService + "/log/" + headerId + "/)");
            }
        } catch (URISyntaxException e) {
            logger.error("Unacceptable 'reporting.service.api.base.url' value", e.getMessage());
        } catch (ResourceAccessException e) {
            logger.error("Reporting Service(" + reportingService + "/log/" + headerId + ") is offline. Missing data: " + object.toString());
        }
    }



    public void saveNewWaitTime(CapacityInformation capacityInformation) {
        logger.info("Sending Capacity Information for Service Id: {} with value of {} to Capacity History Service", capacityInformation.getServiceId(), capacityInformation);
        try {
            sendWaitTimeToRepotingService(new JSONObject()
                    .put("service", new JSONObject()
                            .put("id", capacityInformation.getServiceId())
                            .put("name", capacityInformation.getServiceName())
                    )
                    .put("waitTimeInMinutes", capacityInformation.getWaitingTimeMins())
                    .put("updated", new SimpleDateFormat(CapacityInformation.STRING_DATE_FORMAT).parse(capacityInformation.getLastUpdated()))
                    .put("provider", new JSONObject()
                            .put("name", "Derbyshire Health Care")
                            .put("region", "Leicester, Leicestershire and Rutland")
                    ));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void sendWaitTimeToRepotingService(JSONObject object) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", "application/json");
        try {
            logger.debug("Attempting to call Capacity History Service");
            ResponseEntity<String> result = this.reportingServiceClientRestTemplate.postForEntity(new URI(reportingService + "/wait-times/"), new HttpEntity<String>(object.toString(), httpHeaders), String.class);
            if (result.getStatusCode().value() == 201) {
                logger.debug("Capacity History Service has created: " + result.getHeaders().get("Location"));
            } else {
                logger.error("The JSON object " + object.toString() + " has not been accepted by Capacity History Service(" + reportingService + "/wait-times/)");
            }
        } catch (URISyntaxException e) {
            logger.error("Unacceptable 'reporting.service.api.base.url' value", e.getMessage());
        } catch (ResourceAccessException e) {
            logger.error("Capacity History Service(" + reportingService + "/wait-times/) is offline. Missing data: " + object.toString());
        }
    }

}
