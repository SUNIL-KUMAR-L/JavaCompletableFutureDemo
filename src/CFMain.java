import java.util.concurrent.CompletableFuture;

public class CFMain {

    static PersonRepository personRepository =
            new PersonRepository();
    static PersonService personService =
            new PersonService(personRepository);

    static AddressRepository addressRepository =
            new AddressRepository();
    static AddressService addressService =
            new AddressService(addressRepository);

    public static void sequencial(String personId) {
        final Person personById =
                personService.getPersonById(personId);
        final Address addressById =
                addressService.getAddressById(personId);
        final PersonWithAddress personWithAddress =
                new PersonWithAddress(personById, addressById);
        System.out.println(personWithAddress);
    }

    public static void parallelServiceCallWithCFUsingGet(String personId) {
        final CompletableFuture<Person> personCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        personService.getPersonById(personId));
        final CompletableFuture<Address> addressCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        addressService.getAddressById(personId));
        try {
            final Person person =
                    personCompletableFuture.get(); // until this is not complete below line is not executed
            final Address address =
                    addressCompletableFuture.get(); // until this is not complete below line is not executed
            // below line will be executed based on the slowest service response
            System.out.println(new PersonWithAddress(person, address)); // time taken to return is based on the slowest service response time
        } catch (Exception exp) {
            System.err.println(exp);
        }
    }

    public static void parallelServiceCallWithCFUsingJoin(String personId) {
        final CompletableFuture<Person> personCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        personService.getPersonById(personId));
        final CompletableFuture<Address> addressCompletableFuture =
                CompletableFuture.supplyAsync(() -> addressService.getAddressById(personId));
        final Person person =
                personCompletableFuture.join(); // until this is not complete below line is not executed
        final Address address =
                addressCompletableFuture.join(); // until this is not complete below line is not executed
        // below line will be executed based on the slowest service response
        System.out.println(new PersonWithAddress(person, address)); // time taken to return is based on the slowest service response time

    }

    public static void parallelServiceCallUseAllToMergeFuturesDataUseAllOfAndGet(String personId) {
        final CompletableFuture<Person> personCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        personService.getPersonById(personId));
        final CompletableFuture<Address> addressCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        addressService.getAddressById(personId));

        final CompletableFuture<Void> completableFutureAllOf =
                CompletableFuture.allOf(personCompletableFuture, addressCompletableFuture);
        try {
            completableFutureAllOf.get(); //  time taken to return is based on the slowest service response/ slowest future response
            final Person person =
                    personCompletableFuture.get();
            final Address address =
                    addressCompletableFuture.get();
            System.out.println(new PersonWithAddress(person, address)); // time taken to return is based on the slowest service return time
        } catch (Exception exp) {
            System.err.println(exp);
        }
    }

    public static void parallelServiceCallUseAllToMergeFuturesDataUseAllOfAndJoin(String personId) {
        final CompletableFuture<Person> personCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        personService.getPersonById(personId));
        final CompletableFuture<Address> addressCompletableFuture =
                CompletableFuture.supplyAsync(() -> addressService.getAddressById(personId));
        final CompletableFuture<Void> completableFutureAllOf =
                CompletableFuture.allOf(
                        personCompletableFuture,
                        addressCompletableFuture);
        completableFutureAllOf.join(); //  time taken to return is based on the slowest future call
        final Person person =
                personCompletableFuture.join();
        final Address address =
                addressCompletableFuture.join();
        System.out.println(new PersonWithAddress(person, address)); // time taken to return is based on the slowest service response time
    }

    public static void parallelServiceCallUseAllToMergeFuturesDataUseAllAndThenApply(String personId) {
        final CompletableFuture<Person> personCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        personService.getPersonById(personId));
        final CompletableFuture<Address> addressCompletableFuture =
                CompletableFuture.supplyAsync(() ->
                        addressService.getAddressById(personId));
        final CompletableFuture<Void> completableFutureAllOf =
                CompletableFuture.allOf(personCompletableFuture, addressCompletableFuture);
        final CompletableFuture<PersonWithAddress> personWithAddressCompletableFuture =
                completableFutureAllOf.thenApply(
                        (voidInput) ->
                                new PersonWithAddress(
                                        personCompletableFuture.join(),
                                        addressCompletableFuture.join()));
        System.out.println(personWithAddressCompletableFuture.join()); // time taken to return is based on the slowest service response time
    }

    public static void main(String[] args) {
        long start = -1l;

        System.out.println("===== Approach 1 : Plain old simple java way =====");
        start = System.currentTimeMillis();
        sequencial("1");
        System.out.println("====== Approach 1 : took "+ totalTime(start) + " milliseconds ======");

        System.out.println("===== Approach 2 : Parallel service calls " +
                    "and collect data using CompletableFuture::get() =====");
        start = System.currentTimeMillis();
        parallelServiceCallWithCFUsingGet("1");
        System.out.println("====== Approach 2 : took "+ totalTime(start) + " milliseconds ======");

        System.out.println("===== Approach 3 : Parallel service calls " +
                    "and collect data using CompletableFuture::join() =====");
        start = System.currentTimeMillis();
        parallelServiceCallWithCFUsingJoin("1");
        System.out.println("====== Approach 3 : took "+ totalTime(start) + " milliseconds ======");

        System.out.println("===== Approach 4 (get()) : Parallel service calls and group futures " +
                    "using CompletableFuture::allOf() and then collect each data =====");
        start = System.currentTimeMillis();
        parallelServiceCallUseAllToMergeFuturesDataUseAllOfAndGet("1");
        System.out.println("====== Approach 4 (get()) : took "+ totalTime(start) + " milliseconds ======");

        System.out.println("===== Approach 4 (join()) : Parallel service calls and group futures "+
                    "using CompletableFuture::allOf() and then collect each data =====");
        start = System.currentTimeMillis();
        parallelServiceCallUseAllToMergeFuturesDataUseAllOfAndJoin("1");
        System.out.println("====== Approach 4 (get()) : took "+ totalTime(start) + " milliseconds ======");

        System.out.println("===== Approach 5 (My Preference) : Parallel service calls and group futures " +
                "using CompletableFuture::allOf() and then assemble using CompletableFuture::thenApply() =====");
        start = System.currentTimeMillis();
        parallelServiceCallUseAllToMergeFuturesDataUseAllAndThenApply("1");
        System.out.println("====== Approach 5  : took "+ totalTime(start) + " milliseconds ======");
    }

    public static long totalTime(long startTime) {
        return System.currentTimeMillis() - startTime;
    }
}
