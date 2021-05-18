package javafiles;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor {

    public CustomThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize,20,10,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(2));

    }

    @Override
    public void execute(Runnable run) {
        Class cl = run.getClass();
        Repeat r = (Repeat) cl.getAnnotation(Repeat.class);
        int count = r.value();
        for (int i = 0; i < count; i++)
        { new Thread(run).start();}
    }
}
