package hw05;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class PhThread extends Thread{
    private String name;
    private ReentrantLock leftFork, rightFork;
    private int count = 3; //Сколько раз нужно поесть
    private Random random = new Random();
    
    public PhThread(String name, ReentrantLock leftFork, ReentrantLock rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        while (count>0) {
            if (tryEat()){
                count--;
                System.out.println(name + " думает");
                try {
                    sleep(random.nextInt(1000)+100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
            }

        }
    }

    private boolean tryEat(){
        boolean flag = false;
        
        try {
            leftFork.tryLock(random.nextInt(100), TimeUnit.MILLISECONDS);
            if (leftFork.isHeldByCurrentThread()) {
                System.out.println(name + " взял левую вилку");
            }
            rightFork.tryLock(random.nextInt(300), TimeUnit.MILLISECONDS);
            if (rightFork.isHeldByCurrentThread()) {
                System.out.println(name + " взял правую вилку");
            }
            if (leftFork.isHeldByCurrentThread() && rightFork.isHeldByCurrentThread()){         
                flag = true;
                System.out.println(name + " ест.");
                sleep(random.nextInt(500)+300);
                System.out.println(name + " поел.");
            }
        } catch (InterruptedException e) {
            System.out.println("!Ошибка у " + name);
        } finally {
            if (leftFork.isHeldByCurrentThread()){
            leftFork.unlock();
            System.out.println(name + " положил левую вилку");
            }
            if (rightFork.isHeldByCurrentThread()){
            rightFork.unlock();
            System.out.println(name + " положил правую вилку");
            }
        }
        return flag;
    }
    
    
}
