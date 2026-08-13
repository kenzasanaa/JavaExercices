//Write a Java program to create a class called "Person" with a name and age attribute. Create two instances of the "Person" class, set their attributes using the constructor, and print their name and age.

class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public String getName() {
    return this.name;
    }
    public int getAge(){
        return this.age;
    }
            
}
public class Main {
    public static void main(String[] args) {
       
        Person person1 = new Person("kenza", 22);
        Person person2 = new Person("lina", 18);
        System.out.println(person1.getName());
        System.out.println(person1.getAge());
        System.out.println(person2.getName());
        System.out.println(person2.getAge());      
    }
}