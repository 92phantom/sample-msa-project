package api.enroll.msa.service;


import api.enroll.msa.domain.DeviceEnroll;
import api.enroll.msa.domain.DeviceEnrollRepository;
import api.enroll.msa.dto.EnrollDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EnrollService {
    private Logger LOGGER = LoggerFactory.getLogger(EnrollService.class);

    @Value("${server.id}")
    private String SERVER_ID;

    private final DeviceEnrollRepository deviceEnrollRepository;

    @Transactional
    public EnrollDto handleEnroll(EnrollDto requestParam){

        System.out.println("#####################");
        System.out.println(requestParam.toEntity());

        if(requestParam.getServer_id().equals(SERVER_ID)){
            requestParam.setResponseStatus("OK");
            LOGGER.info(requestParam.toJsonEntity());
        }
        else{
            requestParam.setResponseStatus("INVALID");
            LOGGER.error(requestParam.toJsonEntity());
        }

        // Transactional Part
        Optional<DeviceEnroll> deviceEnroll = deviceEnrollRepository.findById(requestParam.getDevice_id());

        if(!deviceEnroll.isPresent()){
            deviceEnrollRepository.save(requestParam.toEntity());
        }
        else{
            requestParam.setResponseStatus("UPDATED");
            deviceEnroll.get().update(requestParam.getServer_id(), requestParam.getSub_dev_number());
        }

        return requestParam;

    }

    public boolean checkExisting(String device_id){

        Optional<DeviceEnroll> deviceEnroll = deviceEnrollRepository.findById(device_id);

        if(!deviceEnroll.isPresent()){
            System.out.println("@@@@@@@"+deviceEnroll.get().getDevice_id());
            return false;
        }
        else{
            return true;
        }

    }



}
