import Vue from 'vue'
import App from './App.vue'
import router from './plugins/router'
import store from './plugins/store'
import vuetify from './plugins/vuetify'
import VueQRCodeComponent from 'vue-qr-generator'
import * as VueGoogleMaps from 'vue2-google-maps'

Vue.use(VueGoogleMaps, {
    load: {
        key: 'AIzaSyDTA_sKYNBSOIZkoM3eaUxM_u1yLL67GnE',
        libraries: 'places',
    },
})

Vue.component('qr-code', VueQRCodeComponent)
Vue.config.productionTip = false

new Vue({
    router,
    store,
    vuetify,
    render: h => h(App)
}).$mount('#app')
