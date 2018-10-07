/*Q4.) Write a program called ReverseHelloQ4.java that creates a thread (let's call it Thread 1).
        Thread 1 creates another thread (Thread 2); Thread 2 creates Thread 3; and so on, up to Thread 10.
        Each thread should print "Hello from Thread <num>!",
        but you should structure your program such that the threads print their greetings in reverse order.*/



class ReverseHello implements Runnable{
    int count;
    ReverseHello(int count){
        this.count = count;
    }

    public void run() {
        try{
            if(count < 10){
                Thread newThread = new Thread(new ReverseHello(count+1));
                newThread.setName("Hello From Thread "+ (count + 1));

                newThread.start();
                newThread.join();
            }
            System.out.println(Thread.currentThread().getName());
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
public class ReverseHelloQ4 {
    public static void main(String[] args) throws InterruptedException{
        ReverseHello reverseHello = new ReverseHello(1);
        Thread t1 = new Thread(reverseHello);
        t1.setName("Hello From Thread 1");
        t1.start();
        t1.join();
    }
}