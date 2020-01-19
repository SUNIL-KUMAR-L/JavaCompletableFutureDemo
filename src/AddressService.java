public class AddressService {
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressById(String id) {
        try {
            // Sleep for few milliseconds to simulate service call
            Thread.sleep(500l);
        } catch (Exception exp){
            exp.printStackTrace();
        }
        return addressRepository.getAddressById(id);
    }
}
