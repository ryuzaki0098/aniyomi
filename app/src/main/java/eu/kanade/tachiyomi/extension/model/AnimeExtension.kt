package eu.kanade.tachiyomi.extension.model

import android.graphics.drawable.Drawable
import eu.kanade.domain.animesource.model.AnimeSourceData
import eu.kanade.tachiyomi.animesource.AnimeSource

sealed class AnimeExtension {

    abstract val name: String
    abstract val pkgName: String
    abstract val versionName: String
    abstract val versionCode: Long
    abstract val lang: String?
    abstract val isNsfw: Boolean
    abstract val hasReadme: Boolean
    abstract val hasChangelog: Boolean

    data class Installed(
        override val name: String,
        override val pkgName: String,
        override val versionName: String,
        override val versionCode: Long,
        override val lang: String,
        override val isNsfw: Boolean,
        override val hasReadme: Boolean,
        override val hasChangelog: Boolean,
        val pkgFactory: String?,
        val sources: List<AnimeSource>,
        val icon: Drawable?,
        val hasUpdate: Boolean = false,
        val isObsolete: Boolean = false,
        val isUnofficial: Boolean = false,
    ) : AnimeExtension()

    data class Available(
        override val name: String,
        override val pkgName: String,
        override val versionName: String,
        override val versionCode: Long,
        override val lang: String,
        override val isNsfw: Boolean,
        override val hasReadme: Boolean,
        override val hasChangelog: Boolean,
        val apkName: String,
        val iconUrl: String,
    ) : AnimeExtension()

    data class Untrusted(
        override val name: String,
        override val pkgName: String,
        override val versionName: String,
        override val versionCode: Long,
        val signatureHash: String,
        override val lang: String? = null,
        override val isNsfw: Boolean = false,
        override val hasReadme: Boolean = false,
        override val hasChangelog: Boolean = false,
    ) : AnimeExtension()
}

data class AvailableAnimeSources(
    val id: Long,
    val lang: String,
    val name: String,
    val baseUrl: String,
) {
    fun toAnimeSourceData(): AnimeSourceData {
        return AnimeSourceData(
            id = this.id,
            lang = this.lang,
            name = this.name,
        )
    }
}
