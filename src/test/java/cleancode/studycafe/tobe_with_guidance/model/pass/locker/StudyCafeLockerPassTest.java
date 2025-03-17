package cleancode.studycafe.tobe_with_guidance.model.pass.locker;

import cleancode.studycafe.tobe_with_guidance.model.pass.StudyCafePassType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeLockerPassTest {
    @DisplayName("인스턴스 생성 시 주입했던 passType, duration, price을 그대로 반환한다.")
    @Test
    void get() {
        // given
        StudyCafePassType passType1 = StudyCafePassType.HOURLY;
        int duration1 = 2;
        int price1 = 6500;

        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(passType1, duration1, price1);

        // when
        StudyCafePassType passType2 = studyCafeLockerPass.getPassType();
        int duration2 = studyCafeLockerPass.getDuration();
        int price2 = studyCafeLockerPass.getPrice();

        // then
        assertThat(passType1).isEqualTo(passType2);
        assertThat(duration1).isEqualTo(duration2);
        assertThat(price1).isEqualTo(price2);
    }
}
