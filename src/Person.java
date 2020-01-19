public class Person {

    public Person(String id, String firstName, String age) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
    }

    private String id;
    private String firstName;
    private String age;

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
