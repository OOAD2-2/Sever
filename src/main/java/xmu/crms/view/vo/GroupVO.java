package xmu.crms.view.vo;

import java.util.ArrayList;
import java.util.List;

public class GroupVO {
    private int id;
    private String name;
    private List<TopicVO> topicVOList;

    public GroupVO() {
        topicVOList = new ArrayList<TopicVO>();
    }

    public GroupVO(int id, String name, List<TopicVO> topicVOList) {
        this.id = id;
        this.name = name;
        this.topicVOList = topicVOList;
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

    public List<TopicVO> getTopicVOList() {
        return topicVOList;
    }

    public void setTopicVOList(List<TopicVO> topicVOList) {
        this.topicVOList = topicVOList;
    }
}
