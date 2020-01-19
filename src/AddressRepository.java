public class AddressRepository {
    public Address getAddressById(String id) {
        // hard coded ... change it to real data // fetch from DB or API
        return new Address("1", "add1", "add2",
                "add3", "55305",
                "Mpls", "MN", "USA");
    }
}
