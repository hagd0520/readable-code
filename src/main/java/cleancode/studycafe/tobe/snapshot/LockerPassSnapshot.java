package cleancode.studycafe.tobe.snapshot;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;

import java.util.List;

public class LockerPassSnapshot {

    private final List<StudyCafeLockerPass> studyCafeLockerPasses;

    private LockerPassSnapshot(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        this.studyCafeLockerPasses = studyCafeLockerPasses;
    }

    public static LockerPassSnapshot of(List<StudyCafeLockerPass> studyCafeLockerPasses) {
        return new LockerPassSnapshot(studyCafeLockerPasses);
    }

    public StudyCafeLockerPass findStudyCafeLockerPass(StudyCafePass selectedPass) {
        return studyCafeLockerPasses.stream()
            .filter(studyCafeLockerPass ->
                studyCafeLockerPass.isPassTypeEqualTo(selectedPass)
                    && studyCafeLockerPass.isDurationEqualTo(selectedPass)
            )
            .findFirst()
            .orElse(null);
    }
}
