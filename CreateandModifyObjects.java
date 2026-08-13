//Write a  Java program to create a class called " Dog" with a name and breed attribute. Create two instances of the "Dog" class, set their attributes using the constructor and modify the attributes using the setter methods and print the updated values.

class Dog{
    private String name;
    private String breed;
    public Dog (String name, String breed){
        this.name = name;
        this.breed = breed;
    }
    public String getName() {
        return this.name;
    }
    public String getBreed(){
        return this.breed;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setBreed(String breed){
        this.breed = breed;
    }
            
}
public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog("skyla","malinois");
        Dog dog2 = new Dog("patou","golden retriever");
        System.out.println(dog1.getName() + " is a " + dog1.getBreed() );
        System.out.println(dog2.getName() + " is a " + dog2.getBreed() );
        dog1.setName("lina");
        dog2.setName("gucci");  
        System.out.println("the new name of skyla is " + dog1.getName() );
        System.out.println("the new name of patou is " + dog2.getName());
    }
}