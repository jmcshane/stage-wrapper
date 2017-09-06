import static org.junit.Assert.*

import spock.lang.*

class EchoTest extends Specification {
    @Shared GroovyShell shell
    @Shared StringWriter content
    @Shared Echo echo

    def setup() {
        content = new StringWriter()
        Binding binding = new Binding()
        binding.out = new PrintWriter(content)
        echo = Mock(Echo)
        binding.echo = echo
        shell = new GroovyShell(binding)
    }

    def "test that the echo function is called"() {
        when:
        shell.evaluate(new File("vars/doEcho.groovy"))
        then:
        1*echo.call("Hello")
    }

    static interface Echo {
      void call(Object... args)
    }
}
