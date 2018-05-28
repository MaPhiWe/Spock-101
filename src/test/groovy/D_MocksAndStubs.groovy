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

    private static int doubleData(Data myData, String key) {
        return 2 * myData.retrieve(key)
    }

    def "retrieve is called once"() {
        given:
        def myData = Mock(Data)

        when:
        doubleData(myData, "keyString")

        then:
        1 * myData.retrieve(_)
        0 * _
    }

    def "checkData is working as expected"() {
        given:
        Data myData = Mock()
        myData.retrieve("keyString") >> 3
        myData.retrieve("otherString") >> 5

        expect:
        6 == doubleData(myData, "keyString")
        10 == doubleData(myData, "otherString")
    }

    def "in and out is working as expected"() {
        given:
        def myData = Mock(Data)
        1 * myData.retrieve("keyString") >> 3
        1 * myData.retrieve("otherString") >> 5

        expect:
        6 == doubleData(myData, "keyString")
        10 == doubleData(myData, "otherString")
    }

    def "retrieving multiple values"() {
        given:
        Data myData = Mock()
        5 * myData.retrieve(_) >>> [1, 2] >> { throw new InternalError() } \
                               >> { args -> args.size() } >> { String key -> key.length()}

        expect:
        2 == doubleData(myData, "key 1")
        4 == doubleData(myData, "key 2")

        when:
        doubleData(myData, "key 3")

        then:
        thrown(InternalError)

        expect:
        2 == doubleData(myData, "key 4")
        10 == doubleData(myData, "key 5")
    }

    def "mocking the DataClass"() {
        given:
        DataClass dataClass = Mock()
        1 * dataClass.retrieve("keyString") >> 3
        1 * dataClass.retrieve("otherString") >> 5

        expect:
        6 == doubleData(dataClass, "keyString")
        10 == doubleData(dataClass, "otherString")
    }

    def "mocking DataClass again"() {
        given:
        DataClass dataClass = Mock()

        when:
        doubleData(dataClass, "keyString")

        then:
        1 * dataClass.retrieve("keyString")
        0 * _
    }}
