package com.self.service.mbean.util;

import java.util.HashMap;

import javax.management.MBeanServer;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;

import org.jboss.mx.util.MBeanServerLocator;

import com.self.service.mbean.cmd.ServerSwitchMBean;
import com.self.service.util.log.LogUtil;

public class MBeanUtility {

	private final int DEFAULT_UTILITIES_AVAILABLE=1;
	private final String CLASS_NAME="com.self.service.mbean.util.MBeanUtility";
	
	HashMap<String, Object> utilityBelt = new HashMap<String, Object>(DEFAULT_UTILITIES_AVAILABLE);
	
	private final String SERVER_SWITCH_KEY="SS";
	
	private static final class Singleton{
		private static final MBeanUtility instance = new MBeanUtility();
	}
	
	private MBeanUtility(){
		//create instances;
		Object server = initServerSwitch();
		utilityBelt.put(SERVER_SWITCH_KEY, server);
	}
	
	private Object initServerSwitch() {
		Object serverSwitch = null;
		try{
			MBeanServer server = MBeanServerLocator.locate();
			ObjectName objectName;
			
			objectName = new ObjectName("self.service:service=ServerSwitch");
			
			serverSwitch = MBeanServerInvocationHandler.newProxyInstance(
					server,
					objectName,
					ServerSwitchMBean.class,
					false); 
		
			
		}catch(Exception e){
			LogUtil.getInstance(CLASS_NAME).error("Unable to call mbean.", e);
			e.printStackTrace();
		}
		return serverSwitch;
	}

	public static MBeanUtility getInstance(){
		return Singleton.instance;
	}

	public String getActiveServer(){
		Object obj = utilityBelt.get(SERVER_SWITCH_KEY);
		
		String activeServer = "";
		
		if(obj != null){
			ServerSwitchMBean serverSwitch = (ServerSwitchMBean)obj;
			activeServer = serverSwitch.getActiveServer();
			LogUtil.getInstance(CLASS_NAME).info("Server Available:"+activeServer);
		}else{
			LogUtil.getInstance(CLASS_NAME).error("No server Available");
		}
		
		return activeServer;
	}
}
