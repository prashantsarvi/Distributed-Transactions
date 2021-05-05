public class DistributedData5408Main {
    public static void main(String[] args) {

        TransactionsDistributedData5408 DDS= new TransactionsDistributedData5408();
        Thread T1 = new Thread(DDS);
        Thread T2 = new Thread(DDS);
        T1.start();
        T2.start();
    }
}