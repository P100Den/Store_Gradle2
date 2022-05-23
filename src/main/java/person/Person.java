package person;

public class Person {
    private String id;
    private String fio;
    private int age;

    public Person(String id, String fio, int age) {
        this.id = id;
        this.fio = fio;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
