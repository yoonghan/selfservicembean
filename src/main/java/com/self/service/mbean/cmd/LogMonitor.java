package com.self.service.mbean.cmd;

import org.jboss.system.ServiceMBeanSupport;

import com.self.service.logging.log.LogUtil;
import com.self.service.logging.monitor.LogMonitorService;

public class LogMonitor extends ServiceMBeanSupport
	implements LogMonitorMBean {

	private final String CLASS_NAME = "com.self.service.mbean.cmd.LogEmail";
	
	private LogMonitorService logSchedule = LogMonitorService.getInstance();
	
	public void create() throws Exception{
		logSchedule.startScheduler();
	}
	
	public void destroy() {
		logSchedule.stopScheduler();
		super.destroy();
	}

	// The lifecycle
	public void startService() throws Exception
   	{
		LogUtil.getInstance(CLASS_NAME).info("Starting logging monitor");
		startScheduler();
   	}
   
	public void stopService()
   	{
		LogUtil.getInstance(CLASS_NAME).info("Stop logging monitor");
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
