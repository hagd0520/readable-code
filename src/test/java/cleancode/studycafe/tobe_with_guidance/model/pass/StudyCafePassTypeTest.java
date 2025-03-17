package cleancode.studycafe.tobe_with_guidance.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafePassTypeTest {
    @DisplayName("시간 단위 이용권은 라커 타입이 아니다.")
    @Test
    void HourlyPassTypeIsNotLockerType() {
        // given
        StudyCafePassType passType = StudyCafePassType.HOURLY;

        // when
        boolean isLockerType = passType.isLockerType();
        boolean isNotLockerType = passType.isNotLockerType();

        // then
        assertThat(isLockerType).isFalse();
        assertThat(isNotLockerType).isTrue();
    }

    @DisplayName("주 단위 이용권은 라커 타입이 아니다.")
    @Test
    void WeeklyPassTypeIsNotLockerType() {
        // given
        StudyCafePassType passType = StudyCafePassType.WEEKLY;

        // when
        boolean isLockerType = passType.isLockerType();
        boolean isNotLockerType = passType.isNotLockerType();

        // then
        assertThat(isLockerType).isFalse();
        assertThat(isNotLockerType).isTrue();
    }

    @DisplayName("1인 고정석은 라커 타입이다.")
    @Test
    void FixedPassTypeIsLockerType() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;

        // when
        boolean result1 = passType.isLockerType();
        boolean result2 = passType.isNotLockerType();

        // then
        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
    }
}
