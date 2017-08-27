package com.coco.masterwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
    //接收客户端的任务
    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<Task>();
    //存储所有work的对象
    private Map<String,Thread> works = new HashMap<String, Thread>();
    //承接work处理的 结果集
    private ConcurrentHashMap<String,Object> resultMap = new ConcurrentHashMap<String, Object>();

    //构造
    public Master(Worker worker,int taskCount){
        worker.setWorkQueue(this.workQueue);
        worker.setResultMap(this.resultMap);
        for (int i =0;i<taskCount;i++){
            works.put("子节点"+i,new Thread(worker));
        }

    }

    //提交
    public void submit(Task task){
        this.workQueue.add(task);
    }

    //执行
    public void execute(){
        for(Map.Entry<String,Thread> entry : works.entrySet()){
            entry.getKey();
            entry.getValue().start();
        }
    }

    //判断子线程是否都执行完毕
    public boolean isComplete(){
        for(Map.Entry<String,Thread> entry : works.entrySet()){
          if(entry.getValue().getState() != Thread.State.TERMINATED){
              return false;
          }
        }
        return true;
    }

    //获取结果集
    public Object getResult(){
        Object obj = null;
        List<Task> taskList = new ArrayList<Task>();
        for(Map.Entry<String,Object> entry : resultMap.entrySet()){
            taskList.add((Task) entry.getValue());
        }
        return taskList;
    }
}