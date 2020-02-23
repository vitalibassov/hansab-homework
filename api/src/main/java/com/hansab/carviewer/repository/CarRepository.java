package com.hansab.carviewer.repository;

import com.hansab.carviewer.model.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByUserId(Long id);

    @Query("SELECT c FROM Car c WHERE " +
            "lower(c.make) LIKE %:searchStr% OR " +
            "lower(c.model) LIKE %:searchStr% OR " +
            "lower(c.numberplate) LIKE %:searchStr%")
    List<Car> findAllByMakeOrModelOrNumberplate(@Param("searchStr") String searchStr, Sort sort);
}
