package com.nhsd.a2si.capacity.reporting.service.client;

import com.nhsd.a2si.capacity.reporting.service.dto.log.Detail;
import com.nhsd.a2si.capacity.reporting.service.dto.log.Header;
import com.nhsd.a2si.capacity.reporting.service.dto.waittime.WaitTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Stub class for when running capacity service locally.
 * 
 * @author jonathanpearce
 *
 */
@Profile({"capacity-service-local-redis", "capacity-service-local-stub", "doswrapper-local-dos-stub-na-cpsc-stub-na",
    "doswrapper-local-dos-soap-local-wiremock-cpsc-stub-na",
    "doswrapper-local-dos-soap-uat-cpsc-stub-na",
   "doswrapper-local-dos-soap-prod-cpsc-stub-na",

       "doswrapper-aws-dos-stub-na-cpsc-stub-na",
      "doswrapper-aws-dos-soap-uat-cpsc-stub-na",
"doswrapper-aws-dos-soap-aws-wiremock-cpsc-stub-na"})
@Component
public class CapacityReportingServiceClientStub implements CapacityReportingServiceClient
{
    private static final Logger logger = LoggerFactory.getLogger(CapacityReportingServiceClientStub.class);

    public Header sendLogHeaderToRepotingService(Header header) 
    {
        logger.info("Logging Header for: " + header.toString());
        header.setId(1L);
        
        return header;
    }

    public void sendLogDetailsToRepotingService(Detail detail, long headerId) 
    {
        logger.info("Logging Details for: " + detail.toString());
    }

    public void sendWaitTimeToRepotingService(WaitTime waitTime) 
    {
        logger.info("Logging Wait time for: " + waitTime.toString());
    }
}
