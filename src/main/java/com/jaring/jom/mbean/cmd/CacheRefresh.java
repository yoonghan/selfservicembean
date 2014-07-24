package com.jaring.jom.mbean.cmd;

import org.jboss.system.ServiceMBeanSupport;

import com.jaring.jom.logging.impl.Log;
import com.jaring.jom.logging.log.LogFactory;
import com.jaring.jom.store.jdbi.caches.DBCache;

public class CacheRefresh extends ServiceMBeanSupport
	implements CacheRefreshMBean {

	private final Log log = LogFactory.getLogger("com.jaring.jom.mbean.cmd.CacheRefresh");
	
	@Override
	public String getName() {
		return "CacheRefresh";
	}

	@Override
	public int getState() {
		return 1;
	}

	@Override
	public String getStateString() {
		return "Active";
	}

	@Override
	public void refreshImages() {
		DBCache.INSTANCE.getImage().refreshCache();
	}

	@Override
	public void refreshTag() {
		DBCache.INSTANCE.getTag().refreshCache();
		DBCache.INSTANCE.getImageTag().refreshCache();
		DBCache.INSTANCE.getTag().clearFindAllCache();
	}

	@Override
	public void refreshCategory() {
		DBCache.INSTANCE.getCategory().refreshCache();
		DBCache.INSTANCE.getImageCategory().refreshCache();
		DBCache.INSTANCE.getCategory().clearFindAllCache();
	}

	@Override
	public void refreshCountry() {
		DBCache.INSTANCE.getEnumCountry().refreshCache();
	}

	// The lifecycle
	public void startService() throws Exception
   	{
		log.info("Starting refresher");
   	}
   
	public void stopService()
   	{
		log.info("Ending refresher");
   	}
	
	public void destroy(){
		DBCache.INSTANCE.getCategory().stopRecordRefresher();
		DBCache.INSTANCE.getTag().stopRecordRefresher();
	}

	@Override
	public void refreshMenu() {
		DBCache.INSTANCE.getMenu().refreshCache();
		DBCache.INSTANCE.getMenuList().refreshCache();
	}
}
