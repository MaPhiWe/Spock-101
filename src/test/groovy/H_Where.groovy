import spock.lang.Specification
import spock.lang.Unroll

class H_Where extends Specification {

    void "number is even"() {
        expect:
        dataElements % 2 == 0

        where:
        dataElements << [2, 4, 6, 8]

    }

    void "String is of correct length"() {
        expect:
        myString.length() == myLength

        where:
        myString << ["Hello", "World"]
        myLength << stringLength()
    }

    private static int[] stringLength() {
        return [5, 5]
    }

    void "Addition works as expected"() {
        expect:
        a + b == c

        where:
        a | b | c
        1 | 1 | 2
        4 | 5 | 9
    }

    @Unroll
    void "#a + #b equals #c"() {
        expect:
        a + b == c

        where:
        a | b
        1 | 1
        4 | 5

        c = a.plus(b)
    }
}
