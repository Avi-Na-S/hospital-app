package com.api.hospital.apiservice.module.response;

import com.api.hospital.apiservice.module.DoctorData;
import com.api.hospital.apiservice.module.PatientData;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(setterPrefix = "set")
@Getter
@Setter
public class DoctorsDataResponse {
    private String msg;
    private List<DoctorData> doctorDataList;
}
