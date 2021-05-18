package javafiles;

public class Task2 {
    public static void main(String[] strings) {
        CustomThreadPoolExecutor customThreadPoolExecutor =
                new CustomThreadPoolExecutor(10);
        customThreadPoolExecutor.execute(new MyRunnable());
    }

}
