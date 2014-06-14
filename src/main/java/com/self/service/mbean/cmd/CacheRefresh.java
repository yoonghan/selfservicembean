package com.self.service.mbean.cmd;

import org.jboss.system.ServiceMBeanSupport;

import com.self.care.store.jdbi.caches.CategoryCache;
import com.self.care.store.jdbi.caches.EnumCountryCache;
import com.self.care.store.jdbi.caches.ImageCache;
import com.self.care.store.jdbi.caches.ImageCategoryCache;
import com.self.care.store.jdbi.caches.ImageTagCache;
import com.self.care.store.jdbi.caches.TagCache;
import com.self.service.logging.log.LogUtil;

public class CacheRefresh extends ServiceMBeanSupport
	implements CacheRefreshMBean {

	private final String CLASS_NAME = "com.self.service.mbean.cmd.CacheRefresh";
	
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
		ImageCache.getInstance().refreshCache();
	}

	@Override
	public void refreshTag() {
		TagCache.getInstance().refreshCache();
		ImageTagCache.getInstance().refreshCache();
		TagCache.getInstance().clearFindAllCache();
	}

	@Override
	public void refreshCategory() {
		CategoryCache.getInstance().refreshCache();
		ImageCategoryCache.getInstance().refreshCache();
		CategoryCache.getInstance().clearFindAllCache();
	}

	@Override
	public void refreshCountry() {
		EnumCountryCache.getInstance().refreshCache();
	}

	// The lifecycle
	public void startService() throws Exception
   	{
		LogUtil.getInstance(CLASS_NAME).info("Starting refresher");
   	}
   
	public void stopService()
   	{
		LogUtil.getInstance(CLASS_NAME).info("Ending refresher");
   	}
	
	public void destroy(){
		CategoryCache.getInstance().stopRecordRefresher();
		TagCache.getInstance().stopRecordRefresher();
	}
}
