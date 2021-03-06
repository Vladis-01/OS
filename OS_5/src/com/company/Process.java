package com.company;
import javax.swing.*;

public class Process {
    int number;
    int time = 0;
    int maxTime;
    boolean interrupt;
    int workTime = 1000;
    boolean end = false;

    Process(int number, int maxTime, boolean interrupt){
        this.number = number;
        this.maxTime = maxTime;
        this.interrupt = interrupt;
    }

    public int getNumber() {
        return number;
    }

    public int getTime() {
        return time;
    }

    public void addTime(int time){
        this.time += time;
    }

    public void setInterrupt(boolean interrupt) {
        this.interrupt = interrupt;
    }

    public int start(){
        if (!end) {
            System.out.print("Процесс " + number + " начинает выполнение" + '\n');
            time = 0;

            time += workTime;
            if (maxTime < time) {
                System.out.print("Процессу " + number + " не хватило кванта времени" + '\n');
                return 1;
            }
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            end = true;
        }

        if(interrupt){
            System.out.print("Процесс " + number + " заблокирован" + '\n');
            interrupt = false;
            return 3;
        }

        if(end){
            time += workTime;
            if(maxTime > time){
                System.out.print("Процессу " + number + " не хватило кванта времени" + '\n');
                return 1;
            }


            try {
                Thread.sleep(workTime);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.print("Процесс " + number + " выполнен" + '\n');
        }
        return 2;

    }
}