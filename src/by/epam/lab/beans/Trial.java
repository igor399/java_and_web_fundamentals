package by.epam.lab.beans;

import static by.epam.lab.services.GlobalConstants.*;

public class Trial {
    private String account;
    private int mark1;
    private int mark2;

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

    public Trial(String[] param) {
        this(param[ACCOUNT_IND],
                Integer.parseInt(param[MARK1_IND]),
                Integer.parseInt(param[MARK2_IND]));
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

    public boolean isPassed() {
        return mark1 + mark2 >= PASS_MARK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trial trial = (Trial) o;
        return mark1 == trial.mark1 && mark2 == trial.mark2 && account.equals(trial.account);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + SEMICOLON
                + account + SEMICOLON + mark1 + SEMICOLON
                + mark2 + SEMICOLON + isPassed();
    }
}
