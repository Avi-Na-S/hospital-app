package com.api.hospital.apiservice.controller;

import com.api.hospital.apiservice.module.PatientData;
import com.api.hospital.apiservice.module.response.PatientsDataResponse;
import com.api.hospital.apiservice.service.PatientDataService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //used to define a class as a RESTful web service.
// When you annotate a class with @RestController,
// it combines the functionality of @Controller and @ResponseBody
//The @RestController annotation is a specialized version of the @Controller annotation.
// It's a convenience annotation that combines @Controller and @ResponseBody.
// When you use @RestController, Spring automatically applies @ResponseBody to all the methods in the class, so you don't need to use it explicitly for each method.
@RequestMapping("/patientService") // endpoints
public class PatientApiController {

  PatientDataService patientDataService;

  public PatientApiController(PatientDataService patientDataService) {
    this.patientDataService = patientDataService;
  }


  @GetMapping("/getPatientDetail/{id}")
  public PatientsDataResponse getPatientDetails(@PathVariable int id) {
    return patientDataService.getPatientData(id);
  }

  @PostMapping("/addPatientDetails")
  public PatientsDataResponse setPatientDetails(@RequestBody PatientData patientData) {
    return patientDataService.createPatientData(patientData);
  }

  @PutMapping("/updatePatientData/{id}")
  public PatientsDataResponse updatePatientWork(@PathVariable int id,
      @RequestBody PatientData patientData) {
    return patientDataService.updatePatientData(id, patientData);
  }

  @DeleteMapping("/deletePatient/{id}")
  public PatientsDataResponse deletePatient(@PathVariable int id) {
    return patientDataService.deletePatientData(id);
  }
}
