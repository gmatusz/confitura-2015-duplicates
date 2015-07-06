package pl.gmatusz.confitura2015.viacom.model;

/**
 * Created by gmatusz on 2015-07-06.
 */
public class Person {

    protected String name;
    protected int age;

    public static final int AGE_TO_NAME_HASH_COEFF = 131;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        return !(name != null ? !name.equals(person.name) : person.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = AGE_TO_NAME_HASH_COEFF * result + age;
        return result;
    }
}
