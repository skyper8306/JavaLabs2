package BackToTheFuture;

//int t=0; Выполняется t+=1000 каждую секунду и отображается в программе как время (мс).
// Через какой период мы попадем в прошлое? Покажите ваши вычисления.

public class MainBackToTheFuture {
    public static void main(String args[]) throws InterruptedException {
        int t=0;
        while (t>=0){
            t+=1000;
            //Thread.sleep(1000);
        }
        System.out.println(t-1000+"ms");
    }
}
