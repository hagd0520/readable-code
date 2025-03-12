package cleancode.studycafe.tobe_with_guidance;

import cleancode.studycafe.tobe_with_guidance.exception.AppException;
import cleancode.studycafe.tobe_with_guidance.io.InputHandler;
import cleancode.studycafe.tobe_with_guidance.io.OutputHandler;
import cleancode.studycafe.tobe_with_guidance.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe_with_guidance.io.StudyCafeIOHandler;
import cleancode.studycafe.tobe_with_guidance.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe_with_guidance.model.StudyCafePass;
import cleancode.studycafe.tobe_with_guidance.model.StudyCafePassType;

import java.util.List;
import java.util.Optional;

public class StudyCafePassMachine {

    private final StudyCafeIOHandler ioHandler = new StudyCafeIOHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            ioHandler.showWelcomeMessage();
            ioHandler.showAnnouncement();

            StudyCafePass selectedPass = selectPass();

            Optional<StudyCafeLockerPass> optionalLockerPass = selectLockerPass(selectedPass);

            optionalLockerPass.ifPresentOrElse(
                lockerPass -> ioHandler.showPassOrderSummary(selectedPass, lockerPass),
                () -> ioHandler.showPassOrderSummary(selectedPass)
            );
        } catch (AppException e) {
            ioHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            ioHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private StudyCafePass selectPass() {
        StudyCafePassType passType = ioHandler.askPassTypeSelecting();
        List<StudyCafePass> passCandidates = findPassCandidatesBy(passType);

        return ioHandler.askPassSelecting(passCandidates);
    }

    private List<StudyCafePass> findPassCandidatesBy(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> allPasses = studyCafeFileHandler.readStudyCafePasses();
        return allPasses.stream()
            .filter(studyCafePass -> studyCafePass.isSamePassType(studyCafePassType))
            .toList();
    }

    private Optional<StudyCafeLockerPass> selectLockerPass(StudyCafePass selectedPass) {
        // 고정 좌석 타입이 아닌가?
        // 사물함 옵션을 사용할 수 있는 타입이 아닌가?
        if (selectedPass.cannotUseLocker()) {
            return Optional.empty();
        }
        StudyCafeLockerPass lockerPassCandidate = findLockerPassCandidateBy(selectedPass);

        if (lockerPassCandidate != null) {
            boolean isLockerSelected = ioHandler.getLockerSelection(lockerPassCandidate);
            if (isLockerSelected) {
                return Optional.of(lockerPassCandidate);
            }
        }
        return Optional.empty();
    }

    private StudyCafeLockerPass findLockerPassCandidateBy(StudyCafePass pass) {
        List<StudyCafeLockerPass> allLockerPasses = studyCafeFileHandler.readLockerPasses();

        return allLockerPasses.stream()
            .filter(pass::isSameDurationType)
            .findFirst()
            .orElse(null);
    }
}
