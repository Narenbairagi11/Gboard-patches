package dev.jason.gboardpatches.patches.gboard.features.customfont

import dev.jason.gboardpatches.patches.gboard.shared.GboardSoftKeyFamilyFeature
import dev.jason.gboardpatches.patches.gboard.shared.gboardSoftKeyFamilyFeaturePatch

internal val gboardCustomFontSoftKeyPatch =
    gboardSoftKeyFamilyFeaturePatch(
        description = "Apply the custom font to Gboard SoftKey labels.",
        feature = GboardSoftKeyFamilyFeature.CUSTOM_FONT,
    )
