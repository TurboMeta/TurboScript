package com.github.turboscript.graphics.ogl.buffer

import com.github.turboscript.graphics.ogl.OpenGlState
import com.github.turboscript.graphics.turboRender.buffer.TRIBuffer
import dev.luna5ama.kmogus.Arr
import dev.luna5ama.kmogus.MutableArr
import dev.luna5ama.kmogus.asMutable
import org.lwjgl.opengl.GL46.*
import java.nio.ByteBuffer

open class GlBuffer(
    size: Long,
    extraBits: Int = 0,
    mapBits: Int = GL_READ_WRITE,
): TRIBuffer {

    val id: Int = glCreateBuffers()

    private var lastTarget: Int? = null
    protected val arr: MutableArr

    override fun getMappedArr(): MutableArr {
        return arr
    }

    init {
        glNamedBufferStorage(id, size, GL_MAP_READ_BIT or extraBits)
        arr = Arr.wrap(glMapNamedBufferRange(
            id, 0L, size, mapBits
        ) as ByteBuffer).asMutable()
    }

    fun bind(target: Int) {
        lastTarget = target
        OpenGlState.bindBuffer(target, id)
    }

    fun unbind() {
        lastTarget?.let {
            OpenGlState.bindBuffer(it, 0)
            lastTarget = null
        }
    }

}