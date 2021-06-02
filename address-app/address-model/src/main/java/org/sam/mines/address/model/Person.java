package org.sam.mines.address.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
public class Person {
    private UUID id;
    private String name;
    private int weight;
    private int size;
    private String type;

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name= "uuid", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    @Column(name = "name")
    @Min(1)
    @NotNull
    public String getName() {
        return name;
    }
    public void setName(String n) {
        this.name = n;
    }


    @Column(name = "weight")
    @Min(1)
    @NotNull
    public int getWeight() {
        return weight;
    }

    public void setWeight(int w) {
        this.weight = w;
    }

    @Column(name = "size")
    @Min(1)
    @NotNull
    public int getSize() {
        return size;
    }

    public void setSize(int s) {
        this.size = s;
    }

    @Column(name = "type")
    @Min(1)
    @NotNull
    public String getType() {
        return type;
    }

    public void setType(String t) {
        this.type = t;
    }



    /*public void setName(String name) {
        this.name = name;
    }
    @OneToMany(mappedBy = "town")
    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }*/

    public Double getBMI(double height, double weight){
        double BMI = weight / (height * height);

        return BMI;
    }

    public String getCategory(double BMI){

        if (BMI <= 18.5){
            return "Thiness";
        }
        else {
            if (BMI <= 25){
                return "Normal";
            }
            else{
                if (BMI <= 30){
                    return "Overweight";
                }
                else{
                    return "Obese";
                }
            }
        }

    }

    public String getOrigin(String type){

        if (type == "black"){
            return "Africa";
        }
        else if (type == "white"){
            Random rand = new Random(); //instance of random class
            int upperbound = 12;
            //generate random values from 0-24
            int int_random = rand.nextInt(upperbound);

            if (int_random <= 5){
                return "Europe";
            } else {
                return "America";
            }
        }
        else if (type == "yellow"){
            return "Asia";
        }
        else {
            return "South America";
        }
    }

    public String getSexe(String name){

        if (name.startsWith("A")){
            return "F";
        }
        else {
            return "M";
        }
    }

    public Boolean IsPregnant(String category){

        if (category == "Thiness"){
            return false;
        } else{
            return true;
        }
    }


    public static final class PersonBuilder {
        private UUID id;
        private String name;
        private int weight;
        private int size;
        private String type;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public PersonBuilder withWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder withSize(int size) {
            this.size = size;
            return this;
        }

        public Person build() {
            Person p = new Person();
            p.setId(id);
            p.setName(name);
            p.setSize(size);
            p.setWeight(weight);
            p.setType(type);
            return p;
        }
    }
}
