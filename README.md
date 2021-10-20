# junit-parallel-broken-when-using-selenium

This project has a parallel test using two threads, note the:

tasks.withType(Test).configureEach {
    systemProperties = [
            'junit.jupiter.execution.parallel.enabled'                 : 'true',
            'junit.jupiter.execution.parallel.config.strategy'         : 'fixed',
            'junit.jupiter.execution.parallel.config.fixed.parallelism': '2',
    ]
}

The test suite is composed of 8 simple tests that are very simple... basically they sleep for two seconds. So the test should take at least 8 seconds.

If a selenium driver is open (or any attempt), the junit throttling is broken and the tests fail.
