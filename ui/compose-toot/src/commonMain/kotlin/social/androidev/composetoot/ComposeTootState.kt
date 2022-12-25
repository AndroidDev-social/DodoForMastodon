/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androidev.composetoot

data class ComposeTootState(
    val content: Content,
    val currentUser: CurrentUser,
    val selectedAction: Action? = null,
    val tootTextCounter: String,
    val postTootEnabled: Boolean
)

data class CurrentUser(
    val displayName: String,
    val avatarUrl: String
)

data class Content(
    val toot: String,
    val warning: String?
)

enum class Action {
    AddMention,
    AddHashtag,
    ChooseLanguage,
    AddMedia,
    ChangeVisibility,
    AddContentWarning,
    AddEmoji,
    Schedule
}
