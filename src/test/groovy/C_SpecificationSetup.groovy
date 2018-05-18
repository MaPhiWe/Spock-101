

import spock.lang.Shared
import spock.lang.Specification

class C_SpecificationSetup extends Specification {

    int counter = 1

    void "counter is two"() {
        when: "the counter is increased"
        ++counter

        then: "it should be two"
        counter == 2
    }

    void "counter is three"() {
        when: "the counter is increased again"
        ++counter

        then: "it should be three#"
        counter == 3
    }
}
