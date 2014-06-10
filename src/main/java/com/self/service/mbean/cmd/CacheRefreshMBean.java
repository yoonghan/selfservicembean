package com.self.service.mbean.cmd;

import org.jboss.system.ServiceMBean;

public interface CacheRefreshMBean extends ServiceMBean  {
	public void refreshImages();
	public void refreshTag();
	public void refreshCategory();
	public void refreshCountry();
}
