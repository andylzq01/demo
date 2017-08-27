package com.coco.masterwork;

import java.util.Random;

/**
 * master-work 模式
 */
public class Main {
    public static  final void  main(String[] args){
        //,Runtime.getRuntime().availableProcessors() 机器可用线程数
        System.out.println("可用线程数:"+Runtime.getRuntime().availableProcessors());
        Master master = new Master(new MyWork(),Runtime.getRuntime().availableProcessors());
        Random random = new Random();
        for(int i=0;i<10;i++){
            Task task = new Task();
            task.setTaskId(i);
            task.setProcess("处理线程---"+i);
            task.setTaskName("线程--------"+i);
            master.submit(task);
        }
        long startTime = System.currentTimeMillis();
        System.out.println("提交任务 ----------------");
        master.execute();
        while(true){
            if(master.isComplete()){
                long time = System.currentTimeMillis() - startTime;
               System.out.println("任务处理 完毕 ----------------共耗时"+time);
                System.out.println("处理结果："+master.getResult());
                break;

            }

        }



    }
}
