package com.api.hospital.apiservice.service.impl;

import com.api.hospital.apiservice.module.AppointmentData;
import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.AppointmentData;
import com.api.hospital.apiservice.module.request.AppointmentDataRequest;
import com.api.hospital.apiservice.module.response.AppointmentsDataResponse;
import com.api.hospital.apiservice.repository.AppointmentDataRepo;
import com.api.hospital.apiservice.repository.DoctorDataRepo;
import com.api.hospital.apiservice.repository.AppointmentDataRepo;
import com.api.hospital.apiservice.repository.PatientDataRepo;
import com.api.hospital.apiservice.service.AppointmentDataService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service // recognize as a service
public class AppointmentDataServiceImpl implements AppointmentDataService {

  AppointmentDataRepo appointmentDataRepo;
  DoctorDataRepo doctorDataRepo;
  PatientDataRepo patientDataRepo;

  public AppointmentDataServiceImpl(DoctorDataRepo doctorDataRepo, PatientDataRepo patientDataRepo,
      AppointmentDataRepo appointmentDataRepo) {
    this.appointmentDataRepo = appointmentDataRepo;
    this.doctorDataRepo = doctorDataRepo;
    this.patientDataRepo = patientDataRepo;
  }

  @Override
  public AppointmentsDataResponse createAppointmentData(AppointmentData appointmentData) {
    return getAppointmentDataResponse(appointmentDataRepo.save(appointmentData),
        "Created successfully");
  }

  @Override
  public AppointmentsDataResponse updateAppointmentData(int id,
      AppointmentDataRequest appointmentDataRequest) {
    Optional<AppointmentData> appointmentDataOptional = appointmentDataRepo.findById(id);
    try {
      if (appointmentDataOptional.isPresent() && Objects.nonNull(appointmentDataRequest)) {
        AppointmentData existingAppointment = appointmentDataOptional.get();
        AppointmentData updateAppointmentData = AppointmentData.builder().setId(id)
            .setAppointmentAt(
                Objects.nonNull(appointmentDataRequest.getAppointmentAt())
                    ? appointmentDataRequest.getAppointmentAt()
                    : existingAppointment.getAppointmentAt())
            .setDoctorData(appointmentDataRequest.getDoctorId() != 0 ? doctorDataRepo.findById(
                appointmentDataRequest.getDoctorId()).get()
                : existingAppointment.getDoctorData())
            .setPatientData(
                appointmentDataRequest.getPatientId() != 0 ? patientDataRepo.findById(
                    appointmentDataRequest.getDoctorId()).get()
                    : existingAppointment.getPatientData())
            .build();
        return getAppointmentDataResponse(appointmentDataRepo.save(updateAppointmentData),
            "Updated successfully");
      } else {
        return getAppointmentDataResponse(List.of(),
            StringUtils.join("Error: No appointment date found for the given id: ", id,
                " or invalid request"));
      }
    } catch (Exception e) {
      return getAppointmentDataResponse(List.of(),
          StringUtils.join("Error: No appointment date found for the given id: ", id,
              " or invalid request"));
    }
  }

  @Override
  public AppointmentsDataResponse getAppointmentData(int id) {
    Optional<AppointmentData> appointmentDataOptional = appointmentDataRepo.findById(id);
    return appointmentDataOptional.isPresent() ? getAppointmentDataResponse(
        appointmentDataOptional.get(),
        "success")
        : getAppointmentDataResponse(List.of(), "success");
  }

  @Override
  public AppointmentsDataResponse deleteAppointmentData(int id) {
    Optional<AppointmentData> appointmentDataOptional = appointmentDataRepo.findById(id);

    if (appointmentDataOptional.isPresent()) {
      appointmentDataRepo.deleteById(id);
      return getAppointmentDataResponse(List.of(), "Updated successfully");
    } else {
      return getAppointmentDataResponse(List.of(),
          StringUtils.join("Error: No Appointment date found for the given id: ", id));
    }
  }

  @Override
  public AppointmentsDataResponse getAllAppointmentData() {
    return getAppointmentDataResponse(appointmentDataRepo.findAll(), "Success");
  }

  public AppointmentsDataResponse getAppointmentDataResponse(AppointmentData appointmentData,
      String msg) {
    return AppointmentsDataResponse.builder().setAppointmentDataList(List.of(appointmentData))
        .setMsg(msg)
        .build();
  }

  public AppointmentsDataResponse getAppointmentDataResponse(List<AppointmentData> appointmentData,
      String msg) {
    return AppointmentsDataResponse.builder().setAppointmentDataList(appointmentData).setMsg(msg)
        .build();
  }
}
