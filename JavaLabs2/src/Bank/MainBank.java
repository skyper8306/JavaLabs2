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

    //В общем смысл в том чтобы несколько потоков одновременно не получили доступ к одному ресурсу(аккаунту).
    //Но если исправлять только этот метод, получится что два разных потока не смогут параллельно выполняться с 4 разными аккаунтами (откуда1, куда2) (откуда3, куда4)
    synchronized static public void transferMoney(Account acc1, Account acc2, int summ) throws InterruptedException {
        System.out.println("before");
        Thread.sleep(50);
        acc1.decrement(summ);
        acc2.increment(summ);
        System.out.println("after");
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
