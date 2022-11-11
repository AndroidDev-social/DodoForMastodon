package social.androiddev.common.sharedres

import dev.icerock.moko.graphics.Color
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.getImageByFileName
import social.androiddev.common.MR

object SharedResources {

    /**
     * A String that can be localised.
     * On Android, call `toString(context)`, on iOS, call `localized()`
     *
     * @see https://github.com/icerockdev/moko-resources#example-1---simple-localization-string
     */
    fun getLocalisedHello(): StringDesc {

        return StringDesc.Resource(MR.strings.my_string)
    }

    /**
     * Get an image resource by the file name, and falls back to mastodon logo if it fails
     *
     * @see https://github.com/icerockdev/moko-resources#example-7---pass-image
     */
    fun getImageByFileName(name: String): ImageResource {
        val fallbackImage = MR.images.mastodon_logo
        return MR.images.getImageByFileName(name) ?: fallbackImage
    }

    /**
     * Android: val color: Color = MR.colors.valueColor.getColor(context = this)
     *
     * iOS:val color: UIColor = MR.colors.valueColor.getColor(UIScreen.main.traitCollection.userInterfaceStyle)
     *
     * @see https://github.com/icerockdev/moko-resources#example-9---pass-colors
     */
    fun getValueColor(): Color {
        return MR.colors.valueColor.color
    }



}