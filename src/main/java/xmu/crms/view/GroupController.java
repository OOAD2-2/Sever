package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import xmu.crms.entity.*;
import xmu.crms.exception.GroupNotFoundException;
import xmu.crms.exception.InvalidOperationException;
import xmu.crms.exception.UserNotFoundException;
import xmu.crms.service.SeminarGroupService;
import xmu.crms.service.*;
import xmu.crms.view.vo.*;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LUWEIW
 */

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    SeminarGroupService seminarGroupService;
    @Autowired
    TopicService topicService;
    @Autowired
    UserService userService;
    @Autowired
    GradeService gradeService;

    @RequestMapping(value = "/{groupId}", method = GET)
    @ResponseBody
    public ResponseEntity getGourpById(@PathVariable("groupId") int groupId,
                                    @RequestParam(value = "embedTopics", required = false) Boolean embedTopics,
                                    @RequestParam(value = "embedGrade", required = false) Boolean embedGrade) {
        try {
            List<User> userList = seminarGroupService.listSeminarGroupMemberByGroupId(BigInteger.valueOf(groupId));
            SeminarGroup group = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
            //System.out.println(group);
            User groupLeader = group.getLeader();
            //System.out.println(groupLeader);
            StudentVO leader = new StudentVO(groupLeader.getId().intValue(), groupLeader.getName(), groupLeader.getNumber());
            List<StudentVO> members = new ArrayList<StudentVO>();
            for (User user : userList) {
                members.add(new StudentVO(user.getId().intValue(), user.getName(), user.getNumber()));
            }
            SeminarGroup seminarGroup = seminarGroupService.getSeminarGroupByGroupId(BigInteger.valueOf(groupId));
            if (embedTopics==null && embedGrade == null) {
                GroupMembersVO groupMembersVO = new GroupMembersVO(seminarGroup.getId().intValue(),
                        seminarGroup.getId().toString(), leader, members, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupMembersVO);
            }
            else if (embedTopics && embedGrade) {
                List<SeminarGroupTopic> seminarGroupTopicList = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
                List<TopicGroupVO> topicGroupVOList = new ArrayList<TopicGroupVO>();
                for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopicList) {
                    topicGroupVOList.add(new TopicGroupVO(seminarGroupTopic.getTopic().getId().intValue(), seminarGroupTopic.getTopic().getName()));
                }
                List<PresentationGradeVO> presentationGradeVOList = new ArrayList<PresentationGradeVO>();
                for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopicList) {
                    presentationGradeVOList.add(new PresentationGradeVO(seminarGroupTopic.getTopic().getId().intValue(), seminarGroup.getPresentationGrade()));
                }
                GroupGradeVO groupGradeVO = new GroupGradeVO(presentationGradeVOList, seminarGroup);
                GroupMemberTopicGradeVO groupMemberTopicGradeVO = new GroupMemberTopicGradeVO(seminarGroup.getId().intValue(), seminarGroup.getId().toString(),
                        leader, members, topicGroupVOList, groupGradeVO, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupMemberTopicGradeVO);
            } else if (embedTopics && !embedGrade) {
                GroupByIdVO groupByIdVO = new GroupByIdVO(seminarGroup);
                List<SeminarGroupTopic> seminarGroupTopicList = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
                List<TopicGroupVO> topicGroupVOList = new ArrayList<TopicGroupVO>();
                for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopicList) {
                    topicGroupVOList.add(new TopicGroupVO(seminarGroupTopic.getId().intValue(), seminarGroupTopic.getTopic().getName()));
                }
                GroupDetailsVO groupDetailsVO = new GroupDetailsVO(groupByIdVO, leader, members, topicGroupVOList, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupDetailsVO);
            } else if (!embedTopics && embedGrade) {
                List<PresentationGradeVO> presentationGradeVOList = new ArrayList<PresentationGradeVO>();
                List<SeminarGroupTopic> seminarGroupTopicList = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
                for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopicList) {
                    presentationGradeVOList.add(new PresentationGradeVO(seminarGroupTopic.getId().intValue(), seminarGroup.getPresentationGrade()));
                }
                GroupGradeVO groupGradeVO = new GroupGradeVO(presentationGradeVOList, seminarGroup);
                GroupMemberGradeVO groupMemberGradeVO = new GroupMemberGradeVO(seminarGroup.getId().intValue(), seminarGroup.getId().toString(),
                        leader, members, groupGradeVO, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupMemberGradeVO);
            } else {
                GroupMembersVO groupMembersVO = new GroupMembersVO(seminarGroup.getId().intValue(),
                        seminarGroup.getId().toString(), leader, members, "");
                return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON_UTF8).body(groupMembersVO);
            }
        } catch (GroupNotFoundException e) {
            e.printStackTrace();;
            return ResponseEntity.status(404).build();
        }
    }

    @RequestMapping(value = "/{groupId}/resign", method = PUT)
    public Response resignGroupLeader(@PathVariable("groupId") int groupId,
                                    IdVO id,
                                    HttpServletResponse response) {
        try {
            User user = userService.getUserByUserId(BigInteger.valueOf(id.getId()));
            seminarGroupService.resignLeaderById(BigInteger.valueOf(groupId), user.getId());
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (InvalidOperationException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/assign", method = PUT)
    public Response assignGroupLeader(@PathVariable("groupId") int groupId,
                                      IdVO id, HttpServletResponse response) {
        try {
            User user = userService.getUserByUserId(BigInteger.valueOf(id.getId()));
            seminarGroupService.assignLeaderById(BigInteger.valueOf(groupId), user.getId());
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (InvalidOperationException e) {
            e.printStackTrace();
            response.setStatus(409);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/add", method = PUT)
    public Response addGroupMember(@PathVariable("groupId") int groupId,
                                      IdVO id, HttpServletResponse response) {
        try {
            User user = userService.getUserByUserId(BigInteger.valueOf(id.getId()));
            seminarGroupService.insertSeminarGroupMemberById(user.getId(), BigInteger.valueOf(groupId));
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        } catch (InvalidOperationException e) {
            e.printStackTrace();
            response.setStatus(409);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/remove", method = PUT)
    public Response removeGroupMember(@PathVariable("groupId") int groupId,
                                      IdVO id, HttpServletResponse response) {
        try {
            User user = userService.getUserByUserId(BigInteger.valueOf(id.getId()));
            seminarGroupService.deleteSeminarGroupMemberById(BigInteger.valueOf(groupId), user.getId());
            response.setStatus(204);
            return null;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/topic", method = POST)
    public UrlVO selectTopc(@PathVariable("groupId") int groupId,
                                   IdVO id, HttpServletResponse response) {
        try {
            seminarGroupService.insertTopicByGroupId(BigInteger.valueOf(groupId), BigInteger.valueOf(id.getId()));
            response.setStatus(204);
            String url = "/group/" + String.valueOf(groupId) + "/topic/" + String.valueOf(id.getId());
            UrlVO urlVO = new UrlVO(url);
            return urlVO;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/topic/{topicId}", method = DELETE)
    public Response deleteTopic(@PathVariable("groupId") int groupId,
                               @PathVariable("topicId") int topicId,
                               HttpServletResponse response) {
        try {
            topicService.deleteSeminarGroupTopicById(BigInteger.valueOf(groupId), BigInteger.valueOf(topicId));
            response.setStatus(204);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(400);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/grade", method = GET)
    @ResponseBody
    public GroupGradeVO getGradeByGroupId(@PathVariable("groupId") int groupId,
                                          HttpServletResponse response) {
        try {
            SeminarGroup seminarGroup = gradeService.getSeminarGroupBySeminarGroupId(BigInteger.valueOf(groupId));
            List<SeminarGroupTopic> seminarGroupTopicList = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(groupId));
            List<PresentationGradeVO> presentationGradeVOList = new ArrayList<PresentationGradeVO>();
            for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopicList) {
                presentationGradeVOList.add(new PresentationGradeVO(seminarGroupTopic.getTopic().getId().intValue(),seminarGroup.getPresentationGrade()));
            }
            GroupGradeVO groupGradeVO = new GroupGradeVO(presentationGradeVOList, seminarGroup);
            response.setStatus(200);
            return groupGradeVO;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/grade/report", method = PUT)
    public Response finalGradeByGroupId(@PathVariable("groupId") int groupId,
                                          ReportGradeVO reportGrade,
                                          HttpServletResponse response) {
        try {
            gradeService.updateGroupByGroupId(BigInteger.valueOf(groupId), BigInteger.valueOf(reportGrade.getReportGrade()));
            response.setStatus(204);
            return null;
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{groupId}/grade/presentation/{studentId}", method = PUT)
    public Response submitGradeByGroupId(@PathVariable("groupId") int groupId,
                                      @PathVariable("studentId") int studentId,
                                      PresentationsGradeVO presentationsGradeVO,
                                      HttpServletResponse response) {
        for (PresentationGradeVO presentationGradeVO : presentationsGradeVO.getPresentationGradeVOList()) {
            gradeService.insertGroupGradeByUserId(BigInteger.valueOf(presentationGradeVO.getTopicId()), BigInteger.valueOf(studentId),
                    BigInteger.valueOf(groupId), BigInteger.valueOf(presentationGradeVO.getGrade()));
        }
        response.setStatus(204);
        return null;
    }
}
