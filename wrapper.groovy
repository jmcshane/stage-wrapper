def callFunc(name, closure) {
    def start = System.currentTimeMillis();
    def e = null
    try {
        stage(name) {
            closure()
        }
    } catch (Exception ex) {
        e = ex
        throw ex
    }finally {
        def end = System.currentTimeMillis()
        println "${name} took ${end - start} millis"
        if (e != null) {
            echo "${name} failed!"
        }
        def stageMap = [jobName: env.JOB_NAME, jobNumber: env.BUILD_NUMBER,
                        elapsedTime:${end - start}, success: (e == null)]
        def response = httpRequest(url: 'http://elasticsearch-sonar-scan.192.168.0.118.nip.io/jenkinsstages/stage', httpMode: 'POST',
                                   requestBody: "${groovy.json.JsonOutput.toJson(stageMap)}")
        println("Elasticsearch content: "+response.content)
    }
}

return this
