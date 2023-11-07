package ru.wefunni.server;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

//        //Преобразование Java -> JSON (сериализация)
//        Gson gson = new Gson();
//        String json = gson.toJson(myObject);
//
//        //JSON -> Java (десериализация)
//        Gson gson = new Gson();
//        MyClass myObject = gson.fromJson(json, MyClass.class);
//
//
//        public class Person {
//            @SerializedName("имя") //Поле name должно иметь имя "имя" в JSON.
//            private String name;
//
//            @Expose(serialize = false, deseriablize = false) //Поле age должно быть исключено из сериализации
//            // и десериализации, если оно равно 0.
//            private int age;
//
//            @Expose(serialize = true, deserialize = true) //Поле email должно быть сериализовано
//            // только если оно установлено.
//            private String email;
//
//            public Person(String name, int age, String email) {
//                this.name = name;
//                this.age = age;
//                this.email = email;
//            }
//        }
//
//
//        //работа с коллекциями с использованием gson
//        List<Person> people = new ArrayList<>();
//        people.add(new Person("Alice", 25));
//        people.add(new Person("Bob", 30));
//        people.add(new Person("Charlie", 22));
//
//        Gson gson = new Gson();
//
//        //сериализация
//        String json = gson.toJson(people);
//        System.out.println("Сериализованный JSON:");
//        System.out.println(json);
//
//        //десериализация
//        List<Person> deserializedPeople = gson.fromJson(json, new ArrayList<Person>().getClass());

    }
}