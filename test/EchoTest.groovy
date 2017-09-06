class EchoTest extends BasePipelineTest {

    def "test that the echo function is called"() {
        given:
        def binding = initializeMocks("echo")
        when:
        getShell(binding).evaluate(new File("vars/doEcho.groovy"))
        then:
        1*binding.echo.call(["Hello"])
    }
}
