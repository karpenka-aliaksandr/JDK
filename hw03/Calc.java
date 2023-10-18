package hw03;


public class Calc {
    public static <N extends Number, M extends Number> Number sum (N a, M b){
        return a.doubleValue() + b.doubleValue();  
    }
    public static <N extends Number, M extends Number> Number multiply(N a, M b){
        return a.doubleValue() * b.doubleValue();  
    }
    public static <N extends Number, M extends Number> Number divide (N a, M b){
        if (b.doubleValue() != 0) {
            return a.doubleValue() / b.doubleValue();
        } else {
            System.out.println("Попытка деления на 0. Результат NaN.");
            return Double.NaN;
        }
          
    }
    public static <N extends Number, M extends Number> Number subtract (N a, M b){
        return a.doubleValue() - b.doubleValue();  
    }

    public static <T extends Comparable<T>> boolean compareArrays(T[] arr1, T[] arr2){
        if (arr1.length != arr2.length) {
            return false;    
        } else {
            for (int i = 0; i < arr1.length; i++) {
                if (!arr1[i].equals(arr2[i])) {
                    return false;    
                }
            }
        }
        return true;
    }

}



 