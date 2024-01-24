package com.api.hospital.apiservice.service.impl;

import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.response.DoctorsDataResponse;
import com.api.hospital.apiservice.repository.DoctorDataRepo;
import com.api.hospital.apiservice.service.DoctorDataService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service // recognize as a service
public class DoctorDataServiceImpl implements DoctorDataService {

  DoctorDataRepo doctorDataRepo;

  public DoctorDataServiceImpl(DoctorDataRepo doctorDataRepo) {
    this.doctorDataRepo = doctorDataRepo;
  }

  @Override
  public DoctorsDataResponse createDoctorData(DoctorData patientData) {
    return getDoctorDataResponse(doctorDataRepo.save(patientData),
        "Created successfully");
  }

  @Override
  public DoctorsDataResponse updateDoctorData(int id, DoctorData doctorData) {
    Optional<DoctorData> patientDataOptional = doctorDataRepo.findById(id);
    if (patientDataOptional.isPresent() && Objects.nonNull(doctorData)) {
      DoctorData existingDoctor = patientDataOptional.get();
      DoctorData updateDoctorData = DoctorData.builder().setId(id)
          .setName(
              Objects.nonNull(doctorData.getName()) ? doctorData.getName()
                  : existingDoctor.getName())
          .setDob(
              Objects.nonNull(doctorData.getDob()) ? doctorData.getDob() : existingDoctor.getDob())
          .setSpecialization(
              Objects.nonNull(doctorData.getSpecialization()) ? doctorData.getSpecialization()
                  : existingDoctor.getSpecialization())
          .build();
      return getDoctorDataResponse(doctorDataRepo.save(updateDoctorData),
          "Updated successfully");
    } else {
      return getDoctorDataResponse(doctorData,
          StringUtils.join("Error: No patient date found for the given id: ", id,
              " or invalid request"));
    }
  }

  @Override
  public DoctorsDataResponse getDoctorData(int id) {
    Optional<DoctorData> patientDataOptional = doctorDataRepo.findById(id);
    return patientDataOptional.isPresent() ? getDoctorDataResponse(patientDataOptional.get(),
        "success")
        : getDoctorDataResponse(List.of(), "success");
  }

  @Override
  public DoctorsDataResponse deleteDoctorData(int id) {
    Optional<DoctorData> patientDataOptional = doctorDataRepo.findById(id);

    if (patientDataOptional.isPresent()) {
      doctorDataRepo.deleteById(id);
      return getDoctorDataResponse(List.of(), "Updated successfully");
    } else {
      return getDoctorDataResponse(List.of(),
          StringUtils.join("Error: No Doctor date found for the given id: ", id));
    }
  }

  @Override
  public DoctorsDataResponse getAllDoctorData() {
    return getDoctorDataResponse(doctorDataRepo.findAll(), "Success");
  }

  public DoctorsDataResponse getDoctorDataResponse(DoctorData patientData, String msg) {
    return DoctorsDataResponse.builder().setDoctorDataList(List.of(patientData)).setMsg(msg)
        .build();
  }

  public DoctorsDataResponse getDoctorDataResponse(List<DoctorData> patientData, String msg) {
    return DoctorsDataResponse.builder().setDoctorDataList(patientData).setMsg(msg).build();
  }
}
