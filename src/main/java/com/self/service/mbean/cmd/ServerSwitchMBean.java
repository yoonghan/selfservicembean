package com.self.service.mbean.cmd;

import org.jboss.system.ServiceMBean;

public interface ServerSwitchMBean extends ServiceMBean {
	public void switchServer();
	public String getServers();
	public void setServers(String servers);
	public String getActiveServer();
}
