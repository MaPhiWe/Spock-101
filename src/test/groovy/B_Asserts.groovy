

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

    void "checks in closures don't auto-assert"() {
        given:
        Map myMap = [a:1, b:2]
        expect:
        for (i in myMap.keySet()) {
            myMap.i == 1
        }
        myMap.with {
            a == 2
            b == 2
        }
    }
}
