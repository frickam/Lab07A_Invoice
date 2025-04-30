public class CustomerAddress {
    private String name;
    private String address;

    public CustomerAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + "\n" + address;
    }
}