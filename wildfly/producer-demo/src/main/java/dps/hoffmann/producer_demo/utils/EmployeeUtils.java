package dps.hoffmann.producer_demo.utils;

import dps.hoffmann.producer_demo.model.Employee;

import java.util.Random;

public class EmployeeUtils {

    private static final Random RAND = new Random();
    private static final int MAX_SALARY = 100000;

    public static Employee createSampleEmployee() {
        return Employee.builder()
                .id(RAND.nextInt())
                .name(createRandomName())
                .salary(RAND.nextInt(MAX_SALARY))
                .build();
    }

    private enum FirstNames {
        JOHN, SAMUEL, IWAN, TIM, PAUL
    }

    private enum LastNames {
        SMITH, TAO, MCBRIDGE, KING
    }

    private static String createRandomName() {
        String fst = FirstNames.values()[RAND.nextInt(FirstNames.values().length)].name();
        String snd = LastNames.values()[RAND.nextInt(LastNames.values().length)].name();
        return fst + " " + snd;
    }

}
