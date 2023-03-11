package com.wang.example.springbootdatajpa.test.extents_strategy.table_pre_class;

import com.wang.example.springbootdatajpa.entity.extents_strategy.table_pre_class.Car;
import com.wang.example.springbootdatajpa.entity.extents_strategy.table_pre_class.Vehicle;
import com.wang.example.springbootdatajpa.repository.extents_strategy.table_pre_class.CarRepository;
import com.wang.example.springbootdatajpa.repository.extents_strategy.table_pre_class.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2022/12/29
 * @time 10:01
 * @week 星期四
 * @description test
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class PreClassTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void insertVehicle() {

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setSpeed(23);
        Vehicle save = vehicleRepository.save(vehicle);
        System.out.println(save);
        /**
         * Hibernate:
         * select vehicle0_.id as id1_20_0_,
         * ehicle0_.speed as speed2_20_0_,
         * vehicle0_.engine as engine1_2_0_,
         * vehicle0_.clazz_ as clazz_0_
         * from
         * ( select id, speed, null as engine, 0 as clazz_ from t_vehicle
         * union all select id, speed, engine, 1 as clazz_ from t_car
         * ) vehicle0_ where vehicle0_.id=?
         * Hibernate: insert into t_vehicle (speed, id) values (?, ?)
         * Vehicle(id=1, speed=23)
         */

    }

    @Test
    public void inserCar() {
        Car car = new Car();
        car.setId(3);
        car.setSpeed(24);
        car.setEngine("engine1");
        Car save = carRepository.save(car);
        System.out.println(save);
        /**
         * Hibernate:
         * select
         * car0_.id as id1_20_0_,
         * car0_.speed as speed2_20_0_,
         * car0_.engine as engine1_2_0_
         * from t_car car0_ where car0_.id=?
         * Hibernate: insert into t_car (speed, engine, id) values (?, ?, ?)
         * Vehicle(id=3, speed=24)Car{engine='engine1'}
         */
    }

    @Test
    public void selectVehicle() {
        Optional<Vehicle> byId =
                vehicleRepository.findById(1);
        System.out.println(byId);

        List<Vehicle> all = vehicleRepository.findAll();
        System.out.println(all);
        /**
         * Hibernate:
         * select
         * vehicle0_.id as id1_20_0_,
         * vehicle0_.speed as speed2_20_0_,
         * vehicle0_.engine as engine1_2_0_,
         * vehicle0_.clazz_ as clazz_0_
         * from
         * (
         * select id, speed, null as engine, 0 as clazz_ from t_vehicle
         * union all select id, speed, engine, 1 as clazz_ from t_car
         * )
         * vehicle0_ where vehicle0_.id=?
         *
         * Optional[Vehicle(id=1, speed=23)]
         *
         * Hibernate:
         * select vehicle0_.id as id1_20_,
         * vehicle0_.speed as speed2_20_,
         * vehicle0_.engine as engine1_2_,
         * vehicle0_.clazz_ as clazz_
         * from
         * (
         * select id, speed, null as engine, 0 as clazz_ from t_vehicle
         * union all select id, speed, engine, 1 as clazz_ from t_car
         * ) vehicle0_
         * [Vehicle(id=1, speed=23), Vehicle(id=2, speed=24)Car{engine='engine1'}, Vehicle(id=3, speed=24)Car{engine='engine1'}]
         *
         * 结论：
         * 父表会关联字表查询
         */
    }

    @Test
    public void selectCar() {
        Optional<Car> byId = carRepository.findById(2);
        System.out.println(byId);

        List<Car> all = carRepository.findAll();
        System.out.println(all);
        /**
         * Hibernate:
         * select car0_.id as id1_20_0_,
         * car0_.speed as speed2_20_0_,
         * car0_.engine as engine1_2_0_
         * from t_car car0_ where car0_.id=?
         *
         * Optional[Vehicle(id=2, speed=24)Car{engine='engine1'}]
         *
         * Hibernate:
         * select car0_.id as id1_20_,
         * car0_.speed as speed2_20_,
         * car0_.engine as engine1_2_
         * from t_car car0_
         *
         * [Vehicle(id=2, speed=24)Car{engine='engine1'}, Vehicle(id=3, speed=24)Car{engine='engine1'}]
         *
         * 总结：
         * 字表只会查自己的表
         */
    }

}
