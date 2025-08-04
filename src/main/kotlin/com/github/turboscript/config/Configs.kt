package com.github.turboscript.config

import net.neoforged.neoforge.common.ModConfigSpec
import org.apache.commons.lang3.tuple.Pair

class Configs(builder: ModConfigSpec.Builder) {

    companion object {
        private val pair: Pair<Configs, ModConfigSpec> =
            ModConfigSpec.Builder().configure { Configs(it) }

        internal fun get(): Configs = pair.left
        internal val spec: ModConfigSpec get() = pair.right
    }

    internal val renderApi: ModConfigSpec.EnumValue<RenderApi>

    init {
        builder
            .comment("Rendering Settings")
            .comment("Rendering settings for the TurboScript mod")
            .translation("turboscript.config.rendering")
            .push("rendering")

        renderApi = builder
            .comment("Render API")
            .comment("The rendering API to use for the TurboScript mod")
            .translation("turboscript.config.rendering.api")
            .defineEnum("api", RenderApi.OPENGL)

        builder.pop()
    }

}