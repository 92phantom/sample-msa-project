package api.reserve.msa.service;

import api.reserve.msa.domain.ReserveRepository;
import api.reserve.msa.dto.ReserveDto;
import api.reserve.msa.service.api.CallEnroll;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final CallEnroll callEnroll;
    private Logger LOGGER = LoggerFactory.getLogger(ReserveService.class);

    private final ReserveRepository reserveRepository;

    @Value("${server.id}")
    private String SERVER_ID;


    public ReserveDto doDevice_Reservation(ReserveDto requestParam){

        System.out.println("#####################");
        System.out.println(requestParam.toEntity());

        if(requestParam.getServer_id().equals(SERVER_ID)){
            requestParam.setResponseStatus("OK");
//            LOGGER.info(requestParam.toJsonEntity());
        }
        else{
            requestParam.setResponseStatus("INVALID");
            LOGGER.error(requestParam.toJsonEntity());
        }

        boolean enroll_flag = callExtApi_Enroll_Check(requestParam.getDevice_id());

        if(enroll_flag){
            LOGGER.info(requestParam.toJsonEntity());
            reserveRepository.save(requestParam.toEntity());
        }else{
            requestParam.setResponseStatus("NOT REGISTERED DEVICE");
            LOGGER.error(requestParam.toJsonEntity());
        }



        return requestParam;
    }


    public boolean callExtApi_Enroll_Check(String device_id) {

        return callEnroll.doEnrollCheck(device_id);
    }
}

