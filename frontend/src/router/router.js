import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '../pages/HomePage.vue'
import BlogPage from '../pages/BlogPage.vue'

Vue.use(Router)

export function createRouter() {
    return new Router({
        mode: 'history',
        routes: [
            { path: '/', name: 'home', component: HomePage },
            { path: '/blog', name: 'blog', component: BlogPage}
        ]
    })
}
