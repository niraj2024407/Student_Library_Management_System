
package com.example.student_library_management_system.service;

import com.example.student_library_management_system.Repository.StudentRepository;
import com.example.student_library_management_system.converters.StudentConverter;
import com.example.student_library_management_system.enums.CardStatus;
import com.example.student_library_management_system.model.Card;
import com.example.student_library_management_system.model.Student;
import com.example.student_library_management_system.requestdto.StudentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String addStudent(StudentRequestDto studentRequestDto){

        Student student = StudentConverter.convertStudentRequestDtoIntoStudent(studentRequestDto);

        // whenever student is created the card is also created for that student
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVE);
        card.setExpiryDate(LocalDate.now().plusYears(4).toString());
        card.setStudent(student);

        student.setCard(card);

        studentRepository.save(student);
        return "Student saved successfully!";
    }
//
//    public Student findStudentById(int id){
//        Optional<Student> studentOptional = studentRepository.findById(id);
//        if(studentOptional.isPresent()){
//            return studentOptional.get();
//        } else{
//            return null;
//        }
//    }

    public Student findStudentById(int id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(studentOptional.isPresent()){
            return studentOptional.get();
        } else{
            throw new RuntimeException("student not found");
        }
    }

    public List<Student> findAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }


    /*
    Pagination - fetching or getting the records or data in the form of pages
    pagenumber - the number of page we want to see(0,1,2,3,4,5...)
    pagesize - total number of records in each page(fixed for each page)

    total number of records -28 , page size -5
    0th page - 1-5
    1st page - 6-10
    2nd page - 11-15
    3rd page - 16-20
    4th page - 21-25
    5th page - 26-28
    6th page - 0 /empty

    total numbers of records-11, page size-3
    0th page - 1-3
    1st page - 4-6
    2nd page - 7-9
    3rd page - 10-11

    sorting - arranging the records based on asecnding or descending order
    // only pagination

    public List<Student> getAllStudentsUsingPage(int pageNo, int pageSize){
        List<Student> studentList = studentRepository.findAll(PageRequest.of(pageNo,pageSize)).getContent();
        return studentList;
    }
  */

    public List<Student> getAllStudentsUsingPage(int pageNo, int pageSize){
        List<Student> studentList = studentRepository.findAll(PageRequest.of(pageNo,pageSize, Sort.by("name").ascending())).getContent();
        return studentList;
    }


    public String updateStudent(int id, StudentRequestDto newStudentRequestDto){
        Student existingStudent = findStudentById(id);
        if(existingStudent!=null){
            existingStudent.setName(newStudentRequestDto.getName());
            existingStudent.setMobile(newStudentRequestDto.getMobile());
            existingStudent.setDob(newStudentRequestDto.getDob());
            existingStudent.setSem(newStudentRequestDto.getSem());
            existingStudent.setDept(newStudentRequestDto.getDept());
            existingStudent.setGender(newStudentRequestDto.getGender());
            existingStudent.setAddress(newStudentRequestDto.getAddress());
            existingStudent.setEmail(newStudentRequestDto.getEmail());

            studentRepository.save(existingStudent);
            return "Student updated successfully!";
        } else{
            return "Cannot updated as student is not present!";
        }
    }

    public String countStudents(){
        long totalCount = studentRepository.count();
        return "Total students present are : "+totalCount;
    }

    public String deleteStudentById(int id){
        studentRepository.deleteById(id);
        return "Student with id :"+id+"deleted successfully";
    }

    public Student findStudentByEmail(String email){
        Student student = studentRepository.getStudentByEmail(email);
        return student;
    }

    public List<Student> findStudentByDept(String dept){
        List<Student> studentList = studentRepository.getStudentByDept(dept);
        return studentList;
    }

}
