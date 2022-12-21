/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import it.skrape.core.htmlDocument
import it.skrape.selects.eachLink
import it.skrape.selects.eachText
import it.skrape.selects.html5.a
import it.skrape.selects.html5.body

/**
 * Takes a String reciever of html text
 * converts it to an annotated string of text and links
 */
fun String.renderHtml(): AnnotatedString {
    val newlineReplace = this.replace("<br>", "\n")

    if (newlineReplace.contains("<p>").not()) return buildAnnotatedString { }

    val content = htmlDocument(newlineReplace)

    val body = content.body {
        findAll {
            eachText
        }
    }
    val paragraph = body
    val links = try {
        content.a { findAll { eachLink } }
    } catch (exception: Exception) {
        emptyMap()
    }

    val plainText = paragraph.joinToString("\n")
    if (links.isNullOrEmpty()) return buildAnnotatedString {
        append(plainText)
    }
    return buildAnnotatedString {
        // everything before first link
        append(plainText.substringBefore(links.keys.first()))

        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            appendLink(links.keys.first(), links.values.first())
        }
        val restOfLinks = links.entries.drop(1)
        var restOfPlainText = plainText.substringAfter(links.keys.first())
        restOfLinks.forEach {
            val (key, value) = it
            val segment = restOfPlainText.substringBefore(key)
            append(segment)
            withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                appendLink(key, value)
            }
            restOfPlainText = restOfPlainText.substringAfter(key)
        }
        append(restOfPlainText)
    }
}

private fun AnnotatedString.Builder.appendLink(linkText: String, linkUrl: String) {
    pushStringAnnotation(tag = linkUrl, annotation = linkUrl)
    append(linkText)
    pop()
}

private fun AnnotatedString.onLinkClick(offset: Int, onClick: (String) -> Unit) {
    getStringAnnotations(start = offset, end = offset).firstOrNull()?.let {
        onClick(it.item)
    }
}
