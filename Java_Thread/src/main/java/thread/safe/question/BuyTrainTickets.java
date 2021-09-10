package thread.safe.question;

/**
 * @Author: ljf
 * @Create: 2021/9/10 22:56
 * @Description:
 **/
public class BuyTrainTickets extends Thread {

    private static int count = 100;


    @Override
    public void run() {
        while (count > 0){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketSales();

        }
    }

    private  void ticketSales() {
        synchronized(this){
            if (count > 0){
                System.out.println(Thread.currentThread().getName()+"售卖了第"+(100-count+1)+"票");
                count--;
            }
        }

    }
}
