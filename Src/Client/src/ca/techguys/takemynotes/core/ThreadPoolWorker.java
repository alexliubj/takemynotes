package ca.techguys.takemynotes.core;

import android.util.Log;

/**
 * @author Alex.Liu
 * @date 2014.1.29
 * @email alexliubo@gmail.com
 */
@SuppressWarnings({"unused" })
public class ThreadPoolWorker {
	   private static int nextWorkerID = 0;

	   private ObjectFIFO idleWorkers;

	   private int workerID;

	   private ObjectFIFO handoffBox;

	   private Thread internalThread;

	   private volatile boolean noStopRequested;

	   public ThreadPoolWorker(ObjectFIFO idleWorkers) {
	     this.idleWorkers = idleWorkers;

	     workerID = getNextWorkerID();
	     handoffBox = new ObjectFIFO(1); // only one slot

	     // just before returning, the thread should be created and started.
	     noStopRequested = true;

	     Runnable r = new Runnable() {
	       public void run() {
	         try {
	           runWork();
	         } catch (Exception x) {
	           // in case ANY exception slips through
	           x.printStackTrace();
	         }
	       }
	     };

	     internalThread = new Thread(r);
	     internalThread.start();
	   }

	   public static synchronized int getNextWorkerID() {
	     // notice: synchronized at the class level to ensure uniqueness
	     int id = nextWorkerID;
	     nextWorkerID++;
	     return id;
	   }

	   public void process(Runnable target) throws InterruptedException {
	     handoffBox.add(target);
	   }

	   private void runWork() {
	     while (noStopRequested) {
	       try {
//	        	Log.v("ThreadPoolWorker","workerID=" + workerID + ", ready for work");

	         idleWorkers.add(this);

	         Runnable r = (Runnable) handoffBox.remove();

//	         Log.v("ThreadPoolWorker","workerID=" + workerID
//	             + ", starting execution of new ThreadTask: " + r);
	         runIt(r);
	       } catch (InterruptedException x) {
	         Thread.currentThread().interrupt(); // re-assert
	       }catch (Exception runex) {
	    	   //ignore
	       }
	     }
	   }

	   private void runIt(Runnable r) {
	     try {
	    	 r.run();
	     } catch (Exception runex) {
//	    	 Log.v("ThreadPoolWorker","Uncaught exception fell through from run()");
	       runex.printStackTrace();
	     } finally {
	       Thread.interrupted();
	     }
	   }

	   public void stopRequest() {
//		   Log.v("ThreadPoolWorker","workerID=" + workerID + ", stopRequest() received.");
	     noStopRequested = false;
	     internalThread.interrupt();
	   }
//	   public void stopCurrentRequest() {
//		   Log.v("ThreadPoolWorker","workerID=" + workerID + ", stopCurrentRequest() received.");
//	    // noStopRequested = false;
//	     internalThread.interrupt();
//	   }

	   public boolean isAlive() {
	     return internalThread.isAlive();
	   }
}
