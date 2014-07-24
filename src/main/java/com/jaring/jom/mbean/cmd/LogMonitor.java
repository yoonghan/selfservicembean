package com.jaring.jom.mbean.cmd;

import org.jboss.system.ServiceMBeanSupport;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.logging.monitor.LogMonitorService;

public class LogMonitor extends ServiceMBeanSupport
	implements LogMonitorMBean {

	private final Log log = LogFactory.getLogger("com.jaring.jom.mbean.cmd.LogEmail");
	
	private LogMonitorService logSchedule = LogMonitorService.getInstance();
	
	public void create() throws Exception{
		logSchedule.startScheduler();
	}
	
	public void destroy() {
		logSchedule.stopScheduler();
		logSchedule.shutdownScheduler();
		super.destroy();
	}

	// The lifecycle
	public void startService() throws Exception
   	{
		log.info("Starting logging monitor");
		startScheduler();
   	}
   
	public void stopService()
   	{
		log.info("Stop logging monitor");
		stopScheduler();
   	}

	@Override
	public void startScheduler() {
		logSchedule.startScheduler();
	}

	@Override
	public void stopScheduler() {
		logSchedule.stopScheduler();
	}

	@Override
	public boolean getSchedulerStatus() {
		return logSchedule.isScheduleStarted();
	}
	
	public boolean getIsErrorDetected(){
		return logSchedule.getIsError();
	}
}
