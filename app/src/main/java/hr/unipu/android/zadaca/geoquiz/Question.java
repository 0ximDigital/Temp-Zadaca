package hr.unipu.android.zadaca.geoquiz;

public class Question {

    private final int textStringRes;
    private final boolean expectedAnswer;

    private Boolean answer = null;

    public Question(int textStringRes, boolean expectedAnswer) {
        this.textStringRes = textStringRes;
        this.expectedAnswer = expectedAnswer;
    }

    public int getTextStringRes() {
        return textStringRes;
    }

    public boolean expectedAnswer() {
        return expectedAnswer;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(final Boolean answer) {
        this.answer = answer;
    }
}
