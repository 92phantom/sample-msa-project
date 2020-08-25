package api.enroll.msa.web;

import api.enroll.msa.dto.EnrollDto;
import api.enroll.msa.service.EnrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class EnrollController {

    private final EnrollService enrollService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> handleEnroll(@ModelAttribute final EnrollDto requestParam, HttpServletRequest request) {

        EnrollDto enrollDto= enrollService.handleEnroll(requestParam);

        if(enrollDto.getResponseStatus().equals("OK")){
            return new ResponseEntity(enrollDto, HttpStatus.OK);
        }else if(enrollDto.getResponseStatus().equals("INVALID")){
            return new ResponseEntity(enrollDto, HttpStatus.BAD_REQUEST);
        }else  {
            return new ResponseEntity(enrollDto, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/exist_check", method = RequestMethod.GET)
    public boolean handleExistence(@RequestParam("device_id") String device_id) {
        return enrollService.checkExisting(device_id);
    }

}