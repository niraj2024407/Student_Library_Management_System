
package com.example.student_library_management_system.Repository;

import com.example.student_library_management_system.model.Book;
import com.example.student_library_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
