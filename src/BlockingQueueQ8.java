/*8. Solve Pub Sub Model using BlockingQueue */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Message {
    private String msg;

    public Message(String str){
        this.msg=str;
    }

    public String getMsg() {
        return msg;
    }

}

class Publisher implements Runnable {
    private BlockingQueue<Message> queue;
    public Publisher(BlockingQueue<Message> q){
        this.queue=q;
    }
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            Message msg = new Message(""+i);
            try {
                Thread.sleep(500l);
                queue.put(msg);
                System.out.println("Publish "+msg.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message msg = new Message("exit");
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Subscriber implements Runnable{

    private BlockingQueue<Message> queue;

    public Subscriber(BlockingQueue<Message> q){
        this.queue=q;
    }

    @Override
    public void run() {
        try{
            Message msg;
            while((msg = queue.take()).getMsg() !="exit"){
                Thread.sleep(10);
                System.out.println("Subscribe "+msg.getMsg());
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class BlockingQueueQ8 {
    public static void main(String[] args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        Publisher publisher = new Publisher(queue);
        Subscriber subscriber = new Subscriber(queue);
        new Thread(publisher).start();
        new Thread(subscriber).start();
        System.out.println("Publisher and Subscriber has been started");
    }
}