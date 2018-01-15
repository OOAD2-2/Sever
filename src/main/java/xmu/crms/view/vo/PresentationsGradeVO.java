package xmu.crms.view.vo;

import java.util.List;

public class PresentationsGradeVO {

    /**
     * @author: LUWEIW
     */

    private List<PresentationGradeVO> presentationGradeVOList;

    public List<PresentationGradeVO> getPresentationGradeVOList() {
        return presentationGradeVOList;
    }

    public void setPresentationGradeVOList(List<PresentationGradeVO> presentationGradeVOList) {
        this.presentationGradeVOList = presentationGradeVOList;
    }

    @Override
    public String toString() {
        return "PresentationsGradeVO{" +
                "presentationGradeVOList=" + presentationGradeVOList +
                '}';
    }
}
