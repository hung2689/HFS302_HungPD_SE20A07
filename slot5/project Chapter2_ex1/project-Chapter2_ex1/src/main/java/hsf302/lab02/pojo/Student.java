package hsf302.lab02.pojo;

public class Student {

    // TODO 2.1: Field name
    private String name;

    // TODO 2.2: Field age
    private int age;

    // TODO 2.3: Constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // TODO 2.4: Getter getName()
    public String getName() {
        return name;
    }

    // TODO 2.5: Getter getAge()
    public int getAge() {
        return age;
    }

    // TODO 2.6: Setter setName()
    public void setName(String name) {
        this.name = name;
    }

    // TODO 2.7: Setter setAge()
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + "}";
    }
}
