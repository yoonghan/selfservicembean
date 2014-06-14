package com.self.service.mbean.cmd;

import org.jboss.system.ServiceMBean;

public interface ServerSwitchMBean extends ServiceMBean {
	public void switchServer();
	public String getServers();
	public String getActiveServer();
	public void setPrimaryServer(String server);
	public String getPrimaryServer();
	public void setSecondaryServer(String server);
	public String getSecondaryServer();
	public void activatePrimaryServer();
	public void activateSecondaryServer();
}
