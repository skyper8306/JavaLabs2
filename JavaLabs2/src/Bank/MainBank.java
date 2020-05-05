package Bank;

//class Account {
//    private String id;
//    private Integer balance;
//    public Integer getBalance(){ return this.balance;}
//    public String getId(){ return this.id; }
//    public void decrement(int summ){ this.balance -= summ;}
//    public void increment(int summ){ this.balance += summ;}
//}
//
//Метод, выполняющийся в многопоточном приложении:
//
//public void transferMoney(Account acc1, Account acc2, int summ){
//    acc1.decrement(summ);
//    acc2.increment(summ);
//}
//
//Какие проблемы у метода transferMoney? Как исправить метод?"

import java.util.concurrent.locks.Lock;

public class MainBank {
    static class Account {
        private String id;
        private Integer balance=0;

        public Integer getBalance() {
            return this.balance;
        }

        public String getId() {
            return this.id;
        }

        public void decrement(int summ) {
            this.balance -= summ;
        }

        public void increment(int summ) {
            this.balance += summ;
        }
    }

    static public void transferMoney(Account acc1, Account acc2, int summ) throws InterruptedException {
        synchronized (acc1){
            acc1.decrement(summ);
        }
        synchronized (acc2) {
            acc2.increment(summ);
        }
    }

    public static void main(String args[]) {
        Account acc1 = new Account();
        Account acc2 = new Account();
        for (int i = 0; i <= 100; i++) {
            new Thread(() -> {
                try{
                    transferMoney(acc1, acc2, 400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
