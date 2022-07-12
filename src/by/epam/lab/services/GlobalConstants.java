package by.epam.lab.services;

import by.epam.lab.beans.Trial;

public class GlobalConstants {
    public final static String SEMICOLON = ";";
    public final static String PUT = "\n  put> %s";
    public final static String GOT = "\nGOT> %s";
    public final static String FILE_PATH = "resources/trials.csv";
    public final static String NO_FILE = "File not found";
    public final static String THREAD_ERROR = "Thread error: %s";
    public final static int PASS_MARK = 120;
    public final static int ACCOUNT_IND = 0;
    public final static int MARK1_IND = 1;
    public final static int MARK2_IND = 2;
    public final static Trial FAKE_TRIAL = new Trial();
}
