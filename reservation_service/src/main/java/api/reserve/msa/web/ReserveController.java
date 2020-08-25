package api.reserve.msa.web;

import api.reserve.msa.dto.ReserveDto;
import api.reserve.msa.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@RestController
public class ReserveController {

    private final ReserveService reserveService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> handleEnroll(@ModelAttribute final ReserveDto requestParam, HttpServletRequest request) {

        ReserveDto reserveDto= reserveService.doDevice_Reservation(requestParam);

        if(reserveDto.getResponseStatus().equals("OK")){
            return new ResponseEntity(reserveDto, HttpStatus.OK);
        }else if(reserveDto.getResponseStatus().equals("INVALID")){
            return new ResponseEntity(reserveDto, HttpStatus.BAD_REQUEST);
        }else  {
            return new ResponseEntity(reserveDto, HttpStatus.FORBIDDEN);
        }
    }

}