package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.FileHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
import cleancode.studycafe.tobe.snapshot.LockerPassSnapshot;
import cleancode.studycafe.tobe.snapshot.PassSnapshot;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final FileHandler fileHandler;

    public StudyCafePassMachine(StudyCafeConfig studyCafeConfig) {
        this.inputHandler = studyCafeConfig.getInputHandler();
        this.outputHandler = studyCafeConfig.getOutputHandler();
        this.fileHandler = studyCafeConfig.getFileHandler();
    }

    public void run() {
        try {
            outputHandler.showWelcomeMessage();
            outputHandler.showAnnouncement();

            outputHandler.askPassTypeSelection();
            StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

            chooseOptionByPassType(studyCafePassType);
            
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    private void chooseOptionByPassType(StudyCafePassType studyCafePassType) {
        List<StudyCafePass> studyCafePasses = fileHandler.readStudyCafePasses();
        PassSnapshot passSnapshot = PassSnapshot.of(studyCafePassType, studyCafePasses);

        List<StudyCafePass> filteredPasses = passSnapshot.getStudyCafePasses();
        outputHandler.showPassListForSelection(filteredPasses);

        StudyCafePass selectedPass = inputHandler.getSelectPass(filteredPasses);
        outputHandler.showPassOrderSummary(selectedPass, null);

        if (passSnapshot.isFixed()) {
            List<StudyCafeLockerPass> lockerPasses = fileHandler.readLockerPasses();
            LockerPassSnapshot lockerPassSnapshot = LockerPassSnapshot.of(lockerPasses);
            StudyCafeLockerPass lockerPass = lockerPassSnapshot.findStudyCafeLockerPass(selectedPass);

            outputHandler.askLockerPass(lockerPass);
            if (inputHandler.getLockerSelection()) {
                outputHandler.showPassOrderSummary(selectedPass, lockerPass);
                return;
            }
        }

        outputHandler.showPassOrderSummary(selectedPass, null);
    }

}
