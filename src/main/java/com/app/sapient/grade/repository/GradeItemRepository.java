package com.app.sapient.grade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.sapient.grade.model.GradeItem;

@Repository
public interface GradeItemRepository extends JpaRepository<GradeItem, Long> {

}
