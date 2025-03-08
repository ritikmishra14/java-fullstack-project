package com.voting.voting_app.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class OptionVote {

    private String voteOption;
    private Long voteCount = 0L;

    public OptionVote(){

    }
    public OptionVote(String option , Long voteCount){
        this.voteOption = option;
        this.voteCount = voteCount;
    }

    public String getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(String voteOption) {
        this.voteOption = voteOption;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "OptionVote{" +
                "option='" + voteOption + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
