package xmu.crms.view.vo;

import java.util.Arrays;

/**
 * @author mads
 */
public class GroupTopicVO {

    private Integer id;
    private String name;
    private TopicVO[] topics;

    public GroupTopicVO() {
    }

    public GroupTopicVO(int id, String name, TopicVO[] topics) {
        this.id = id;
        this.name = name;
        this.topics = topics;
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

    public TopicVO[] getTopics() {
        return topics;
    }

    public void setTopics(TopicVO[] topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "GroupTopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", topics=" + Arrays.toString(topics) +
                '}';
    }
}
