package com.github.turboscript.graphics.turboRender.cmd

import com.github.turboscript.graphics.turboRender.TRIObject
import com.github.turboscript.graphics.turboRender.buffer.TRIBuffer
import com.github.turboscript.graphics.turboRender.shader.TRIPipeline

interface TRIRenderPass: TRIObject {

    fun bindPipeline(pipeline: TRIPipeline)

    fun bindDescriptorSets(descriptorSets: List<TRIDescriptorSet>)

    fun bindVertexBuffer(buffer: TRIBuffer)

    fun bindIndexBuffer(buffer: TRIBuffer)

    fun draw(
        vertexCount: Int,
        instanceCount: Int = 1,
        firstVertex: Int = 0,
        firstInstance: Int = 0
    )

    fun drawIndexed(
        indexCount: Int,
        instanceCount: Int = 1,
        firstIndex: Int = 0,
        vertexOffset: Int = 0,
        firstInstance: Int = 0
    )

}