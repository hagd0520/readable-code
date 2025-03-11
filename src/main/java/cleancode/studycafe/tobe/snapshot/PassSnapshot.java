package cleancode.studycafe.tobe.snapshot;

import cleancode.studycafe.tobe.io.FileHandler;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class PassSnapshot {

    private final FileHandler fileHandler;
    private final StudyCafePassType studyCafePassType;
    private final List<StudyCafePass> studyCafePasses;

    private PassSnapshot(StudyCafePassType studyCafePassType, FileHandler fileHandler) {
        this.studyCafePassType = studyCafePassType;
        this.fileHandler = fileHandler;
        this.studyCafePasses = initializeStudyCafePasses(studyCafePassType, fileHandler);
    }

    public static PassSnapshot of(StudyCafePassType studyCafePassType, FileHandler fileHandler) {
        return new PassSnapshot(studyCafePassType, fileHandler);
    }

    public boolean isHourly() {
        return studyCafePassType == StudyCafePassType.HOURLY;
    }

    public boolean isWeekly() {
        return studyCafePassType == StudyCafePassType.WEEKLY;
    }

    public boolean isFixed() {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    public List<StudyCafePass> getStudyCafePasses() {
        return studyCafePasses;
    }

    private List<StudyCafePass> initializeStudyCafePasses(StudyCafePassType studyCafePassType, FileHandler fileHandler) {
        List<StudyCafePass> studyCafePasses = fileHandler.readStudyCafePasses();
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.isPassTypeEqualTo(studyCafePassType))
            .toList();
    }
}
