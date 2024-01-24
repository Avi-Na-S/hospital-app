package com.api.hospital.apiservice.service;

import com.api.hospital.apiservice.module.AppointmentData;
import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.AppointmentData;
import com.api.hospital.apiservice.module.request.AppointmentDataRequest;
import com.api.hospital.apiservice.module.response.AppointmentsDataResponse;

public interface AppointmentDataService {

  public AppointmentsDataResponse createAppointmentData(AppointmentData appointmentData);

  public AppointmentsDataResponse updateAppointmentData(int id, AppointmentDataRequest appointmentDataRequest);

  public AppointmentsDataResponse getAppointmentData(int id);

  public AppointmentsDataResponse deleteAppointmentData(int id);

  public AppointmentsDataResponse getAllAppointmentData();
}
