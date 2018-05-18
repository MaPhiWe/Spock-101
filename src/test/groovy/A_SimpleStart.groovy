

import spock.lang.Specification

class A_SimpleStart extends Specification {

    void "One plus Two is Three"() {
        expect:
        1+2 == 3
    }
}
