def callFunc(name, closure) {
    def start = System.currentTimeMillis();
    def e = null
    try {
        closure()
    } catch (Exception ex) {
        e = ex
    }finally {
        def end = System.currentTimeMillis()
        println "${name} took ${end - start} millis"
        if (e != null) {
            echo "${name} failed!"
        }
        def response = httpRequest [url: 'http://google.com', timeout: 2000]
    }
}

return this
