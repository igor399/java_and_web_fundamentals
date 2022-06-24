package by.epam.lab;

import static by.epam.lab.services.GlobalConstants.*;

public class StrongTrial extends Trial {
    public StrongTrial() {
        super();
    }

    public StrongTrial(String account, int mark1, int mark2) {
        super(account, mark1, mark2);
    }

    public StrongTrial(StrongTrial strongTrial) {
        this(strongTrial.getAccount(),
                strongTrial.getMark1(),
                strongTrial.getMark2());
    }

    @Override
    public Trial getCopy() {
        return new StrongTrial(this);
    }

    @Override
    public boolean isPassed() {
        return getMark1() / DIVIDER + getMark2() >= PASS_MARK;
    }
}
