package com.coco.masterwork;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Worker implements Runnable {
    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;


    public void run() {
        while (true) {
            Task task = this.workQueue.poll();
            if (task == null)
                break;
            Object obj = handle(task);
            this.resultMap.put(Integer.toString(task.getTaskId()),obj);
        }

    }

    public  Object handle(Task task) {
        System.out.println("调用 work=======");
        return null;
    }



    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }
}
