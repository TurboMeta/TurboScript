package com.github.turboscript.graphics.ogl.buffer

import org.lwjgl.opengl.GL46.*

/**
 * Persistent & Coherent Mapped Buffer
 * Automatically flushes the buffer when it reaches 3/4 of its size.
 */
class GlPersistentMappedBuffer(
    size: Long,
    private val stride: Int,
): GlBuffer(size, GL_MAP_PERSISTENT_BIT or GL_MAP_COHERENT_BIT) {

    var offset = 0L

    fun end() {
        offset = (arr.pos / stride)
    }

    private var sync = 0L

    fun waitAndSync() {
        // Check if the draw call is complete and reset the Buffer
        if (sync == 0L) {
            if (arr.pos >= arr.len / 4 * 3) {   // 3/4 of the buffer is full
                sync = glFenceSync(GL_SYNC_GPU_COMMANDS_COMPLETE, 0)
            }
        } else if (
            IntArray(1).apply {
                glGetSynciv(sync, GL_SYNC_STATUS, IntArray(1), this)
            }[0] == GL_SIGNALED
        ) {
            glDeleteSync(sync)
            sync = 0L
            arr.pos = 0L
            offset = 0
        }
    }

}