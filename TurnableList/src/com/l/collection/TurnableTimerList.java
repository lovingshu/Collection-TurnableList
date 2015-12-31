package com.l.collection;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 功能描述:倒计时可切换list,属于TurnableList的子类
 * @createTime: 2015年7月27日 上午10:40:25
 * @author: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 2015年7月27日 上午10:40:25
 * @updateAuthor: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @changesSum:
 */
public abstract class TurnableTimerList<T> extends TurnableList<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造方法
	 * @param 倒计时毫秒数
	 */
	public TurnableTimerList(Long timer){
		schedule(timer);
		super.max=-1;
	}
	
	/**
	 * 构造方法 当timer或者是maxSize任一条件满足后,便立马调用turn方法
	 * @param timer 倒计时毫秒数
	 * @param maxSize 集合最长长度
	 */
	public TurnableTimerList(Long timer,Integer maxSize){
		super.max=maxSize;
		schedule(timer);
	}
	
	private void schedule(Long timerCount){
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				turn();
			}
		},timerCount,timerCount);
	}
	
	@Override
	protected void turn() {
		synchronized ("") {
			super.turn();
		}
	}
}
