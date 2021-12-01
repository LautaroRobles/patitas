import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter);

// Importando los componentes para las rutas

import Inicio from '../views/Inicio'
import Publicaciones from '../views/Publicaciones'
import Login from "@/views/Login";
import RegistrarUsuario from "@/views/RegistrarUsuario";
import RegistrarPersona from "@/views/RegistrarPersona";
import RegistrarMascota from "@/views/RegistrarMascota";
import ConfiguracionUsuario from "@/views/ConfiguracionUsuario";
import RegistrarMascotaPersona from "@/views/RegistrarMascotaPersona";
import NotificarDuenioMascota from "@/views/NotificarDuenioMascota";
import MascotaPerdida from "@/views/MascotaPerdida";
import Mascotas from "@/views/Mascotas";
import Mascota from "@/views/Mascota";
import RegistrarMascotaPerdida from "@/views/RegistrarMascotaPerdida";
import store from "@/plugins/store";
import DarEnAdopcion from "@/views/DarEnAdopcion";
import PreguntasAdopcion from "@/views/PreguntasAdopcion";
import HogaresDeTransito from "@/views/HogaresDeTransito";
import GestionarOrganizacion from "@/views/GestionarOrganizacion";
import CrearPublicacion from "@/views/CrearPublicacion";

const routes = [
    {
        name: 'inicio',
        path: '/',
        component: Inicio
    },
    {
        name: 'publicaciones',
        path: '/publicaciones',
        component: Publicaciones,
        beforeEnter() {
            let token = store.state.token;
            let clientePesado = window.location.origin;
            window.location.href = `${process.env.VUE_APP_API_URL}/publicacion?token=${token}&clientePesado=${clientePesado}&filtro=`;
        }
    },
    {
        name: 'registrar-interesado',
        path: '/registrar/interesado',
        component: RegistrarPersona,
        props: {
            goto: {name: 'crear-publicacion'}
        }
    },
    {
        name: 'crear-publicacion',
        path: '/publicaciones/crear',
        component: CrearPublicacion,
        props: true
    },
    {
        name: 'login',
        path: '/login',
        component: Login
    },
    {
        name: 'registrar-usuario',
        path: '/crearcuenta',
        component: RegistrarUsuario
    },
    {
        path: '/registrar',
        component: RegistrarMascotaPersona,
        children: [
            {
                path: '',
                name: 'registrar-mascota-persona',
                redirect: {name: 'registrar-persona'}
            },
            {
                name: 'registrar-persona',
                path: 'persona',
                component: RegistrarPersona,
                props: {
                    goto: {name: 'registrar-mascota'}
                }
            },
            {
                name: 'registrar-mascota',
                path: 'mascota',
                component: RegistrarMascota,
                props: true
            }
        ]
    },
    {
        name: 'mascotas',
        path: '/mascota',
        component: Mascotas,
        props: {
            goto: {name: 'mascota'}
        }
    },
    {
        name: 'mascota',
        path: '/mascota/:id',
        component: Mascota
    },
    {
        name: 'mascota-perdida',
        path: '/mascota/:id/perdida',
        component: MascotaPerdida
    },
    {
        name: 'notificar-duenio',
        path: '/mascota/:idMascota/notificar',
        component: NotificarDuenioMascota,
        props: true
    },
    {
        name: 'registrar-rescatista',
        path: '/registrar/rescatista/:idMascota',
        component: RegistrarPersona,
        props: {
            goto: {name: 'notificar-duenio'}
        }
    },
    {
        name: 'registrar-rescatista-sin-chapita',
        path: '/registrar/rescatista',
        component: RegistrarPersona,
        props: {
            goto: {name: 'registrar-mascota-perdida'}
        }
    },
    {
        name: 'registrar-mascota-perdida',
        path: '/mascotaperdida',
        component: RegistrarMascotaPerdida,
        props: true
    },
    {
        name: 'hogares-transito',
        path: '/hogares',
        component: HogaresDeTransito,
        props: true
    },
    {
        name: 'configuracion',
        path: '/configuracion',
        component: ConfiguracionUsuario
    },
    {
        path: '/darenadopcion',
        component: DarEnAdopcion,
        children: [
            {
                name: 'dar-en-adopcion',
                path: '',
                component: Mascotas,
                props: {
                    goto: {name: 'dar-en-adopcion_preguntas'},
                    titulo: "Selecciona Mascota para dar en adopción"
                }
            },
            {
                name: 'dar-en-adopcion_preguntas',
                path: 'preguntas',
                component: PreguntasAdopcion
            }
        ]
    },
    {
        name: 'gestionar-organizacion',
        path: '/organizacion',
        component: GestionarOrganizacion,
        beforeEnter: (to, from, next) => {
            let token = store.getters.getToken;

            if(!token || token.CLAIM_TOKEN[0] !== "ROLE_ADMINISTRADOR") {
                next({name: 'inicio'});
            }
            next();
        }
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
