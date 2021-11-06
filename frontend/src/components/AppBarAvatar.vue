<template>
    <div v-if="!logeado">
        <v-btn class="mr-2" to="/login" text>Ingresar</v-btn>
        <v-btn v-if="$vuetify.breakpoint.smAndUp" to="/registrar" outlined>Crear cuenta</v-btn>
    </div>
    <div v-else>
        <v-menu
            bottom
            offset-y
        >
            <template v-slot:activator="{ on, attrs }">
                <v-avatar
                    color="primary darken-1"
                    v-bind="attrs"
                    v-on="on"
                >{{avatar.iniciales}}</v-avatar>
            </template>
            <v-card>
                <div class="pa-2">
                    {{avatar.username}}
                </div>
                <v-divider></v-divider>
                <v-list
                    dense
                    nav
                >
                    <v-list-item
                        link
                        @click="logout()"
                    >
                        <v-list-item-icon>
                            <v-icon>mdi-logout</v-icon>
                        </v-list-item-icon>
                        <v-list-item-action>
                            <v-list-item-title>Cerrar sesión</v-list-item-title>
                        </v-list-item-action>
                    </v-list-item>
                </v-list>
            </v-card>
        </v-menu>
    </div>
</template>

<script>
export default {
    data: () => ({
        logeado: false,
        avatar: {
            iniciales: "",
            username: ""
        }
    }),
    methods: {
        checkIfLogged() {
            let token = this.$store.getters.getToken;
            this.logeado = token !== null;

            if (this.logeado) {
                this.avatar.username = token.sub;
                this.avatar.iniciales = token.sub.slice(0, 2).toUpperCase();

                let hoy = new Date();
                if(token.exp * 1000 < hoy.getTime()){
                    // El token ya vencio, hay que obtener uno nuevo o borrarlo
                    console.log("El token vencio");
                    this.$store.commit('setToken', "");
                }
            }
        },
        logout() {
            this.$store.commit('setToken', "");
        }
    },
    watch: {
        '$store.state.token'() {
            this.checkIfLogged()
        }
    },
    created() {
        this.checkIfLogged()
    }
}
</script>

<style>

</style>