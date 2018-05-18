

import spock.lang.Specification

class B_Asserts extends Specification {

    void "the two lists are equal"() {
        given: "two lists that are not really equal"
        List firstList = ["a", "b", "c"]
        List secondList = ["a", "b", "d"]

        expect: "a failure, but on purpose"
        firstList == secondList
    }

    void "an exception is thrown"() {
        when:
        throwsException()

        then:
        thrown(IllegalArgumentException)
    }

    private static void throwsException() {
        throw new IllegalArgumentException()

    }
}
