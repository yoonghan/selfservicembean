package com.jaring.jom.mbean.cmd;

import org.jboss.system.ServiceMBeanSupport;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;

public class ServerSwitch extends ServiceMBeanSupport
	implements ServerSwitchMBean{

	private final Log log = LogFactory.getLogger(getClass().getName());
	
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
		log.info("Starting with server="+getServers());
   	}
   
	public void stopService()
   	{
		log.info("End with server="+getServers());
   	}
	
	@Override
	public String getServers(){
		return String.format("Primary:%s[%s],Secondary:%s[%s]",
				primaryServer, getActive(PRIMARY),
				secondaryServer, getActive(SECONDARY));
	}
	
	@Override
	public String getActiveServer(){
		return activeServer==PRIMARY?
				getPrimaryServer():
				getSecondaryServer();
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
