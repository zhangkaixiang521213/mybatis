package com.ariadnethread.datacenter.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import core.utils.PropertiesUtil;


//import core.category.Logger;

public class MemCacheManager {
    
	private static MemCacheManager uniqueInstance = null;
    
	private ConcurrentHashMap<String, CacheObject> cacheHolder = null;
    private Comparator<Entry<String, CacheObject>> comparator = null;
    
    //per cache item can be as much as 100kB, so 10000 will cost 1GB
    private final int maxCacheCnt = (int)Math.pow(2, 13);
    private final int minCacheCnt = (int)Math.pow(2, 12);
    
    private int savePeriodMins = 1;
    
    private MemCacheManager() {
		cacheHolder = new ConcurrentHashMap<String, CacheObject>(maxCacheCnt);
		comparator = new Comparator<Entry<String, CacheObject>>() {
			public int compare(Entry<String, CacheObject> e1, Entry<String, CacheObject> e2){
				return e1.getValue().accessTime() - e2.getValue().accessTime()>0? 1: 0;
			}
		};
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				save();
			}
		}, 1000*10, savePeriodMins*60*1000);
	}
	
	public static String uniqueFuncId (Object...objects) {
		StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
		StringBuilder sb = new StringBuilder();
		sb.append(traceElement.getFileName());
		sb.append(".");
		sb.append(traceElement.getMethodName());
		sb.append("(");
		for (Object object : objects) {
			sb.append(object==null? " ": object);
			sb.append(",");
		}
		if (objects.length > 0) {
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(")");
		return sb.toString();
	}
	
	public static MemCacheManager getInstance() {
		if (uniqueInstance == null) {
			synchronized (MemCacheManager.class) {
		        if (uniqueInstance == null) {
		        	uniqueInstance = new MemCacheManager();
		        }
			}
		}
	    return uniqueInstance;
	}
	
	public Object getCacheForKey (String key) {
		if (key != null && PropertiesUtil.enableCache()) {
			CacheObject cache = cacheHolder.get(key);
			if (cache != null && cache.isValid()) {
				cache.accessTime(System.currentTimeMillis());
				return cache.value();
			}
		}
		return null;
	}
	
	public boolean setCacheForKey (String key, Object value, int validMins) {
		if (key != null && value != null && validMins > 0 
				&& PropertiesUtil.enableCache()) {
			CacheObject cache = new CacheObject(value, validMins);
			if (cache != null) {
				if (cacheHolder.size() > maxCacheCnt) {
					cleanCache();
				}
				cacheHolder.put(key, cache);
			}
		}
		return true;
	}
	
	public void deleteCacheForKey (String key) {
		synchronized (cacheHolder) {
			cacheHolder.remove(key);
		}
	}
	
	public void deleteCacheWithKeyLike (String key) {
		synchronized (cacheHolder) {
			for (Iterator<Entry<String, CacheObject>> i = cacheHolder.entrySet().iterator(); i.hasNext(); ) {
			    Entry<String, CacheObject> entry = i.next();
			    if (entry.getKey().indexOf(key) > 0) {
			        i.remove();
			    }
			}
		}
	}
	
	public Set<String> getCacheKeys () {
		if (cacheHolder != null) {
			return cacheHolder.keySet();
		}
		return null;
	}

	public void cleanCache () {
		ArrayList<Entry<String, CacheObject>> allCache = new ArrayList<Entry<String, CacheObject>>(cacheHolder.entrySet());
		if (allCache.size() > minCacheCnt) {
			//FIFO		
			Collections.sort(allCache, comparator);
			
			long accTime = allCache.get(minCacheCnt).getValue().accessTime();
			int toDel = allCache.size() - minCacheCnt;
			for (Entry<String, CacheObject> entry : allCache) {
				if (entry.getValue().accessTime() == 0 
					||entry.getValue().accessTime() > accTime) {
					cacheHolder.remove(entry.getKey());
					toDel --;
					if (toDel == 0) {
						allCache = null;
						return;
					}
				}
			}
		}
	}

	public void resetCache () {
		cacheHolder.clear();
	}
	
	private void save () {
		//TODO:write cache to disk
		//Logger.getInstance().dconsole(
		//		"CacheManager",
		//		"-----time:" + System.currentTimeMillis() + " action:save cache to disk here-----");
	}
	
	public HashMap<String, Object> managerInfo () {
		HashMap<String, Object> info = new HashMap<String, Object>();
		info.put("最大缓存条数", maxCacheCnt);
		info.put("最小缓存条数", minCacheCnt);
		info.put("当前缓存条数", cacheHolder.size());
		info.put("保存到磁盘频率", savePeriodMins);
		return info;
	}

	public static void main(String[] args) {

		MemCacheManager.getInstance().setCacheForKey("key", 1, 1);
		System.out.println(MemCacheManager.getInstance().getCacheForKey("key"));
		MemCacheManager.getInstance().deleteCacheForKey("key");
		System.out.println(MemCacheManager.getInstance().getCacheForKey("key"));
		
		
		for (int i = 0; i < 10000; i++) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					if (new Random().nextInt(2) == 0) {
						MemCacheManager.getInstance().setCacheForKey(
								"key", System.currentTimeMillis(), 1);
					} else {
						MemCacheManager.getInstance().deleteCacheForKey("key");
					}
				
					MemCacheManager.getInstance().cleanCache();
					System.out.println(MemCacheManager.getInstance().getCacheForKey("key"));
				}
			});
			t.start();
		}
		
		System.out.println(MemCacheManager.getInstance().cacheHolder);
		MemCacheManager.getInstance().deleteCacheWithKeyLike("tt");
		System.out.println(MemCacheManager.getInstance().cacheHolder);

	}

}
