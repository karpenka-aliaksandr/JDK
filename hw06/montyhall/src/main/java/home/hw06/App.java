package home.hw06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Random rnd = new Random();
        final int COUNT = 100000; // количество попыток розыгрыша
        Map<Integer, Boolean> resultChange = new HashMap<Integer, Boolean>();
        Map<Integer, Boolean> resultNoChange = new HashMap<Integer, Boolean>();
        Set<Integer> doors, doorsMaster, doorsUser;
        for (int i = 0; i < COUNT; i++) {
            doors = new HashSet<Integer>(Arrays.asList(0, 1, 2));
            int win = rnd.nextInt(3); // выйгрышная дверь
            int userNoChange = rnd.nextInt(3); // дверь, избранная пользователем изначально.
            doorsMaster = new HashSet<Integer>(doors);
            doorsMaster.remove(win);
            doorsMaster.remove(userNoChange);

            int master = (int) doorsMaster.toArray()[rnd.nextInt(doorsMaster.size())]; // дверь, которую открывает
                                                                                       // ведущий
            doorsUser = new HashSet<Integer>(doors);
            doorsUser.remove(master);
            doorsUser.remove(userNoChange);
            int userChange = (int) doorsUser.toArray()[rnd.nextInt(doorsUser.size())]; // дверь, на которую сменил выбор
                                                                                       // пользователь.
            resultChange.put(i, win == userChange);
            resultNoChange.put(i, win == userNoChange);
        }
        System.out.printf("Вероятность выйгрыша если поменять дверь: %.5f\n", getProbability(resultChange));
        System.out.printf("Вероятность выйгрыша если не менять дверь: %.5f\n", getProbability(resultNoChange));
    }

 
    private static double getProbability(Map<Integer, Boolean> map){
        int count = 0;
        int all = 0;
        for (Boolean elem : map.values()) {
            if (elem) count++;
            all++;
        }
        return (double)count / all;
    }

}
