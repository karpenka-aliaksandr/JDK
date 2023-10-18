package hw03;

public class Main {
    public static void main(String[] args) {
        System.out.println(Calc.sum(5, 3.14));
        System.out.println(Calc.multiply(5, 3.14));
        System.out.println(Calc.divide(5, 3.14));
        System.out.println(Calc.subtract(5, 3.14));
        System.out.println(Calc.divide(5, 0.0));
        System.out.println(Calc.divide(5, 0));
        
        Integer[] arr1 = new Integer[]{1,2,3};
        Integer[] arr2 = new Integer[]{1,2,3};
        Double[] arr3 = new Double[]{1.0,2.0,3.0};
        Integer[] arr4 = new Integer[]{1,2,3,4};
        Double[] arr5 = new Double[]{1.0,2.0,3.0};
        String[] arr6 = new String[]{"1","2","3","4"};

        System.out.println(Calc.compareArrays(arr1,arr2));
        //System.out.println(Calc.compareArrays(arr1,arr3)); //Разные типы
        System.out.println(Calc.compareArrays(arr1,arr4));
        System.out.println(Calc.compareArrays(arr3,arr5));
        //System.out.println(Calc.compareArrays(arr4,arr6)); //Разные типы
        System.out.println(Calc.compareArrays(arr6,arr6));
        Pair<Integer,String> p1 = new Pair<>(1,"1");
        Pair<String,String> p2 = new Pair<>("1","1");
        Pair<Pair, Pair> p3 = new Pair<>(p1, p2); // Не безопасная операция
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }
}
