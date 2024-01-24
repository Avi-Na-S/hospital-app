package com.api.hospital.apiservice.module;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
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

@Entity // signals to the JPA provider that the class should be treated as a table in the database.
@Table(name = "doctor_data")
// signals to the JPA provider about info of the table default will be class name => user_date
public class DoctorData {

  @Id // primaryKey
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //annotation specifies the generation strategy for the primary key. In this case,
  // GenerationType.IDENTITY is used, which generally maps to an auto-incremented column in a relational database
  private int id;
  @Column(name = "doctor_name")
  private String name;
  @Column(name = "doctor_dob", columnDefinition = "DATE")
  private Date dob;
  @Column(name = "doctor_specialization")
  private String specialization;
  @OneToMany(mappedBy = "doctorData", cascade = CascadeType.ALL) // mappedby should be same as a value name in appointmentdata
  private List<AppointmentData> appointments;
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
