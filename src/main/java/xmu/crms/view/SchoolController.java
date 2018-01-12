package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import xmu.crms.entity.*;
import xmu.crms.service.SchoolService;
import xmu.crms.view.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/school")

public class SchoolController {

	@Autowired
	SchoolService schoolService;
   
	@SuppressWarnings("rawtypes")
	@RequestMapping( method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getSchools(@RequestParam(value="city", required=true) String city) {
        List<School> schools = schoolService.listSchoolByCity(city);
		return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(schools);
    }

	@RequestMapping(method = POST)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity addSchool(AddSchoolVO addSchoolVO,
                                    HttpServletRequest httpServletRequest) throws IOException {
        /*
	    BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        AddSchoolVO addSchoolVO = new AddSchoolVO(wholeStr);
        School school = new School();
        school.setCity(addSchoolVO.getCity());
        school.setName(addSchoolVO.getName());
        school.setProvince(addSchoolVO.getProvince());
        Map<String, Integer> result = new HashMap<String, Integer>();
        int id = schoolService.insertSchool(school).intValue();
        result.put("id", id);
        */
        School school = new School();
        school.setCity(addSchoolVO.getCity());
        school.setName(addSchoolVO.getName());
        school.setProvince(addSchoolVO.getProvince());
        System.out.println("schoolId"+schoolService.insertSchool(school).intValue());
        return ResponseEntity.status(201).build();
    }
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/province", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getProvinces() {
        List<String> provinces = new ArrayList<String>();
        for (String province : schoolService.listProvince()) {
            if (!provinces.contains(province))
                provinces.add(province);
        }
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(provinces);
    }

    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/city", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity getCities(@RequestParam String province) {
    	List<String> citys = new ArrayList<String>();
        for (String city : schoolService.listCity(province)) {
            if (!citys.contains(city))
                citys.add(city);
        }
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(citys);
    }

}
