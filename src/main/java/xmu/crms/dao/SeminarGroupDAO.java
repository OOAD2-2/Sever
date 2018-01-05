package xmu.crms.dao;

import java.math.BigInteger;
import java.util.List;

import javax.crypto.interfaces.PBEKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xmu.crms.entity.Seminar;
import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.SeminarGroupMember;
import xmu.crms.mapper.CourseMapper;
import xmu.crms.mapper.GradeMapper;
import xmu.crms.mapper.SeminarGroupMapper;
import xmu.crms.mapper.SeminarMapper;

@Component
public class SeminarGroupDAO {

    @Autowired
    CourseMapper courseMapper;
    @Autowired
	SeminarGroupMapper seminarGroupMapper;
    @Autowired
	SeminarMapper seminarMapper;
    @Autowired
	GradeMapper gradeMapper;
    
    /**
     * 创建讨论课小组.
     * <p>在指定讨论课下创建讨论课小组<br>
     *
     * @param seminarId    讨论课的id
     * @param seminarGroup 小组信息
     * @return BigInteger 若创建成功返回该小组的id，失败则返回-1
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @author YeHongjie
     * @see SeminarGroupService #insertSeminarGroupMemberByGroupId(BigInteger groupId,SeminarGroupMember SeminarGroupMember)
     */
    
    public BigInteger insertSeminarGroupBySeminarId(BigInteger seminarId, SeminarGroup seminarGroup) throws IllegalArgumentException {
        Seminar seminar = new Seminar();
        seminar.setId(seminarId);
        seminarGroup.setSeminar(seminar);
        seminarGroupMapper.insertSeminarGroupBySeminarId(seminarGroup);
        System.out.println(seminarGroup.getId());
        return seminarGroup.getId();
    }
}
