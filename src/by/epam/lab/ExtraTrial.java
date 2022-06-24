package by.epam.lab;

import static by.epam.lab.services.GlobalConstants.*;

public class ExtraTrial extends Trial {
    private final static int PASS_THIRD_MARK = 75;
    private int mark3;

    public ExtraTrial() {
        this("", 0, 0, 0);
    }

    public ExtraTrial(String account, int mark1, int mark2, int mark3) {
        super(account, mark1, mark2);
        this.mark3 = mark3;
    }

    public ExtraTrial(ExtraTrial extraTrial) {
        this(extraTrial.getAccount(),
                extraTrial.getMark1(),
                extraTrial.getMark2(),
                extraTrial.mark3);
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && mark3 >= PASS_THIRD_MARK;
    }

    @Override
    public Trial getCopy() {
        return new ExtraTrial(this);
    }

    @Override
    public void clearMarks() {
        super.clearMarks();
        mark3 = 0;
    }

    @Override
    public String fieldToString() {
        return super.fieldToString() + SEMICOLON + mark3;
    }
}
