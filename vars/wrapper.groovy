def call(config, body) {
    def start = System.currentTimeMillis();
    def e = null
    try {
        stage(config.name) {
            body()
        }
    } catch (Exception ex) {
        e = ex
        throw ex
    }finally {
        def end = System.currentTimeMillis()
        println "${config.name} took ${end - start} millis"
        if (e != null) {
            echo "${config.name} failed!"
        }
        def elapsed = end - start
        def stageMap = [jobName: env.JOB_NAME, jobNumber: env.BUILD_NUMBER,
                        elapsedTime: elapsed, success: (e == null)]
        def response = httpRequest(url: 'http://elasticsearch-sonar-scan.192.168.0.118.nip.io/jenkinsstages/stage', httpMode: 'POST',
                                   requestBody: groovy.json.JsonOutput.toJson(stageMap))
        println("Elasticsearch content: "+response.content)
    }
}
