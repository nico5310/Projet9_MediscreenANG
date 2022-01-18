package com.nico5310.assessmentMicroservice.proxies;

import com.nico5310.assessmentMicroservice.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note-microservice", url = "${note.url}")
public interface NoteMSProxy {


    @GetMapping("/note/list/{patientId}")
    List<NoteBean> listNote(@PathVariable("patientId") Integer patientId);


}