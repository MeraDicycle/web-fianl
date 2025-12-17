import { createRouter, createWebHistory } from 'vue-router'
import DefaultLayout from '../layouts/DefaultLayout.vue'
import Search from '../views/Search.vue'
import ExploreMusic from '../views/ExploreMusic.vue'
import MyMusic from '../views/MyMusic.vue'

const routes = [
    // {
    //     path: '/test',
    //     component: () => import("../components/AppHeader.vue")
    // },
    {
        path: '/',
        component: DefaultLayout,
        redirect: '/explore-music',
        children: [
            { path: 'search-music', component: Search },
            { path: 'explore-music', component: ExploreMusic },
            { path: 'my-music', component: MyMusic }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router