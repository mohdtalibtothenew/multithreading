/*A program consists of n thread printing 1 to 10. Below is the sample output of executing 4 concurrent threads.
Thread 1: 1
Thread 1: 5
Thread 1: 9
Thread 3: 3
Thread 3: 7
Thread 2: 2
Thread 4: 4
Thread 2: 6
Thread 4: 8
Thread 2: 10
Identify the pattern and create a program to get the output for N concurrent threads.*/


class MyThread extends Thread{
    int start;
    public MyThread(int start) {
        this.start = start;
    }
    public void run() {
        while(start <= 10){
            System.out.println(Thread.currentThread().getName() +" "+start);
            start += 4;
        }
    }
}
public class PatternQ9 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            MyThread myThread = new MyThread(i+1);
            myThread.setName("Thread--"+(i+1));
            myThread.start();
        }
        Thread.sleep(500l);
    }
}