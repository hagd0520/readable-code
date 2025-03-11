package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.config.StudyCafeConfig;
import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.FileHandler;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;
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
        PassSnapshot passProcessor = PassSnapshot.of(studyCafePassType, fileHandler);

        if (passProcessor.isHourly()) {
            List<StudyCafePass> hourlyPasses = passProcessor.getStudyCafePasses();
            outputHandler.showPassListForSelection(hourlyPasses);
            StudyCafePass selectedPass = inputHandler.getSelectPass(hourlyPasses);
            outputHandler.showPassOrderSummary(selectedPass, null);
        }

        if (passProcessor.isWeekly()) {
            List<StudyCafePass> weeklyPasses = passProcessor.getStudyCafePasses();
            outputHandler.showPassListForSelection(weeklyPasses);
            StudyCafePass selectedPass = inputHandler.getSelectPass(weeklyPasses);
            outputHandler.showPassOrderSummary(selectedPass, null);
        }

        if (passProcessor.isFixed()) {
            List<StudyCafePass> fixedPasses = passProcessor.getStudyCafePasses();
            outputHandler.showPassListForSelection(fixedPasses);
            StudyCafePass selectedPass = inputHandler.getSelectPass(fixedPasses);

            List<StudyCafeLockerPass> lockerPasses = fileHandler.readLockerPasses();
            StudyCafeLockerPass lockerPass = lockerPasses.stream()
                .filter(option ->
                    option.getPassType() == selectedPass.getPassType()
                        && option.getDuration() == selectedPass.getDuration()
                )
                .findFirst()
                .orElse(null);

            boolean lockerSelection = false;
            if (lockerPass != null) {
                outputHandler.askLockerPass(lockerPass);
                lockerSelection = inputHandler.getLockerSelection();
            }

            if (lockerSelection) {
                outputHandler.showPassOrderSummary(selectedPass, lockerPass);
            } else {
                outputHandler.showPassOrderSummary(selectedPass, null);
            }
        }
    }

}
