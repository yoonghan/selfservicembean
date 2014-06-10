package com.self.service.mbean.cmd;

import org.jboss.system.ServiceMBeanSupport;

import com.self.service.util.log.LogUtil;

public class ServerSwitch extends ServiceMBeanSupport
	implements ServerSwitchMBean{

	private final String CLASS_NAME = "com.self.service.mbean.cmd.ServerSwitch";
	
	private byte PRIMARY = 0;
	private byte SECONDARY = 1;
	
	private String primaryServer="";
	private String secondaryServer="";
	private byte activeServer = PRIMARY;

	@Override
	public void switchServer() {
		String holder = primaryServer;
		primaryServer = secondaryServer;
		secondaryServer = holder;
		activeServer = activeServer==PRIMARY?
				SECONDARY:PRIMARY;
	}

	// The life cycle
	public void startService() throws Exception
   	{
		LogUtil.getInstance(CLASS_NAME).info("Starting with server="+getActiveServers());
   	}
   
	public void stopService()
   	{
		LogUtil.getInstance(CLASS_NAME).info("End with server="+getActiveServers());
   	}
	
	@Override
	public String getActiveServers(){
		return String.format("Primary:%s[%s],Secondary:%s[%s]",
				primaryServer, getActive(PRIMARY),
				secondaryServer, getActive(SECONDARY));
	}

	private String getActive(byte serverHierarchy) {
		
		return serverHierarchy == activeServer? 
				"ACTIVE":
					"HALT";
	}
	
	@Override
	public void setPrimaryServer(String server){
		if(server == null)
			return;
		primaryServer = server;
	}
	
	@Override
	public String getPrimaryServer(){
		return primaryServer;
	}
	
	@Override
	public void setSecondaryServer(String server){
		if(server == null)
			return;
		secondaryServer = server;
	}
	
	@Override
	public String getSecondaryServer(){
		return secondaryServer;
	}
	
	@Override
	public void activatePrimaryServer(){
		activeServer = PRIMARY;
	}
	
	@Override
	public void activateSecondaryServer(){
		activeServer = SECONDARY;
	}
}
