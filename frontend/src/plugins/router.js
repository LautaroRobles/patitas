import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

// Importando los componentes para las rutas

import Inicio from '../views/Inicio'
import Publicaciones from '../views/Publicaciones'
import Login from "@/views/Login";
import RegistrarUsuario from "@/views/RegistrarUsuario";
import RegistrarPersona from "@/views/RegistrarPersona";

const routes = [
    {
        name: 'inicio',
        path: '/',
        component: Inicio
    },
    {
        name: 'publicaciones',
        path: '/publicaciones',
        component: Publicaciones
    },
    {
        name: 'login',
        path: '/login',
        component: Login
    },
    {
        name: 'registrar',
        path: '/registrar',
        component: RegistrarUsuario
    },
    {
        name: 'registrar-persona',
        path: '/persona',
        component: RegistrarPersona
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
