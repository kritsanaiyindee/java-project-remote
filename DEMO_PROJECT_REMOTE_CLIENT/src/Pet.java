
import print_network.uploadFileForm;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HT170305
 */
public class Pet {
    private String name;
    private String animal;
    private int age;
    public Pet(String name,String animal,int age){
        this.name = name;
        this.animal = animal;
        this.age = age;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the animal
     */
    public String getAnimal() {
        return animal;
    }

    /**
     * @param animal the animal to set
     */
    public void setAnimal(String animal) {
        this.animal = animal;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    public static void main(String args[]) {
           Pet  p1 =new Pet("Mickey","rat",1);  
           Pet  p2 =new Pet("Kitty","cat",2);
           
           System.out.println("Name : "+p1.getName());
           System.out.println("Type : "+p1.getAnimal());
           System.out.println("Age : "+p1.getAge());
           System.out.println("Name : "+p2.getName());
           System.out.println("Type : "+p2.getAnimal());
           System.out.println("Age : "+p2.getAge());
    }
}
