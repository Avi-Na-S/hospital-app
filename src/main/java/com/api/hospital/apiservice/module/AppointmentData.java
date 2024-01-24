package com.api.hospital.apiservice.module;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(setterPrefix = "set")
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment_data")
public class AppointmentData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  @JoinColumn(name = "patient_id", nullable = false)
  private PatientData patientData;
  @ManyToOne
  @JoinColumn(name = "doctor_id", nullable = false)
  private DoctorData doctorData;
  @Column(name = "appointment_at", columnDefinition = "TIMESTAMP")
  private Date appointmentAt;
  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private Date createdAt;
  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  private Date updatedAt = new Date();

  @PrePersist
  public void prePersist() {
    if (createdAt == null) {
      createdAt = new Date();
    }
  }
}
