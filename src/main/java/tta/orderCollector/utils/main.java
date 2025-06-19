package tta.orderCollector.utils;

public class main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("debut");
		int waitingTime=10000;
		do{
			Thread.sleep(100);
			waitingTime-=100;
			System.out.println("waitingTime"+ waitingTime);
		}
		while(waitingTime>0 );
		System.out.println("fin");
	}

}
