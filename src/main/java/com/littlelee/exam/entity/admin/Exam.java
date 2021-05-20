package com.littlelee.exam.entity.admin;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 考试信息实体
 * @author littlelee
 * @date 2020/3/9 0:09
 */
@Component
public class Exam {

    private Long id;
    private String name;//考试名称
    private Long subjectId;//所属学科专业ID
    private Date startTime;//考试开始时间
    private Date endTime;//考试结束时间
    private int availableTime;//考试所需时间
    private int questionNum;//试题总数
    private int totalScore;//试题总分
    private int passScore;//及格分数
    //单选、多选、判断题数量
    private int singleQuestionNum;
    private int muiltQuestionNum;
    private int chargeQuestionNum;
    private int paperNum;//试卷数量
    private int examedNum;//已考人数
    private int passNum;//及格人数
    private Date createTime;//添加时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            this.startTime = sdf.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            this.endTime = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getPassScore() {
        return passScore;
    }

    public void setPassScore(int passScore) {
        this.passScore = passScore;
    }

    public int getSingleQuestionNum() {
        return singleQuestionNum;
    }

    public void setSingleQuestionNum(int singleQuestionNum) {
        this.singleQuestionNum = singleQuestionNum;
    }

    public int getMuiltQuestionNum() {
        return muiltQuestionNum;
    }

    public void setMuiltQuestionNum(int muiltQuestionNum) {
        this.muiltQuestionNum = muiltQuestionNum;
    }

    public int getChargeQuestionNum() {
        return chargeQuestionNum;
    }

    public void setChargeQuestionNum(int chargeQuestionNum) {
        this.chargeQuestionNum = chargeQuestionNum;
    }

    public int getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(int paperNum) {
        this.paperNum = paperNum;
    }

    public int getExamedNum() {
        return examedNum;
    }

    public void setExamedNum(int examedNum) {
        this.examedNum = examedNum;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjectId=" + subjectId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", availableTime=" + availableTime +
                ", questionNum=" + questionNum +
                ", totalScore=" + totalScore +
                ", passScore=" + passScore +
                ", singleQuestionNum=" + singleQuestionNum +
                ", muiltQuestionNum=" + muiltQuestionNum +
                ", chargeQuestionNum=" + chargeQuestionNum +
                ", paperNum=" + paperNum +
                ", examedNum=" + examedNum +
                ", passNum=" + passNum +
                ", createTime=" + createTime +
                '}';
    }
}
