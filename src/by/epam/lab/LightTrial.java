package by.epam.lab;

public class LightTrial extends Trial {
    private final static int PASS_FIRST_MARK = 60;
    private final static int PASS_SECOND_MARK = 70;

    public LightTrial() {
        super();
    }

    public LightTrial(String account, int mark1, int mark2) {
        super(account, mark1, mark2);
    }

    public LightTrial(LightTrial lightTrial) {
        this(lightTrial.getAccount(), lightTrial.getMark1(), lightTrial.getMark2());
    }

    @Override
    public Trial copyTrials() {
        return new LightTrial(this);
    }

    @Override
    public boolean isPassed() {
        return getMark1() >= PASS_FIRST_MARK && getMark2() >= PASS_SECOND_MARK;
    }
}
