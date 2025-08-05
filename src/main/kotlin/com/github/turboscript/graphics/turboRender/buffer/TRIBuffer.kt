package com.github.turboscript.graphics.turboRender.buffer

import com.github.turboscript.graphics.turboRender.TRIResource
import dev.luna5ama.kmogus.MutableArr

interface TRIBuffer: TRIResource {

    fun getMappedArr(): MutableArr

}