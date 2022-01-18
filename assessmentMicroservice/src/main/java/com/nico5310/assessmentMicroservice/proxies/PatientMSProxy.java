package com.nico5310.assessmentMicroservice.proxies;

import com.nico5310.assessmentMicroservice.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "patient-microservice", url = "${patient.url}")
public interface PatientMSProxy {


    @GetMapping("/patient/getById")
    PatientBean getById(@RequestParam Integer id) ;

}
