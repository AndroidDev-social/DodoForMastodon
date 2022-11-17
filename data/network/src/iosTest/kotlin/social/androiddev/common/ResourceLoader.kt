/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common

import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSBundle
import platform.Foundation.NSData
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.dataWithContentsOfFile
import platform.posix.memcpy

actual fun readBinaryResource(resourcePath: String): ByteArray {
    val path = resourcePath.substringBeforeLast(".")
    val fileType = resourcePath.substringAfterLast(".")
    println("resourcePath=$resourcePath, path=$path, fileType=$fileType")

    val absolutePath = NSBundle.mainBundle.pathForResource(path, fileType)
    println("absolutePath=$absolutePath")

    return NSData.dataWithContentsOfFile(absolutePath!!, NSUTF8StringEncoding, null)!!.toByteArray()
}

internal fun NSData.toByteArray(): ByteArray {
    return ByteArray(length.toInt()).apply {
        usePinned {
            memcpy(it.addressOf(0), bytes, length)
        }
    }
}
