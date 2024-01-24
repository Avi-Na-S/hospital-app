package com.api.hospital.apiservice.repository;

import com.api.hospital.apiservice.module.AppointmentData;
import org.springframework.data.jpa.repository.JpaRepository;

// Jpa repo <Class need to convert as Table, primary Key datatype>
public interface AppointmentDataRepo extends JpaRepository<AppointmentData, Integer> {

}
