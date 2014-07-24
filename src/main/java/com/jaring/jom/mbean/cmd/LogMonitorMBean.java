package com.jaring.jom.mbean.cmd;

import org.jboss.system.ServiceMBean;

public interface LogMonitorMBean extends ServiceMBean  {
	public void startScheduler();
	public void stopScheduler();
	public boolean getSchedulerStatus();
	public boolean getIsErrorDetected();
}
