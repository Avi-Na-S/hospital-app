package com.api.hospital.apiservice.module.request;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AppointmentDataRequest {
  private int id;
  private int patientId;
  private int doctorId;
  private Date appointmentAt;
}
