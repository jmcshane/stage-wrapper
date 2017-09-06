class WrapperTest extends BasePipelineTest {

    def "test that the echo function is called"() {
        given:
        def binding = initializeMocks("echo", "stage", "httpRequest")
        setEnv(binding, ["JOB_NAME": "my-job", "BUILD_NUMBER": "12"])
        when:
        def wrapper = getShell(binding).evaluate(new File("vars/wrapper.groovy"))
        wrapper([name: "jobStage"]) {
            println "hi"
        }
        then:
        1*binding.httpRequest.call(*_) >> [content : "received"]
        binding.sysout.toString().contains("Elasticsearch content: received")
        1*binding.stage.call('jobStage', _)
    }
}
