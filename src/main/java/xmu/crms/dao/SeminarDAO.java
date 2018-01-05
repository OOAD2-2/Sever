package xmu.crms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

import xmu.crms.entity.Seminar;
import xmu.crms.mapper.SeminarMapper;

@Component
public class SeminarDAO{

	@Autowired
	SeminarMapper seminarMapper;
	
	private TopicDAO topicDAO;
    /**
     * 按courseId获取Seminar.
     * @author lhl
     * @param courseId 课程Id
     * @return List 讨论课列表
     */

    public List<Seminar> listSeminarByCourseId(BigInteger courseId){
    	List<Seminar> listSeminar=seminarMapper.listSeminarByCourseId(courseId);
        return listSeminar;
    }



    /**
     * 用户通过讨论课id获得讨论课的信息.
     * <p>用户通过讨论课id获得讨论课的信息（包括讨论课名称、讨论课描述、分组方式、开始时间、结束时间）<br>
     * @author lhl
     * @param seminarId 讨论课的id
     * @return 相应的讨论课信息
     */
    public Seminar getSeminarBySeminarId(BigInteger seminarId){
        return seminarMapper.getSeminarBySeminarId(seminarId);
    }




    /**
     * 按讨论课id删除讨论课.
     * <p>用户（老师）通过seminarId删除讨论课<br>(包括删除讨论课包含的topic信息和小组信息).
     * @author lhl
     * @param seminarId 讨论课的id
     */
    public void deleteSeminarBySeminarId(BigInteger seminarId) {
    	topicDAO.deleteTopicBySeminarId(seminarId);
    	seminarMapper.deleteSeminarGroupBySeminarId(seminarId);
    	seminarMapper.deleteSeminarById(seminarId);
    }


    /**
     * 新增讨论课.
     * <p>用户（老师）在指定的课程下创建讨论课<br>
     * @author lhl
     * @param courseId 课程的id
     * @param seminar 讨论课信息
     */
    public Integer insertSeminarByCourseId(Seminar seminar){
        return seminarMapper.insertSeminarByCourseId(seminar);
    }
}
