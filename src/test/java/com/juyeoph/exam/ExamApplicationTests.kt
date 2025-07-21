package com.juyeoph.exam

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import java.net.ServerSocket

@SpringBootTest
class ExamApplicationTests {
    companion object {
        @DynamicPropertySource
        @JvmStatic
        fun setProperties(registry: DynamicPropertyRegistry) {
            // random port
            registry.add("gameserver.port", { ServerSocket(0).use { it.localPort }})
        }
    }

    @Test
    fun contextLoads() {
    }
}
