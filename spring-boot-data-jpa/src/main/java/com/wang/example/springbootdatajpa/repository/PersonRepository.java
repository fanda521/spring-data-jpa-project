package com.wang.example.springbootdatajpa.repository;

import com.wang.example.springbootdatajpa.entity.Person;
import com.wang.example.springbootdatajpa.entity.PersonVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author allen
 * @version 1.0
 * @date 2020-02-19 20:04
 */
@Repository
public interface PersonRepository extends JpaRepository<Person,Integer>, JpaSpecificationExecutor<Person> , QuerydslPredicateExecutor<Person> {
    public List<Person> findByName(String name);

    public Optional<Person> findById(Integer id);

    /**
     * %name%
     * @param name
     * @return
     */
    List<Person> findByNameLike(String name);

    /**
     * 和上面相反
     * @param name
     * @return
     */
    List<Person> findByNameNotLike(String name);

    /**
     * %name
     * @param name
     * @return
     */
    List<Person> findByNameStartingWith(String name);

    /**
     * name%
     * @param name
     * @return
     */
    List<Person> findByNameEndingWith(String name);

    /**
     * or eauals
     * @param name
     * @param age
     * @return
     */
    List<Person> getByNameIsOrAgeEquals(String name, Integer age);

    /**
     * between
     * @param start
     * @param end
     * @return
     */
    List<Person> getByAgeBetweenOrNameEquals( int start, int end,String name);


    /**
     * GreaterTanEqual >=
     * @param age
     * @return
     */
    List<Person> queryByAgeIsGreaterThanEqual(int age);

    /**
     * 时间 before
     * @param before
     * @return
     */
    List<Person> queryByBirthdayBefore(Date before);

    /**
     * In
     * @param ages
     * @return
     */
    List<Person> getByAgeIn(List<Integer> ages);


    /**
     * OrderBy
     * @param ages
     * @return
     */
    List<Person> getByAgeInOrderByIdDesc(List<Integer> ages);

    /**
     * IgnoreCase
     * @param name
     * @return
     */
    List<Person> getByNameIgnoreCase(String name);

    @Query("from Person where name=:name")
    List<Person> selectByName(@Param("name") String name);

    /**
     * 坑：
     * 不能起别名
     * 可能原因：有可能是Person 用了jpa注解,如果是普通的类或许可以按照原生的sql 取别名
     * @param name
     * @return
     */
    @Query(value = "select t_id ,t_name  ,t_age ,t_address ,t_birthday  from t_person t where t_name=:name",nativeQuery = true)
    List<Person> selectByNameNative(@Param("name") String name);

    /**
     * 取别名 和 * 的会报错
     * at org.springframework.core.convert.support.GenericConversionService.handleConverterNotFound
     * 这个是接收的对象不是表对应的实体没有相关的注解就无法转换
     * @param name
     * @return
     */
    @Query(value = "select *  from t_person t where t_name=:name",nativeQuery = true)
    List<PersonVo> selectByNameNative2(@Param("name") String name);



    @Query("from Person where name=?1 or age =?2")
    List<Person> selectByNameOrAge(String name,Integer age);


    @Query(value = "select t_id as id,t_name as name ,t_age as age,t_address as address,t_birthday as birthday from t_person where t_name=?1 or age =?2",nativeQuery = true)
    List<Person> selectByNameOrAgeNative(String name,Integer age);

    /**
     * 修改类型的必须加 注解：@Modifying
     * @param person
     * @return
     */
    @Modifying
    @Transactional
    @Query("update Person set name =:#{#p.name} where id =:#{#p.id}")
    Integer updatePerson(@Param("p") Person person);


    @Query(nativeQuery = true,
    value = "select t.* from t_person t")
    List<Person> selectAllPersons();

    @Query(nativeQuery = true,
            value = "select t.t_birthday,t.t_name,t.t_address from t_person t")
    List<Person> selectAllPersons2();

}
