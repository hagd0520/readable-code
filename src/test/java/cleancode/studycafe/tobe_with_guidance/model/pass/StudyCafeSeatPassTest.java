package cleancode.studycafe.tobe_with_guidance.model.pass;

import cleancode.studycafe.tobe_with_guidance.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {
    @DisplayName("인스턴스 생성 시 주입했던 passType, duration, price을 그대로 반환한다.")
    @Test
    void get() {
        // given
        StudyCafePassType passType1 = StudyCafePassType.HOURLY;
        int duration1 = 2;
        int price1 = 6500;
        double discountRate1 = 0.0;

        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType1, duration1, price1, discountRate1);

        // when
        StudyCafePassType passType2 = studyCafeSeatPass.getPassType();
        int duration2 = studyCafeSeatPass.getDuration();
        int price2 = studyCafeSeatPass.getPrice();

        // then
        assertThat(passType1).isEqualTo(passType2);
        assertThat(duration1).isEqualTo(duration2);
        assertThat(price1).isEqualTo(price2);
    }

    @DisplayName("이용권 타입과 이용 기간이 좌석 이용권과 같은 라커 이용권일 경우 true를 반환한다.")
    @Test
    void isSameDurationType() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;
        int duration = 4;
        int price = 250000;
        double discountRate = 0.1;

        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType, duration, price, discountRate);
        StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(passType, duration, price);

        // when
        boolean isSameDurationType = studyCafeSeatPass.isSameDurationType(studyCafeLockerPass);

        // then
        assertThat(isSameDurationType).isTrue();
    }

    @DisplayName("이용권의 할인율이 적용된 가격을 반환한다.")
    @Test
    void test() {
        // given
        StudyCafePassType passType = StudyCafePassType.FIXED;
        int duration = 4;
        int price = 250000;
        double discountRate = 0.1;
        int expectedDiscountPrice = (int) (price * discountRate);

        StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(passType, duration, price, discountRate);

        // when
        int discountPrice = studyCafeSeatPass.getDiscountPrice();

        // then
        assertThat(discountPrice).isEqualTo(expectedDiscountPrice);
    }
}
