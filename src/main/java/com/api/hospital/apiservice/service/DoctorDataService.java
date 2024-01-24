package com.api.hospital.apiservice.service;

import com.api.hospital.apiservice.module.AppointmentData;
import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.response.DoctorsDataResponse;

public interface DoctorDataService {

  public DoctorsDataResponse createDoctorData(DoctorData doctorData);

  public DoctorsDataResponse updateDoctorData(int id, DoctorData doctorData);

  public DoctorsDataResponse getDoctorData(int id);

  public DoctorsDataResponse deleteDoctorData(int id);

  public DoctorsDataResponse getAllDoctorData();
}
