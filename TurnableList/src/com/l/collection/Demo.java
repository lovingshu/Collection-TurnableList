package com.l.collection;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
	public static void main(String[] args) {
		System.out.println("----------------->");
		/*TurnableList<String> t_list=new TurnableList<String>(500) {
			@Override
			void turned(List<String> list) {
				System.out.println(list);
			}
		};*/
		TList<String> t_list=new TurnableTimerList<String>(500l,8000000) {
			@Override
			public void turned(List<String> list) {
				System.out.println(list.size());
			}
		};
		t_list.showLog(false);
		ExecutorService exe=Executors.newFixedThreadPool(1000);
		for(int i=0;i<1000;i++){
			exe.execute(new Demo().new RunTest(t_list));
			exe.execute(new Demo().new RunTest2(t_list));
		}
	}
	
	class RunTest implements Runnable{
		private TList<String> list;
		
		public RunTest(TList<String> list){
			this.list=list;
		}
		@Override
		public void run() {
			while(true){
				list.add("1");
			}
		}
	}
	
	class RunTest2 implements Runnable{
		private TList<String> list;
		
		public RunTest2(TList<String> list){
			this.list=list;
		}
		@Override
		public void run() {
			while(true){
				list.add("2");
			}
		}
	}
}
