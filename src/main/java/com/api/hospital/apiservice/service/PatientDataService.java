package com.api.hospital.apiservice.service;

import com.api.hospital.apiservice.module.AppointmentData;
import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.PatientData;
import com.api.hospital.apiservice.module.response.PatientsDataResponse;

public interface PatientDataService {

  public PatientsDataResponse createPatientData(PatientData patientData);

  public PatientsDataResponse updatePatientData(int id, PatientData patientData);

  public PatientsDataResponse getPatientData(int id);

  public PatientsDataResponse deletePatientData(int id);

  public PatientsDataResponse getAllPatientData();
}
