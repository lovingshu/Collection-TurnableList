package com.l.collection;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ��������:����ʱ���л�list,����TurnableList������
 * @createTime: 2015��7��27�� ����10:40:25
 * @author: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 2015��7��27�� ����10:40:25
 * @updateAuthor: <a href="mailto:liushulin@qidou.com">liushulin</a>
 * @changesSum:
 */
public abstract class TurnableTimerList<T> extends TurnableList<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ���췽��
	 * @param ����ʱ������
	 */
	public TurnableTimerList(Long timer){
		schedule(timer);
		super.max=-1;
	}
	
	/**
	 * ���췽�� ��timer������maxSize��һ���������,���������turn����
	 * @param timer ����ʱ������
	 * @param maxSize ���������
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
