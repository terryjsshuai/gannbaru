package design.pattern.compositeentity;

public class CompositeEntityPatternDemo {
    public static void main(String[] args) {
        Client client = new Client();
        client.setData("concurrent.test1.Test", "Data");
        client.printData();
        client.setData("Second concurrent.test1.Test", "Data1");
        client.printData();
    }
}
