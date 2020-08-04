/**
 * Copyright: 版权所有 ( c ) 北京瑞和云图科技有限公司 2017。保留所有权利。
 * Author:	gengpan
 * Created: 2018年1月11日
 */
package cn.com.rivercloud.wechat.jwt;



import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *	注销缓存 
 * 	注销时存入 token的id，使该token作废
 * 	定时清理，当token过期时间小于当前时间则清理
 */
@Slf4j
public class LogoutCache {

	private static LogoutCache me = new LogoutCache();

	private LogoutCache(){
		thread = new LogoutCacheCleanThread();
		thread.start();
	}
	
	public static LogoutCache me(){
		return me;
	}
	

	private LogoutCacheCleanThread thread;
	
	private ConcurrentMap<String, Long> cache = new ConcurrentHashMap<String, Long>();
	
	
	public void put(String key, Long value){
		cache.put(key, value);
	}
	
	public boolean isLogout(String key){
		return cache.get(key) != null;
	}
	
	
	private class LogoutCacheCleanThread extends Thread {
		
		private boolean active;
		
		public LogoutCacheCleanThread() {
			super("LogoutCacheCleanThread");
			active = true;
		}

		@Override
		public void run() {
			
			while (active) {
				ConcurrentMap<String, Long> tmpcache = new ConcurrentHashMap<String, Long>();
				for (String key : cache.keySet()) {
					if(cache.get(key) > System.currentTimeMillis()) {
						tmpcache.put(key, cache.get(key));
					}
				}
				cache = tmpcache;
				try {
					Thread.sleep(3600*1000); //每小时轮询一次
				} catch (InterruptedException e) {
					log.error(e.getMessage(), e);
				}
				
			}
		}
		
		public void end() {
			active = false;
			this.interrupt();
		}
	}
	
}
