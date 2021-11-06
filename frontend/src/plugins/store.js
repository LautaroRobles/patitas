import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: ""
    },
    mutations: {
        setToken(state, token) {
            state.token = token;
        }
    },
    getters: {
        getToken: state => {
            return parseJwt(state.token);
        }
    },
    actions: {
    },
    modules: {
    },
    plugins: [new VuexPersistence().plugin]
});

function parseJwt (token) {
    if(token === "" || token == null)
        return null;

    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    let jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
}
