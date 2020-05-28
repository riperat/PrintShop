package com.company;

public class PrintingMachine implements Runnable {
    private Thread t;
    private String threadName;
    private int maxPapers;
    private int paperPerMinute;
    private boolean coloured;
    private Item item;
    private double money;

    public PrintingMachine(String threadName, int maxPapers, boolean coloured, Item item, double money, int paperPerMinute) {
        this.threadName = threadName;
        this.maxPapers = maxPapers;
        this.paperPerMinute = paperPerMinute;
        this.coloured = coloured;
        this.item = item;
        this.money = money;
    }

    @Override
    public void run(){
        if (this.maxPapers < this.item.getPageCount()) {
                throw new InvalidPrintException("Not Enough paper");
        }

        if (this.money < this.item.paperPrice()) {
                throw new InvalidPrintException("Not Enough money");
        }

    }

    public void start() {
        System.out.println("Starting " + threadName + " " + this.item.getTitle());
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

