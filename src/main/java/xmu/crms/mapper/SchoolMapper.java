package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.School;
@Mapper
@Component
public interface SchoolMapper {

	List<School> listSchoolByCity(String city);
	
	Integer insertSchool(School school);
	
	List<String> listProvince();
	
	List<String> listCity(String province);
	
	School getSchoolBySchoolId(BigInteger SchoolId);
}
