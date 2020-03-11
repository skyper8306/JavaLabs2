package NumberFormat;

//Запишите в переменную типа byte числовой литерал в бинарном формате (все единички)
// и выведите на экран в форматах bin (11111111), hex (FF) и dec.

public class MainNumberFormat {
    public static void main(String args[]) {
        byte byteMax = 0b1111111;//Byte.MAX_VALUE;
        System.out.println("bin=" + Integer.toBinaryString(byteMax));
        System.out.println("hex=" + Integer.toHexString(byteMax));
        System.out.println("dec=" + byteMax);
    }
}
