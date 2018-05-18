import spock.lang.Specification
import spock.lang.Unroll

@SuppressWarnings("GroovyAssignabilityCheck")
class H_Where extends Specification {

    void "number is even"(int dataElements) {
        expect:
        dataElements % 2 == 0

        where:
        dataElements << [2, 4, 6, 8]

    }

    void "String is of correct length"(String myString, int myLength) {
        expect:
        myString.length() == myLength

        where:
        myString << ["Hello", "World"]
        myLength << stringLength()
    }

    private static int[] stringLength() {
        return [5, 5]
    }

    void "Addition works as expected"(int a, int b, int c) {
        expect:
        a + b == c

        where:
        a | b | c
        1 | 1 | 2
        4 | 5 | 9
    }

    @Unroll
    void "#a + #b equals #c"(int a, int b, Number c) {
        expect:
        a + b == c

        where:
        a | b
        1 | 1
        4 | 5

        //noinspection GroovyUnusedAssignment
        c = a.plus(b)
    }
}
