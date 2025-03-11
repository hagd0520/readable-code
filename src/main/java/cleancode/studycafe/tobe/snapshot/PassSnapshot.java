package cleancode.studycafe.tobe.snapshot;

import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class PassSnapshot {

    private final StudyCafePassType studyCafePassType;
    private final List<StudyCafePass> studyCafePasses;

    private PassSnapshot(StudyCafePassType studyCafePassType, List<StudyCafePass> studyCafePasses) {
        this.studyCafePassType = studyCafePassType;
        this.studyCafePasses = filterStudyCafePassesByPassType(studyCafePassType, studyCafePasses);
    }

    public static PassSnapshot of(StudyCafePassType studyCafePassType, List<StudyCafePass> studyCafePasses) {
        return new PassSnapshot(studyCafePassType, studyCafePasses);
    }

    public boolean isFixed() {
        return studyCafePassType == StudyCafePassType.FIXED;
    }

    public List<StudyCafePass> getStudyCafePasses() {
        return studyCafePasses;
    }

    private List<StudyCafePass> filterStudyCafePassesByPassType(StudyCafePassType studyCafePassType, List<StudyCafePass> studyCafePasses) {
        return studyCafePasses.stream()
            .filter(studyCafePass -> studyCafePass.isPassTypeEqualTo(studyCafePassType))
            .toList();
    }
}
