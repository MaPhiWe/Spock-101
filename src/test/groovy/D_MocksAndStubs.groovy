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

    def "mocking the DataClass"() {
        given:
        DataClass dataClass = Mock()
        1 * dataClass.retrieve("keyString") >> { 3 }
        1 * dataClass.retrieve("otherString") >> { 5 }

        expect:
        3 == checkData(dataClass, "keyString")
        5 == checkData(dataClass, "otherString")
    }
}
