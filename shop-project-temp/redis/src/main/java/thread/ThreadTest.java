package thread;

/**
 * @author DuQian
 * @Date 2019/3/25
 */
public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        new Thread(myThread).start();
        new Thread(myThread).start();
    }
}
class MyThread implements Runnable{
    private Integer ticket = 100;
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(ticket--);
            if (ticket==0){
                break;
            }
        }
    }
}
