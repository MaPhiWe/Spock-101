import spock.lang.Specification

abstract class ParentSpecification extends Specification {
    void setupSpec() {
        println "Parent Setup Spec"
    }

    void setup () {
        println "Parent Setup"
    }

    void cleanup() {
        println "Parent Cleanup"
    }

    void cleanupSpec() {
        println "Parent Cleanup Spec"
    }
}

class I_Inheritance extends ParentSpecification {
    void setupSpec() {
        println "Child Setup Spec"
    }

    void setup () {
        println "Child Setup"
    }

    void cleanup() {
        println "Child Cleanup"
    }

    void cleanupSpec() {
        println "Child Cleanup Spec"
    }

    def dummyTest() {
        expect:
        true
    }

}
