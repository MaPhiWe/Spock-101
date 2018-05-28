

import spock.lang.Specification

class E_Old extends Specification {

    void "increment increases value by one"() {
        given:
        int i = 0

        when:
        ++i

        then:
        i == old(i) + 1
    }
}
