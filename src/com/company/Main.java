package com.company;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Employee ent = new Operator(20);
        Employee man = new Manager(200);
        Printshop shop = new Printshop();

        shop.addEmployee(ent);
        shop.addEmployee(man);

        Item i = new Item(Item.type.book, "Мемета", 20, Item.pageSize.A3, Item.paperType.glossyPaper);

        for (int j = 0; j < 200; j++) {
            shop.addItem(i);
        }

        shop.startPrintingMachines();
    }
}
