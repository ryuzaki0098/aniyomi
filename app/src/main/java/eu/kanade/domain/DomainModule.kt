package eu.kanade.domain

import eu.kanade.data.anime.AnimeRepositoryImpl
import eu.kanade.data.animehistory.AnimeHistoryRepositoryImpl
import eu.kanade.data.animesource.AnimeSourceRepositoryImpl
import eu.kanade.data.animetrack.AnimeTrackRepositoryImpl
import eu.kanade.data.category.CategoryRepositoryImpl
import eu.kanade.data.category.CategoryRepositoryImplAnime
import eu.kanade.data.chapter.ChapterRepositoryImpl
import eu.kanade.data.episode.EpisodeRepositoryImpl
import eu.kanade.data.history.HistoryRepositoryImpl
import eu.kanade.data.manga.MangaRepositoryImpl
import eu.kanade.data.source.SourceRepositoryImpl
import eu.kanade.data.track.TrackRepositoryImpl
import eu.kanade.domain.anime.interactor.GetAnime
import eu.kanade.domain.anime.interactor.GetAnimeWithEpisodes
import eu.kanade.domain.anime.interactor.GetAnimelibAnime
import eu.kanade.domain.anime.interactor.GetDuplicateLibraryAnime
import eu.kanade.domain.anime.interactor.InsertAnime
import eu.kanade.domain.anime.interactor.SetAnimeEpisodeFlags
import eu.kanade.domain.anime.interactor.SetAnimeViewerFlags
import eu.kanade.domain.anime.interactor.UpdateAnime
import eu.kanade.domain.anime.repository.AnimeRepository
import eu.kanade.domain.animedownload.interactor.DeleteAnimeDownload
import eu.kanade.domain.animeextension.interactor.GetAnimeExtensionLanguages
import eu.kanade.domain.animeextension.interactor.GetAnimeExtensionSources
import eu.kanade.domain.animeextension.interactor.GetAnimeExtensionUpdates
import eu.kanade.domain.animeextension.interactor.GetAnimeExtensions
import eu.kanade.domain.animehistory.interactor.DeleteAnimeHistoryTable
import eu.kanade.domain.animehistory.interactor.GetAnimeHistory
import eu.kanade.domain.animehistory.interactor.GetNextEpisode
import eu.kanade.domain.animehistory.interactor.RemoveAnimeHistoryByAnimeId
import eu.kanade.domain.animehistory.interactor.RemoveAnimeHistoryById
import eu.kanade.domain.animehistory.interactor.UpsertAnimeHistory
import eu.kanade.domain.animehistory.repository.AnimeHistoryRepository
import eu.kanade.domain.animesource.interactor.GetAnimeSourceData
import eu.kanade.domain.animesource.interactor.GetAnimeSourcesWithFavoriteCount
import eu.kanade.domain.animesource.interactor.GetAnimeSourcesWithNonLibraryAnime
import eu.kanade.domain.animesource.interactor.GetEnabledAnimeSources
import eu.kanade.domain.animesource.interactor.GetLanguagesWithAnimeSources
import eu.kanade.domain.animesource.interactor.ToggleAnimeSource
import eu.kanade.domain.animesource.interactor.ToggleAnimeSourcePin
import eu.kanade.domain.animesource.interactor.UpsertAnimeSourceData
import eu.kanade.domain.animesource.repository.AnimeSourceRepository
import eu.kanade.domain.animetrack.interactor.DeleteAnimeTrack
import eu.kanade.domain.animetrack.interactor.GetAnimeTracks
import eu.kanade.domain.animetrack.interactor.InsertAnimeTrack
import eu.kanade.domain.animetrack.repository.AnimeTrackRepository
import eu.kanade.domain.category.interactor.DeleteCategory
import eu.kanade.domain.category.interactor.DeleteCategoryAnime
import eu.kanade.domain.category.interactor.GetCategories
import eu.kanade.domain.category.interactor.GetCategoriesAnime
import eu.kanade.domain.category.interactor.InsertCategory
import eu.kanade.domain.category.interactor.InsertCategoryAnime
import eu.kanade.domain.category.interactor.SetAnimeCategories
import eu.kanade.domain.category.interactor.SetMangaCategories
import eu.kanade.domain.category.interactor.UpdateCategory
import eu.kanade.domain.category.interactor.UpdateCategoryAnime
import eu.kanade.domain.category.repository.CategoryRepository
import eu.kanade.domain.category.repository.CategoryRepositoryAnime
import eu.kanade.domain.chapter.interactor.GetChapter
import eu.kanade.domain.chapter.interactor.GetChapterByMangaId
import eu.kanade.domain.chapter.interactor.SetReadStatus
import eu.kanade.domain.chapter.interactor.ShouldUpdateDbChapter
import eu.kanade.domain.chapter.interactor.SyncChaptersWithSource
import eu.kanade.domain.chapter.interactor.SyncChaptersWithTrackServiceTwoWay
import eu.kanade.domain.chapter.interactor.UpdateChapter
import eu.kanade.domain.chapter.repository.ChapterRepository
import eu.kanade.domain.download.interactor.DeleteDownload
import eu.kanade.domain.episode.interactor.GetEpisode
import eu.kanade.domain.episode.interactor.GetEpisodeByAnimeId
import eu.kanade.domain.episode.interactor.SetSeenStatus
import eu.kanade.domain.episode.interactor.ShouldUpdateDbEpisode
import eu.kanade.domain.episode.interactor.SyncEpisodesWithSource
import eu.kanade.domain.episode.interactor.SyncEpisodesWithTrackServiceTwoWay
import eu.kanade.domain.episode.interactor.UpdateEpisode
import eu.kanade.domain.episode.repository.EpisodeRepository
import eu.kanade.domain.extension.interactor.GetExtensionLanguages
import eu.kanade.domain.extension.interactor.GetExtensionSources
import eu.kanade.domain.extension.interactor.GetExtensionUpdates
import eu.kanade.domain.extension.interactor.GetExtensions
import eu.kanade.domain.history.interactor.DeleteHistoryTable
import eu.kanade.domain.history.interactor.GetHistory
import eu.kanade.domain.history.interactor.GetNextChapter
import eu.kanade.domain.history.interactor.RemoveHistoryById
import eu.kanade.domain.history.interactor.RemoveHistoryByMangaId
import eu.kanade.domain.history.interactor.UpsertHistory
import eu.kanade.domain.history.repository.HistoryRepository
import eu.kanade.domain.manga.interactor.GetDuplicateLibraryManga
import eu.kanade.domain.manga.interactor.GetFavorites
import eu.kanade.domain.manga.interactor.GetLibraryManga
import eu.kanade.domain.manga.interactor.GetManga
import eu.kanade.domain.manga.interactor.GetMangaWithChapters
import eu.kanade.domain.manga.interactor.InsertManga
import eu.kanade.domain.manga.interactor.ResetViewerFlags
import eu.kanade.domain.manga.interactor.SetMangaChapterFlags
import eu.kanade.domain.manga.interactor.SetMangaViewerFlags
import eu.kanade.domain.manga.interactor.UpdateManga
import eu.kanade.domain.manga.repository.MangaRepository
import eu.kanade.domain.source.interactor.GetEnabledSources
import eu.kanade.domain.source.interactor.GetLanguagesWithSources
import eu.kanade.domain.source.interactor.GetSourceData
import eu.kanade.domain.source.interactor.GetSourcesWithFavoriteCount
import eu.kanade.domain.source.interactor.GetSourcesWithNonLibraryManga
import eu.kanade.domain.source.interactor.SetMigrateSorting
import eu.kanade.domain.source.interactor.ToggleLanguage
import eu.kanade.domain.source.interactor.ToggleSource
import eu.kanade.domain.source.interactor.ToggleSourcePin
import eu.kanade.domain.source.interactor.UpsertSourceData
import eu.kanade.domain.source.repository.SourceRepository
import eu.kanade.domain.track.interactor.DeleteTrack
import eu.kanade.domain.track.interactor.GetTracks
import eu.kanade.domain.track.interactor.InsertTrack
import eu.kanade.domain.track.repository.TrackRepository
import uy.kohesive.injekt.api.InjektModule
import uy.kohesive.injekt.api.InjektRegistrar
import uy.kohesive.injekt.api.addFactory
import uy.kohesive.injekt.api.addSingletonFactory
import uy.kohesive.injekt.api.get
import eu.kanade.domain.anime.interactor.GetFavorites as GetFavoritesAnime
import eu.kanade.domain.anime.interactor.ResetViewerFlags as ResetViewerFlagsAnime

class DomainModule : InjektModule {

    override fun InjektRegistrar.registerInjectables() {
        addSingletonFactory<AnimeRepository> { AnimeRepositoryImpl(get()) }
        addFactory { GetDuplicateLibraryAnime(get()) }
        addFactory { GetFavoritesAnime(get()) }
        addFactory { GetAnimelibAnime(get()) }
        addFactory { GetAnimeWithEpisodes(get(), get()) }
        addFactory { GetAnime(get()) }
        addFactory { GetNextEpisode(get()) }
        addFactory { ResetViewerFlagsAnime(get()) }
        addFactory { SetAnimeEpisodeFlags(get()) }
        addFactory { SetAnimeViewerFlags(get()) }
        addFactory { InsertAnime(get()) }
        addFactory { UpdateAnime(get()) }
        addFactory { SetAnimeCategories(get()) }

        addSingletonFactory<EpisodeRepository> { EpisodeRepositoryImpl(get()) }
        addFactory { GetEpisode(get()) }
        addFactory { GetEpisodeByAnimeId(get()) }
        addFactory { UpdateEpisode(get()) }
        addFactory { SetSeenStatus(get(), get(), get(), get()) }
        addFactory { ShouldUpdateDbEpisode() }
        addFactory { SyncEpisodesWithSource(get(), get(), get(), get()) }
        addFactory { SyncEpisodesWithTrackServiceTwoWay(get(), get()) }

        addSingletonFactory<CategoryRepositoryAnime> { CategoryRepositoryImplAnime(get()) }
        addFactory { GetCategoriesAnime(get()) }
        addFactory { InsertCategoryAnime(get()) }
        addFactory { UpdateCategoryAnime(get()) }
        addFactory { DeleteCategoryAnime(get()) }

        addSingletonFactory<CategoryRepository> { CategoryRepositoryImpl(get()) }
        addFactory { GetCategories(get()) }
        addFactory { InsertCategory(get()) }
        addFactory { UpdateCategory(get()) }
        addFactory { DeleteCategory(get()) }

        addSingletonFactory<MangaRepository> { MangaRepositoryImpl(get()) }
        addFactory { GetDuplicateLibraryManga(get()) }
        addFactory { GetFavorites(get()) }
        addFactory { GetLibraryManga(get()) }
        addFactory { GetMangaWithChapters(get(), get()) }
        addFactory { GetManga(get()) }
        addFactory { GetNextChapter(get()) }
        addFactory { ResetViewerFlags(get()) }
        addFactory { SetMangaChapterFlags(get()) }
        addFactory { SetMangaViewerFlags(get()) }
        addFactory { InsertManga(get()) }
        addFactory { UpdateManga(get()) }
        addFactory { SetMangaCategories(get()) }

        addSingletonFactory<AnimeTrackRepository> { AnimeTrackRepositoryImpl(get()) }
        addFactory { DeleteAnimeTrack(get()) }
        addFactory { GetAnimeTracks(get()) }
        addFactory { InsertAnimeTrack(get()) }

        addSingletonFactory<TrackRepository> { TrackRepositoryImpl(get()) }
        addFactory { DeleteTrack(get()) }
        addFactory { GetTracks(get()) }
        addFactory { InsertTrack(get()) }

        addSingletonFactory<ChapterRepository> { ChapterRepositoryImpl(get()) }
        addFactory { GetChapter(get()) }
        addFactory { GetChapterByMangaId(get()) }
        addFactory { UpdateChapter(get()) }
        addFactory { SetReadStatus(get(), get(), get(), get()) }
        addFactory { ShouldUpdateDbChapter() }
        addFactory { SyncChaptersWithSource(get(), get(), get(), get()) }
        addFactory { SyncChaptersWithTrackServiceTwoWay(get(), get()) }

        addSingletonFactory<AnimeHistoryRepository> { AnimeHistoryRepositoryImpl(get()) }
        addFactory { DeleteAnimeHistoryTable(get()) }
        addFactory { GetAnimeHistory(get()) }
        addFactory { UpsertAnimeHistory(get()) }
        addFactory { RemoveAnimeHistoryById(get()) }
        addFactory { RemoveAnimeHistoryByAnimeId(get()) }

        addSingletonFactory<HistoryRepository> { HistoryRepositoryImpl(get()) }
        addFactory { DeleteHistoryTable(get()) }
        addFactory { GetHistory(get()) }
        addFactory { UpsertHistory(get()) }
        addFactory { RemoveHistoryById(get()) }
        addFactory { RemoveHistoryByMangaId(get()) }

        addSingletonFactory<AnimeSourceRepository> { AnimeSourceRepositoryImpl(get(), get()) }
        addFactory { GetEnabledAnimeSources(get(), get()) }
        addFactory { GetLanguagesWithAnimeSources(get(), get()) }
        addFactory { GetAnimeSourceData(get()) }
        addFactory { GetAnimeSourcesWithFavoriteCount(get(), get()) }
        addFactory { GetAnimeSourcesWithNonLibraryAnime(get()) }
        addFactory { ToggleAnimeSource(get()) }
        addFactory { ToggleAnimeSourcePin(get()) }
        addFactory { UpsertAnimeSourceData(get()) }

        addFactory { GetAnimeExtensions(get(), get()) }
        addFactory { GetAnimeExtensionSources(get()) }
        addFactory { GetAnimeExtensionUpdates(get(), get()) }
        addFactory { GetAnimeExtensionLanguages(get(), get()) }

        addFactory { DeleteDownload(get(), get()) }
        addFactory { DeleteAnimeDownload(get(), get()) }

        addFactory { GetExtensions(get(), get()) }
        addFactory { GetExtensionSources(get()) }
        addFactory { GetExtensionUpdates(get(), get()) }
        addFactory { GetExtensionLanguages(get(), get()) }

        addSingletonFactory<SourceRepository> { SourceRepositoryImpl(get(), get()) }
        addFactory { GetEnabledSources(get(), get()) }
        addFactory { GetLanguagesWithSources(get(), get()) }
        addFactory { GetSourceData(get()) }
        addFactory { GetSourcesWithFavoriteCount(get(), get()) }
        addFactory { GetSourcesWithNonLibraryManga(get()) }
        addFactory { SetMigrateSorting(get()) }
        addFactory { ToggleLanguage(get()) }
        addFactory { ToggleSource(get()) }
        addFactory { ToggleSourcePin(get()) }
        addFactory { UpsertSourceData(get()) }
    }
}
