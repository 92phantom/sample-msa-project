package api.report.msa.web;

import api.report.msa.dto.ReportDto;
import api.report.msa.service.ReportMonitorService;
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
public class ReportController {

    private final ReportMonitorService reportMonitorService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> handleUsageReport(@ModelAttribute final ReportDto requestParam
            , HttpServletRequest request) {

        ReportDto reportDto = reportMonitorService.saveReportMonitoring(requestParam);

        if(reportDto.getResponseStatus().equals("OK")){
            return new ResponseEntity(reportDto, HttpStatus.OK);
        }

        else{
            return new ResponseEntity(reportDto, HttpStatus.BAD_REQUEST);
        }
    }
}