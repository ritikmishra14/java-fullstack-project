package com.voting.voting_app.request;

public class Vote {
    private Long pollId;
    private int optionIndex;

    public Vote(){

    }

    public Vote(Long pollId, int optionIndex) {
        this.pollId = pollId;
        this.optionIndex = optionIndex;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(int optionIndex) {
        this.optionIndex = optionIndex;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "pollId=" + pollId +
                ", optionIndex=" + optionIndex +
                '}';
    }
}
