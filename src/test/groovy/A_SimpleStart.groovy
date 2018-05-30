

import spock.lang.Specification

class A_SimpleStart extends Specification {

    void "One plus Two is Three"() {
        expect:
        1+2 == 3
    }

    void "The += operator concatenates two Strings"() {

        given: "the first half of the best-known String in IT"
        String myString = "Hello"

        when: "the second half is concatenated to it"
        myString += " World"

        then: "the result is the sum of both parts"
        myString == "Hello World"
        myString.length() == 11
    }

    class Database {
        int dbsize = 0
        void insert(def content) { ++dbsize}
        int size() { dbsize }
        void close() { dbsize = 0 }

    }

    void "Inserting increases the DB size by one"() {
        setup: "Initialising the database"
        Database db = new Database()

        when:
        db.insert("Hello World")

        then:
        db.size() == 1

        cleanup:
        db.close()
    }
}
