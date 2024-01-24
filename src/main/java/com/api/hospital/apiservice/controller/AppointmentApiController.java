package com.api.hospital.apiservice.controller;

import com.api.hospital.apiservice.module.AppointmentData;
import com.api.hospital.apiservice.module.request.AppointmentDataRequest;
import com.api.hospital.apiservice.module.response.AppointmentsDataResponse;
import com.api.hospital.apiservice.service.AppointmentDataService;
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
@RequestMapping("/appointmentService") // endpoints
public class AppointmentApiController {

  AppointmentDataService appointmentDataService;

  public AppointmentApiController(AppointmentDataService appointmentDataService) {
    this.appointmentDataService = appointmentDataService;
  }


  @GetMapping("/getAppointmentDetail/{id}")
  public AppointmentsDataResponse getAppointmentDetails(@PathVariable int id) {
    return appointmentDataService.getAppointmentData(id);
  }

  @PostMapping("/addAppointmentDetails")
  public AppointmentsDataResponse setAppointmentDetails(
      @RequestBody AppointmentData appointmentData) {
    return appointmentDataService.createAppointmentData(appointmentData);
  }

  @PutMapping("/updateAppointmentData/{id}")
  public AppointmentsDataResponse updateAppointmentWork(@PathVariable int id,
      @RequestBody AppointmentDataRequest appointmentDataRequest) {
    return appointmentDataService.updateAppointmentData(id, new AppointmentDataRequest());
  }

  @DeleteMapping("/deleteAppointment/{id}")
  public AppointmentsDataResponse deleteAppointment(@PathVariable int id) {
    return appointmentDataService.deleteAppointmentData(id);
  }
}
