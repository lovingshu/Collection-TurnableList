package com.l.collection;

import java.util.Collection;

public interface TList<T> {
	public boolean add(T t);
	public boolean addAll(Collection<T> t);
	public T remove(Integer index);
	public boolean removeAll(Collection<T> t);
	public Integer clear();
	/**
	 * �����������Ƿ���log��־
	 * @author:liushulin
	 * @param show
	 * @return void
	 * 2015��8��4�� ����10:47:35
	 */
	public void showLog(boolean show);
	/**
	 * 
	 * �����������Ƿ�ֱ�����ý����,true����ô���صĽ��������ֱ�����õ��Ǹ������false�����ص�����һ��new��list����
	 * @author:liushulin
	 * @param dependency
	 * @return void
	 * 2015��8��4�� ����10:47:51
	 */
	//public void isDependency(boolean dependency);
}