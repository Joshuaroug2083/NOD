package dev.nod.protocol

import dev.nod.protocol.v1.Hello
import dev.nod.protocol.v1.NodEnvelope
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class EnvelopeTest {
    @Test
    fun helloRoundTripsThroughGeneratedLiteSchema() {
        val envelope = hello()
        val decoded = NodEnvelope.parseFrom(envelope.toByteArray())

        assertEquals(envelope, decoded)
        assertEquals(NodEnvelope.BodyCase.HELLO, decoded.bodyCase)
        assertEquals(listOf(1), decoded.hello.supportedVersionsList)
    }

    @Test
    fun preservesUnknownAdditiveField() {
        // Field 100, wire type varint, value 1: a future additive envelope field.
        val futureBytes = hello().toByteArray() + byteArrayOf(0xA0.toByte(), 0x06, 0x01)
        val decoded = NodEnvelope.parseFrom(futureBytes)

        assertEquals(hello().hello, decoded.hello)
        assertArrayEquals(futureBytes, decoded.toByteArray())
    }

    private fun hello(): NodEnvelope = NodEnvelope.newBuilder()
        .setProtocolVersion(1)
        .setSessionId("00000000-0000-4000-8000-000000000001")
        .setMessageId("00000000-0000-4000-8000-000000000002")
        .setHello(Hello.newBuilder().addSupportedVersions(1))
        .build()
}
