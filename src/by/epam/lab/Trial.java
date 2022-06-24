package by.epam.lab;

import static by.epam.lab.services.GlobalConstants.*;

public class Trial {
    private String account;
    private int mark1;
    private int mark2;
    protected final static int PASS_MARK = 120;

    public Trial() {
        this("", 0, 0);
    }

    public Trial(String account, int mark1, int mark2) {
        this.account = account;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public Trial(Trial trial) {
        this(trial.account, trial.mark1, trial.mark2);
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public final int result(){
        return mark1 + mark2;
    }

    public boolean isPassed() {
        return result() >= PASS_MARK;
    }

    public Trial getCopy() {
        return new Trial(this);
    }

    public void clearMarks() {
        mark1 = 0;
        mark2 = 0;
    }

    public String fieldToString() {
        return getClass().getSimpleName() + SEMICOLON
                + account + SEMICOLON + mark1 + SEMICOLON + mark2;
    }

    @Override
    public String toString() {
        return fieldToString() + SEMICOLON + isPassed();
    }
}
