package pl.fis.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fis.restapi.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
