import { createRouter, createWebHistory } from 'vue-router'
import DefaultLayout from '../layouts/DefaultLayout.vue'
import Search from '../views/Search.vue'
import ExploreMusic from '../views/ExploreMusic.vue'
import MyMusic from '../views/MyMusic.vue'
import ExploreLayout from '../layouts/ExploreLayout.vue'
import ExploreHome from '../views/explore/ExploreHome.vue'
import ExplorePlaylist from '../views/explore/ExplorePlaylist.vue'
import ExploreRank from '../views/explore/ExploreRank.vue'
import ExplorePlaylistDetail from '../views/explore/ExplorePlaylistDetail.vue'
import SongDetail from '../views/explore/songDetail.vue'
import MyPlaylist from '../views/MyPlaylist.vue'

const routes = [
    {
        path: '/',
        component: DefaultLayout,
        redirect: '/explore-music',
        children: [
            { path: 'search-music', component: Search },
            { path: 'explore-music', component: ExploreLayout,  redirect: '/explore-music/home',
                children: [
                    {path: 'home', component: ExploreHome},
                    {path: 'playlist', component: ExplorePlaylist},
                    {path: 'playlist/:id', component: ExplorePlaylistDetail},
                    {path: 'rank', component: ExploreRank},
                    {path: 'song-detail/:id', component: SongDetail},
                ]
            },
            { path: 'my-music', component: MyMusic },
            { path: 'my-playlist/:id', component: MyPlaylist },
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router