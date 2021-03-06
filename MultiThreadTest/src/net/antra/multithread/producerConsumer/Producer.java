package net.antra.multithread.producerConsumer;

import java.util.ArrayList;
import java.util.List;

public class Producer extends Thread{

	private List<String> productList = new ArrayList<String>();
	private static int pNo = 1;
	private volatile int maxNum;
	public Producer(int max) {
		this.maxNum = max;
	}
	@Override
	public void run() {
		System.out.println("Producer is running...........");
		try {
			for(int i = 0 ; i < maxNum; i ++){
				produce();
				//sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Producer is done...........");
	}

	public synchronized void produce() throws InterruptedException{
//		if(productList.size() >= maxNum){
//			wait();
//		}
		productList.add("Product number:" + pNo);
		System.out.println("Product is ready : # " + pNo);
		pNo++;
		notify();
	}

	public synchronized String getProduct() throws InterruptedException {
		if(productList.size() < 1){
			wait();
		}
		String res = productList.get(0);
		productList.remove(0);
		//notify();
		return res;
	}
}
