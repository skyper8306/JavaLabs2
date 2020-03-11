package BigIntegers;

import java.math.BigInteger;

//Найдите среднее арифметическое чисел 9_000_000_000_000_000_000 8_444_444_444_444_444_444

public class MainBigIntegers {
    public static void main(String args[]){
        BigInteger bigInt1 = new BigInteger("9000000000000000000");
        BigInteger bigInt2 = new BigInteger("8444444444444444444");
        System.out.println(bigInt1.add(bigInt2).divide(BigInteger.TWO));
    }
}
