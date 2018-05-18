import groovy.transform.TupleConstructor
import spock.lang.Specification

class G_With extends Specification {
    def "using with to easily access methods"() {
        given:
        String myString

        when:
        myString = "Hello World"

        then:
        with(myString) {
            length() == 11
            contains("World")
        }
    }

    @TupleConstructor
    class Data { String name; int value }

    def "using with to easily access members"() {
        given:
        //noinspection GroovyAssignabilityCheck
        Data myData = new Data("Groovy", 5)

        expect:
        with(myData) {
            name == "Groovy"
            value == 5
        }
    }
}
