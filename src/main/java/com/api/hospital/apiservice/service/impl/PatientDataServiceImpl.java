package com.api.hospital.apiservice.service.impl;

import com.api.hospital.apiservice.module.PatientData;
import com.api.hospital.apiservice.module.response.PatientsDataResponse;
import com.api.hospital.apiservice.repository.AppointmentDataRepo;
import com.api.hospital.apiservice.repository.DoctorDataRepo;
import com.api.hospital.apiservice.repository.PatientDataRepo;
import com.api.hospital.apiservice.service.PatientDataService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service // recognize as a service
public class PatientDataServiceImpl implements PatientDataService {

  PatientDataRepo patientDataRepo;

  public PatientDataServiceImpl(PatientDataRepo patientDataRepo) {
    this.patientDataRepo = patientDataRepo;
  }

  @Override
  public PatientsDataResponse createPatientData(PatientData patientData) {
    return getPatientDataResponse(patientDataRepo.save(patientData),
        "Created successfully");
  }

  @Override
  public PatientsDataResponse updatePatientData(int id, PatientData patientData) {
    Optional<PatientData> patientDataOptional = patientDataRepo.findById(id);
    if (patientDataOptional.isPresent() && Objects.nonNull(patientData)) {
      PatientData existingPatientData = patientDataOptional.get();
      PatientData updatedPatientData = PatientData.builder().setId(id)
          .setName(
              Objects.nonNull(patientData.getName()) ? patientData.getName()
                  : existingPatientData.getName())
          .setDob(Objects.nonNull(patientData.getDob()) ? patientData.getDob()
              : existingPatientData.getDob())
          .setOccupation(
              Objects.nonNull(patientData.getOccupation()) ? patientData.getOccupation()
                  : existingPatientData.getOccupation())
          .build();
      return getPatientDataResponse(patientDataRepo.save(updatedPatientData),
          "Updated successfully");
    } else {
      return getPatientDataResponse(patientData,
          StringUtils.join("Error: No patient date found for the given id: ", id,
              " or invalid request"));
    }
  }

  @Override
  public PatientsDataResponse getPatientData(int id) {
    Optional<PatientData> patientDataOptional = patientDataRepo.findById(id);
    return patientDataOptional.isPresent() ? getPatientDataResponse(patientDataOptional.get(),
        "success")
        : getPatientDataResponse(List.of(),
            StringUtils.join("Error: No Patient date found for the given id: ", id));
  }

  @Override
  public PatientsDataResponse deletePatientData(int id) {
    Optional<PatientData> patientDataOptional = patientDataRepo.findById(id);

    if (patientDataOptional.isPresent()) {
      patientDataRepo.deleteById(id);
      return getPatientDataResponse(patientDataOptional.get(), "Deleted successfully");
    } else {
      return getPatientDataResponse(List.of(),
          StringUtils.join("Error: No Patient date found for the given id: ", id));
    }
  }

  @Override
  public PatientsDataResponse getAllPatientData() {
    return getPatientDataResponse(patientDataRepo.findAll(), "Success");
  }

  public PatientsDataResponse getPatientDataResponse(PatientData patientData, String msg) {
    return PatientsDataResponse.builder().setPatientDataList(List.of(patientData)).setMsg(msg)
        .build();
  }

  public PatientsDataResponse getPatientDataResponse(List<PatientData> patientData, String msg) {
    return PatientsDataResponse.builder().setPatientDataList(patientData).setMsg(msg).build();
  }
}
