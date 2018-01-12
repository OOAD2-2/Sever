package xmu.crms.view;

import static org.springframework.web.bind.annotation.RequestMethod.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xmu.crms.entity.*;
import xmu.crms.service.*;
import xmu.crms.exception.TopicNotFoundException;
import xmu.crms.service.impl.SeminarGroupServiceImpl;
import xmu.crms.service.impl.TopicServiceImpl;
import xmu.crms.view.vo.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LUWEIW
 */

@Controller
@RequestMapping("/topic")
@PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
public class TopicController {

    @Autowired
    TopicService topicService;
    @Autowired
    SeminarGroupService seminarGroupService;

    @RequestMapping(value = "/{topicId}", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public TopicByIdVO getTopicById(@PathVariable("topicId") int topicId, HttpServletResponse response) {
        try{
            Topic topic = topicService.getTopicByTopicId(BigInteger.valueOf(topicId));
            List<SeminarGroup> seminarGroups = seminarGroupService.listGroupByTopicId(BigInteger.valueOf(topicId));
            TopicByIdVO topicByIdVO = new TopicByIdVO(topic, topic.getGroupNumberLimit()-seminarGroups.size());
            response.setStatus(200);
            return topicByIdVO;
        } catch(TopicNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{topicId}", method = PUT)
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public Response updateTopicById(@PathVariable("topicId") int topicId,
                                    @RequestBody TopicUpdateVO topicUpdateVO,
                                    HttpServletResponse response) {
        try {
            Topic topic = topicService.getTopicByTopicId(BigInteger.valueOf(topicId));
            topic.setSerial(topicUpdateVO.getSerial());
            topic.setName(topicUpdateVO.getName());
            topic.setDescription(topicUpdateVO.getDescription());
            topic.setGroupNumberLimit(topicUpdateVO.getGroupLimit());
            topic.setGroupStudentLimit(topicUpdateVO.getGroupMemberLimit());
            topicService.updateTopicByTopicId(BigInteger.valueOf(topicId), topic);
            response.setStatus(204);
            return null;
        } catch (TopicNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{topicId}", method = DELETE)
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public Response deleteTopicById(@PathVariable("topicId") int topicId,
                                    HttpServletResponse response) {
        try {
            topicService.deleteTopicByTopicId(BigInteger.valueOf(topicId));
            response.setStatus(204);
            return null;
        } catch (TopicNotFoundException e) {
            e.printStackTrace();
            response.setStatus(404);
            return null;
        }
    }

    @RequestMapping(value = "/{topicId}/group", method = GET)
    @ResponseBody @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public List<TopicGroupVO> getGroupsByTopicId(@PathVariable("topicId") int topicId, HttpServletResponse response) {
        List<SeminarGroupTopic> seminarGroupTopicList = topicService.listSeminarGroupTopicByGroupId(BigInteger.valueOf(topicId));

        List<TopicGroupVO> topicGroupVOS = new ArrayList<TopicGroupVO>();
        for (SeminarGroupTopic seminarGroupTopic : seminarGroupTopicList) {
            topicGroupVOS.add(new TopicGroupVO(seminarGroupTopic.getSeminarGroup().getId().intValue(), seminarGroupTopic.getSeminarGroup().getId().toString()));
        }
        response.setStatus(200);
        return topicGroupVOS;
    }
}
