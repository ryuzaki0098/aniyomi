package eu.kanade.presentation.browse

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import eu.kanade.presentation.components.EmptyScreen
import eu.kanade.presentation.components.LoadingScreen
import eu.kanade.tachiyomi.R
import eu.kanade.tachiyomi.ui.browse.animeextension.AnimeExtensionFilterPresenter
import eu.kanade.tachiyomi.ui.browse.animeextension.ExtensionFilterState
import eu.kanade.tachiyomi.ui.browse.animeextension.FilterUiModel

@Composable
fun AnimeExtensionFilterScreen(
    nestedScrollInterop: NestedScrollConnection,
    presenter: AnimeExtensionFilterPresenter,
    onClickLang: (String) -> Unit,
) {
    val state by presenter.state.collectAsState()

    when (state) {
        is ExtensionFilterState.Loading -> LoadingScreen()
        is ExtensionFilterState.Error -> Text(text = (state as ExtensionFilterState.Error).error.message!!)
        is ExtensionFilterState.Success ->
            SourceFilterContent(
                nestedScrollInterop = nestedScrollInterop,
                items = (state as ExtensionFilterState.Success).models,
                onClickLang = onClickLang,
            )
    }
}

@Composable
fun SourceFilterContent(
    nestedScrollInterop: NestedScrollConnection,
    items: List<FilterUiModel>,
    onClickLang: (String) -> Unit,
) {
    if (items.isEmpty()) {
        EmptyScreen(textResource = R.string.empty_screen)
        return
    }

    LazyColumn(
        modifier = Modifier.nestedScroll(nestedScrollInterop),
        contentPadding = WindowInsets.navigationBars.asPaddingValues(),
    ) {
        items(
            items = items,
        ) { model ->
            ExtensionFilterItem(
                modifier = Modifier.animateItemPlacement(),
                lang = model.lang,
                enabled = model.enabled,
                onClickItem = onClickLang,
            )
        }
    }
}
