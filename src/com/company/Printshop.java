package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Printshop {
    static int fileNumber = 0;
    private List<Employee> operatorList;
    private List<Employee> managerList;
    private List<Item> itemList;
    private final double expectedProfit = 500;
    private final double bonusPercent = 30;
    private double money;
    private double profit;
    private double expenses = 0;
    private FileWriter fileWriter;


    public Printshop() throws IOException {
        this.operatorList = new ArrayList<>();
        this.managerList = new ArrayList<>();
        this.itemList = new ArrayList<>();
        this.money = 1000;
        fileNumber++;

        File file = new File("Report" + fileNumber + ".txt");
        file.createNewFile();
        this.fileWriter = new FileWriter(file);
    }

    //done
    public int addEmployee(Employee employee) {
        String[] split = employee.getClass().getTypeName().split("\\.");
        if (split[2].equals("Operator")) {
            this.operatorList.add(employee);
        } else {
            this.managerList.add(employee);
        }

        return 1;
    }

    public void startPrintingMachines() throws IOException {
        this.fileWriter.write("Printed Items:\n");
        for (int i = 0; i < this.itemList.size() - 1; i++) {
            Item curItem = this.itemList.get(i);
            this.fileWriter.write("Item " + i + ": " + curItem.getTitle() + "\n");
            try {
                PrintingMachine m1 = new PrintingMachine("Machine-1", 600, true, curItem, this.money, 5);
                m1.start();
                this.money -= curItem.paperPrice();
                this.expenses += curItem.paperPrice();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.calculateProfit();
    }

    //print machine
    public int addItem(Item item) {
        this.itemList.add(item);
        return 1;
    }

    private void calculateProfit() throws IOException {
        this.profit = 0;
        for (Item s :
                itemList) {
            this.profit += s.typeProfit() * s.paperPrice();
        }
        this.fileWriter.write("\n");
        this.fileWriter.write("Profit " + this.profit + "\n");

        if (this.profit < this.expectedProfit) {
            this.fileWriter.write("\n");
            this.fileWriter.write("Paid manager bonuses \n");
            this.expenses += managerBonus();
            this.profit -= managerBonus();
        }

        this.money += this.profit;

        paySalaries();
        this.fileWriter.write("Expenses " + this.expenses + "\n");
        this.fileWriter.write("Current money " + this.money + "\n");
        this.fileWriter.close();
    }

    private void paySalaries() throws IOException {
        for (Employee empl :
                this.managerList) {
            this.expenses += empl.getSalary();
            this.money -= empl.getSalary();
        }
        for (Employee empl :
                this.operatorList) {
            this.expenses += empl.getSalary();
            this.money -= empl.getSalary();
        }
    }

    private double managerBonus() {
        double bonus = (this.profit / managerList.size()) / managerList.size();

        return bonus * managerList.size();
    }

}
