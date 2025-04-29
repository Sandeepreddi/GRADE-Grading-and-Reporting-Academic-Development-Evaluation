package com.example.demo.controller;

import com.example.demo.model.DepartmentsData;
import com.example.demo.model.DepartmentsData.Subject;
import com.example.demo.repository.DepartmentsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentsController {

    private final DepartmentsRepository departmentsRepository;
    private final Logger logger = LoggerFactory.getLogger(DepartmentsController.class);

    public DepartmentsController(DepartmentsRepository departmentsRepository) {
        this.departmentsRepository = departmentsRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<DepartmentsData> createDepartment(@RequestBody DepartmentsData department) {
        try {
            DepartmentsData saved = departmentsRepository.save(department);
            logger.info("Created new department: {}", saved);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating department: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentsData>> getAllDepartments() {
        List<DepartmentsData> departments = departmentsRepository.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentsData> getDepartmentById(@PathVariable String id) {
        Optional<DepartmentsData> department = departmentsRepository.findById(id);
        return department.map(data -> new ResponseEntity<>(data, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable String id) {
        try {
            departmentsRepository.deleteById(id);
            logger.info("Deleted department with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting department: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Updated for 3D list model
    @PostMapping("/{departmentId}/year/{year}/semester/{semester}")
    public ResponseEntity<DepartmentsData> addSubjectToSemester(
            @PathVariable String departmentId,
            @PathVariable int year,
            @PathVariable int semester,
            @RequestBody Subject subject) {

        logger.info("Received request to add subject to Department ID: {}, Year: {}, Semester: {}", departmentId, year, semester);
        Optional<DepartmentsData> departmentOptional = departmentsRepository.findById(departmentId);

        if (departmentOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (year < 1 || year > 4 || semester < 1 || semester > 2) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        DepartmentsData department = departmentOptional.get();
        List<List<List<Subject>>> subjects = department.getSubjects();

        // Ensure the structure is initialized properly
        if (subjects == null || subjects.size() != 4) {
            logger.warn("Initializing or correcting subjects structure...");
            subjects = new java.util.ArrayList<>();
            for (int i = 0; i < 4; i++) {
                List<List<Subject>> yearList = new java.util.ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    yearList.add(new java.util.ArrayList<>());
                }
                subjects.add(yearList);
            }
            department.setSubjects(subjects);
        }

        // Add subject
        subjects.get(year - 1).get(semester - 1).add(subject);

        // Save updated department
        DepartmentsData updated = departmentsRepository.save(department);
        logger.info("Added subject '{}' to Department: {} | Year: {} | Semester: {}",
                    subject.getSubjectName(), department.getDepartment_Name(), year, semester);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}/year/{year}/semester/{semester}/subjects")
public ResponseEntity<List<Subject>> getSubjectsForSemester(
        @PathVariable String departmentId,
        @PathVariable int year,
        @PathVariable int semester) {

    logger.info("Request to fetch subjects for Department ID: {}, Year: {}, Semester: {}",
                departmentId, year, semester);

    Optional<DepartmentsData> departmentOptional = departmentsRepository.findById(departmentId);

    if (departmentOptional.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    if (year < 1 || year > 4 || semester < 1 || semester > 2) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    DepartmentsData department = departmentOptional.get();
    List<List<List<Subject>>> subjects = department.getSubjects();

    if (subjects == null || subjects.size() < year || subjects.get(year - 1).size() < semester) {
        logger.warn("No subjects found for the given year and semester");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    List<Subject> subjectList = subjects.get(year - 1).get(semester - 1);
    return new ResponseEntity<>(subjectList, HttpStatus.OK);
}

@DeleteMapping("/{departmentId}/year/{year}/semester/{semester}/subject/{subjectCode}")
public ResponseEntity<HttpStatus> deleteSubjectFromSemester(
        @PathVariable String departmentId,
        @PathVariable int year,
        @PathVariable int semester,
        @PathVariable String subjectCode) {

    logger.info("Request to delete subject '{}' from Department ID: {}, Year: {}, Semester: {}",
                subjectCode, departmentId, year, semester);

    Optional<DepartmentsData> departmentOptional = departmentsRepository.findById(departmentId);

    if (departmentOptional.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    if (year < 1 || year > 4 || semester < 1 || semester > 2) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    DepartmentsData department = departmentOptional.get();
    List<List<List<Subject>>> subjects = department.getSubjects();

    if (subjects == null || subjects.size() < year || subjects.get(year - 1).size() < semester) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    List<Subject> semesterSubjects = subjects.get(year - 1).get(semester - 1);
    boolean removed = semesterSubjects.removeIf(subject -> subject.getSubjectCode().equalsIgnoreCase(subjectCode));

    if (!removed) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    departmentsRepository.save(department);
    logger.info("Deleted subject '{}' from Department: {}, Year: {}, Semester: {}",
                subjectCode, department.getDepartment_Name(), year, semester);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}


}


