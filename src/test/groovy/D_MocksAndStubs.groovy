import spock.lang.Specification

@SuppressWarnings("GroovyAssignabilityCheck")
class D_MocksAndStubs extends Specification {
    interface Data {
        int retrieve(String key)
    }

    class DataClass implements Data {
        int retrieve (String key) {
            return 0
        }
    }

    private static int checkData(Data myData, String key) { return myData.retrieve(key) }

    def "retrieve is called once"() {
        given:
        def myData = Mock(Data)

        when:
        checkData(myData, "keyString")

        then:
        1 * myData.retrieve(_)
        0 * _
    }

    def "checkData is working as expected"() {
        given:
        Data myData = Mock()
        myData.retrieve("keyString") >> { 3 }
        myData.retrieve("otherString") >> { 5 }

        expect:
        3 == checkData(myData, "keyString")
        5 == checkData(myData, "otherString")
    }

    def "in and out is working as expected"() {
        given:
        def myData = Mock(Data)
        1 * myData.retrieve("keyString") >> { 3 }
        1 * myData.retrieve("otherString") >> { 5 }

        expect:
        3 == checkData(myData, "keyString")
        5 == checkData(myData, "otherString")
    }

    def "retrieving multiple values"() {
        given:
        Data myData = Mock()
        5 * myData.retrieve(_) >>> [1, 2] >> { throw new InternalError() } \
                               >> { args -> args.size() } >> { String key -> key.length()}

        expect:
        1 == checkData(myData, "key 1")
        2 == checkData(myData, "key 2")

        when:
        checkData(myData, "key 3")

        then:
        thrown(InternalError)

        expect:
        1 == checkData(myData, "key 4")
        5 == checkData(myData, "key 5")
    }

    def "mocking the DataClass"() {
        given:
        DataClass dataClass = Mock()
        1 * dataClass.retrieve("keyString") >> 3
        1 * dataClass.retrieve("otherString") >> 5

        expect:
        3 == checkData(dataClass, "keyString")
        5 == checkData(dataClass, "otherString")
    }
}
