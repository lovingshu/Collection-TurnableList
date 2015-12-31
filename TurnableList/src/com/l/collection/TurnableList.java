package com.l.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ��������:�����ڲ��л���list,����list��������������⣬�������ܺܺ�
 * @createTime: 2015��7��27�� ����9:51:50
 * @author: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 2015��7��27�� ����9:51:50
 * @updateAuthor: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @changesSum:0
 */
public abstract class TurnableList<T> implements TList<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger("TurnableList");
	//����list
	private List<T> list_cu,list_1,list_2;
	private Boolean isCurrent,logOn;
	protected Integer max=-1;
	//֪ͨ������list�л�ʱ
	public abstract void turned(List<T> list);
	
	//��ʼ��
	private void init(){
		isCurrent=true;
		logOn=false;
		list_1=new ArrayList<T>();
		list_2=new ArrayList<T>();
		//Ĭ����list_cu=1;
		list_cu=list_1;
	}
	
	public TurnableList(){
		init();
	}
	
	/**
	 * 
	 * ���췽��
	 * @param maxLength ָ���ü��ϵ���󳤶�
	 */
	public TurnableList(Integer maxLength){
		init();
		max=maxLength;
	}
	
	/**
	 * 
	 * �������������һ��ֵ
	 * @author:liushulin
	 * @param val ֵ
	 * @return
	 * @return boolean
	 * 2015��8��4�� ����9:56:52
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
	 * �������������һ������
	 * @author:liushulin
	 * @param vals
	 * @return
	 * @return boolean
	 * 2015��8��4�� ����9:57:14
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
	 * ����������ȥ��ָ���±��ֵ
	 * @author:liushulin
	 * @param index �±�
	 * @return
	 * @return T
	 * 2015��8��4�� ����9:57:39
	 */
	public T remove(Integer index){
		synchronized ("") {
			T t=list_cu.get(index);
			list_cu.remove(index);
			return t;
		}
	}
	
	/**
	 * �����������Ƴ�һ������
	 * @author:liushulin
	 * @param collection
	 * @return
	 * @return boolean
	 * 2015��8��4�� ����10:01:19
	 */
	public boolean removeAll(Collection<T> collection){
		synchronized ("") {
			return list_cu.removeAll(collection);
		}
	}
	
	/**
	 * ���������������Ƿ������Ԫ��
	 * @author:liushulin
	 * @param t ����
	 * @return
	 * @return boolean
	 * 2015��8��4�� ����9:58:07
	 */
	public boolean contains(T t){
		synchronized ("") {
			return list_cu.contains(t);
		}
	}
	
	/**
	 * ������������ռ���
	 * @author:liushulin
	 * @return
	 * @return Integer
	 * 2015��8��4�� ����9:58:47
	 */
	public Integer clear(){
		synchronized ("") {
			Integer size=list_cu.size();
			list_cu.clear();
			return size;
		}
	}
	
	/**
	 * �л�����
	 * ����������
	 * @author:liushulin
	 * @return void
	 * 2015��7��24�� ����4:46:05
	 */
	protected void turn(){
		if(logOn) logger.info((list_1.size()+"||"+list_2.size()));
		//�ѵ�ǰlist�л�������list
		list_cu=(isCurrent?list_2:list_1);
		//�л�ʹ�ñ�ʶ
		isCurrent=!isCurrent;
		//��������
		turned((isCurrent?list_2:list_1));
		(isCurrent?list_2:list_1).clear();
	}
	
	public void showLog(boolean isShowing){
		this.logOn=isShowing;
	}
}
