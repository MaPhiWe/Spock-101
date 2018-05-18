

import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class F_AutoCleanup extends Specification {

    void "First no-op test to demonstrate AutoCleanup"() {
        expect:
        true
    }

    void "Second no-op test to demonstrate AutoCleanup"() {
        expect:
        true
    }

    @AutoCleanup
    SomeResource myResource = new SomeResource()

    class SomeResource {
        void close() { // Called after each test
            println("close() has been called")
        }
    }

    @AutoCleanup("cleanup")
    @Shared
    OtherResource otherResource = new OtherResource()

    class OtherResource {
        void cleanup() { // Called after all tests
            println("cleanup() has been called")
        }
    }
}

