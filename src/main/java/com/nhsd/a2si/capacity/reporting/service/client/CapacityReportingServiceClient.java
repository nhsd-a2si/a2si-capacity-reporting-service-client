package com.nhsd.a2si.capacity.reporting.service.client;

import com.nhsd.a2si.capacity.reporting.service.dto.log.Detail;
import com.nhsd.a2si.capacity.reporting.service.dto.log.Header;
import com.nhsd.a2si.capacity.reporting.service.dto.waittime.WaitTime;

public interface CapacityReportingServiceClient 
{
	public Header sendLogHeaderToRepotingService(Header header);
	public void sendLogDetailsToRepotingService(Detail detail, long headerId);
	public void sendWaitTimeToRepotingService(WaitTime waitTime);
}
