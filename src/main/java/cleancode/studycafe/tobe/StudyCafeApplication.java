package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.io.impl.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.impl.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.impl.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafeConfig studyCafeConfig = new StudyCafeConfig(
            new ConsoleInputHandler(),
            new ConsoleOutputHandler(),
            new StudyCafeFileHandler()
        );

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafeConfig);
        studyCafePassMachine.run();
    }

}
