package com.api.hospital.apiservice.repository;

import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.PatientData;
import org.springframework.data.jpa.repository.JpaRepository;

// Jpa repo <Class need to convert as Table, primary Key datatype>
public interface DoctorDataRepo extends JpaRepository<DoctorData, Integer> {

}
