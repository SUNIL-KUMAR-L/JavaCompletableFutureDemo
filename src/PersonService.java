public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonById(String id) {
        try {
            // Sleep for few milliseconds to simulate service call
            Thread.sleep(300l);
        } catch (Exception exp){
            exp.printStackTrace();
        }
        return personRepository.getPersonById(id);
    }
}
