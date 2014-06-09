package com.self.service.mbean.cmd;

import org.jboss.system.ServiceMBeanSupport;

public class ServerSwitch extends ServiceMBeanSupport
	implements ServerSwitchMBean{

	private String[] servers = new String[2];
	private byte activeServer = 0;
	private final char SEPERATOR='|';
	private final String REGEX_SEPERATOR="\\"+SEPERATOR;

	@Override
	public void switchServer() {
		setActiveServer(
				getSwitchedServer()==0?
						(byte)1:
						(byte)0);
	}

	@Override
	public String getServers() {
		String formattedValue =  String.format("%s%c%s", 
				servers[0].toString(),
				SEPERATOR,
				servers[1].toString());
		return formattedValue;
	}

	@Override
	public void setServers(String servers) {
		
		//made to control jboss shutdown and clean up.
		if(servers == null || this.servers == null){
			return;
		}
		
		String[] splittedServer = servers.split(REGEX_SEPERATOR);
		
		this.servers[0] = splittedServer[0];
		
		this.servers[1] = splittedServer.length == 1?
				splittedServer[0]:
				splittedServer[1];
	}

	private int getSwitchedServer() {
		return servers.length == 1?
				0:
				activeServer;
	}

	public void setActiveServer(byte activeServer) {
		this.activeServer = activeServer;
	}

	// The lifecycle
	public void startService() throws Exception
   	{
	   System.out.println("Starting with server="+getServers());
   	}
   
	public void stopService()
   	{
   		System.out.println("End with server="+getServers());
   	}

	@Override
	public String getActiveServer() {
		return servers[getSwitchedServer()];
	}
}
