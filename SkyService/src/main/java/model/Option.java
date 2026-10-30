package model;

public class Option {
    private final String name;
    private final double price;

    public Option(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

