package com.example.demo.controller;

import com.example.demo.model.InternshipsData;
import com.example.demo.model.StudentPointsSummary;
import com.example.demo.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/internship")
@CrossOrigin(origins = "http://localhost:3000")
public class InternshipsController {

    @Autowired
    private InternshipRepository internshipsRepository;

    // ====== INTERNSHIP SUBMISSION ======
    // In InternshipsController.java

// Update all constructors to include type parameter
@PostMapping("/submitInternship")
public ResponseEntity<InternshipsData> submitInternship(@RequestBody Map<String, String> request) {
    try {
        InternshipsData internship = new InternshipsData(
            null,
            request.get("name"),
            request.get("studentId"),
            request.get("department"),
            request.get("type"), // Add type
            request.get("title"),
            request.get("company"),
            request.get("duration"),
            "PENDING",
            request.get("certificateUrl"),
            request.get("githuburl"),
            request.get("liveprojecturl"),
            request.get("field"),
            request.get("points") != null ? Integer.parseInt(request.get("points")) : 0
        );
        internship.setType("INTERNSHIP"); // Set type explicitly
        return new ResponseEntity<>(internshipsRepository.save(internship), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PostMapping("/submitCertificate")
public ResponseEntity<InternshipsData> submitCertificate(@RequestBody Map<String, String> request) {
    try {
        InternshipsData certificate = new InternshipsData(
            null,
            request.get("name"),
            request.get("studentId"),
            request.get("department"),
            request.get("type"), // Add type
            request.get("title"),
            request.get("company"),
            request.get("field"),
            request.get("duration"),
            "PENDING",
            request.get("certificateUrl"),
            request.get("points") != null ? Integer.parseInt(request.get("points")) : 0
        );
        certificate.setType("CERTIFICATE"); // Set type explicitly
        return new ResponseEntity<>(internshipsRepository.save(certificate), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PostMapping("/submitProject")
public ResponseEntity<InternshipsData> submitProject(@RequestBody Map<String, String> request) {
    try {
        InternshipsData project = new InternshipsData(
            null,
            request.get("name"),
            request.get("studentId"),
            request.get("department"),
            request.get("type"), // Add type
            request.get("title"),
            request.get("duration"),
            "PENDING",
            request.get("guidename"),
            request.get("abstracturl"),
            request.get("documentationurl"),
            request.get("liveprojecturl"),
            request.get("githuburl"),
            request.get("field"),
            request.get("points") != null ? Integer.parseInt(request.get("points")) : 0
        );
        project.setType("PROJECT"); // Set type explicitly
        return new ResponseEntity<>(internshipsRepository.save(project), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // ====== COMMON: GET BY STUDENT ID ======
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<InternshipsData>> getByStudentId(@PathVariable String studentId) {
        List<InternshipsData> data = internshipsRepository.findByStudentId(studentId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // ====== COMMON: GET BY DEPARTMENT ======
    @GetMapping("/department/{department}")
    public ResponseEntity<List<InternshipsData>> getByDepartment(@PathVariable String department) {
        List<InternshipsData> data = internshipsRepository.findByDepartment(department);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // ====== COMMON: GET BY ID ======
    @GetMapping("/id/{id}")
    public ResponseEntity<InternshipsData> getById(@PathVariable String id) {
        Optional<InternshipsData> data = internshipsRepository.findById(id);
        return data.map(internship ->
                new ResponseEntity<>(internship, HttpStatus.OK)
        ).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    // ====== COMMON: UPDATE STATUS ======
    @PutMapping("/updateStatus/id/{id}")
    public ResponseEntity<InternshipsData> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        Optional<InternshipsData> optionalInternship = internshipsRepository.findById(id);
        if (optionalInternship.isPresent()) {
            InternshipsData internship = optionalInternship.get();
            internship.setStatus(body.get("status"));
            internship.setVerifiedBy(body.get("verifiedBy"));
            internship.setPoints(Integer.parseInt(body.get("points").toString()));
            return new ResponseEntity<>(internshipsRepository.save(internship), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ====== COMMON: GET BY FACULTY APPROVAL ======
    @GetMapping("/approvedBy/{facultyName}")
    public ResponseEntity<List<InternshipsData>> getInternshipsApprovedByFaculty(@PathVariable String facultyName) {
        List<InternshipsData> list = internshipsRepository.findByStatusAndVerifiedBy("Approved", facultyName);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/all")
public ResponseEntity<List<InternshipsData>> getAllSubmissions() {
    List<InternshipsData> allData = internshipsRepository.findAll();
    return new ResponseEntity<>(allData, HttpStatus.OK);
}

    @GetMapping("/internship/points-summary")
public List<StudentPointsSummary> getStudentPointsSummary() {
    List<InternshipsData> all = internshipsRepository.findAll();

    Map<String, StudentPointsSummary> summaryMap = new HashMap<>();

    for (InternshipsData data : all) {
        String studentId = data.getStudentId();
        int points = data.getPoints();

        summaryMap.putIfAbsent(studentId, new StudentPointsSummary(studentId, 0, 0, 0, 0));
        StudentPointsSummary summary = summaryMap.get(studentId);

        switch (data.getType()) {
            case "INTERNSHIP":
                summary.setInternshipPoints(summary.getInternshipPoints() + points);
                break;
            case "PROJECT":
                summary.setProjectPoints(summary.getProjectPoints() + points);
                break;
            case "CERTIFICATE":
                summary.setCertificatePoints(summary.getCertificatePoints() + points);
                break;
        }

        int total = summary.getInternshipPoints() + summary.getProjectPoints() + summary.getCertificatePoints();
        summary.setTotalPoints(total);
    }

    return new ArrayList<>(summaryMap.values());
}

}
