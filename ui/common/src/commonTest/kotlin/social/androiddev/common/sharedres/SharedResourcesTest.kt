package social.androiddev.common.sharedres

import dev.icerock.moko.graphics.Color
import kotlin.test.assertEquals
import kotlin.test.Test

/**
 * These tests aren't ideal, but they show that there's some file access going on
 */
class SharedResourcesTest {

    @Test
    fun `getLocalisedHello returns a stringresource with unknown content`() {

        // This test only confirms that the resource is there. To get the content, it must run on androidTest, desktopTest
        assertEquals(
            "ResourceStringDesc(stringRes=StringResource(resourceId=2132017188))",
            SharedResources.getLocalisedHello().toString()
        )

    }

    @Test
    fun `getting empty image still returns a fallback image`() {
        assertEquals(
            "dev.icerock.moko.resources.ImageResource@821330f",
            SharedResources.getImageByFileName("").toString()
        )
    }

    @Test
    fun `getValueColor returns a specific Color class`() {
        assertEquals(
            Color(red=176, green=39, blue=67, alpha=255),
            SharedResources.getValueColor()
        )
    }
}