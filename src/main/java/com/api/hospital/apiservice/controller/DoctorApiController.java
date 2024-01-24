package com.api.hospital.apiservice.controller;

import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.response.DoctorsDataResponse;
import com.api.hospital.apiservice.service.DoctorDataService;
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
@RequestMapping("/doctorService") // endpoints
public class DoctorApiController {

  DoctorDataService doctorDataService;

  public DoctorApiController(DoctorDataService doctorDataService) {
    this.doctorDataService = doctorDataService;
  }


  @GetMapping("/getDoctorDetail/{id}")
  public DoctorsDataResponse getDoctorDetails(@PathVariable int id) {
    return doctorDataService.getDoctorData(id);
  }

  @PostMapping("/addDoctorDetails")
  public DoctorsDataResponse setDoctorDetails(@RequestBody DoctorData doctorData) {
    return doctorDataService.createDoctorData(doctorData);
  }

  @PutMapping("/updateDoctorData/{id}")
  public DoctorsDataResponse updateDoctorWork(@PathVariable int id,
      @RequestBody DoctorData doctorData) {
    return doctorDataService.updateDoctorData(id, doctorData);
  }

  @DeleteMapping("/deleteDoctor/{id}")
  public DoctorsDataResponse deleteDoctor(@PathVariable int id) {
    return doctorDataService.deleteDoctorData(id);
  }
}
