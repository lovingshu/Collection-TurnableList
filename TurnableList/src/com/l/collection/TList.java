package com.l.collection;

import java.util.Collection;

public interface TList<T> {
	public boolean add(T t);
	public boolean addAll(Collection<T> t);
	public T remove(Integer index);
	public boolean removeAll(Collection<T> t);
	public Integer clear();
	/**
	 * 功能描述：是否开启log日志
	 * @author:liushulin
	 * @param show
	 * @return void
	 * 2015年8月4日 上午10:47:35
	 */
	public void showLog(boolean show);
	/**
	 * 
	 * 功能描述：是否直接引用结果集,true，那么返回的结果集就是直接引用的那个结果，false，返回的则是一个new的list对象
	 * @author:liushulin
	 * @param dependency
	 * @return void
	 * 2015年8月4日 上午10:47:51
	 */
	//public void isDependency(boolean dependency);
}