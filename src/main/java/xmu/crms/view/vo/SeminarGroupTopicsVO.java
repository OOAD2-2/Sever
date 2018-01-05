package xmu.crms.view.vo;

import xmu.crms.entity.SeminarGroup;
import xmu.crms.entity.Topic;

import java.util.ArrayList;
import java.util.List;

public class SeminarGroupTopicsVO {
    public int id;
    public String name;
    public List<SeminarTopicVO> seminarTopicVOList;

    public SeminarGroupTopicsVO() {
        seminarTopicVOList = new ArrayList<SeminarTopicVO>();
    }

    public SeminarGroupTopicsVO(SeminarGroup seminarGroup, List<Topic> topicList) {
        seminarTopicVOList = new ArrayList<SeminarTopicVO>();
        this.id = seminarGroup.getId().intValue();
        this.name = seminarGroup.getName();
        for (Topic topic : topicList) {
            SeminarTopicVO seminarTopicVO = new SeminarTopicVO(topic.getId().intValue(), topic.getName());
            System.out.println(seminarTopicVO);
            seminarTopicVOList.add(seminarTopicVO);
        }
    }

    public SeminarGroupTopicsVO(int id, String name, List<SeminarTopicVO> seminarTopicVOList) {
        this.id = id;
        this.name = name;
        this.seminarTopicVOList = seminarTopicVOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SeminarTopicVO> getSeminarTopicVOList() {
        return seminarTopicVOList;
    }

    public void setSeminarTopicVOList(List<SeminarTopicVO> seminarTopicVOList) {
        this.seminarTopicVOList = seminarTopicVOList;
    }
}
