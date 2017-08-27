package com.coco.masterwork;

public class MyWork extends Worker {

    @Override
    public  Object handle(Task task) {
        System.out.println("调用 mywork");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return task;
    }
}
