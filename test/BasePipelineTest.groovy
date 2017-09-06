import spock.lang.Shared
import spock.lang.Specification

class BasePipelineTest extends Specification {

    @Shared Map mocks = [:]

    def initializeMocks(String... args) {
        def binding = new Binding()
        for (def arg : args) {
            def mock = Mock(PipelineFunction)
            mocks << ["$arg" : mock]
            binding[arg] = mock
        }
        binding.sysout = new StringWriter()
        binding.out = new PrintWriter(binding.sysout)
        return binding
    }

    def setEnv(binding, map) {
        binding.env = [:]
        map.each { k,v ->
            binding.env[k] = v
        }
    }

    def getShell(binding) {
        return new GroovyShell(binding)
    }
}
