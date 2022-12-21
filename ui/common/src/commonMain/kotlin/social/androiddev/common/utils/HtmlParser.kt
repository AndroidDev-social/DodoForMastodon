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
    if (newlineReplace.contains("<p>")) {
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
        if(links.isNullOrEmpty()) return buildAnnotatedString {
            append(plainText)
        }
        return buildAnnotatedString {
            //everything before first link
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
   return  buildAnnotatedString {  }
}


fun AnnotatedString.Builder.appendLink(linkText: String, linkUrl: String) {
    pushStringAnnotation(tag = linkUrl, annotation = linkUrl)
    append(linkText)
    pop()
}

fun AnnotatedString.onLinkClick(offset: Int, onClick: (String) -> Unit) {
    getStringAnnotations(start = offset, end = offset).firstOrNull()?.let {
        onClick(it.item)
    }
}