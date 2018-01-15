package xmu.crms.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xmu.crms.entity.School;

/**
 * @author ASUS7
 */
@Mapper
@Component
public interface SchoolMapper {

	/**
	 * 返回学校
	 * @param city
	 * @return List<School>
	 */
	List<School> listSchoolByCity(String city);

	/**
	 * 插入学校
	 * @param school
	 * @return Integer
	 */
	Integer insertSchool(School school);

	/**
	 * 返回省份
	 * @return List<String>
	 */
	List<String> listProvince();

	/**
	 * 返回城市
	 * @param province
	 * @return List<String>
	 */
	List<String> listCity(String province);

	/**
	 * 搜索学校
	 * @param schoolId
	 * @return School
	 */
	School getSchoolBySchoolId(BigInteger schoolId);
}
