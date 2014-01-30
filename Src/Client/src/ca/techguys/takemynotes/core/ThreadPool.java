package ca.techguys.takemynotes.core;

/**
 * Thread Pool
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
public class ThreadPool{
	   private ObjectFIFO idleWorkers;

	   private ThreadPoolWorker[] workerList;
	   int numberOfThreads ;
	   public ThreadPool(int numberOfThreads) {
	     // make sure that it's at least one
	     numberOfThreads = Math.max(1, numberOfThreads);
	     this.numberOfThreads = numberOfThreads;
	     idleWorkers = new ObjectFIFO(numberOfThreads);
	     workerList = new ThreadPoolWorker[numberOfThreads];

	     for (int i = 0; i < workerList.length; i++) {
	       workerList[i] = new ThreadPoolWorker(idleWorkers);
	     }
	   }

	   public void execute(Runnable target) throws InterruptedException {
	     // block (forever) until a worker is available
	     ThreadPoolWorker worker = (ThreadPoolWorker) idleWorkers.remove(); //wait occurs here
	     worker.process(target);
	   }

	   public void stopRequestIdleWorkers() {
	     try {
	       Object[] idle = idleWorkers.removeAll();
	       for (int i = 0; i < idle.length; i++) {
	         ((ThreadPoolWorker) idle[i]).stopRequest();
	       }
	     } catch (InterruptedException x) {
	       Thread.currentThread().interrupt(); // re-assert
	     }
	   }

	   public void stop() {
	     stopRequestIdleWorkers();

	     try {
	       Thread.sleep(250);
	     } catch (InterruptedException x) {
	     }

	     for (int i = 0; i < workerList.length; i++) {
	       if (workerList[i].isAlive()) {
	         workerList[i].stopRequest();
	       }
	     }
	   }
	   public void restart() {
		     try {
				stop();
		       Thread.sleep(250);
				 idleWorkers.removeAll();
				 for (int i = 0; i < workerList.length; i++) {
				   workerList[i] = new ThreadPoolWorker(idleWorkers);
				 }
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   }
}