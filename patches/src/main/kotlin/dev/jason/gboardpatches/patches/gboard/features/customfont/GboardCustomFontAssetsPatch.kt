package dev.jason.gboardpatches.patches.gboard.features.customfont

import app.morphe.patcher.patch.ResourcePatchContext
import app.morphe.patcher.patch.resourcePatch
import dev.jason.gboardpatches.patches.shared.Constants.COMPATIBILITY_GBOARD

internal val gboardCustomFontAssetsPatch = resourcePatch(
    description = "Copy the bundled custom font into the target Gboard APK.",
) {
    compatibleWith(COMPATIBILITY_GBOARD)

    finalize {
        copyCustomFontAsset()
    }
}

context(context: ResourcePatchContext)
private fun copyCustomFontAsset() = with(context) {
    val loader = object {}.javaClass.classLoader
    val bytes = loader
        .getResourceAsStream("custom-font-assets/CustomFont.ttf")
        ?.use { it.readBytes() }
        ?: error("Custom font not found: custom-font-assets/CustomFont.ttf")

    val target = this["assets/gboard-custom-fonts/CustomFont.ttf", false]
    target.parentFile?.mkdirs()
    target.outputStream().use { it.write(bytes) }
}
