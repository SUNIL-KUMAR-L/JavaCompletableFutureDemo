public class Address {

    private String addressId;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String zipCode;
    private String city;
    private String state;
    private String country;

    public Address(
            String addressId,
            String addressLine1,
            String addressLine2,
            String addressLine3,
            String zipCode,
            String city,
            String state,
            String country) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", addressLine3='" + addressLine3 + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}