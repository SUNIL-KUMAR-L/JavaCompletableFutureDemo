public class PersonWithAddress {
    private Person person;
    private Address address;

    public PersonWithAddress(Person person, Address address) {
        this.person = person;
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonWithAddress{" +
                "person=" + person +
                ", address=" + address +
                '}';
    }
}
