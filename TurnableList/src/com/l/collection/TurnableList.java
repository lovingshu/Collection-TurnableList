package com.l.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 功能描述:可做内部切换的list,即该list不会产生并发问题，并且性能很好
 * @createTime: 2015年7月27日 上午9:51:50
 * @author: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 2015年7月27日 上午9:51:50
 * @updateAuthor: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @changesSum:0
 */
public abstract class TurnableList<T> implements TList<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger("TurnableList");
	//定义list
	private List<T> list_cu,list_1,list_2;
	private Boolean isCurrent,logOn;
	protected Integer max=-1;
	//通知当发生list切换时
	public abstract void turned(List<T> list);
	
	//初始化
	private void init(){
		isCurrent=true;
		logOn=false;
		list_1=new ArrayList<T>();
		list_2=new ArrayList<T>();
		//默认让list_cu=1;
		list_cu=list_1;
	}
	
	public TurnableList(){
		init();
	}
	
	/**
	 * 
	 * 构造方法
	 * @param maxLength 指定该集合的最大长度
	 */
	public TurnableList(Integer maxLength){
		init();
		max=maxLength;
	}
	
	/**
	 * 
	 * 功能描述：添加一个值
	 * @author:liushulin
	 * @param val 值
	 * @return
	 * @return boolean
	 * 2015年8月4日 上午9:56:52
	 */
	public boolean add(T val) {
		synchronized ("") {
			list_cu.add(val);
			if(max>0&&list_cu.size()>=max){
				turn();
			}
			return true;
		}
	}
	
	/**
	 * 
	 * 功能描述：添加一个集合
	 * @author:liushulin
	 * @param vals
	 * @return
	 * @return boolean
	 * 2015年8月4日 上午9:57:14
	 */
	public boolean addAll(Collection<T> vals) {
		synchronized ("") {
			list_cu.addAll(vals);
			if(max>0&&list_cu.size()>=max){
				turn();
			}
			return true;
		}
	}

	/**
	 * 功能描述：去掉指定下标的值
	 * @author:liushulin
	 * @param index 下标
	 * @return
	 * @return T
	 * 2015年8月4日 上午9:57:39
	 */
	public T remove(Integer index){
		synchronized ("") {
			T t=list_cu.get(index);
			list_cu.remove(index);
			return t;
		}
	}
	
	/**
	 * 功能描述：移除一个集合
	 * @author:liushulin
	 * @param collection
	 * @return
	 * @return boolean
	 * 2015年8月4日 上午10:01:19
	 */
	public boolean removeAll(Collection<T> collection){
		synchronized ("") {
			return list_cu.removeAll(collection);
		}
	}
	
	/**
	 * 功能描述：集合是否包含该元素
	 * @author:liushulin
	 * @param t 参数
	 * @return
	 * @return boolean
	 * 2015年8月4日 上午9:58:07
	 */
	public boolean contains(T t){
		synchronized ("") {
			return list_cu.contains(t);
		}
	}
	
	/**
	 * 功能描述：清空集合
	 * @author:liushulin
	 * @return
	 * @return Integer
	 * 2015年8月4日 上午9:58:47
	 */
	public Integer clear(){
		synchronized ("") {
			Integer size=list_cu.size();
			list_cu.clear();
			return size;
		}
	}
	
	/**
	 * 切换动作
	 * 功能描述：
	 * @author:liushulin
	 * @return void
	 * 2015年7月24日 下午4:46:05
	 */
	protected void turn(){
		if(logOn) logger.info((list_1.size()+"||"+list_2.size()));
		//把当前list切换到备用list
		list_cu=(isCurrent?list_2:list_1);
		//切换使用标识
		isCurrent=!isCurrent;
		//传递数据
		turned((isCurrent?list_2:list_1));
		(isCurrent?list_2:list_1).clear();
	}
	
	public void showLog(boolean isShowing){
		this.logOn=isShowing;
	}
}
