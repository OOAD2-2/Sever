package xmu.crms.service.impl;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import xmu.crms.dao.SeminarGroupDAO;
import xmu.crms.entity.*;
import xmu.crms.exception.*;
import xmu.crms.mapper.SeminarGroupMapper;
import xmu.crms.mapper.SeminarMapper;
import xmu.crms.mapper.TopicMapper;
import xmu.crms.service.SeminarGroupService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zw
 * @date 2017/12/20 0020
 * @time 14:19
 * @email 493703217@qq.com
 */
@Service
@Component
public class SeminarGroupServiceImpl implements SeminarGroupService {
    @Autowired(required = false)
    SeminarGroupMapper seminarGroupMapper;

    @Autowired(required = false)
    TopicMapper topicMapper;

    @Autowired(required = false)
    SeminarMapper seminarMapper;
    
   
    private SeminarGroupDAO seminarGroupDAO;


    @Override
    public List<User> listSeminarGroupMemberByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
        List<User> users = new ArrayList<User>();
        List<SeminarGroupMember> seminarGroupMembers = seminarGroupMapper.listSeminarGroupMemberByGroupId(groupId);
        for (SeminarGroupMember seminarGroupMember : seminarGroupMembers) {
            users.add(seminarGroupMember.getStudent());
        }
        return users;
    }


    @Override
    public BigInteger insertSeminarGroupMemberByGroupId(BigInteger groupId, SeminarGroupMember seminarGroupMember) {
        return BigInteger.valueOf(seminarGroupMapper.insertSeminarGroupMemberByGroupId(groupId, seminarGroupMember));
    }


    @Override
    public SeminarGroup getSeminarGroupById(BigInteger seminarId, BigInteger userId) throws IllegalArgumentException, GroupNotFoundException {
        List<BigInteger>  list=seminarGroupMapper.getSeminarGroupIdBySeminarIdAndUserId(userId);
        System.out.println(seminarGroupMapper.getSeminarGroupByGroupId(BigInteger.valueOf(42)));
        if(list==null) {
        	throw new GroupNotFoundException("未找到小组");}
        for(BigInteger id:list)
        {
        	if(seminarGroupMapper.getSeminarGroupByGroupId(id).getSeminar().getId().equals(seminarId))
        		{return seminarGroupMapper.getSeminarGroupByGroupId(id);}
        }
        return null;
    }

    @Override
    public void automaticallyAllotTopic(BigInteger seminarId) throws IllegalArgumentException, SeminarNotFoundException, GroupNotFoundException {
        //获得seminar下所有topic （topic）
        List<Topic> topics = new ArrayList<Topic>();
        topics = topicMapper.listTopicBySeminarId(seminarId);
        //获得所有的选了话题的小组
        List<SeminarGroup> seminarGroupsHasTopic = new ArrayList<SeminarGroup>();
        for(Topic topic:topics)
        {
            List<SeminarGroup> seminarGroupsHasThisTopic= null;
            seminarGroupsHasThisTopic = listGroupByTopicId(topic.getId());
			System.out.println("Topicid:"+topic.getId());
            for(SeminarGroup seminarGroup:seminarGroupsHasThisTopic){
                seminarGroupsHasTopic.add(seminarGroup);
            }
        }
        System.out.println("seminarGroupsHasTopic:"+seminarGroupsHasTopic.size());
        //通过seminarid找到所有的seminargroup （seminar gourp）
        List<SeminarGroup> allSeminarGroups;
        allSeminarGroups = seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
        //通过seminargroupid找到所有的没有话题的组 （seminargourptopic）
        System.out.println("allSeminarGroups:"+allSeminarGroups.size());
        List<SeminarGroup> seminarGroupsHasNoTopic = new ArrayList<SeminarGroup>();
        for(SeminarGroup seminarGroup:allSeminarGroups)
        {
            int judge = 0;
            for(SeminarGroup seminarGroup1:seminarGroupsHasTopic)
            {

                if(seminarGroup.getId().equals(seminarGroup1.getId()))
                {
                    judge = 1;
                    break;
                }
            }
            if(judge == 0)
            {
                seminarGroupsHasNoTopic.add(seminarGroup);
            }

        }
        seminarGroupsHasNoTopic = allSeminarGroups;
        //%2 seminargourptopic
        int j = 0;
        for(int i=0; i<seminarGroupsHasNoTopic.size(); i++){

            for(;j<topics.size();)
            {
                if(seminarGroupMapper.listGroupIdByTopicId(topics.get(j).getId()).size()>=topics.get(j).getGroupNumberLimit())
                {
                    j=(j+1)%topics.size();
                }
                else{
                    seminarGroupMapper.insertSeminarGroupTopicByTopicIdAndSeminarGroupId(topics.get(j).getId(),seminarGroupsHasNoTopic.get(i).getId());
                    j=(j+1)%topics.size();
                    break;
                }
            }
            }

    }
//
//
//


    /**
     * for ali
     * @param
     * @return
     * @author zhouwei
     * @date 19:49 2017/12/23 0023
     */

    /**
     * for ali 成为组长.
     * <p>同学按小组id和自身id成为组长<br>
     *
     * @param groupId 小组id
     * @param userId  学生id
     * @throws IllegalArgumentException  信息不合法，id格式错误
     * @throws GroupNotFoundException    未找到小组
     * @throws UserNotFoundException     不存在该学生
     * @throws InvalidOperationException 已经有组长了
     */
    @Override
    public void assignLeaderById(BigInteger groupId, BigInteger userId) throws IllegalArgumentException, UserNotFoundException, GroupNotFoundException, InvalidOperationException {
        if (seminarGroupMapper.getSeminarGroupByGroupId(groupId) == null) {
            throw new GroupNotFoundException("未找到该课程");
        }
        if (seminarGroupMapper.getUserIdByUserId(userId) == null) {
            throw new UserNotFoundException("未找到该学生");
        }
        if (seminarGroupMapper.getSeminarGroupLeaderByGroupId(groupId) != null) {
            throw new InvalidOperationException("已经有组长了");
        }
        seminarGroupMapper.assignLeaderById(groupId, userId);
    }

    @Override
    public void automaticallyGrouping(BigInteger seminarId, BigInteger classId) throws ClassesNotFoundException, SeminarNotFoundException {
        //get all studentid by seminarid and classid  <attendence>
        List<BigInteger> studentIdList = seminarGroupMapper.listStudentIdBySeminarIdAndClassId(seminarId, classId);
        //find the smallest groupnumber limit in topic
        List<Topic> topics = topicMapper.listTopicBySeminarId(seminarId);
        System.out.println(topics);
        int smallestlimit = 1000;
        int groupnumberlimit;
        for (Topic topic : topics) {
            groupnumberlimit = topic.getGroupStudentLimit();
            if (groupnumberlimit < smallestlimit) {
                smallestlimit = groupnumberlimit;
            }
        }
        int groupnumber = studentIdList.size() / smallestlimit;
        System.out.println("studentIdList:" + studentIdList.size());
        System.out.println("groupnumber:" + groupnumber);
        System.out.println("smallestlimit:" + smallestlimit);
        if ((studentIdList.size() % smallestlimit) != 0) {
            groupnumber++;
        }
        //add group number in seminar_group
        for (int i = 0; i < groupnumber; i++) {
            SeminarGroup seminarGroup = new SeminarGroup();
            ClassInfo classInfo = new ClassInfo();
            classInfo.setId(classId);
            seminarGroup.setClassInfo(classInfo);
            Seminar seminar = seminarMapper.getSeminarBySeminarId(seminarId);
            seminarGroup.setSeminar(seminar);
            seminarGroupMapper.insertSeminarGroupBySeminarId(seminarGroup);

            BigInteger groupId = seminarGroup.getId();
            for (int j = 0; j < smallestlimit && (j+i*smallestlimit) < studentIdList.size(); j++) {
                try {
                    insertSeminarGroupMemberById(studentIdList.get(j + i * smallestlimit), groupId);
                    if (j == 0)
                        {assignLeaderById(groupId, studentIdList.get(j + i * smallestlimit));}
                } catch (GroupNotFoundException e) {
                    e.printStackTrace();
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                } catch (InvalidOperationException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * for ali 删除讨论课小组.
     * <p>按照id删除讨论课小组<br>
     *
     * @param seminarGroupId 讨论课小组的id
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @author YeHongjie
     * @see SeminarGroupService #deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId)
     */
    @Override
    public void deleteSeminarGroupByGroupId(BigInteger seminarGroupId) throws IllegalArgumentException {
        seminarGroupMapper.deleteSeminarGroupByGroupId(seminarGroupId);
    }

    /**
     * for ali 按seminarId删除讨论课小组信息.
     * <p>根据seminarId获得SeminarGroup，然后根据SeminarGroupId删除SeminarGroupMember信息，最后再删除SeminarGroup信息<br>
     *
     * @param seminarId 讨论课Id
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @author zhouzhongjun
     * @see SeminarGroupService #listSeminarGroupBySeminarId(BigInteger seminarId)
     * @see SeminarGroupService #deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId)
     */
    @Override
    public void deleteSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException {
        List<SeminarGroup> seminarGroups = seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
        for (SeminarGroup seminarGroup : seminarGroups) {
            seminarGroupMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroup.getId());
        }
        seminarGroupMapper.deleteSeminarGroupBySeminarId(seminarId);
    }

    /**
     * for ali 按seminarGroupId删除SeminarGroupMember信息.
     * <p>按seminarGroupId删除SeminarGroupMember信息<br>
     *
     * @param seminarGroupId 讨论课小组Id
     * @author zhouzhongjun
     */
    @Override
    public void deleteSeminarGroupMemberBySeminarGroupId(BigInteger seminarGroupId) {
        seminarGroupMapper.deleteSeminarGroupMemberBySeminarGroupId(seminarGroupId);
    }

    /**
     * for ali 查询讨论课小组.
     * <p>按照id查询某一讨论课小组的信息（包括成员）<br>
     *
     * @param groupId 小组的id
     * @return seminarGroup 讨论课小组对象，若未找到相关小组返回空(null)
     * @throws IllegalArgumentException (信息不合法，id格式错误)
     * @throws GroupNotFoundException   (未找到小组)
     * @author YeHongjie
     * @see SeminarGroupService #listSeminarGroupMemberByGroupId(BigInteger groupId)
     */
    @Override
    public SeminarGroup getSeminarGroupByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
        if (seminarGroupMapper.getSeminarGroupByGroupId(groupId) == null) {
            throw new GroupNotFoundException("未找到小组");
        }
        return seminarGroupMapper.getSeminarGroupByGroupId(groupId);
    }

    /**
     * for ali 获取学生所在讨论课队长.
     * <p>按照用户id和讨论课id获取学生所在讨论课小组队长<br>
     *
     * @param userId    用户的id
     * @param seminarId 讨论课id
     * @return BigInteger 讨论课小组的队长id，若未找到相关小组队长返回空(null)
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @author YeHongjie
     * @see SeminarGroupService #getSeminarGroupById(BigInteger userId, BigInteger seminarId)
     * @see SeminarGroupService #getSeminarGroupLeaderByGroupId(BigInteger groupId)
     */

    @Override
    public BigInteger getSeminarGroupLeaderById(BigInteger userId, BigInteger seminarId) throws IllegalArgumentException {
        BigInteger groupId;
        return null;
    }

    /**
     * for ali 查询讨论课小组队长id.
     * <p>按照讨论课小组id查询该小组的队长id<br>
     *
     * @param groupId 要查询的讨论课小组id
     * @return leaderId 讨论课小组队长id
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @throws GroupNotFoundException   未找到小组
     * @author YeHongjie
     */
    @Override
    public BigInteger getSeminarGroupLeaderByGroupId(BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException {
        if (seminarGroupMapper.getSeminarGroupByGroupId(groupId) == null) {
            throw new GroupNotFoundException("未找到小组");
        }
        return seminarGroupMapper.getSeminarGroupByGroupId(groupId).getLeader().getId();
    }

    
    /**
     * for ali 按seminarId获取SeminarGroup.
     * <p>按seminarId获取SeminarGroup<br>
     *
     * @param seminarId 课程Id
     * @return 讨论课小组列表
     * @throws IllegalArgumentException 信息不合法，id格式错误
     * @throws SeminarNotFoundException 未找到讨论课
     * @author zhouzhongjun
     */
    @Override
    public List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException, SeminarNotFoundException {
        if (seminarGroupMapper.getSeminarIdBySeminarId(seminarId) == null) {
            throw new SeminarNotFoundException("未找到讨论课");
        }
        return seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
    }


    /**
     * for ali 将学生加入讨论课小组.
     *
     * @param userId  用户Id
     * @param groupId 小组Id
     * @author YellowPure
     * @return 
     * @date 20:43 2017/12/22
     */
    @Override
    public BigInteger insertSeminarGroupMemberById(BigInteger userId, BigInteger groupId) throws IllegalArgumentException, GroupNotFoundException, UserNotFoundException, InvalidOperationException {
        if (seminarGroupMapper.getSeminarGroupById(groupId) == null) {
            throw new GroupNotFoundException("未找到小组");
        }
        if (seminarGroupMapper.getUserById(userId) == null) {
            throw new UserNotFoundException("不存在该学生");
        }
        if (seminarGroupMapper.getSeminarGroupMemberByStudentIdAndSeminarGroupId(userId, groupId) == null) {
            List<SeminarGroupMember> seminarGroupMemberList = seminarGroupMapper.listSeminarGroupIdByStudentId(userId);
            for (SeminarGroupMember seminarGroupMember : seminarGroupMemberList) {
                if (seminarGroupMember.getSeminarGroup() != null) {
                    if (seminarGroupMember.getSeminarGroup().getSeminar().getId().equals(this.getSeminarGroupByGroupId(groupId).getSeminar().getId())) {
                        return BigInteger.valueOf(-1);
                    }
                }
            }
            seminarGroupMapper.insertSeminarGroupMemberById(userId, groupId);
            return seminarGroupMapper.getSeminarGroupMemberByStudentIdAndSeminarGroupId(userId, groupId).getId();
        } else {
            throw new InvalidOperationException("待添加学生已添加进小组");
        }
    }


    /**
     * for ali 获取某学生所有的讨论课小组.
     *
     * @param userId 用户id
     * @return List<SeminarGroup>
     * @author YellowPure
     * @date 22:41 2017/12/22
     */
    @Override
    public List<SeminarGroup> listSeminarGroupIdByStudentId(BigInteger userId) throws IllegalArgumentException {
        List<SeminarGroupMember> seminarGroupMembers = seminarGroupMapper.listSeminarGroupIdByStudentId(userId);
        List<SeminarGroup> seminarGroups = new ArrayList<>();
        for (SeminarGroupMember seminarGroupMember : seminarGroupMembers) {
            seminarGroups.add(seminarGroupMember.getSeminarGroup());
        }
        return seminarGroups;
    }


    /**
     * for ali按seminarId获取SeminarGroup.
     *
     * @param seminarId 讨论课id
     * @return List<SeminarGroup>
     * @author YellowPure
     * @date 22:50 2017/12/22
     */
//      @Override
//      public List<SeminarGroup> listSeminarGroupBySeminarId(BigInteger seminarId) throws IllegalArgumentException, SeminarNotFoundException {
//            if(seminarGroupMapper.getSeminarById(seminarId)==null){
//                  throw new SeminarNotFoundException("未找到讨论课");
//            }
//            return seminarGroupMapper.listSeminarGroupBySeminarId(seminarId);
//      }

    /**
     * for ali 根据话题Id获得选择该话题的所有小组的信息.
     *
     * @param topicId 话题Id
     * @return List<SeminarGroup>
     * @author YellowPure
     * @date 22:51 2017/12/22
     */
    @Override
    public List<SeminarGroup> listGroupByTopicId(BigInteger topicId) throws IllegalArgumentException {
        List<BigInteger> seminarGroupId = new ArrayList<>();
        seminarGroupId = seminarGroupMapper.listGroupIdByTopicId(topicId);
        SeminarGroup seminarGroup = new SeminarGroup();
        List<SeminarGroup> seminarGroups = new ArrayList<>();
        for (int i = 0; i < seminarGroupId.size(); i++) {
            seminarGroup = seminarGroupMapper.getSeminarGroupByGroupId(seminarGroupId.get(i));
            seminarGroups.add(seminarGroup);
        }
        return seminarGroups;
    }

    /**
     * for ali 小组按id选择话题.
     *
     * @param groupId 讨论组id
     * @param topicId 话题Id
     * @author YellowPure
     * @return 
     * @date 23:00 2017/12/22
     */
    @Override
    public BigInteger insertTopicByGroupId(BigInteger groupId, BigInteger topicId) throws IllegalArgumentException, GroupNotFoundException {
        if (seminarGroupMapper.getSeminarGroupById(groupId) == null) {
            throw new GroupNotFoundException("该小组不存在时抛出");
        }
        System.out.println(groupId.toString()+":"+topicId.toString());
        seminarGroupMapper.insertSeminarGroupTopicByTopicIdAndSeminarGroupId(topicId, groupId);
        return topicMapper.getSeminarGroupTopicById(topicId, groupId).getId();
    }


    /**
     * for ali 组长辞职.
     *
     * @param groupId 讨论组id
     * @param userId  用户Id
     * @author YellowPure
     * @date 23:02 2017/12/22
     */
    @Override
    public void resignLeaderById(BigInteger groupId, BigInteger userId) throws IllegalArgumentException, GroupNotFoundException {
        if (seminarGroupMapper.getSeminarGroupById(groupId) == null) {
            throw new GroupNotFoundException("GroupNotFoundException");
        }
        seminarGroupMapper.resignLeaderById(groupId, userId);
    }

	@Override
	public BigInteger insertSeminarGroupBySeminarId(BigInteger seminarId, BigInteger classId, SeminarGroup seminarGroup)
			throws IllegalArgumentException {
		Seminar seminar = new Seminar();
        seminar.setId(seminarId);
        seminarGroup.setSeminar(seminar);
        ClassInfo classInfo=new ClassInfo();
        classInfo.setId(classId);
        seminarGroup.setClassInfo(classInfo);
		seminarGroupMapper.insertSeminarGroupBySeminarId(seminarGroup);
		return null;
	}

	@Override
	public void deleteSeminarGroupMemberById(BigInteger seminarGroupId, BigInteger userId) {
		seminarGroupMapper.deleteSeminarGroupMemberByuId(seminarGroupId, userId);
	}
}
