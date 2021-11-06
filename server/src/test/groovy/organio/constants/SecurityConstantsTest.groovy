package organio.constants

import spock.lang.Specification

import static organio.constants.Constants.UNAUTHORIZED_ACCESS_FORMAT

class SecurityConstantsTest extends Specification {
    def 'should throw AssertionError on new Constants event creations'() {
        when:
        new SecurityConstants()

        then:
        def exception = thrown(AssertionError)
        exception.message == String.format(UNAUTHORIZED_ACCESS_FORMAT, 'organio.constants.SecurityConstants')
    }
}
