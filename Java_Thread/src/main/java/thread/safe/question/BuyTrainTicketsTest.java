package thread.safe.question;

/**
 * @Author: ljf
 * @Create: 2021/9/10 22:53
 * @Description:
 * BuyTrainTickets 买火车票
 *
 **/
public class BuyTrainTicketsTest {

    public static void main(String[] args) {
        BuyTrainTickets buyTrainTickets = new BuyTrainTickets();
        Thread thread01 = new Thread(buyTrainTickets,"第一窗口");
        Thread thread02 = new Thread(buyTrainTickets,"第二窗口");
        Thread thread03 = new Thread(buyTrainTickets,"第三窗口");

        thread01.start();
        thread02.start();
        thread03.start();


    }
}


