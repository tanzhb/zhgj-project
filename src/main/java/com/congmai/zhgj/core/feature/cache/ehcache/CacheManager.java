package com.congmai.zhgj.core.feature.cache.ehcache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.activiti.engine.impl.interceptor.Session;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;

/**
 * @author tanzb
 * @version 2017年9月2日 下午3:01:00
 */
public class CacheManager<K, V> implements Cache<K, V> {
	private EhCacheManager cacheManager;
	private Cache<K, V> cache = null;
	
	public Cache<K, V> getCache() {
		try {
			if(null == cache){
				cache = cacheManager.getCache("shiro_cache");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cache;
	}
	@Override
	public void clear() throws CacheException {
		getCache().clear();
	}
	@Override
	public V get(K key) throws CacheException {
		return getCache().get(key);
	}
	@Override
	public Set<K> keys() {
		
		return getCache().keys();
	}
	@Override
	public V put(K key, V value) throws CacheException {
		return getCache().put(key, value);
	}
	@Override
	public V remove(K key) throws CacheException {
		return getCache().remove(key);
	}
	@Override
	public int size() {
		return getCache().size();
	}
	@Override
	public Collection<V> values() {
		return getCache().values();
	}
	public EhCacheManager getCacheManager() {
		return cacheManager;
	}
	public void setCacheManager(EhCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	public void setCache(Cache<K, V> cache) {
		this.cache = cache;
	}
	/**
	 * 获取所有Session
	 * @throws Exception
	 */
	public Collection<Session> AllSession() throws Exception {
                Set<Session> sessions = new HashSet<Session>();
		try {
			//TODO 注意事项：必须此缓存只存储Session，要不造成性能下降
			cache = getCache();
			Collection<V> values = cache.values();
			for (V v : values) {
				if(v != null && v instanceof Session){
					sessions.add((Session)v);
				}
			}
                 } catch (Exception e) {
                    throw e;
                 } 
                 return sessions;
	}
}
