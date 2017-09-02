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
            println "${name} failed!"
        }
    }
}
