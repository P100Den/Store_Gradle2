package person;

public class Person {
    private String firstName;
    private String password;

    public Person(String firstName,String password){
        this.firstName = firstName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + password + '\'' +
                ", fio='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
