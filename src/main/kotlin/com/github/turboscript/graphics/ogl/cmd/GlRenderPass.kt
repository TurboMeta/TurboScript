package com.github.turboscript.graphics.ogl.cmd

import com.github.turboscript.graphics.ogl.OpenGlState
import com.github.turboscript.graphics.ogl.shader.GlPipeline
import com.github.turboscript.graphics.turboRender.buffer.TRIBuffer
import com.github.turboscript.graphics.turboRender.cmd.TRIDescriptorSet
import com.github.turboscript.graphics.turboRender.cmd.TRIDescriptorSetLayout
import com.github.turboscript.graphics.turboRender.cmd.TRIRenderPass
import com.github.turboscript.graphics.turboRender.shader.TRIPipeline
import org.lwjgl.opengl.GL46.*

class GlRenderPass: TRIRenderPass {

    private var pipeline: GlPipeline? = null

    override fun bindPipeline(pipeline: TRIPipeline) {
        this.pipeline = pipeline as GlPipeline
    }

    override fun bindDescriptorSets(descriptorSets: List<TRIDescriptorSet>) {
        descriptorSets.forEachIndexed { index, set ->
            set.getLayouts().forEach { layout -> bindSetLayout(index, layout) }
        }
    }

    override fun bindVertexBuffer(buffer: TRIBuffer) {
        OpenGlState.bindBuffer(GL_ARRAY_BUFFER, buffer.getId())
    }

    override fun bindIndexBuffer(buffer: TRIBuffer) {
        OpenGlState.bindBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer.getId())
    }

    override fun draw(
        vertexCount: Int,
        instanceCount: Int,
        firstVertex: Int,
        firstInstance: Int
    ) {
        pipeline?.let {
            it.bindStates()

            if (instanceCount == 1) {
                glDrawArrays(it.primitiveGl, firstVertex, vertexCount)
            } else {
                glDrawArraysInstanced(it.primitiveGl, firstVertex, vertexCount, instanceCount)
            }
        }
    }

    override fun drawIndexed(
        indexCount: Int,
        instanceCount: Int,
        firstIndex: Int,
        vertexOffset: Int,
        firstInstance: Int
    ) {
        pipeline?.let {
            it.bindStates()

            if (instanceCount == 1) {
                glDrawElements(it.primitiveGl, indexCount, GL_UNSIGNED_INT,
                    firstIndex.toLong() * 4)
            } else {
                glDrawElementsInstanced(it.primitiveGl, indexCount, GL_UNSIGNED_INT,
                    firstIndex.toLong() * 4, instanceCount)
            }
        }
    }

    private fun bindSetLayout(setIndex: Int, layout: TRIDescriptorSetLayout) {
        when (layout.type) {
            TRIDescriptorSetLayout.LayoutType.SAMPLER -> {
                OpenGlState.bindTexture(layout.binding, layout.target.getId())
            }
            TRIDescriptorSetLayout.LayoutType.UNIFORM_BUFFER -> {
                glBindBufferBase(GL_UNIFORM_BUFFER, layout.binding, layout.target.getId())
            }
            TRIDescriptorSetLayout.LayoutType.STORAGE_BUFFER -> {
                glBindBufferBase(GL_SHADER_STORAGE_BUFFER,
                    layout.binding, layout.target.getId())
            }
        }
    }

}