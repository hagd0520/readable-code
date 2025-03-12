package cleancode.studycafe.tobe_with_guidance;

import cleancode.studycafe.tobe_with_guidance.io.provider.LockerPassFileReader;
import cleancode.studycafe.tobe_with_guidance.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe_with_guidance.provider.LockerPassProvider;
import cleancode.studycafe.tobe_with_guidance.provider.SeatPassProvider;

public class StudyCafeApplication {

    public static void main(String[] args) {
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        LockerPassProvider lockerPassProvider = new LockerPassFileReader();

        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
            seatPassProvider, lockerPassProvider
        );
        studyCafePassMachine.run();
    }

}
