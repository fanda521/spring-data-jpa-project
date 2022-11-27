package com.wang.example.springbootdatajpa.test.extents_strategy.joined;

import com.wang.example.springbootdatajpa.entity.extents_strategy.joined.Animal;
import com.wang.example.springbootdatajpa.entity.extents_strategy.joined.Bird;
import com.wang.example.springbootdatajpa.entity.extents_strategy.joined.Dog;
import com.wang.example.springbootdatajpa.repository.extents_strategy.joined.AnimalRepository;
import com.wang.example.springbootdatajpa.repository.extents_strategy.joined.BirdRepository;
import com.wang.example.springbootdatajpa.repository.extents_strategy.joined.DogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @Aythor allen
 * @date 2022/11/27 17:39
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JoinTest {
    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private BirdRepository birdRepository;

    @Autowired
    private DogRepository dogRepository;

    @Test
    public void saveTest() {
        Animal animal = new Animal();
        animal.setColor("red");
        animal.setName("animal");
        System.out.println(animal);
        System.out.println("-------------------");
        Animal save = animalRepository.save(animal);
        System.out.println(save);
        System.out.println("-------------------");

        Bird bird = new Bird();
        bird.setColor("yellow");
        bird.setName("bird");
        bird.setSpeed("speed");
        System.out.println(bird);
        System.out.println("-------------------");
        Bird save1 = birdRepository.save(bird);
        System.out.println(save1);
        System.out.println("-------------------");

        Dog dog = new Dog();
        dog.setLegs(1);
        dog.setColor("black");
        dog.setName("dog");
        System.out.println(dog);
        System.out.println("--------------------");
        Dog save2 = dogRepository.save(dog);
        System.out.println(save2);

    }

    @Test
    public void selectTest01() {
        List<Animal> all = animalRepository.findAll();
        System.out.println(all);
        System.out.println("----------------------------");
        List<Bird> all1 = birdRepository.findAll();
        System.out.println(all1);
        System.out.println("----------------------------");
        List<Dog> all2 = dogRepository.findAll();
        System.out.println(all2);
        System.out.println("----------------------------");

        /**
         * Hibernate: select animal0_.id as id2_0_, animal0_.color as color3_0_, animal0_.name as name4_0_, animal0_1_.speed as speed1_1_, animal0_2_.legs as legs1_4_, animal0_.aaa as aaa1_0_ from t_animal animal0_ left outer join t_bird animal0_1_ on animal0_.id=animal0_1_.id left outer join t_dog animal0_2_ on animal0_.id=animal0_2_.id
         * [Animal(id=19, name=bird, color=yellow)Bird{speed='speed'}, Animal(id=20, name=dog, color=black)Dog{legs=1}, Animal(id=18, name=animal, color=red)]
         * ----------------------------
         * Hibernate: select bird0_.id as id2_0_, bird0_1_.color as color3_0_, bird0_1_.name as name4_0_, bird0_.speed as speed1_1_ from t_bird bird0_ inner join t_animal bird0_1_ on bird0_.id=bird0_1_.id
         * [Animal(id=19, name=bird, color=yellow)Bird{speed='speed'}]
         * ----------------------------
         * Hibernate: select dog0_.id as id2_0_, dog0_1_.color as color3_0_, dog0_1_.name as name4_0_, dog0_.legs as legs1_4_ from t_dog dog0_ inner join t_animal dog0_1_ on dog0_.id=dog0_1_.id
         * [Animal(id=20, name=dog, color=black)Dog{legs=1}]
         * ----------------------------
         */

        /**
         * 结论
         * 跟第一种差不多
         * 都是父表的记录都有
         * 字表的只有自己的，不同的是第一种是自动会根据自己的辨别字段作为where条件，
         * 第二种是主表是子表，连接的是主表，自然只有子表的记录了
         */
    }

    @Test
    public void findById() {
        Optional<Animal> byId = animalRepository.findById(18);
        System.out.println(byId);
        System.out.println("---------------------------");
        Optional<Bird> byId1 = birdRepository.findById(19);
        System.out.println(byId1);
        System.out.println("---------------------------");
        Optional<Dog> byId2 = dogRepository.findById(20);
        System.out.println(byId2);

        /**
         * Hibernate: select animal0_.id as id2_0_0_, animal0_.color as color3_0_0_, animal0_.name as name4_0_0_, animal0_1_.speed as speed1_1_0_, animal0_2_.legs as legs1_4_0_, animal0_.aaa as aaa1_0_0_ from t_animal animal0_ left outer join t_bird animal0_1_ on animal0_.id=animal0_1_.id left outer join t_dog animal0_2_ on animal0_.id=animal0_2_.id where animal0_.id=?
         * Optional[Animal(id=18, name=animal, color=red)]
         * ---------------------------
         * Hibernate: select bird0_.id as id2_0_0_, bird0_1_.color as color3_0_0_, bird0_1_.name as name4_0_0_, bird0_.speed as speed1_1_0_ from t_bird bird0_ inner join t_animal bird0_1_ on bird0_.id=bird0_1_.id where bird0_.id=?
         * Optional[Animal(id=19, name=bird, color=yellow)Bird{speed='speed'}]
         * ---------------------------
         * Hibernate: select dog0_.id as id2_0_0_, dog0_1_.color as color3_0_0_, dog0_1_.name as name4_0_0_, dog0_.legs as legs1_4_0_ from t_dog dog0_ inner join t_animal dog0_1_ on dog0_.id=dog0_1_.id where dog0_.id=?
         * Optional[Animal(id=20, name=dog, color=black)Dog{legs=1}]
         */

        /**
         * 结论
         * 父表 自己是主表 左外连接子表
         * 子表 自己是主表 内连接父表
         */


    }

    @Test
    public void selectAnimalTest() {
        List<Animal> animal = animalRepository.findByName("animal");
        System.out.println(animal);
        System.out.println("-----------------------");
        List<Animal> animal1 = animalRepository.getAnimalName("animal");
        System.out.println(animal1);

        /**
         * Hibernate: select animal0_.id as id2_0_, animal0_.color as color3_0_, animal0_.name as name4_0_, animal0_1_.speed as speed1_1_, animal0_2_.legs as legs1_4_, animal0_.aaa as aaa1_0_ from t_animal animal0_ left outer join t_bird animal0_1_ on animal0_.id=animal0_1_.id left outer join t_dog animal0_2_ on animal0_.id=animal0_2_.id where animal0_.name=?
         * [Animal(id=18, name=animal, color=red)]
         * -----------------------
         * Hibernate: select animal0_.id as id2_0_, animal0_.color as color3_0_, animal0_.name as name4_0_, animal0_1_.speed as speed1_1_, animal0_2_.legs as legs1_4_, animal0_.aaa as aaa1_0_ from t_animal animal0_ left outer join t_bird animal0_1_ on animal0_.id=animal0_1_.id left outer join t_dog animal0_2_ on animal0_.id=animal0_2_.id where animal0_.name=?
         * [Animal(id=18, name=animal, color=red)]
         */
    }

    @Test
    public void selectBirdTest() {
        List<Bird> dog = birdRepository.findByName("bird");
        System.out.println(dog);
        System.out.println("------------------------");
        List<Bird> dog1 = birdRepository.getBirdName("bird");
        System.out.println(dog1);

        /**
         * Hibernate: select bird0_.id as id2_0_, bird0_1_.color as color3_0_, bird0_1_.name as name4_0_, bird0_.speed as speed1_1_ from t_bird bird0_ inner join t_animal bird0_1_ on bird0_.id=bird0_1_.id where bird0_1_.name=?
         * [Animal(id=19, name=bird, color=yellow)Bird{speed='speed'}]
         * ------------------------
         * Hibernate: select bird0_.id as id2_0_, bird0_1_.color as color3_0_, bird0_1_.name as name4_0_, bird0_.speed as speed1_1_ from t_bird bird0_ inner join t_animal bird0_1_ on bird0_.id=bird0_1_.id where bird0_1_.name=?
         * [Animal(id=19, name=bird, color=yellow)Bird{speed='speed'}]
         */
    }

    @Test
    public void selectDogTest() {
        List<Dog> bird = dogRepository.findByName("dog");
        System.out.println(bird);
        System.out.println("---------------------------");
        List<Dog> bird1 = dogRepository.getDogName("dog");
        System.out.println(bird1);

        /**
         * Hibernate: select dog0_.id as id2_0_, dog0_1_.color as color3_0_, dog0_1_.name as name4_0_, dog0_.legs as legs1_4_ from t_dog dog0_ inner join t_animal dog0_1_ on dog0_.id=dog0_1_.id where dog0_1_.name=?
         * [Animal(id=20, name=dog, color=black)Dog{legs=1}]
         * ---------------------------
         * Hibernate: select dog0_.id as id2_0_, dog0_1_.color as color3_0_, dog0_1_.name as name4_0_, dog0_.legs as legs1_4_ from t_dog dog0_ inner join t_animal dog0_1_ on dog0_.id=dog0_1_.id where dog0_1_.name=?
         * [Animal(id=20, name=dog, color=black)Dog{legs=1}]
         */
    }

}
