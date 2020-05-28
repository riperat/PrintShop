package com.company;

public class Item {
    private type TYPE;
    private String title;
    private int pageCount;
    private pageSize PageSize;
    private paperType PaperType;

    public enum pageSize {
        A1,
        A2,
        A3,
        A4,
        A5
    }

    public enum paperType {
        normalPaper,
        glossyPaper,
        newsprint,
    }

    public enum type {
        book,
        poster,
        newspaper,
    }

    public Item(type TYPE, String title, int pageCount, pageSize pageSize, paperType paperType) {
        this.TYPE = TYPE;
        this.title = title;
        this.pageCount = pageCount;
        PageSize = pageSize;
        PaperType = paperType;
    }

    public double paperPrice() {
        double price = 0;

        switch (this.PaperType) {
            case normalPaper:
                price = 0.01 * pageCount;
                break;
            case glossyPaper:
                price = 0.02 * pageCount;
                break;
            case newsprint:
                price = 0.03 * pageCount;
                break;
        }
        return price;
    }


    public double typeProfit() {
        double price = 0;

        switch (this.TYPE) {
            case book:
                price = 20;
                break;
            case poster:
                price = 0.50;
                break;
            case newspaper:
                price = 1;
                break;
        }
        return price;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getTitle() {
        return title;
    }
}
